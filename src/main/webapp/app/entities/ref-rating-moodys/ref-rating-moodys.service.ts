import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefRatingMoodys } from 'app/shared/model/ref-rating-moodys.model';

type EntityResponseType = HttpResponse<IRefRatingMoodys>;
type EntityArrayResponseType = HttpResponse<IRefRatingMoodys[]>;

@Injectable({ providedIn: 'root' })
export class RefRatingMoodysService {
    public resourceUrl = SERVER_API_URL + 'api/ref-rating-moodys';

    constructor(private http: HttpClient) {}

    create(refRatingMoodys: IRefRatingMoodys): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refRatingMoodys);
        return this.http
            .post<IRefRatingMoodys>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(refRatingMoodys: IRefRatingMoodys): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refRatingMoodys);
        return this.http
            .put<IRefRatingMoodys>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRefRatingMoodys>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRefRatingMoodys[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(refRatingMoodys: IRefRatingMoodys): IRefRatingMoodys {
        const copy: IRefRatingMoodys = Object.assign({}, refRatingMoodys, {
            createdOn: refRatingMoodys.createdOn != null && refRatingMoodys.createdOn.isValid() ? refRatingMoodys.createdOn.toJSON() : null,
            updatedOn: refRatingMoodys.updatedOn != null && refRatingMoodys.updatedOn.isValid() ? refRatingMoodys.updatedOn.toJSON() : null
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
            res.body.forEach((refRatingMoodys: IRefRatingMoodys) => {
                refRatingMoodys.createdOn = refRatingMoodys.createdOn != null ? moment(refRatingMoodys.createdOn) : null;
                refRatingMoodys.updatedOn = refRatingMoodys.updatedOn != null ? moment(refRatingMoodys.updatedOn) : null;
            });
        }
        return res;
    }
}
