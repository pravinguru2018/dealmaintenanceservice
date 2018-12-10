import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IDeal } from 'app/shared/model/deal.model';
import { DealService } from './deal.service';
import { IRefSyndicationTeam } from 'app/shared/model/ref-syndication-team.model';
import { RefSyndicationTeamService } from 'app/entities/ref-syndication-team';
import { IRefDealStatus } from 'app/shared/model/ref-deal-status.model';
import { RefDealStatusService } from 'app/entities/ref-deal-status';
import { IRefOriginationTeam } from 'app/shared/model/ref-origination-team.model';
import { RefOriginationTeamService } from 'app/entities/ref-origination-team';
import { IRefRecourseToClient } from 'app/shared/model/ref-recourse-to-client.model';
import { RefRecourseToClientService } from 'app/entities/ref-recourse-to-client';
import { IRefLineOfBusiness } from 'app/shared/model/ref-line-of-business.model';
import { RefLineOfBusinessService } from 'app/entities/ref-line-of-business';
import { IRefRatingCrr } from 'app/shared/model/ref-rating-crr.model';
import { RefRatingCrrService } from 'app/entities/ref-rating-crr';
import { IRefRatingMoodys } from 'app/shared/model/ref-rating-moodys.model';
import { RefRatingMoodysService } from 'app/entities/ref-rating-moodys';
import { IRefRatingSAndP } from 'app/shared/model/ref-rating-s-and-p.model';
import { RefRatingSAndPService } from 'app/entities/ref-rating-s-and-p';
import { IRefRatingFitch } from 'app/shared/model/ref-rating-fitch.model';
import { RefRatingFitchService } from 'app/entities/ref-rating-fitch';
import { IRefCountry } from 'app/shared/model/ref-country.model';
import { RefCountryService } from 'app/entities/ref-country';
import { IRefPraSector } from 'app/shared/model/ref-pra-sector.model';
import { RefPraSectorService } from 'app/entities/ref-pra-sector';
import { ITranche } from 'app/shared/model/tranche.model';
import { TrancheService } from 'app/entities/tranche';

@Component({
    selector: 'jhi-deal-update',
    templateUrl: './deal-update.component.html'
})
export class DealUpdateComponent implements OnInit {
    deal: IDeal;
    isSaving: boolean;

    syndicationteamids: IRefSyndicationTeam[];

    dealstatusids: IRefDealStatus[];

    originationteamids: IRefOriginationTeam[];

    recoursetoclientids: IRefRecourseToClient[];

    lineofbusinessids: IRefLineOfBusiness[];

    ratingcrrids: IRefRatingCrr[];

    ratingmoodysids: IRefRatingMoodys[];

    ratingsandpids: IRefRatingSAndP[];

    ratingfitchids: IRefRatingFitch[];

    countryids: IRefCountry[];

    prasectorids: IRefPraSector[];

