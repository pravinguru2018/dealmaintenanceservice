import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRefFacilityType } from 'app/shared/model/ref-facility-type.model';
import { RefFacilityTypeService } from './ref-facility-type.service';

@Component({
    selector: 'jhi-ref-facility-type-update',
    templateUrl: './ref-facility-type-update.component.html'
})
export class RefFacilityTypeUpdateComponent implements OnInit {
    refFacilityType: IRefFacilityType;
    isSaving: boolean;
    createdOn: string;
    updatedOn: string;

    constructor(private refFacilityTypeService: RefFacilityTypeService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refFacilityType }) => {
            this.refFacilityType = refFacilityType;
            this.createdOn = this.refFacilityType.createdOn != null ? this.refFacilityType.createdOn.format(DATE_TIME_FORMAT) : null;
            this.updatedOn = this.refFacilityType.updatedOn != null ? this.refFacilityType.updatedOn.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.refFacilityType.createdOn = this.createdOn != null ? moment(this.createdOn, DATE_TIME_FORMAT) : null;
        this.refFacilityType.updatedOn = this.updatedOn != null ? moment(this.updatedOn, DATE_TIME_FORMAT) : null;
        if (this.refFacilityType.id !== undefined) {
            this.subscribeToSaveResponse(this.refFacilityTypeService.update(this.refFacilityType));
        } else {
            this.subscribeToSaveResponse(this.refFacilityTypeService.create(this.refFacilityType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRefFacilityType>>) {
        result.subscribe((res: HttpResponse<IRefFacilityType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
