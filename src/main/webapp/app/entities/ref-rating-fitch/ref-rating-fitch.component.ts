import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRefRatingFitch } from 'app/shared/model/ref-rating-fitch.model';
import { Principal } from 'app/core';
import { RefRatingFitchService } from './ref-rating-fitch.service';

@Component({
    selector: 'jhi-ref-rating-fitch',
    templateUrl: './ref-rating-fitch.component.html'
})
export class RefRatingFitchComponent implements OnInit, OnDestroy {
    refRatingFitches: IRefRatingFitch[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private refRatingFitchService: RefRatingFitchService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.refRatingFitchService.query().subscribe(
            (res: HttpResponse<IRefRatingFitch[]>) => {
                this.refRatingFitches = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRefRatingFitches();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRefRatingFitch) {
        return item.id;
    }

    registerChangeInRefRatingFitches() {
        this.eventSubscriber = this.eventManager.subscribe('refRatingFitchListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
