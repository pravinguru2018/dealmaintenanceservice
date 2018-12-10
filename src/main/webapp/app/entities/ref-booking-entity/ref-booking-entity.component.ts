import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRefBookingEntity } from 'app/shared/model/ref-booking-entity.model';
import { Principal } from 'app/core';
import { RefBookingEntityService } from './ref-booking-entity.service';

@Component({
    selector: 'jhi-ref-booking-entity',
    templateUrl: './ref-booking-entity.component.html'
})
export class RefBookingEntityComponent implements OnInit, OnDestroy {
    refBookingEntities: IRefBookingEntity[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private refBookingEntityService: RefBookingEntityService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.refBookingEntityService.query().subscribe(
            (res: HttpResponse<IRefBookingEntity[]>) => {
                this.refBookingEntities = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRefBookingEntities();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRefBookingEntity) {
        return item.id;
    }

    registerChangeInRefBookingEntities() {
        this.eventSubscriber = this.eventManager.subscribe('refBookingEntityListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
