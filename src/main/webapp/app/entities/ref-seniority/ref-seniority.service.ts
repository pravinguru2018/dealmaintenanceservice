import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefSeniority } from 'app/shared/model/ref-seniority.model';

type EntityResponseType = HttpResponse<IRefSeniority>;
type EntityArrayResponseType = HttpResponse<IRefSeniority[]>;

@Injectable({ providedIn: 'root' })
export class RefSeniorityService {
    public resourceUrl = SERVER_API_URL + 'api/ref-seniorities';

    constructor(private http: HttpClient) {}

    create(refSeniority: IRefSeniority): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refSeniority);
        return this.http
            .post<IRefSeniority>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(refSeniority: IRefSeniority): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refSeniority);
        return this.http
            .put<IRefSeniority>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRefSeniority>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRefSeniority[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(refSeniority: IRefSeniority): IRefSeniority {
        const copy: IRefSeniority = Object.assign({}, refSeniority, {
            createdOn: refSeniority.createdOn != null && refSeniority.createdOn.isValid() ? refSeniority.createdOn.toJSON() : null,
            updatedOn: refSeniority.updatedOn != null && refSeniority.updatedOn.isValid() ? refSeniority.updatedOn.toJSON() : null
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
            res.body.forEach((refSeniority: IRefSeniority) => {
                refSeniority.createdOn = refSeniority.createdOn != null ? moment(refSeniority.createdOn) : null;
                refSeniority.updatedOn = refSeniority.updatedOn != null ? moment(refSeniority.updatedOn) : null;
            });
        }
        return res;
    }
}
