import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefCountry } from 'app/shared/model/ref-country.model';

type EntityResponseType = HttpResponse<IRefCountry>;
type EntityArrayResponseType = HttpResponse<IRefCountry[]>;

@Injectable({ providedIn: 'root' })
export class RefCountryService {
    public resourceUrl = SERVER_API_URL + 'api/ref-countries';

    constructor(private http: HttpClient) {}

    create(refCountry: IRefCountry): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refCountry);
        return this.http
            .post<IRefCountry>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(refCountry: IRefCountry): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refCountry);
        return this.http
            .put<IRefCountry>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRefCountry>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRefCountry[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(refCountry: IRefCountry): IRefCountry {
        const copy: IRefCountry = Object.assign({}, refCountry, {
            createdOn: refCountry.createdOn != null && refCountry.createdOn.isValid() ? refCountry.createdOn.toJSON() : null,
            updatedOn: refCountry.updatedOn != null && refCountry.updatedOn.isValid() ? refCountry.updatedOn.toJSON() : null
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
            res.body.forEach((refCountry: IRefCountry) => {
                refCountry.createdOn = refCountry.createdOn != null ? moment(refCountry.createdOn) : null;
                refCountry.updatedOn = refCountry.updatedOn != null ? moment(refCountry.updatedOn) : null;
            });
        }
        return res;
    }
}
