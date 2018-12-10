import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefBookingEntity } from 'app/shared/model/ref-booking-entity.model';

type EntityResponseType = HttpResponse<IRefBookingEntity>;
type EntityArrayResponseType = HttpResponse<IRefBookingEntity[]>;

@Injectable({ providedIn: 'root' })
export class RefBookingEntityService {
    public resourceUrl = SERVER_API_URL + 'api/ref-booking-entities';

    constructor(private http: HttpClient) {}

    create(refBookingEntity: IRefBookingEntity): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refBookingEntity);
        return this.http
            .post<IRefBookingEntity>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(refBookingEntity: IRefBookingEntity): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refBookingEntity);
        return this.http
            .put<IRefBookingEntity>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRefBookingEntity>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRefBookingEntity[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(refBookingEntity: IRefBookingEntity): IRefBookingEntity {
        const copy: IRefBookingEntity = Object.assign({}, refBookingEntity, {
            createdOn:
                refBookingEntity.createdOn != null && refBookingEntity.createdOn.isValid() ? refBookingEntity.createdOn.toJSON() : null,
            updatedOn:
                refBookingEntity.updatedOn != null && refBookingEntity.updatedOn.isValid() ? refBookingEntity.updatedOn.toJSON() : null
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
            res.body.forEach((refBookingEntity: IRefBookingEntity) => {
                refBookingEntity.createdOn = refBookingEntity.createdOn != null ? moment(refBookingEntity.createdOn) : null;
                refBookingEntity.updatedOn = refBookingEntity.updatedOn != null ? moment(refBookingEntity.updatedOn) : null;
            });
        }
        return res;
    }
}
