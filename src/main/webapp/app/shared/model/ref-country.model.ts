import { Moment } from 'moment';

export interface IRefCountry {
    id?: number;
    countryId?: number;
    countryName?: string;
    countryPra?: string;
    regionPra?: string;
    isActive?: boolean;
    createdBy?: string;
    createdOn?: Moment;
    updatedBy?: string;
    updatedOn?: Moment;
}

export class RefCountry implements IRefCountry {
    constructor(
        public id?: number,
        public countryId?: number,
        public countryName?: string,
        public countryPra?: string,
        public regionPra?: string,
        public isActive?: boolean,
        public createdBy?: string,
        public createdOn?: Moment,
        public updatedBy?: string,
        public updatedOn?: Moment
    ) {
        this.isActive = this.isActive || false;
    }
}
