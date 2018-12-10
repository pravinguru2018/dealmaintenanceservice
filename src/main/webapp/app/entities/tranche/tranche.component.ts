import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITranche } from 'app/shared/model/tranche.model';
import { Principal } from 'app/core';
import { TrancheService } from './tranche.service';

@Component({
    selector: 'jhi-tranche',
    templateUrl: './tranche.component.html'
})
export class TrancheComponent implements OnInit, OnDestroy {
    tranches: ITranche[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private trancheService: TrancheService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.trancheService.query().subscribe(
            (res: HttpResponse<ITranche[]>) => {
                this.tranches = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTranches();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITranche) {
        return item.id;
    }

    registerChangeInTranches() {
        this.eventSubscriber = this.eventManager.subscribe('trancheListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
