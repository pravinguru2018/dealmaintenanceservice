import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRefCovenant } from 'app/shared/model/ref-covenant.model';
import { RefCovenantService } from './ref-covenant.service';

@Component({
    selector: 'jhi-ref-covenant-update',
    templateUrl: './ref-covenant-update.component.html'
})
export class RefCovenantUpdateComponent implements OnInit {
    refCovenant: IRefCovenant;
    isSaving: boolean;
    createdOn: string;
    updatedOn: string;

    constructor(private refCovenantService: RefCovenantService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refCovenant }) => {
            this.refCovenant = refCovenant;
            this.createdOn = this.refCovenant.createdOn != null ? this.refCovenant.createdOn.format(DATE_TIME_FORMAT) : null;
            this.updatedOn = this.refCovenant.updatedOn != null ? this.refCovenant.updatedOn.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.refCovenant.createdOn = this.createdOn != null ? moment(this.createdOn, DATE_TIME_FORMAT) : null;
        this.refCovenant.updatedOn = this.updatedOn != null ? moment(this.updatedOn, DATE_TIME_FORMAT) : null;
        if (this.refCovenant.id !== undefined) {
            this.subscribeToSaveResponse(this.refCovenantService.update(this.refCovenant));
        } else {
            this.subscribeToSaveResponse(this.refCovenantService.create(this.refCovenant));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRefCovenant>>) {
        result.subscribe((res: HttpResponse<IRefCovenant>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
