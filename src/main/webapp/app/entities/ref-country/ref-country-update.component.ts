import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRefCountry } from 'app/shared/model/ref-country.model';
import { RefCountryService } from './ref-country.service';

@Component({
    selector: 'jhi-ref-country-update',
    templateUrl: './ref-country-update.component.html'
})
export class RefCountryUpdateComponent implements OnInit {
    refCountry: IRefCountry;
    isSaving: boolean;
    createdOn: string;
    updatedOn: string;

    constructor(private refCountryService: RefCountryService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refCountry }) => {
            this.refCountry = refCountry;
            this.createdOn = this.refCountry.createdOn != null ? this.refCountry.createdOn.format(DATE_TIME_FORMAT) : null;
            this.updatedOn = this.refCountry.updatedOn != null ? this.refCountry.updatedOn.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.refCountry.createdOn = this.createdOn != null ? moment(this.createdOn, DATE_TIME_FORMAT) : null;
        this.refCountry.updatedOn = this.updatedOn != null ? moment(this.updatedOn, DATE_TIME_FORMAT) : null;
        if (this.refCountry.id !== undefined) {
            this.subscribeToSaveResponse(this.refCountryService.update(this.refCountry));
        } else {
            this.subscribeToSaveResponse(this.refCountryService.create(this.refCountry));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRefCountry>>) {
        result.subscribe((res: HttpResponse<IRefCountry>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
