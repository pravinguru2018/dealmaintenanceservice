import { Moment } from 'moment';

export interface IRefCovenant {
    id?: number;
    covenantId?: number;
    covenantName?: string;
    isActive?: boolean;
    createdBy?: string;
    createdOn?: Moment;
    updatedBy?: string;
    updatedOn?: Moment;
}

export class RefCovenant implements IRefCovenant {
    constructor(
        public id?: number,
        public covenantId?: number,
        public covenantName?: string,
        public isActive?: boolean,
        public createdBy?: string,
        public createdOn?: Moment,
        public updatedBy?: string,
        public updatedOn?: Moment
    ) {
        this.isActive = this.isActive || false;
    }
}
