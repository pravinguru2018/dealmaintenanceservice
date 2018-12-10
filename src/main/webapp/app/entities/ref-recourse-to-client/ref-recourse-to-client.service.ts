import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefRecourseToClient } from 'app/shared/model/ref-recourse-to-client.model';

type EntityResponseType = HttpResponse<IRefRecourseToClient>;
type EntityArrayResponseType = HttpResponse<IRefRecourseToClient[]>;

@Injectable({ providedIn: 'root' })
export class RefRecourseToClientService {
    public resourceUrl = SERVER_API_URL + 'api/ref-recourse-to-clients';

    constructor(private http: HttpClient) {}

    create(refRecourseToClient: IRefRecourseToClient): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refRecourseToClient);
        return this.http
            .post<IRefRecourseToClient>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(refRecourseToClient: IRefRecourseToClient): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refRecourseToClient);
        return this.http
            .put<IRefRecourseToClient>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRefRecourseToClient>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRefRecourseToClient[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(refRecourseToClient: IRefRecourseToClient): IRefRecourseToClient {
        const copy: IRefRecourseToClient = Object.assign({}, refRecourseToClient, {
            createdOn:
                refRecourseToClient.createdOn != null && refRecourseToClient.createdOn.isValid()
                    ? refRecourseToClient.createdOn.toJSON()
                    : null,
            updatedOn:
                refRecourseToClient.updatedOn != null && refRecourseToClient.updatedOn.isValid()
                    ? refRecourseToClient.updatedOn.toJSON()
                    : null
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
            res.body.forEach((refRecourseToClient: IRefRecourseToClient) => {
                refRecourseToClient.createdOn = refRecourseToClient.createdOn != null ? moment(refRecourseToClient.createdOn) : null;
                refRecourseToClient.updatedOn = refRecourseToClient.updatedOn != null ? moment(refRecourseToClient.updatedOn) : null;
            });
        }
        return res;
    }
}
