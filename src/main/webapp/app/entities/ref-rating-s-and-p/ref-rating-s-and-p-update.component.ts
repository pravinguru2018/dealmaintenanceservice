import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRefRatingSAndP } from 'app/shared/model/ref-rating-s-and-p.model';
import { RefRatingSAndPService } from './ref-rating-s-and-p.service';

@Component({
    selector: 'jhi-ref-rating-s-and-p-update',
    templateUrl: './ref-rating-s-and-p-update.component.html'
})
export class RefRatingSAndPUpdateComponent implements OnInit {
    refRatingSAndP: IRefRatingSAndP;
    isSaving: boolean;
    createdOn: string;
    updatedOn: string;

    constructor(private refRatingSAndPService: RefRatingSAndPService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refRatingSAndP }) => {
            this.refRatingSAndP = refRatingSAndP;
            this.createdOn = this.refRatingSAndP.createdOn != null ? this.refRatingSAndP.createdOn.format(DATE_TIME_FORMAT) : null;
            this.updatedOn = this.refRatingSAndP.updatedOn != null ? this.refRatingSAndP.updatedOn.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.refRatingSAndP.createdOn = this.createdOn != null ? moment(this.createdOn, DATE_TIME_FORMAT) : null;
        this.refRatingSAndP.updatedOn = this.updatedOn != null ? moment(this.updatedOn, DATE_TIME_FORMAT) : null;
        if (this.refRatingSAndP.id !== undefined) {
            this.subscribeToSaveResponse(this.refRatingSAndPService.update(this.refRatingSAndP));
        } else {
            this.subscribeToSaveResponse(this.refRatingSAndPService.create(this.refRatingSAndP));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRefRatingSAndP>>) {
        result.subscribe((res: HttpResponse<IRefRatingSAndP>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
