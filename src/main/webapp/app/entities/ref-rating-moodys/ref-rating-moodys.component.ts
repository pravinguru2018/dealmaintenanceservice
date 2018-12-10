import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRefRatingMoodys } from 'app/shared/model/ref-rating-moodys.model';
import { Principal } from 'app/core';
import { RefRatingMoodysService } from './ref-rating-moodys.service';

@Component({
    selector: 'jhi-ref-rating-moodys',
    templateUrl: './ref-rating-moodys.component.html'
})
export class RefRatingMoodysComponent implements OnInit, OnDestroy {
    refRatingMoodys: IRefRatingMoodys[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private refRatingMoodysService: RefRatingMoodysService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.refRatingMoodysService.query().subscribe(
            (res: HttpResponse<IRefRatingMoodys[]>) => {
                this.refRatingMoodys = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRefRatingMoodys();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRefRatingMoodys) {
        return item.id;
    }

    registerChangeInRefRatingMoodys() {
        this.eventSubscriber = this.eventManager.subscribe('refRatingMoodysListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
