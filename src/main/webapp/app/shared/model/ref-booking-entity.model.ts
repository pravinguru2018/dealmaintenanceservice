import { Moment } from 'moment';

export interface IRefBookingEntity {
    id?: number;
    bookingEntityId?: number;
    bookingEntityName?: string;
    isActive?: boolean;
    createdBy?: string;
    createdOn?: Moment;
    updatedBy?: string;
    updatedOn?: Moment;
}

export class RefBookingEntity implements IRefBookingEntity {
    constructor(
        public id?: number,
        public bookingEntityId?: number,
        public bookingEntityName?: string,
        public isActive?: boolean,
        public createdBy?: string,
        public createdOn?: Moment,
        public updatedBy?: string,
        public updatedOn?: Moment
    ) {
        this.isActive = this.isActive || false;
    }
}
