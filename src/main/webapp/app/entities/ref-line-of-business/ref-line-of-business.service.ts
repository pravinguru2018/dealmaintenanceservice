import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefLineOfBusiness } from 'app/shared/model/ref-line-of-business.model';

type EntityResponseType = HttpResponse<IRefLineOfBusiness>;
type EntityArrayResponseType = HttpResponse<IRefLineOfBusiness[]>;

@Injectable({ providedIn: 'root' })
export class RefLineOfBusinessService {
    public resourceUrl = SERVER_API_URL + 'api/ref-line-of-businesses';

    constructor(private http: HttpClient) {}

    create(refLineOfBusiness: IRefLineOfBusiness): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refLineOfBusiness);
        return this.http
            .post<IRefLineOfBusiness>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(refLineOfBusiness: IRefLineOfBusiness): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refLineOfBusiness);
        return this.http
            .put<IRefLineOfBusiness>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRefLineOfBusiness>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRefLineOfBusiness[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(refLineOfBusiness: IRefLineOfBusiness): IRefLineOfBusiness {
        const copy: IRefLineOfBusiness = Object.assign({}, refLineOfBusiness, {
            createdOn:
                refLineOfBusiness.createdOn != null && refLineOfBusiness.createdOn.isValid() ? refLineOfBusiness.createdOn.toJSON() : null,
            updatedOn:
                refLineOfBusiness.updatedOn != null && refLineOfBusiness.updatedOn.isValid() ? refLineOfBusiness.updatedOn.toJSON() : null
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
            res.body.forEach((refLineOfBusiness: IRefLineOfBusiness) => {
                refLineOfBusiness.createdOn = refLineOfBusiness.createdOn != null ? moment(refLineOfBusiness.createdOn) : null;
                refLineOfBusiness.updatedOn = refLineOfBusiness.updatedOn != null ? moment(refLineOfBusiness.updatedOn) : null;
            });
        }
        return res;
    }
}
