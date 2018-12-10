import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRefRatingCrr } from 'app/shared/model/ref-rating-crr.model';
import { Principal } from 'app/core';
import { RefRatingCrrService } from './ref-rating-crr.service';

@Component({
    selector: 'jhi-ref-rating-crr',
    templateUrl: './ref-rating-crr.component.html'
})
export class RefRatingCrrComponent implements OnInit, OnDestroy {
    refRatingCrrs: IRefRatingCrr[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private refRatingCrrService: RefRatingCrrService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.refRatingCrrService.query().subscribe(
            (res: HttpResponse<IRefRatingCrr[]>) => {
                this.refRatingCrrs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRefRatingCrrs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRefRatingCrr) {
        return item.id;
    }

    registerChangeInRefRatingCrrs() {
        this.eventSubscriber = this.eventManager.subscribe('refRatingCrrListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
