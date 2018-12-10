import { Moment } from 'moment';
import { ITranche } from 'app/shared/model//tranche.model';

export interface ITrancheMargin {
    id?: number;
    trancheMarginId?: number;
    marginSpreadBps?: string;
    floorPercentage?: number;
    interestPaymentFrequency?: string;
    totalPricingFlexBpsPa?: string;
    underwriteFeesBps?: number;
    participationFees?: number;
    extensionFees6Months?: number;
    extensionFees6To12Months?: number;
    otherFlex?: string;
    createdBy?: string;
    createdOn?: Moment;
    updatedBy?: string;
    updatedOn?: Moment;
    trancheId?: ITranche;
}

export class TrancheMargin implements ITrancheMargin {
    constructor(
        public id?: number,
        public trancheMarginId?: number,
        public marginSpreadBps?: string,
        public floorPercentage?: number,
        public interestPaymentFrequency?: string,
        public totalPricingFlexBpsPa?: string,
        public underwriteFeesBps?: number,
        public participationFees?: number,
        public extensionFees6Months?: number,
        public extensionFees6To12Months?: number,
        public otherFlex?: string,
        public createdBy?: string,
        public createdOn?: Moment,
        public updatedBy?: string,
        public updatedOn?: Moment,
        public trancheId?: ITranche
    ) {}
}
