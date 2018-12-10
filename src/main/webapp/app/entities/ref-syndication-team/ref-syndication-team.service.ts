import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefSyndicationTeam } from 'app/shared/model/ref-syndication-team.model';

type EntityResponseType = HttpResponse<IRefSyndicationTeam>;
type EntityArrayResponseType = HttpResponse<IRefSyndicationTeam[]>;

@Injectable({ providedIn: 'root' })
export class RefSyndicationTeamService {
    public resourceUrl = SERVER_API_URL + 'api/ref-syndication-teams';

    constructor(private http: HttpClient) {}

    create(refSyndicationTeam: IRefSyndicationTeam): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refSyndicationTeam);
        return this.http
            .post<IRefSyndicationTeam>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(refSyndicationTeam: IRefSyndicationTeam): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refSyndicationTeam);
        return this.http
            .put<IRefSyndicationTeam>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRefSyndicationTeam>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRefSyndicationTeam[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(refSyndicationTeam: IRefSyndicationTeam): IRefSyndicationTeam {
        const copy: IRefSyndicationTeam = Object.assign({}, refSyndicationTeam, {
            createdOn:
                refSyndicationTeam.createdOn != null && refSyndicationTeam.createdOn.isValid()
                    ? refSyndicationTeam.createdOn.toJSON()
                    : null,
            updatedOn:
                refSyndicationTeam.updatedOn != null && refSyndicationTeam.updatedOn.isValid()
                    ? refSyndicationTeam.updatedOn.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createdOn = res.body.createdOn != null ? moment(res.body.createdOn) : null;
            res.body.updatedOn = res.body.updatedOn != null ? moment(res.body.updatedOn) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((refSyndicationTeam: IRefSyndicationTeam) => {
                refSyndicationTeam.createdOn = refSyndicationTeam.createdOn != null ? moment(refSyndicationTeam.createdOn) : null;
                refSyndicationTeam.updatedOn = refSyndicationTeam.updatedOn != null ? moment(refSyndicationTeam.updatedOn) : null;
            });
        }
        return res;
    }
}
