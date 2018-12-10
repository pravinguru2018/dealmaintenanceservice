import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITrancheMargin } from 'app/shared/model/tranche-margin.model';

type EntityResponseType = HttpResponse<ITrancheMargin>;
type EntityArrayResponseType = HttpResponse<ITrancheMargin[]>;

@Injectable({ providedIn: 'root' })
export class TrancheMarginService {
    public resourceUrl = SERVER_API_URL + 'api/tranche-margins';

    constructor(private http: HttpClient) {}

    create(trancheMargin: ITrancheMargin): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(trancheMargin);
        return this.http
            .post<ITrancheMargin>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(trancheMargin: ITrancheMargin): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(trancheMargin);
        return this.http
            .put<ITrancheMargin>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITrancheMargin>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITrancheMargin[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(trancheMargin: ITrancheMargin): ITrancheMargin {
        const copy: ITrancheMargin = Object.assign({}, trancheMargin, {
            createdOn: trancheMargin.createdOn != null && trancheMargin.createdOn.isValid() ? trancheMargin.createdOn.toJSON() : null,
            updatedOn: trancheMargin.updatedOn != null && trancheMargin.updatedOn.isValid() ? trancheMargin.updatedOn.toJSON() : null
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
            res.body.forEach((trancheMargin: ITrancheMargin) => {
                trancheMargin.createdOn = trancheMargin.createdOn != null ? moment(trancheMargin.createdOn) : null;
                trancheMargin.updatedOn = trancheMargin.updatedOn != null ? moment(trancheMargin.updatedOn) : null;
            });
        }
        return res;
    }
}
