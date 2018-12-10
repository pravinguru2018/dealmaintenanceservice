import { Moment } from 'moment';

export interface IRefRecourseToClient {
    id?: number;
    recourseToClientId?: number;
    recourseToClientName?: string;
    isActive?: boolean;
    createdBy?: string;
    createdOn?: Moment;
    updatedBy?: string;
    updatedOn?: Moment;
}

export class RefRecourseToClient implements IRefRecourseToClient {
    constructor(
        public id?: number,
        public recourseToClientId?: number,
        public recourseToClientName?: string,
        public isActive?: boolean,
        public createdBy?: string,
        public createdOn?: Moment,
        public updatedBy?: string,
        public updatedOn?: Moment
    ) {
        this.isActive = this.isActive || false;
    }
}
