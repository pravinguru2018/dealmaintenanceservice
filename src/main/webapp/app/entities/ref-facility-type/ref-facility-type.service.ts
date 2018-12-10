import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefFacilityType } from 'app/shared/model/ref-facility-type.model';

type EntityResponseType = HttpResponse<IRefFacilityType>;
type EntityArrayResponseType = HttpResponse<IRefFacilityType[]>;

@Injectable({ providedIn: 'root' })
export class RefFacilityTypeService {
    public resourceUrl = SERVER_API_URL + 'api/ref-facility-types';

    constructor(private http: HttpClient) {}

    create(refFacilityType: IRefFacilityType): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refFacilityType);
        return this.http
            .post<IRefFacilityType>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(refFacilityType: IRefFacilityType): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refFacilityType);
        return this.http
            .put<IRefFacilityType>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRefFacilityType>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRefFacilityType[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(refFacilityType: IRefFacilityType): IRefFacilityType {
        const copy: IRefFacilityType = Object.assign({}, refFacilityType, {
            createdOn: refFacilityType.createdOn != null && refFacilityType.createdOn.isValid() ? refFacilityType.createdOn.toJSON() : null,
            updatedOn: refFacilityType.updatedOn != null && refFacilityType.updatedOn.isValid() ? refFacilityType.updatedOn.toJSON() : null
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
            res.body.forEach((refFacilityType: IRefFacilityType) => {
                refFacilityType.createdOn = refFacilityType.createdOn != null ? moment(refFacilityType.createdOn) : null;
                refFacilityType.updatedOn = refFacilityType.updatedOn != null ? moment(refFacilityType.updatedOn) : null;
            });
        }
        return res;
    }
}
