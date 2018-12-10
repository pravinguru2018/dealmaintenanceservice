import { Moment } from 'moment';

export interface IRefLineOfBusiness {
    id?: number;
    lineOfBusinessId?: number;
    lineOfBusinessName?: string;
    isActive?: boolean;
    createdBy?: string;
    createdOn?: Moment;
    updatedBy?: string;
    updatedOn?: Moment;
}

export class RefLineOfBusiness implements IRefLineOfBusiness {
    constructor(
        public id?: number,
        public lineOfBusinessId?: number,
        public lineOfBusinessName?: string,
        public isActive?: boolean,
        public createdBy?: string,
        public createdOn?: Moment,
        public updatedBy?: string,
        public updatedOn?: Moment
    ) {
        this.isActive = this.isActive || false;
    }
}
