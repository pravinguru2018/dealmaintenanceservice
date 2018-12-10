import { Moment } from 'moment';

export interface IRefRatingSAndP {
    id?: number;
    ratingSAndPId?: number;
    ratingSAndPName?: string;
    isActive?: boolean;
    createdBy?: string;
    createdOn?: Moment;
    updatedBy?: string;
    updatedOn?: Moment;
}

export class RefRatingSAndP implements IRefRatingSAndP {
    constructor(
        public id?: number,
        public ratingSAndPId?: number,
        public ratingSAndPName?: string,
        public isActive?: boolean,
        public createdBy?: string,
        public createdOn?: Moment,
        public updatedBy?: string,
        public updatedOn?: Moment
    ) {
        this.isActive = this.isActive || false;
    }
}
