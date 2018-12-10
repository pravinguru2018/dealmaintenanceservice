import { Moment } from 'moment';

export interface IRefSeniority {
    id?: number;
    seniorityId?: number;
    seniorityName?: string;
    isActive?: boolean;
    createdBy?: string;
    createdOn?: Moment;
    updatedBy?: string;
    updatedOn?: Moment;
}

export class RefSeniority implements IRefSeniority {
    constructor(
        public id?: number,
        public seniorityId?: number,
        public seniorityName?: string,
        public isActive?: boolean,
        public createdBy?: string,
        public createdOn?: Moment,
        public updatedBy?: string,
        public updatedOn?: Moment
    ) {
        this.isActive = this.isActive || false;
    }
}
