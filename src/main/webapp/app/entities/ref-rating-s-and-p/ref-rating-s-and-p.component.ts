import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRefRatingSAndP } from 'app/shared/model/ref-rating-s-and-p.model';
import { Principal } from 'app/core';
import { RefRatingSAndPService } from './ref-rating-s-and-p.service';

@Component({
    selector: 'jhi-ref-rating-s-and-p',
    templateUrl: './ref-rating-s-and-p.component.html'
})
export class RefRatingSAndPComponent implements OnInit, OnDestroy {
    refRatingSAndPS: IRefRatingSAndP[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private refRatingSAndPService: RefRatingSAndPService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.refRatingSAndPService.query().subscribe(
            (res: HttpResponse<IRefRatingSAndP[]>) => {
                this.refRatingSAndPS = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRefRatingSAndPS();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRefRatingSAndP) {
        return item.id;
    }

    registerChangeInRefRatingSAndPS() {
        this.eventSubscriber = this.eventManager.subscribe('refRatingSAndPListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
