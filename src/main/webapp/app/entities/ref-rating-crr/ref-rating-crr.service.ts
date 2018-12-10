import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefRatingCrr } from 'app/shared/model/ref-rating-crr.model';

type EntityResponseType = HttpResponse<IRefRatingCrr>;
type EntityArrayResponseType = HttpResponse<IRefRatingCrr[]>;

@Injectable({ providedIn: 'root' })
export class RefRatingCrrService {
    public resourceUrl = SERVER_API_URL + 'api/ref-rating-crrs';

    constructor(private http: HttpClient) {}

    create(refRatingCrr: IRefRatingCrr): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refRatingCrr);
        return this.http
            .post<IRefRatingCrr>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(refRatingCrr: IRefRatingCrr): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refRatingCrr);
        return this.http
            .put<IRefRatingCrr>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRefRatingCrr>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRefRatingCrr[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(refRatingCrr: IRefRatingCrr): IRefRatingCrr {
        const copy: IRefRatingCrr = Object.assign({}, refRatingCrr, {
            createdOn: refRatingCrr.createdOn != null && refRatingCrr.createdOn.isValid() ? refRatingCrr.createdOn.toJSON() : null,
            updatedOn: refRatingCrr.updatedOn != null && refRatingCrr.updatedOn.isValid() ? refRatingCrr.updatedOn.toJSON() : null
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
            res.body.forEach((refRatingCrr: IRefRatingCrr) => {
                refRatingCrr.createdOn = refRatingCrr.createdOn != null ? moment(refRatingCrr.createdOn) : null;
                refRatingCrr.updatedOn = refRatingCrr.updatedOn != null ? moment(refRatingCrr.updatedOn) : null;
            });
        }
        return res;
    }
}
