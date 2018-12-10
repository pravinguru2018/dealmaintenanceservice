import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRefRecourseToClient } from 'app/shared/model/ref-recourse-to-client.model';
import { RefRecourseToClientService } from './ref-recourse-to-client.service';

@Component({
    selector: 'jhi-ref-recourse-to-client-update',
    templateUrl: './ref-recourse-to-client-update.component.html'
})
export class RefRecourseToClientUpdateComponent implements OnInit {
    refRecourseToClient: IRefRecourseToClient;
    isSaving: boolean;
    createdOn: string;
    updatedOn: string;

    constructor(private refRecourseToClientService: RefRecourseToClientService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refRecourseToClient }) => {
            this.refRecourseToClient = refRecourseToClient;
            this.createdOn =
                this.refRecourseToClient.createdOn != null ? this.refRecourseToClient.createdOn.format(DATE_TIME_FORMAT) : null;
            this.updatedOn =
                this.refRecourseToClient.updatedOn != null ? this.refRecourseToClient.updatedOn.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.refRecourseToClient.createdOn = this.createdOn != null ? moment(this.createdOn, DATE_TIME_FORMAT) : null;
        this.refRecourseToClient.updatedOn = this.updatedOn != null ? moment(this.updatedOn, DATE_TIME_FORMAT) : null;
        if (this.refRecourseToClient.id !== undefined) {
            this.subscribeToSaveResponse(this.refRecourseToClientService.update(this.refRecourseToClient));
        } else {
            this.subscribeToSaveResponse(this.refRecourseToClientService.create(this.refRecourseToClient));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRefRecourseToClient>>) {
        result.subscribe((res: HttpResponse<IRefRecourseToClient>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
