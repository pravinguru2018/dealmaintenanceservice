import { Moment } from 'moment';

export interface IRefRatingMoodys {
    id?: number;
    ratingMoodysId?: number;
    ratingMoodysName?: string;
    isActive?: boolean;
    createdBy?: string;
    createdOn?: Moment;
    updatedBy?: string;
    updatedOn?: Moment;
}

export class RefRatingMoodys implements IRefRatingMoodys {
    constructor(
        public id?: number,
        public ratingMoodysId?: number,
        public ratingMoodysName?: string,
        public isActive?: boolean,
        public createdBy?: string,
        public createdOn?: Moment,
        public updatedBy?: string,
        public updatedOn?: Moment
    ) {
        this.isActive = this.isActive || false;
    }
}
