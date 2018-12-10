import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRefCountry } from 'app/shared/model/ref-country.model';
import { Principal } from 'app/core';
import { RefCountryService } from './ref-country.service';

@Component({
    selector: 'jhi-ref-country',
    templateUrl: './ref-country.component.html'
})
export class RefCountryComponent implements OnInit, OnDestroy {
    refCountries: IRefCountry[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private refCountryService: RefCountryService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.refCountryService.query().subscribe(
            (res: HttpResponse<IRefCountry[]>) => {
                this.refCountries = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRefCountries();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRefCountry) {
        return item.id;
    }

    registerChangeInRefCountries() {
        this.eventSubscriber = this.eventManager.subscribe('refCountryListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
