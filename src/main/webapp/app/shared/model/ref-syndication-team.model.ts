import { Moment } from 'moment';

export interface IRefSyndicationTeam {
    id?: number;
    refSyndicationTeamId?: number;
    refSyndicationTeamName?: string;
    isActive?: boolean;
    createdBy?: string;
    createdOn?: Moment;
    updatedBy?: string;
    updatedOn?: Moment;
}

export class RefSyndicationTeam implements IRefSyndicationTeam {
    constructor(
        public id?: number,
        public refSyndicationTeamId?: number,
        public refSyndicationTeamName?: string,
        public isActive?: boolean,
        public createdBy?: string,
        public createdOn?: Moment,
        public updatedBy?: string,
        public updatedOn?: Moment
    ) {
        this.isActive = this.isActive || false;
    }
}
