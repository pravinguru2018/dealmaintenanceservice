import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefOriginationTeam } from 'app/shared/model/ref-origination-team.model';

type EntityResponseType = HttpResponse<IRefOriginationTeam>;
type EntityArrayResponseType = HttpResponse<IRefOriginationTeam[]>;

@Injectable({ providedIn: 'root' })
export class RefOriginationTeamService {
    public resourceUrl = SERVER_API_URL + 'api/ref-origination-teams';

    constructor(private http: HttpClient) {}

    create(refOriginationTeam: IRefOriginationTeam): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refOriginationTeam);
        return this.http
            .post<IRefOriginationTeam>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(refOriginationTeam: IRefOriginationTeam): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refOriginationTeam);
        return this.http
            .put<IRefOriginationTeam>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRefOriginationTeam>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRefOriginationTeam[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(refOriginationTeam: IRefOriginationTeam): IRefOriginationTeam {
        const copy: IRefOriginationTeam = Object.assign({}, refOriginationTeam, {
            createdOn:
                refOriginationTeam.createdOn != null && refOriginationTeam.createdOn.isValid()
                    ? refOriginationTeam.createdOn.toJSON()
                    : null,
            updatedOn:
                refOriginationTeam.updatedOn != null && refOriginationTeam.updatedOn.isValid()
                    ? refOriginationTeam.updatedOn.toJSON()
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
            res.body.forEach((refOriginationTeam: IRefOriginationTeam) => {
                refOriginationTeam.createdOn = refOriginationTeam.createdOn != null ? moment(refOriginationTeam.createdOn) : null;
                refOriginationTeam.updatedOn = refOriginationTeam.updatedOn != null ? moment(refOriginationTeam.updatedOn) : null;
            });
        }
        return res;
    }
}
