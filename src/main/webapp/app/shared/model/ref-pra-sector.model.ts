import { Moment } from 'moment';

export interface IRefPraSector {
    id?: number;
    praSectorId?: number;
    praSectorName?: string;
    isActive?: boolean;
    createdBy?: string;
    createdOn?: Moment;
    updatedBy?: string;
    updatedOn?: Moment;
}

export class RefPraSector implements IRefPraSector {
    constructor(
        public id?: number,
        public praSectorId?: number,
        public praSectorName?: string,
        public isActive?: boolean,
        public createdBy?: string,
        public createdOn?: Moment,
        public updatedBy?: string,
        public updatedOn?: Moment
    ) {
        this.isActive = this.isActive || false;
    }
}
