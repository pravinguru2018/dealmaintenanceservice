import { Moment } from 'moment';

export interface IRefRatingCrr {
    id?: number;
    ratingCrrId?: number;
    ratingCrrName?: string;
    isActive?: boolean;
    createdBy?: string;
    createdOn?: Moment;
    updatedBy?: string;
    updatedOn?: Moment;
}

export class RefRatingCrr implements IRefRatingCrr {
    constructor(
        public id?: number,
        public ratingCrrId?: number,
        public ratingCrrName?: string,
        public isActive?: boolean,
        public createdBy?: string,
        public createdOn?: Moment,
        public updatedBy?: string,
        public updatedOn?: Moment
    ) {
        this.isActive = this.isActive || false;
    }
}
