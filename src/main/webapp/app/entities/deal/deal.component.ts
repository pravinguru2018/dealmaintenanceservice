import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IDeal } from 'app/shared/model/deal.model';
import { Principal } from 'app/core';
import { DealService } from './deal.service';

@Component({
    selector: 'jhi-deal',
    templateUrl: './deal.component.html'
})
export class DealComponent implements OnInit, OnDestroy {
    deals: IDeal[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private dealService: DealService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.dealService.query().subscribe(
            (res: HttpResponse<IDeal[]>) => {
                this.deals = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInDeals();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IDeal) {
        return item.id;
    }

    registerChangeInDeals() {
        this.eventSubscriber = this.eventManager.subscribe('dealListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
