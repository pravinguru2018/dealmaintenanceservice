import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRefBookingEntity } from 'app/shared/model/ref-booking-entity.model';
import { RefBookingEntityService } from './ref-booking-entity.service';

@Component({
    selector: 'jhi-ref-booking-entity-update',
    templateUrl: './ref-booking-entity-update.component.html'
})
export class RefBookingEntityUpdateComponent implements OnInit {
    refBookingEntity: IRefBookingEntity;
    isSaving: boolean;
    createdOn: string;
    updatedOn: string;

    constructor(private refBookingEntityService: RefBookingEntityService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refBookingEntity }) => {
            this.refBookingEntity = refBookingEntity;
            this.createdOn = this.refBookingEntity.createdOn != null ? this.refBookingEntity.createdOn.format(DATE_TIME_FORMAT) : null;
            this.updatedOn = this.refBookingEntity.updatedOn != null ? this.refBookingEntity.updatedOn.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.refBookingEntity.createdOn = this.createdOn != null ? moment(this.createdOn, DATE_TIME_FORMAT) : null;
        this.refBookingEntity.updatedOn = this.updatedOn != null ? moment(this.updatedOn, DATE_TIME_FORMAT) : null;
        if (this.refBookingEntity.id !== undefined) {
            this.subscribeToSaveResponse(this.refBookingEntityService.update(this.refBookingEntity));
        } else {
            this.subscribeToSaveResponse(this.refBookingEntityService.create(this.refBookingEntity));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRefBookingEntity>>) {
        result.subscribe((res: HttpResponse<IRefBookingEntity>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
