import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DealmaintenanceserviceSharedModule } from 'app/shared';
import {
    RefRatingFitchComponent,
    RefRatingFitchDetailComponent,
    RefRatingFitchUpdateComponent,
    RefRatingFitchDeletePopupComponent,
    RefRatingFitchDeleteDialogComponent,
    refRatingFitchRoute,
    refRatingFitchPopupRoute
} from './';

const ENTITY_STATES = [...refRatingFitchRoute, ...refRatingFitchPopupRoute];

@NgModule({
    imports: [DealmaintenanceserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RefRatingFitchComponent,
        RefRatingFitchDetailComponent,
        RefRatingFitchUpdateComponent,
        RefRatingFitchDeleteDialogComponent,
        RefRatingFitchDeletePopupComponent
    ],
    entryComponents: [
        RefRatingFitchComponent,
        RefRatingFitchUpdateComponent,
        RefRatingFitchDeleteDialogComponent,
        RefRatingFitchDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DealmaintenanceserviceRefRatingFitchModule {}
