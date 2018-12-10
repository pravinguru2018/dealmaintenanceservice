import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRefDealStatus } from 'app/shared/model/ref-deal-status.model';
import { RefDealStatusService } from './ref-deal-status.service';

@Component({
    selector: 'jhi-ref-deal-status-update',
    templateUrl: './ref-deal-status-update.component.html'
})
export class RefDealStatusUpdateComponent implements OnInit {
    refDealStatus: IRefDealStatus;
    isSaving: boolean;
    createdOn: string;
    updatedOn: string;

    constructor(private refDealStatusService: RefDealStatusService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refDealStatus }) => {
            this.refDealStatus = refDealStatus;
            this.createdOn = this.refDealStatus.createdOn != null ? this.refDealStatus.createdOn.format(DATE_TIME_FORMAT) : null;
            this.updatedOn = this.refDealStatus.updatedOn != null ? this.refDealStatus.updatedOn.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.refDealStatus.createdOn = this.createdOn != null ? moment(this.createdOn, DATE_TIME_FORMAT) : null;
        this.refDealStatus.updatedOn = this.updatedOn != null ? moment(this.updatedOn, DATE_TIME_FORMAT) : null;
        if (this.refDealStatus.id !== undefined) {
            this.subscribeToSaveResponse(this.refDealStatusService.update(this.refDealStatus));
        } else {
            this.subscribeToSaveResponse(this.refDealStatusService.create(this.refDealStatus));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRefDealStatus>>) {
        result.subscribe((res: HttpResponse<IRefDealStatus>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
