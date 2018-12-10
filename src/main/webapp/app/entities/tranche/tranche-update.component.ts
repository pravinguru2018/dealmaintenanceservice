import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ITranche } from 'app/shared/model/tranche.model';
import { TrancheService } from './tranche.service';
import { IRefFacilityType } from 'app/shared/model/ref-facility-type.model';
import { RefFacilityTypeService } from 'app/entities/ref-facility-type';
import { IRefBookingEntity } from 'app/shared/model/ref-booking-entity.model';
import { RefBookingEntityService } from 'app/entities/ref-booking-entity';
import { IRefSeniority } from 'app/shared/model/ref-seniority.model';
import { RefSeniorityService } from 'app/entities/ref-seniority';
import { IRefCovenant } from 'app/shared/model/ref-covenant.model';
import { RefCovenantService } from 'app/entities/ref-covenant';
import { IRefRatingMoodys } from 'app/shared/model/ref-rating-moodys.model';
import { RefRatingMoodysService } from 'app/entities/ref-rating-moodys';
import { IRefRatingSAndP } from 'app/shared/model/ref-rating-s-and-p.model';
import { RefRatingSAndPService } from 'app/entities/ref-rating-s-and-p';
import { IRefRatingFitch } from 'app/shared/model/ref-rating-fitch.model';
import { RefRatingFitchService } from 'app/entities/ref-rating-fitch';

@Component({
    selector: 'jhi-tranche-update',
    templateUrl: './tranche-update.component.html'
})
export class TrancheUpdateComponent implements OnInit {
    tranche: ITranche;
    isSaving: boolean;

    facilitytypeids: IRefFacilityType[];

    bookingentityids: IRefBookingEntity[];

    seniorityids: IRefSeniority[];

    covenantids: IRefCovenant[];

    ratingmoodysids: IRefRatingMoodys[];

    ratingsandpids: IRefRatingSAndP[];

    ratingfitchids: IRefRatingFitch[];
    bridgeTakeoutDate: string;
    createdOn: string;
    updatedOn: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private trancheService: TrancheService,
        private refFacilityTypeService: RefFacilityTypeService,
        private refBookingEntityService: RefBookingEntityService,
        private refSeniorityService: RefSeniorityService,
        private refCovenantService: RefCovenantService,
        private refRatingMoodysService: RefRatingMoodysService,
        private refRatingSAndPService: RefRatingSAndPService,
        private refRatingFitchService: RefRatingFitchService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tranche }) => {
            this.tranche = tranche;
            this.bridgeTakeoutDate =
                this.tranche.bridgeTakeoutDate != null ? this.tranche.bridgeTakeoutDate.format(DATE_TIME_FORMAT) : null;
            this.createdOn = this.tranche.createdOn != null ? this.tranche.createdOn.format(DATE_TIME_FORMAT) : null;
            this.updatedOn = this.tranche.updatedOn != null ? this.tranche.updatedOn.format(DATE_TIME_FORMAT) : null;
        });
        this.refFacilityTypeService.query({ filter: 'tranche-is-null' }).subscribe(
            (res: HttpResponse<IRefFacilityType[]>) => {
                if (!this.tranche.facilityTypeId || !this.tranche.facilityTypeId.id) {
                    this.facilitytypeids = res.body;
                } else {
                    this.refFacilityTypeService.find(this.tranche.facilityTypeId.id).subscribe(
                        (subRes: HttpResponse<IRefFacilityType>) => {
                            this.facilitytypeids = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.refBookingEntityService.query({ filter: 'tranche-is-null' }).subscribe(
            (res: HttpResponse<IRefBookingEntity[]>) => {
                if (!this.tranche.bookingEntityId || !this.tranche.bookingEntityId.id) {
                    this.bookingentityids = res.body;
                } else {
                    this.refBookingEntityService.find(this.tranche.bookingEntityId.id).subscribe(
                        (subRes: HttpResponse<IRefBookingEntity>) => {
                            this.bookingentityids = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.refSeniorityService.query({ filter: 'tranche-is-null' }).subscribe(
            (res: HttpResponse<IRefSeniority[]>) => {
                if (!this.tranche.seniorityId || !this.tranche.seniorityId.id) {
                    this.seniorityids = res.body;
                } else {
                    this.refSeniorityService.find(this.tranche.seniorityId.id).subscribe(
                        (subRes: HttpResponse<IRefSeniority>) => {
                            this.seniorityids = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.refCovenantService.query({ filter: 'tranche-is-null' }).subscribe(
            (res: HttpResponse<IRefCovenant[]>) => {
                if (!this.tranche.covenantId || !this.tranche.covenantId.id) {
                    this.covenantids = res.body;
                } else {
                    this.refCovenantService.find(this.tranche.covenantId.id).subscribe(
                        (subRes: HttpResponse<IRefCovenant>) => {
                            this.covenantids = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.refRatingMoodysService.query({ filter: 'tranche-is-null' }).subscribe(
            (res: HttpResponse<IRefRatingMoodys[]>) => {
                if (!this.tranche.ratingMoodysId || !this.tranche.ratingMoodysId.id) {
                    this.ratingmoodysids = res.body;
                } else {
                    this.refRatingMoodysService.find(this.tranche.ratingMoodysId.id).subscribe(
                        (subRes: HttpResponse<IRefRatingMoodys>) => {
                            this.ratingmoodysids = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.refRatingSAndPService.query({ filter: 'tranche-is-null' }).subscribe(
            (res: HttpResponse<IRefRatingSAndP[]>) => {
                if (!this.tranche.ratingSAndPId || !this.tranche.ratingSAndPId.id) {
                    this.ratingsandpids = res.body;
                } else {
                    this.refRatingSAndPService.find(this.tranche.ratingSAndPId.id).subscribe(
                        (subRes: HttpResponse<IRefRatingSAndP>) => {
                            this.ratingsandpids = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.refRatingFitchService.query({ filter: 'tranche-is-null' }).subscribe(
            (res: HttpResponse<IRefRatingFitch[]>) => {
                if (!this.tranche.ratingFitchId || !this.tranche.ratingFitchId.id) {
                    this.ratingfitchids = res.body;
                } else {
                    this.refRatingFitchService.find(this.tranche.ratingFitchId.id).subscribe(
                        (subRes: HttpResponse<IRefRatingFitch>) => {
                            this.ratingfitchids = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.tranche.bridgeTakeoutDate = this.bridgeTakeoutDate != null ? moment(this.bridgeTakeoutDate, DATE_TIME_FORMAT) : null;
        this.tranche.createdOn = this.createdOn != null ? moment(this.createdOn, DATE_TIME_FORMAT) : null;
        this.tranche.updatedOn = this.updatedOn != null ? moment(this.updatedOn, DATE_TIME_FORMAT) : null;
        if (this.tranche.id !== undefined) {
            this.subscribeToSaveResponse(this.trancheService.update(this.tranche));
        } else {
            this.subscribeToSaveResponse(this.trancheService.create(this.tranche));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITranche>>) {
        result.subscribe((res: HttpResponse<ITranche>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackRefFacilityTypeById(index: number, item: IRefFacilityType) {
        return item.id;
    }

    trackRefBookingEntityById(index: number, item: IRefBookingEntity) {
        return item.id;
    }

    trackRefSeniorityById(index: number, item: IRefSeniority) {
        return item.id;
    }

    trackRefCovenantById(index: number, item: IRefCovenant) {
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
}
