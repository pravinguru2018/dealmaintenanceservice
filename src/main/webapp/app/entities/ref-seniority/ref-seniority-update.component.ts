import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRefSeniority } from 'app/shared/model/ref-seniority.model';
import { RefSeniorityService } from './ref-seniority.service';

@Component({
    selector: 'jhi-ref-seniority-update',
    templateUrl: './ref-seniority-update.component.html'
})
export class RefSeniorityUpdateComponent implements OnInit {
    refSeniority: IRefSeniority;
    isSaving: boolean;
    createdOn: string;
    updatedOn: string;

    constructor(private refSeniorityService: RefSeniorityService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refSeniority }) => {
            this.refSeniority = refSeniority;
            this.createdOn = this.refSeniority.createdOn != null ? this.refSeniority.createdOn.format(DATE_TIME_FORMAT) : null;
            this.updatedOn = this.refSeniority.updatedOn != null ? this.refSeniority.updatedOn.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.refSeniority.createdOn = this.createdOn != null ? moment(this.createdOn, DATE_TIME_FORMAT) : null;
        this.refSeniority.updatedOn = this.updatedOn != null ? moment(this.updatedOn, DATE_TIME_FORMAT) : null;
        if (this.refSeniority.id !== undefined) {
            this.subscribeToSaveResponse(this.refSeniorityService.update(this.refSeniority));
        } else {
            this.subscribeToSaveResponse(this.refSeniorityService.create(this.refSeniority));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRefSeniority>>) {
        result.subscribe((res: HttpResponse<IRefSeniority>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
