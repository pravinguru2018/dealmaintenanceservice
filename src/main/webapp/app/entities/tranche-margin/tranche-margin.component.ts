import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITrancheMargin } from 'app/shared/model/tranche-margin.model';
import { Principal } from 'app/core';
import { TrancheMarginService } from './tranche-margin.service';

@Component({
    selector: 'jhi-tranche-margin',
    templateUrl: './tranche-margin.component.html'
})
export class TrancheMarginComponent implements OnInit, OnDestroy {
    trancheMargins: ITrancheMargin[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private trancheMarginService: TrancheMarginService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.trancheMarginService.query().subscribe(
            (res: HttpResponse<ITrancheMargin[]>) => {
                this.trancheMargins = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTrancheMargins();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITrancheMargin) {
        return item.id;
    }

    registerChangeInTrancheMargins() {
        this.eventSubscriber = this.eventManager.subscribe('trancheMarginListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
