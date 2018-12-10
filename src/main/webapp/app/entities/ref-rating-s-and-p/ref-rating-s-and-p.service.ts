import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefRatingSAndP } from 'app/shared/model/ref-rating-s-and-p.model';

type EntityResponseType = HttpResponse<IRefRatingSAndP>;
type EntityArrayResponseType = HttpResponse<IRefRatingSAndP[]>;

@Injectable({ providedIn: 'root' })
export class RefRatingSAndPService {
    public resourceUrl = SERVER_API_URL + 'api/ref-rating-s-and-ps';

    constructor(private http: HttpClient) {}

    create(refRatingSAndP: IRefRatingSAndP): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refRatingSAndP);
        return this.http
            .post<IRefRatingSAndP>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(refRatingSAndP: IRefRatingSAndP): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refRatingSAndP);
        return this.http
            .put<IRefRatingSAndP>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRefRatingSAndP>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRefRatingSAndP[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(refRatingSAndP: IRefRatingSAndP): IRefRatingSAndP {
        const copy: IRefRatingSAndP = Object.assign({}, refRatingSAndP, {
            createdOn: refRatingSAndP.createdOn != null && refRatingSAndP.createdOn.isValid() ? refRatingSAndP.createdOn.toJSON() : null,
            updatedOn: refRatingSAndP.updatedOn != null && refRatingSAndP.updatedOn.isValid() ? refRatingSAndP.updatedOn.toJSON() : null
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
            res.body.forEach((refRatingSAndP: IRefRatingSAndP) => {
                refRatingSAndP.createdOn = refRatingSAndP.createdOn != null ? moment(refRatingSAndP.createdOn) : null;
                refRatingSAndP.updatedOn = refRatingSAndP.updatedOn != null ? moment(refRatingSAndP.updatedOn) : null;
            });
        }
        return res;
    }
}
