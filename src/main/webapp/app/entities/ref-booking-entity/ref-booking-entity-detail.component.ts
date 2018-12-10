import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefBookingEntity } from 'app/shared/model/ref-booking-entity.model';

@Component({
    selector: 'jhi-ref-booking-entity-detail',
    templateUrl: './ref-booking-entity-detail.component.html'
})
export class RefBookingEntityDetailComponent implements OnInit {
    refBookingEntity: IRefBookingEntity;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refBookingEntity }) => {
            this.refBookingEntity = refBookingEntity;
        });
    }

    previousState() {
        window.history.back();
    }
}
