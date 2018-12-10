import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRefPraSector } from 'app/shared/model/ref-pra-sector.model';
import { Principal } from 'app/core';
import { RefPraSectorService } from './ref-pra-sector.service';

@Component({
    selector: 'jhi-ref-pra-sector',
    templateUrl: './ref-pra-sector.component.html'
})
export class RefPraSectorComponent implements OnInit, OnDestroy {
    refPraSectors: IRefPraSector[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private refPraSectorService: RefPraSectorService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.refPraSectorService.query().subscribe(
            (res: HttpResponse<IRefPraSector[]>) => {
                this.refPraSectors = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRefPraSectors();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRefPraSector) {
        return item.id;
    }

    registerChangeInRefPraSectors() {
        this.eventSubscriber = this.eventManager.subscribe('refPraSectorListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
