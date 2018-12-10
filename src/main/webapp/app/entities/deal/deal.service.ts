import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDeal } from 'app/shared/model/deal.model';

type EntityResponseType = HttpResponse<IDeal>;
type EntityArrayResponseType = HttpResponse<IDeal[]>;

@Injectable({ providedIn: 'root' })
export class DealService {
    public resourceUrl = SERVER_API_URL + 'api/deals';

    constructor(private http: HttpClient) {}

    create(deal: IDeal): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(deal);
        return this.http
            .post<IDeal>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(deal: IDeal): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(deal);
        return this.http
            .put<IDeal>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IDeal>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IDeal[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(deal: IDeal): IDeal {
        const copy: IDeal = Object.assign({}, deal, {
            regulatoryApprovalDate:
                deal.regulatoryApprovalDate != null && deal.regulatoryApprovalDate.isValid() ? deal.regulatoryApprovalDate.toJSON() : null,
            creditApprovalDate:
                deal.creditApprovalDate != null && deal.creditApprovalDate.isValid() ? deal.creditApprovalDate.toJSON() : null,
            allocationDate: deal.allocationDate != null && deal.allocationDate.isValid() ? deal.allocationDate.toJSON() : null,
            syndicationLaunchDate:
                deal.syndicationLaunchDate != null && deal.syndicationLaunchDate.isValid() ? deal.syndicationLaunchDate.toJSON() : null,
            commitmentDate: deal.commitmentDate != null && deal.commitmentDate.isValid() ? deal.commitmentDate.toJSON() : null,
            creditApprovalOffRiskDate:
                deal.creditApprovalOffRiskDate != null && deal.creditApprovalOffRiskDate.isValid()
                    ? deal.creditApprovalOffRiskDate.toJSON()
                    : null,
            createdOn: deal.createdOn != null && deal.createdOn.isValid() ? deal.createdOn.toJSON() : null,
            updatedBy: deal.updatedBy != null && deal.updatedBy.isValid() ? deal.updatedBy.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.regulatoryApprovalDate = res.body.regulatoryApprovalDate != null ? moment(res.body.regulatoryApprovalDate) : null;
            res.body.creditApprovalDate = res.body.creditApprovalDate != null ? moment(res.body.creditApprovalDate) : null;
            res.body.allocationDate = res.body.allocationDate != null ? moment(res.body.allocationDate) : null;
            res.body.syndicationLaunchDate = res.body.syndicationLaunchDate != null ? moment(res.body.syndicationLaunchDate) : null;
            res.body.commitmentDate = res.body.commitmentDate != null ? moment(res.body.commitmentDate) : null;
            res.body.creditApprovalOffRiskDate =
                res.body.creditApprovalOffRiskDate != null ? moment(res.body.creditApprovalOffRiskDate) : null;
            res.body.createdOn = res.body.createdOn != null ? moment(res.body.createdOn) : null;
            res.body.updatedBy = res.body.updatedBy != null ? moment(res.body.updatedBy) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((deal: IDeal) => {
                deal.regulatoryApprovalDate = deal.regulatoryApprovalDate != null ? moment(deal.regulatoryApprovalDate) : null;
                deal.creditApprovalDate = deal.creditApprovalDate != null ? moment(deal.creditApprovalDate) : null;
                deal.allocationDate = deal.allocationDate != null ? moment(deal.allocationDate) : null;
                deal.syndicationLaunchDate = deal.syndicationLaunchDate != null ? moment(deal.syndicationLaunchDate) : null;
                deal.commitmentDate = deal.commitmentDate != null ? moment(deal.commitmentDate) : null;
                deal.creditApprovalOffRiskDate = deal.creditApprovalOffRiskDate != null ? moment(deal.creditApprovalOffRiskDate) : null;
                deal.createdOn = deal.createdOn != null ? moment(deal.createdOn) : null;
                deal.updatedBy = deal.updatedBy != null ? moment(deal.updatedBy) : null;
            });
        }
        return res;
    }
}
