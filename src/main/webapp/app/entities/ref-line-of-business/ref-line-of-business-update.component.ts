import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRefLineOfBusiness } from 'app/shared/model/ref-line-of-business.model';
import { RefLineOfBusinessService } from './ref-line-of-business.service';

@Component({
    selector: 'jhi-ref-line-of-business-update',
    templateUrl: './ref-line-of-business-update.component.html'
})
export class RefLineOfBusinessUpdateComponent implements OnInit {
    refLineOfBusiness: IRefLineOfBusiness;
    isSaving: boolean;
    createdOn: string;
    updatedOn: string;

    constructor(private refLineOfBusinessService: RefLineOfBusinessService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refLineOfBusiness }) => {
            this.refLineOfBusiness = refLineOfBusiness;
            this.createdOn = this.refLineOfBusiness.createdOn != null ? this.refLineOfBusiness.createdOn.format(DATE_TIME_FORMAT) : null;
            this.updatedOn = this.refLineOfBusiness.updatedOn != null ? this.refLineOfBusiness.updatedOn.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.refLineOfBusiness.createdOn = this.createdOn != null ? moment(this.createdOn, DATE_TIME_FORMAT) : null;
        this.refLineOfBusiness.updatedOn = this.updatedOn != null ? moment(this.updatedOn, DATE_TIME_FORMAT) : null;
        if (this.refLineOfBusiness.id !== undefined) {
            this.subscribeToSaveResponse(this.refLineOfBusinessService.update(this.refLineOfBusiness));
        } else {
            this.subscribeToSaveResponse(this.refLineOfBusinessService.create(this.refLineOfBusiness));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRefLineOfBusiness>>) {
        result.subscribe((res: HttpResponse<IRefLineOfBusiness>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
