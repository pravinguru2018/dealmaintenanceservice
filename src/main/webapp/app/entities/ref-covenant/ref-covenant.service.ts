import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefCovenant } from 'app/shared/model/ref-covenant.model';

type EntityResponseType = HttpResponse<IRefCovenant>;
type EntityArrayResponseType = HttpResponse<IRefCovenant[]>;

@Injectable({ providedIn: 'root' })
export class RefCovenantService {
    public resourceUrl = SERVER_API_URL + 'api/ref-covenants';

    constructor(private http: HttpClient) {}

    create(refCovenant: IRefCovenant): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refCovenant);
        return this.http
            .post<IRefCovenant>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(refCovenant: IRefCovenant): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refCovenant);
        return this.http
            .put<IRefCovenant>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRefCovenant>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRefCovenant[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(refCovenant: IRefCovenant): IRefCovenant {
        const copy: IRefCovenant = Object.assign({}, refCovenant, {
            createdOn: refCovenant.createdOn != null && refCovenant.createdOn.isValid() ? refCovenant.createdOn.toJSON() : null,
            updatedOn: refCovenant.updatedOn != null && refCovenant.updatedOn.isValid() ? refCovenant.updatedOn.toJSON() : null
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
            res.body.forEach((refCovenant: IRefCovenant) => {
                refCovenant.createdOn = refCovenant.createdOn != null ? moment(refCovenant.createdOn) : null;
                refCovenant.updatedOn = refCovenant.updatedOn != null ? moment(refCovenant.updatedOn) : null;
            });
        }
        return res;
    }
}
