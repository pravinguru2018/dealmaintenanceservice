import { Moment } from 'moment';

export interface IRefOriginationTeam {
    id?: number;
    originationTeamId?: number;
    originationTeamName?: string;
    isActive?: boolean;
    createdBy?: string;
    createdOn?: Moment;
    updatedBy?: string;
    updatedOn?: Moment;
}

export class RefOriginationTeam implements IRefOriginationTeam {
    constructor(
        public id?: number,
        public originationTeamId?: number,
        public originationTeamName?: string,
        public isActive?: boolean,
        public createdBy?: string,
        public createdOn?: Moment,
        public updatedBy?: string,
        public updatedOn?: Moment
    ) {
        this.isActive = this.isActive || false;
    }
}
