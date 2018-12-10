import { Moment } from 'moment';

export interface IRefFacilityType {
    id?: number;
    facilityTypeId?: number;
    facilityTypeName?: string;
    isActive?: boolean;
    createdBy?: string;
    createdOn?: Moment;
    updatedBy?: string;
    updatedOn?: Moment;
}

export class RefFacilityType implements IRefFacilityType {
    constructor(
        public id?: number,
        public facilityTypeId?: number,
        public facilityTypeName?: string,
        public isActive?: boolean,
        public createdBy?: string,
        public createdOn?: Moment,
        public updatedBy?: string,
        public updatedOn?: Moment
    ) {
        this.isActive = this.isActive || false;
    }
}
