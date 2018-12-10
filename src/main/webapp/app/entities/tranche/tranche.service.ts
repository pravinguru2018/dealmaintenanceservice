import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITranche } from 'app/shared/model/tranche.model';

type EntityResponseType = HttpResponse<ITranche>;
type EntityArrayResponseType = HttpResponse<ITranche[]>;

@Injectable({ providedIn: 'root' })
export class TrancheService {
    public resourceUrl = SERVER_API_URL + 'api/tranches';

    constructor(private http: HttpClient) {}

    create(tranche: ITranche): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(tranche);
        return this.http
            .post<ITranche>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(tranche: ITranche): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(tranche);
        return this.http
            .put<ITranche>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITranche>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITranche[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(tranche: ITranche): ITranche {
        const copy: ITranche = Object.assign({}, tranche, {
            bridgeTakeoutDate:
                tranche.bridgeTakeoutDate != null && tranche.bridgeTakeoutDate.isValid() ? tranche.bridgeTakeoutDate.toJSON() : null,
            createdOn: tranche.createdOn != null && tranche.createdOn.isValid() ? tranche.createdOn.toJSON() : null,
            updatedOn: tranche.updatedOn != null && tranche.updatedOn.isValid() ? tranche.updatedOn.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.bridgeTakeoutDate = res.body.bridgeTakeoutDate != null ? moment(res.body.bridgeTakeoutDate) : null;
            res.body.createdOn = res.body.createdOn != null ? moment(res.body.createdOn) : null;
            res.body.updatedOn = res.body.updatedOn != null ? moment(res.body.updatedOn) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((tranche: ITranche) => {
                tranche.bridgeTakeoutDate = tranche.bridgeTakeoutDate != null ? moment(tranche.bridgeTakeoutDate) : null;
                tranche.createdOn = tranche.createdOn != null ? moment(tranche.createdOn) : null;
                tranche.updatedOn = tranche.updatedOn != null ? moment(tranche.updatedOn) : null;
            });
        }
        return res;
    }
}
