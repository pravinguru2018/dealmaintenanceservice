import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRefRatingFitch } from 'app/shared/model/ref-rating-fitch.model';
import { RefRatingFitchService } from './ref-rating-fitch.service';

@Component({
    selector: 'jhi-ref-rating-fitch-update',
    templateUrl: './ref-rating-fitch-update.component.html'
})
export class RefRatingFitchUpdateComponent implements OnInit {
    refRatingFitch: IRefRatingFitch;
    isSaving: boolean;
    createdOn: string;
    updatedOn: string;

    constructor(private refRatingFitchService: RefRatingFitchService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refRatingFitch }) => {
            this.refRatingFitch = refRatingFitch;
            this.createdOn = this.refRatingFitch.createdOn != null ? this.refRatingFitch.createdOn.format(DATE_TIME_FORMAT) : null;
            this.updatedOn = this.refRatingFitch.updatedOn != null ? this.refRatingFitch.updatedOn.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.refRatingFitch.createdOn = this.createdOn != null ? moment(this.createdOn, DATE_TIME_FORMAT) : null;
        this.refRatingFitch.updatedOn = this.updatedOn != null ? moment(this.updatedOn, DATE_TIME_FORMAT) : null;
        if (this.refRatingFitch.id !== undefined) {
            this.subscribeToSaveResponse(this.refRatingFitchService.update(this.refRatingFitch));
        } else {
            this.subscribeToSaveResponse(this.refRatingFitchService.create(this.refRatingFitch));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRefRatingFitch>>) {
        result.subscribe((res: HttpResponse<IRefRatingFitch>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
