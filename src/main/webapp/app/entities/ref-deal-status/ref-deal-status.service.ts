import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefDealStatus } from 'app/shared/model/ref-deal-status.model';

type EntityResponseType = HttpResponse<IRefDealStatus>;
type EntityArrayResponseType = HttpResponse<IRefDealStatus[]>;

@Injectable({ providedIn: 'root' })
export class RefDealStatusService {
    public resourceUrl = SERVER_API_URL + 'api/ref-deal-statuses';

    constructor(private http: HttpClient) {}

    create(refDealStatus: IRefDealStatus): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refDealStatus);
        return this.http
            .post<IRefDealStatus>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(refDealStatus: IRefDealStatus): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refDealStatus);
        return this.http
            .put<IRefDealStatus>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRefDealStatus>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRefDealStatus[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(refDealStatus: IRefDealStatus): IRefDealStatus {
        const copy: IRefDealStatus = Object.assign({}, refDealStatus, {
            createdOn: refDealStatus.createdOn != null && refDealStatus.createdOn.isValid() ? refDealStatus.createdOn.toJSON() : null,
            updatedOn: refDealStatus.updatedOn != null && refDealStatus.updatedOn.isValid() ? refDealStatus.updatedOn.toJSON() : null
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
            res.body.forEach((refDealStatus: IRefDealStatus) => {
                refDealStatus.createdOn = refDealStatus.createdOn != null ? moment(refDealStatus.createdOn) : null;
                refDealStatus.updatedOn = refDealStatus.updatedOn != null ? moment(refDealStatus.updatedOn) : null;
            });
        }
        return res;
    }
}
