import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefRatingFitch } from 'app/shared/model/ref-rating-fitch.model';

type EntityResponseType = HttpResponse<IRefRatingFitch>;
type EntityArrayResponseType = HttpResponse<IRefRatingFitch[]>;

@Injectable({ providedIn: 'root' })
export class RefRatingFitchService {
    public resourceUrl = SERVER_API_URL + 'api/ref-rating-fitches';

    constructor(private http: HttpClient) {}

    create(refRatingFitch: IRefRatingFitch): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refRatingFitch);
        return this.http
            .post<IRefRatingFitch>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(refRatingFitch: IRefRatingFitch): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refRatingFitch);
        return this.http
            .put<IRefRatingFitch>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRefRatingFitch>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRefRatingFitch[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(refRatingFitch: IRefRatingFitch): IRefRatingFitch {
        const copy: IRefRatingFitch = Object.assign({}, refRatingFitch, {
            createdOn: refRatingFitch.createdOn != null && refRatingFitch.createdOn.isValid() ? refRatingFitch.createdOn.toJSON() : null,
            updatedOn: refRatingFitch.updatedOn != null && refRatingFitch.updatedOn.isValid() ? refRatingFitch.updatedOn.toJSON() : null
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
            res.body.forEach((refRatingFitch: IRefRatingFitch) => {
                refRatingFitch.createdOn = refRatingFitch.createdOn != null ? moment(refRatingFitch.createdOn) : null;
                refRatingFitch.updatedOn = refRatingFitch.updatedOn != null ? moment(refRatingFitch.updatedOn) : null;
            });
        }
        return res;
    }
}
