import { Moment } from 'moment';

export interface IRefRatingFitch {
    id?: number;
    ratingFitchId?: number;
    ratingFitchName?: string;
    isActive?: boolean;
    createdBy?: string;
    createdOn?: Moment;
    updatedBy?: string;
    updatedOn?: Moment;
}

export class RefRatingFitch implements IRefRatingFitch {
    constructor(
        public id?: number,
        public ratingFitchId?: number,
        public ratingFitchName?: string,
        public isActive?: boolean,
        public createdBy?: string,
        public createdOn?: Moment,
        public updatedBy?: string,
        public updatedOn?: Moment
    ) {
        this.isActive = this.isActive || false;
    }
}
