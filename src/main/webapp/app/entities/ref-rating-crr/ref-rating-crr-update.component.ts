import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRefRatingCrr } from 'app/shared/model/ref-rating-crr.model';
import { RefRatingCrrService } from './ref-rating-crr.service';

@Component({
    selector: 'jhi-ref-rating-crr-update',
    templateUrl: './ref-rating-crr-update.component.html'
})
export class RefRatingCrrUpdateComponent implements OnInit {
    refRatingCrr: IRefRatingCrr;
    isSaving: boolean;
    createdOn: string;
    updatedOn: string;

    constructor(private refRatingCrrService: RefRatingCrrService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refRatingCrr }) => {
            this.refRatingCrr = refRatingCrr;
            this.createdOn = this.refRatingCrr.createdOn != null ? this.refRatingCrr.createdOn.format(DATE_TIME_FORMAT) : null;
            this.updatedOn = this.refRatingCrr.updatedOn != null ? this.refRatingCrr.updatedOn.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.refRatingCrr.createdOn = this.createdOn != null ? moment(this.createdOn, DATE_TIME_FORMAT) : null;
        this.refRatingCrr.updatedOn = this.updatedOn != null ? moment(this.updatedOn, DATE_TIME_FORMAT) : null;
        if (this.refRatingCrr.id !== undefined) {
            this.subscribeToSaveResponse(this.refRatingCrrService.update(this.refRatingCrr));
        } else {
            this.subscribeToSaveResponse(this.refRatingCrrService.create(this.refRatingCrr));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRefRatingCrr>>) {
        result.subscribe((res: HttpResponse<IRefRatingCrr>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
