import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRefLineOfBusiness } from 'app/shared/model/ref-line-of-business.model';
import { Principal } from 'app/core';
import { RefLineOfBusinessService } from './ref-line-of-business.service';

@Component({
    selector: 'jhi-ref-line-of-business',
    templateUrl: './ref-line-of-business.component.html'
})
export class RefLineOfBusinessComponent implements OnInit, OnDestroy {
    refLineOfBusinesses: IRefLineOfBusiness[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private refLineOfBusinessService: RefLineOfBusinessService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.refLineOfBusinessService.query().subscribe(
            (res: HttpResponse<IRefLineOfBusiness[]>) => {
                this.refLineOfBusinesses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRefLineOfBusinesses();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRefLineOfBusiness) {
        return item.id;
    }

    registerChangeInRefLineOfBusinesses() {
        this.eventSubscriber = this.eventManager.subscribe('refLineOfBusinessListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
