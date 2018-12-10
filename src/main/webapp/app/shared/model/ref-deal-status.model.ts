import { Moment } from 'moment';

export interface IRefDealStatus {
    id?: number;
    dealStatusId?: number;
    dealStatusName?: string;
    creditApprovalStatus?: string;
    displayOrder?: number;
    displayKey?: string;
    isActive?: boolean;
    createdBy?: string;
    createdOn?: Moment;
    updatedBy?: string;
    updatedOn?: Moment;
}

export class RefDealStatus implements IRefDealStatus {
    constructor(
        public id?: number,
        public dealStatusId?: number,
        public dealStatusName?: string,
        public creditApprovalStatus?: string,
        public displayOrder?: number,
        public displayKey?: string,
        public isActive?: boolean,
        public createdBy?: string,
        public createdOn?: Moment,
        public updatedBy?: string,
        public updatedOn?: Moment
    ) {
        this.isActive = this.isActive || false;
    }
}
