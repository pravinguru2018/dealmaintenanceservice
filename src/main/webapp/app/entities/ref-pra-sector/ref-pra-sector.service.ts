import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefPraSector } from 'app/shared/model/ref-pra-sector.model';

type EntityResponseType = HttpResponse<IRefPraSector>;
type EntityArrayResponseType = HttpResponse<IRefPraSector[]>;

@Injectable({ providedIn: 'root' })
export class RefPraSectorService {
    public resourceUrl = SERVER_API_URL + 'api/ref-pra-sectors';

    constructor(private http: HttpClient) {}

    create(refPraSector: IRefPraSector): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refPraSector);
        return this.http
            .post<IRefPraSector>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(refPraSector: IRefPraSector): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(refPraSector);
        return this.http
            .put<IRefPraSector>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRefPraSector>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRefPraSector[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(refPraSector: IRefPraSector): IRefPraSector {
        const copy: IRefPraSector = Object.assign({}, refPraSector, {
            createdOn: refPraSector.createdOn != null && refPraSector.createdOn.isValid() ? refPraSector.createdOn.toJSON() : null,
            updatedOn: refPraSector.updatedOn != null && refPraSector.updatedOn.isValid() ? refPraSector.updatedOn.toJSON() : null
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
            res.body.forEach((refPraSector: IRefPraSector) => {
                refPraSector.createdOn = refPraSector.createdOn != null ? moment(refPraSector.createdOn) : null;
                refPraSector.updatedOn = refPraSector.updatedOn != null ? moment(refPraSector.updatedOn) : null;
            });
        }
        return res;
    }
}
