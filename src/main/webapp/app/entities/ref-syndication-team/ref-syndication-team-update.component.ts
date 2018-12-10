import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRefSyndicationTeam } from 'app/shared/model/ref-syndication-team.model';
import { RefSyndicationTeamService } from './ref-syndication-team.service';

@Component({
    selector: 'jhi-ref-syndication-team-update',
    templateUrl: './ref-syndication-team-update.component.html'
})
export class RefSyndicationTeamUpdateComponent implements OnInit {
    refSyndicationTeam: IRefSyndicationTeam;
    isSaving: boolean;
    createdOn: string;
    updatedOn: string;

    constructor(private refSyndicationTeamService: RefSyndicationTeamService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refSyndicationTeam }) => {
            this.refSyndicationTeam = refSyndicationTeam;
            this.createdOn = this.refSyndicationTeam.createdOn != null ? this.refSyndicationTeam.createdOn.format(DATE_TIME_FORMAT) : null;
            this.updatedOn = this.refSyndicationTeam.updatedOn != null ? this.refSyndicationTeam.updatedOn.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.refSyndicationTeam.createdOn = this.createdOn != null ? moment(this.createdOn, DATE_TIME_FORMAT) : null;
        this.refSyndicationTeam.updatedOn = this.updatedOn != null ? moment(this.updatedOn, DATE_TIME_FORMAT) : null;
        if (this.refSyndicationTeam.id !== undefined) {
            this.subscribeToSaveResponse(this.refSyndicationTeamService.update(this.refSyndicationTeam));
        } else {
            this.subscribeToSaveResponse(this.refSyndicationTeamService.create(this.refSyndicationTeam));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRefSyndicationTeam>>) {
        result.subscribe((res: HttpResponse<IRefSyndicationTeam>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
