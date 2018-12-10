import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ITrancheMargin } from 'app/shared/model/tranche-margin.model';
import { TrancheMarginService } from './tranche-margin.service';
import { ITranche } from 'app/shared/model/tranche.model';
import { TrancheService } from 'app/entities/tranche';

@Component({
    selector: 'jhi-tranche-margin-update',
    templateUrl: './tranche-margin-update.component.html'
})
export class TrancheMarginUpdateComponent implements OnInit {
    trancheMargin: ITrancheMargin;
    isSaving: boolean;

    trancheids: ITranche[];
    createdOn: string;
    updatedOn: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private trancheMarginService: TrancheMarginService,
        private trancheService: TrancheService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ trancheMargin }) => {
            this.trancheMargin = trancheMargin;
            this.createdOn = this.trancheMargin.createdOn != null ? this.trancheMargin.createdOn.format(DATE_TIME_FORMAT) : null;
            this.updatedOn = this.trancheMargin.updatedOn != null ? this.trancheMargin.updatedOn.format(DATE_TIME_FORMAT) : null;
        });
        this.trancheService.query({ filter: 'tranchemargin-is-null' }).subscribe(
            (res: HttpResponse<ITranche[]>) => {
                if (!this.trancheMargin.trancheId || !this.trancheMargin.trancheId.id) {
                    this.trancheids = res.body;
                } else {
                    this.trancheService.find(this.trancheMargin.trancheId.id).subscribe(
                        (subRes: HttpResponse<ITranche>) => {
                            this.trancheids = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.trancheMargin.createdOn = this.createdOn != null ? moment(this.createdOn, DATE_TIME_FORMAT) : null;
        this.trancheMargin.updatedOn = this.updatedOn != null ? moment(this.updatedOn, DATE_TIME_FORMAT) : null;
        if (this.trancheMargin.id !== undefined) {
            this.subscribeToSaveResponse(this.trancheMarginService.update(this.trancheMargin));
        } else {
            this.subscribeToSaveResponse(this.trancheMarginService.create(this.trancheMargin));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITrancheMargin>>) {
        result.subscribe((res: HttpResponse<ITrancheMargin>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackTrancheById(index: number, item: ITranche) {
        return item.id;
    }
}
