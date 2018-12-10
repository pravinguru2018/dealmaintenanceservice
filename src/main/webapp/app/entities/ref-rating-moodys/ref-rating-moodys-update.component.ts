import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRefRatingMoodys } from 'app/shared/model/ref-rating-moodys.model';
import { RefRatingMoodysService } from './ref-rating-moodys.service';

@Component({
    selector: 'jhi-ref-rating-moodys-update',
    templateUrl: './ref-rating-moodys-update.component.html'
})
export class RefRatingMoodysUpdateComponent implements OnInit {
    refRatingMoodys: IRefRatingMoodys;
    isSaving: boolean;
    createdOn: string;
    updatedOn: string;

    constructor(private refRatingMoodysService: RefRatingMoodysService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refRatingMoodys }) => {
            this.refRatingMoodys = refRatingMoodys;
            this.createdOn = this.refRatingMoodys.createdOn != null ? this.refRatingMoodys.createdOn.format(DATE_TIME_FORMAT) : null;
            this.updatedOn = this.refRatingMoodys.updatedOn != null ? this.refRatingMoodys.updatedOn.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.refRatingMoodys.createdOn = this.createdOn != null ? moment(this.createdOn, DATE_TIME_FORMAT) : null;
        this.refRatingMoodys.updatedOn = this.updatedOn != null ? moment(this.updatedOn, DATE_TIME_FORMAT) : null;
        if (this.refRatingMoodys.id !== undefined) {
            this.subscribeToSaveResponse(this.refRatingMoodysService.update(this.refRatingMoodys));
        } else {
            this.subscribeToSaveResponse(this.refRatingMoodysService.create(this.refRatingMoodys));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRefRatingMoodys>>) {
        result.subscribe((res: HttpResponse<IRefRatingMoodys>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