    tranches: ITranche[];
    regulatoryApprovalDate: string;
    creditApprovalDate: string;
    allocationDate: string;
    syndicationLaunchDate: string;
    commitmentDate: string;
    creditApprovalOffRiskDate: string;
    createdOn: string;
    updatedBy: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private dealService: DealService,
        private refSyndicationTeamService: RefSyndicationTeamService,
        private refDealStatusService: RefDealStatusService,
        private refOriginationTeamService: RefOriginationTeamService,
        private refRecourseToClientService: RefRecourseToClientService,
        private refLineOfBusinessService: RefLineOfBusinessService,
        private refRatingCrrService: RefRatingCrrService,
        private refRatingMoodysService: RefRatingMoodysService,
        private refRatingSAndPService: RefRatingSAndPService,
        private refRatingFitchService: RefRatingFitchService,
        private refCountryService: RefCountryService,
        private refPraSectorService: RefPraSectorService,
        private trancheService: TrancheService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ deal }) => {
            this.deal = deal;
            this.regulatoryApprovalDate =
                this.deal.regulatoryApprovalDate != null ? this.deal.regulatoryApprovalDate.format(DATE_TIME_FORMAT) : null;
            this.creditApprovalDate = this.deal.creditApprovalDate != null ? this.deal.creditApprovalDate.format(DATE_TIME_FORMAT) : null;
            this.allocationDate = this.deal.allocationDate != null ? this.deal.allocationDate.format(DATE_TIME_FORMAT) : null;
            this.syndicationLaunchDate =
                this.deal.syndicationLaunchDate != null ? this.deal.syndicationLaunchDate.format(DATE_TIME_FORMAT) : null;
            this.commitmentDate = this.deal.commitmentDate != null ? this.deal.commitmentDate.format(DATE_TIME_FORMAT) : null;
            this.creditApprovalOffRiskDate =
                this.deal.creditApprovalOffRiskDate != null ? this.deal.creditApprovalOffRiskDate.format(DATE_TIME_FORMAT) : null;
            this.createdOn = this.deal.createdOn != null ? this.deal.createdOn.format(DATE_TIME_FORMAT) : null;
            this.updatedBy = this.deal.updatedBy != null ? this.deal.updatedBy.format(DATE_TIME_FORMAT) : null;
        });
        this.refSyndicationTeamService.query({ filter: 'deal-is-null' }).subscribe(
            (res: HttpResponse<IRefSyndicationTeam[]>) => {
                if (!this.deal.syndicationTeamId || !this.deal.syndicationTeamId.id) {
                    this.syndicationteamids = res.body;
                } else {
                    this.refSyndicationTeamService.find(this.deal.syndicationTeamId.id).subscribe(
                        (subRes: HttpResponse<IRefSyndicationTeam>) => {
                            this.syndicationteamids = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.refDealStatusService.query({ filter: 'deal-is-null' }).subscribe(
            (res: HttpResponse<IRefDealStatus[]>) => {
                if (!this.deal.dealStatusId || !this.deal.dealStatusId.id) {
                    this.dealstatusids = res.body;
                } else {
                    this.refDealStatusService.find(this.deal.dealStatusId.id).subscribe(
                        (subRes: HttpResponse<IRefDealStatus>) => {
                            this.dealstatusids = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.refOriginationTeamService.query({ filter: 'deal-is-null' }).subscribe(
            (res: HttpResponse<IRefOriginationTeam[]>) => {
                if (!this.deal.originationTeamId || !this.deal.originationTeamId.id) {
                    this.originationteamids = res.body;
                } else {
                    this.refOriginationTeamService.find(this.deal.originationTeamId.id).subscribe(
                        (subRes: HttpResponse<IRefOriginationTeam>) => {
                            this.originationteamids = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.refRecourseToClientService.query({ filter: 'deal-is-null' }).subscribe(
            (res: HttpResponse<IRefRecourseToClient[]>) => {
                if (!this.deal.recourseToClientId || !this.deal.recourseToClientId.id) {
                    this.recoursetoclientids = res.body;
                } else {
                    this.refRecourseToClientService.find(this.deal.recourseToClientId.id).subscribe(
                        (subRes: HttpResponse<IRefRecourseToClient>) => {
                            this.recoursetoclientids = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.refLineOfBusinessService.query({ filter: 'deal-is-null' }).subscribe(
            (res: HttpResponse<IRefLineOfBusiness[]>) => {
                if (!this.deal.lineOfBusinessId || !this.deal.lineOfBusinessId.id) {
                    this.lineofbusinessids = res.body;
                } else {
                    this.refLineOfBusinessService.find(this.deal.lineOfBusinessId.id).subscribe(
                        (subRes: HttpResponse<IRefLineOfBusiness>) => {
                            this.lineofbusinessids = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.refRatingCrrService.query({ filter: 'deal-is-null' }).subscribe(
            (res: HttpResponse<IRefRatingCrr[]>) => {
                if (!this.deal.ratingCrrId || !this.deal.ratingCrrId.id) {
                    this.ratingcrrids = res.body;
                } else {
                    this.refRatingCrrService.find(this.deal.ratingCrrId.id).subscribe(
                        (subRes: HttpResponse<IRefRatingCrr>) => {
                            this.ratingcrrids = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.refRatingMoodysService.query({ filter: 'deal-is-null' }).subscribe(
            (res: HttpResponse<IRefRatingMoodys[]>) => {
                if (!this.deal.ratingMoodysId || !this.deal.ratingMoodysId.id) {
                    this.ratingmoodysids = res.body;
                } else {
                    this.refRatingMoodysService.find(this.deal.ratingMoodysId.id).subscribe(
                        (subRes: HttpResponse<IRefRatingMoodys>) => {
                            this.ratingmoodysids = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.refRatingSAndPService.query({ filter: 'deal-is-null' }).subscribe(
            (res: HttpResponse<IRefRatingSAndP[]>) => {
                if (!this.deal.ratingSAndPId || !this.deal.ratingSAndPId.id) {
                    this.ratingsandpids = res.body;
                } else {
                    this.refRatingSAndPService.find(this.deal.ratingSAndPId.id).subscribe(
                        (subRes: HttpResponse<IRefRatingSAndP>) => {
                            this.ratingsandpids = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.refRatingFitchService.query({ filter: 'deal-is-null' }).subscribe(
            (res: HttpResponse<IRefRatingFitch[]>) => {
                if (!this.deal.ratingFitchId || !this.deal.ratingFitchId.id) {
                    this.ratingfitchids = res.body;
                } else {
                    this.refRatingFitchService.find(this.deal.ratingFitchId.id).subscribe(
                        (subRes: HttpResponse<IRefRatingFitch>) => {
                            this.ratingfitchids = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.refCountryService.query({ filter: 'deal-is-null' }).subscribe(
            (res: HttpResponse<IRefCountry[]>) => {
                if (!this.deal.countryId || !this.deal.countryId.id) {
                    this.countryids = res.body;
                } else {
                    this.refCountryService.find(this.deal.countryId.id).subscribe(
                        (subRes: HttpResponse<IRefCountry>) => {
                            this.countryids = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.refPraSectorService.query({ filter: 'deal-is-null' }).subscribe(
            (res: HttpResponse<IRefPraSector[]>) => {
                if (!this.deal.praSectorId || !this.deal.praSectorId.id) {
                    this.prasectorids = res.body;
                } else {
                    this.refPraSectorService.find(this.deal.praSectorId.id).subscribe(
                        (subRes: HttpResponse<IRefPraSector>) => {
                            this.prasectorids = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.trancheService.query().subscribe(
            (res: HttpResponse<ITranche[]>) => {
                this.tranches = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.deal.regulatoryApprovalDate =
            this.regulatoryApprovalDate != null ? moment(this.regulatoryApprovalDate, DATE_TIME_FORMAT) : null;
        this.deal.creditApprovalDate = this.creditApprovalDate != null ? moment(this.creditApprovalDate, DATE_TIME_FORMAT) : null;
        this.deal.allocationDate = this.allocationDate != null ? moment(this.allocationDate, DATE_TIME_FORMAT) : null;
        this.deal.syndicationLaunchDate = this.syndicationLaunchDate != null ? moment(this.syndicationLaunchDate, DATE_TIME_FORMAT) : null;
        this.deal.commitmentDate = this.commitmentDate != null ? moment(this.commitmentDate, DATE_TIME_FORMAT) : null;
        this.deal.creditApprovalOffRiskDate =
            this.creditApprovalOffRiskDate != null ? moment(this.creditApprovalOffRiskDate, DATE_TIME_FORMAT) : null;
        this.deal.createdOn = this.createdOn != null ? moment(this.createdOn, DATE_TIME_FORMAT) : null;
        this.deal.updatedBy = this.updatedBy != null ? moment(this.updatedBy, DATE_TIME_FORMAT) : null;
        if (this.deal.id !== undefined) {
            this.subscribeToSaveResponse(this.dealService.update(this.deal));
        } else {
            this.subscribeToSaveResponse(this.dealService.create(this.deal));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDeal>>) {
        result.subscribe((res: HttpResponse<IDeal>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackRefSyndicationTeamById(index: number, item: IRefSyndicationTeam) {
        return item.id;
    }

    trackRefDealStatusById(index: number, item: IRefDealStatus) {
        return item.id;
    }

    trackRefOriginationTeamById(index: number, item: IRefOriginationTeam) {
        return item.id;
    }

    trackRefRecourseToClientById(index: number, item: IRefRecourseToClient) {
        return item.id;
    }

    trackRefLineOfBusinessById(index: number, item: IRefLineOfBusiness) {
        return item.id;
    }

    trackRefRatingCrrById(index: number, item: IRefRatingCrr) {
        return item.id;
    }

    trackRefRatingMoodysById(index: number, item: IRefRatingMoodys) {
        return item.id;
    }

    trackRefRatingSAndPById(index: number, item: IRefRatingSAndP) {
        return item.id;
    }

    trackRefRatingFitchById(index: number, item: IRefRatingFitch) {
        return item.id;
    }

    trackRefCountryById(index: number, item: IRefCountry) {
        return item.id;
    }

    trackRefPraSectorById(index: number, item: IRefPraSector) {
        return item.id;
    }

    trackTrancheById(index: number, item: ITranche) {
        return item.id;
    }
}
