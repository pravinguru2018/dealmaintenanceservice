import { Moment } from 'moment';
import { IRefFacilityType } from 'app/shared/model//ref-facility-type.model';
import { IRefBookingEntity } from 'app/shared/model//ref-booking-entity.model';
import { IRefSeniority } from 'app/shared/model//ref-seniority.model';
import { IRefCovenant } from 'app/shared/model//ref-covenant.model';
import { IRefRatingMoodys } from 'app/shared/model//ref-rating-moodys.model';
import { IRefRatingSAndP } from 'app/shared/model//ref-rating-s-and-p.model';
import { IRefRatingFitch } from 'app/shared/model//ref-rating-fitch.model';
import { IDeal } from 'app/shared/model//deal.model';

export interface ITranche {
    id?: number;
    trancheId?: number;
    trancheName?: string;
    fundedDuringSyndication?: string;
    debtTakeout?: string;
    tenorYears?: number;
    tenorMonths?: number;
    bridgeTakeoutDate?: Moment;
    creditApprovedLcymStructure?: number;
    creditApprovedLcymCommit?: number;
    creditApprovedLcymHold?: number;
    finalApprovedLcymStructure?: number;
    finalApprovedLcymCommit?: number;
    finalApprovedLcymHold?: number;
    marketRiskLcymEconomic?: number;
    marketRiskLcymLegal?: number;
    marketRiskLcymSettlement?: number;
    currentBridgeHoldLcym?: number;
    tenorHighYieldBondYears?: number;
    tenorHighYieldBondMonths?: number;
    bondCapRate?: number;
    refMarginRateName?: string;
    currency?: string;
    sortOrder?: number;
    createdBy?: string;
    createdOn?: Moment;
    updatedBy?: string;
    updatedOn?: Moment;
    facilityTypeId?: IRefFacilityType;
    bookingEntityId?: IRefBookingEntity;
    seniorityId?: IRefSeniority;
    covenantId?: IRefCovenant;
    ratingMoodysId?: IRefRatingMoodys;
    ratingSAndPId?: IRefRatingSAndP;
    ratingFitchId?: IRefRatingFitch;
    dealIds?: IDeal[];
}

export class Tranche implements ITranche {
    constructor(
        public id?: number,
        public trancheId?: number,
        public trancheName?: string,
        public fundedDuringSyndication?: string,
        public debtTakeout?: string,
        public tenorYears?: number,
        public tenorMonths?: number,
        public bridgeTakeoutDate?: Moment,
        public creditApprovedLcymStructure?: number,
        public creditApprovedLcymCommit?: number,
        public creditApprovedLcymHold?: number,
        public finalApprovedLcymStructure?: number,
        public finalApprovedLcymCommit?: number,
        public finalApprovedLcymHold?: number,
        public marketRiskLcymEconomic?: number,
        public marketRiskLcymLegal?: number,
        public marketRiskLcymSettlement?: number,
        public currentBridgeHoldLcym?: number,
        public tenorHighYieldBondYears?: number,
        public tenorHighYieldBondMonths?: number,
        public bondCapRate?: number,
        public refMarginRateName?: string,
        public currency?: string,
        public sortOrder?: number,
        public createdBy?: string,
        public createdOn?: Moment,
        public updatedBy?: string,
        public updatedOn?: Moment,
        public facilityTypeId?: IRefFacilityType,
        public bookingEntityId?: IRefBookingEntity,
        public seniorityId?: IRefSeniority,
        public covenantId?: IRefCovenant,
        public ratingMoodysId?: IRefRatingMoodys,
        public ratingSAndPId?: IRefRatingSAndP,
        public ratingFitchId?: IRefRatingFitch,
        public dealIds?: IDeal[]
    ) {}
}
