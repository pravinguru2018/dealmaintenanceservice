import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRefPraSector } from 'app/shared/model/ref-pra-sector.model';
import { RefPraSectorService } from './ref-pra-sector.service';

@Component({
    selector: 'jhi-ref-pra-sector-update',
    templateUrl: './ref-pra-sector-update.component.html'
})
export class RefPraSectorUpdateComponent implements OnInit {
    refPraSector: IRefPraSector;
    isSaving: boolean;
    createdOn: string;
    updatedOn: string;

    constructor(private refPraSectorService: RefPraSectorService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refPraSector }) => {
            this.refPraSector = refPraSector;
            this.createdOn = this.refPraSector.createdOn != null ? this.refPraSector.createdOn.format(DATE_TIME_FORMAT) : null;
            this.updatedOn = this.refPraSector.updatedOn != null ? this.refPraSector.updatedOn.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.refPraSector.createdOn = this.createdOn != null ? moment(this.createdOn, DATE_TIME_FORMAT) : null;
        this.refPraSector.updatedOn = this.updatedOn != null ? moment(this.updatedOn, DATE_TIME_FORMAT) : null;
        if (this.refPraSector.id !== undefined) {
            this.subscribeToSaveResponse(this.refPraSectorService.update(this.refPraSector));
        } else {
            this.subscribeToSaveResponse(this.refPraSectorService.create(this.refPraSector));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRefPraSector>>) {
        result.subscribe((res: HttpResponse<IRefPraSector>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
