import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRefOriginationTeam } from 'app/shared/model/ref-origination-team.model';
import { RefOriginationTeamService } from './ref-origination-team.service';

@Component({
    selector: 'jhi-ref-origination-team-update',
    templateUrl: './ref-origination-team-update.component.html'
})
export class RefOriginationTeamUpdateComponent implements OnInit {
    refOriginationTeam: IRefOriginationTeam;
    isSaving: boolean;
    createdOn: string;
    updatedOn: string;

    constructor(private refOriginationTeamService: RefOriginationTeamService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refOriginationTeam }) => {
            this.refOriginationTeam = refOriginationTeam;
            this.createdOn = this.refOriginationTeam.createdOn != null ? this.refOriginationTeam.createdOn.format(DATE_TIME_FORMAT) : null;
            this.updatedOn = this.refOriginationTeam.updatedOn != null ? this.refOriginationTeam.updatedOn.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.refOriginationTeam.createdOn = this.createdOn != null ? moment(this.createdOn, DATE_TIME_FORMAT) : null;
        this.refOriginationTeam.updatedOn = this.updatedOn != null ? moment(this.updatedOn, DATE_TIME_FORMAT) : null;
        if (this.refOriginationTeam.id !== undefined) {
            this.subscribeToSaveResponse(this.refOriginationTeamService.update(this.refOriginationTeam));
        } else {
            this.subscribeToSaveResponse(this.refOriginationTeamService.create(this.refOriginationTeam));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRefOriginationTeam>>) {
        result.subscribe((res: HttpResponse<IRefOriginationTeam>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
