import { Moment } from 'moment';
import { IRefSyndicationTeam } from 'app/shared/model//ref-syndication-team.model';
import { IRefDealStatus } from 'app/shared/model//ref-deal-status.model';
import { IRefOriginationTeam } from 'app/shared/model//ref-origination-team.model';
import { IRefRecourseToClient } from 'app/shared/model//ref-recourse-to-client.model';
import { IRefLineOfBusiness } from 'app/shared/model//ref-line-of-business.model';
import { IRefRatingCrr } from 'app/shared/model//ref-rating-crr.model';
import { IRefRatingMoodys } from 'app/shared/model//ref-rating-moodys.model';
import { IRefRatingSAndP } from 'app/shared/model//ref-rating-s-and-p.model';
import { IRefRatingFitch } from 'app/shared/model//ref-rating-fitch.model';
import { IRefCountry } from 'app/shared/model//ref-country.model';
import { IRefPraSector } from 'app/shared/model//ref-pra-sector.model';
import { ITranche } from 'app/shared/model//tranche.model';

export interface IDeal {
    id?: number;
    dealId?: number;
    projectName?: string;
    clientSponsor?: string;
    targetAsset?: string;
    syndicateOwner?: string;
    dealPipelineId?: string;
    originationContact?: string;
    relationshipManager?: string;
    llf?: string;
    regulatoryApprovalRequired?: string;
    regulatoryApprovalObtained?: string;
    countryOfIncorporation?: string;
    regulatoryApprovalDate?: Moment;
    dealDescription?: string;
    updateNote?: string;
    creditApprovalDate?: Moment;
    allocationDate?: Moment;
    syndicationLaunchDate?: Moment;
    commitmentDate?: Moment;
    creditApprovalOffRiskDate?: Moment;
    creditOfficer?: string;
    isActive?: boolean;
    createdBy?: string;
    createdOn?: Moment;
    updatedBy?: Moment;
    syndicationTeamId?: IRefSyndicationTeam;
    dealStatusId?: IRefDealStatus;
    originationTeamId?: IRefOriginationTeam;
    recourseToClientId?: IRefRecourseToClient;
    lineOfBusinessId?: IRefLineOfBusiness;
    ratingCrrId?: IRefRatingCrr;
    ratingMoodysId?: IRefRatingMoodys;
    ratingSAndPId?: IRefRatingSAndP;
    ratingFitchId?: IRefRatingFitch;
    countryId?: IRefCountry;
    praSectorId?: IRefPraSector;
    tranche?: ITranche;
}

export class Deal implements IDeal {
    constructor(
        public id?: number,
        public dealId?: number,
        public projectName?: string,
        public clientSponsor?: string,
        public targetAsset?: string,
        public syndicateOwner?: string,
        public dealPipelineId?: string,
        public originationContact?: string,
        public relationshipManager?: string,
        public llf?: string,
        public regulatoryApprovalRequired?: string,
        public regulatoryApprovalObtained?: string,
        public countryOfIncorporation?: string,
        public regulatoryApprovalDate?: Moment,
        public dealDescription?: string,
        public updateNote?: string,
        public creditApprovalDate?: Moment,
        public allocationDate?: Moment,
        public syndicationLaunchDate?: Moment,
        public commitmentDate?: Moment,
        public creditApprovalOffRiskDate?: Moment,
        public creditOfficer?: string,
        public isActive?: boolean,
        public createdBy?: string,
        public createdOn?: Moment,
        public updatedBy?: Moment,
        public syndicationTeamId?: IRefSyndicationTeam,
        public dealStatusId?: IRefDealStatus,
        public originationTeamId?: IRefOriginationTeam,
        public recourseToClientId?: IRefRecourseToClient,
        public lineOfBusinessId?: IRefLineOfBusiness,
        public ratingCrrId?: IRefRatingCrr,
        public ratingMoodysId?: IRefRatingMoodys,
        public ratingSAndPId?: IRefRatingSAndP,
        public ratingFitchId?: IRefRatingFitch,
        public countryId?: IRefCountry,
        public praSectorId?: IRefPraSector,
        public tranche?: ITranche
    ) {
        this.isActive = this.isActive || false;
    }
}
