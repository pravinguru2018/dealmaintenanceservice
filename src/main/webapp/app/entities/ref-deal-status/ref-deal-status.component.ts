import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRefDealStatus } from 'app/shared/model/ref-deal-status.model';
import { Principal } from 'app/core';
import { RefDealStatusService } from './ref-deal-status.service';

@Component({
    selector: 'jhi-ref-deal-status',
    templateUrl: './ref-deal-status.component.html'
})
export class RefDealStatusComponent implements OnInit, OnDestroy {
    refDealStatuses: IRefDealStatus[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private refDealStatusService: RefDealStatusService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.refDealStatusService.query().subscribe(
            (res: HttpResponse<IRefDealStatus[]>) => {
                this.refDealStatuses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRefDealStatuses();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRefDealStatus) {
        return item.id;
    }

    registerChangeInRefDealStatuses() {
        this.eventSubscriber = this.eventManager.subscribe('refDealStatusListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
