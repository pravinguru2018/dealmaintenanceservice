import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DealmaintenanceserviceSharedModule } from 'app/shared';
import {
    RefPraSectorComponent,
    RefPraSectorDetailComponent,
    RefPraSectorUpdateComponent,
    RefPraSectorDeletePopupComponent,
    RefPraSectorDeleteDialogComponent,
    refPraSectorRoute,
    refPraSectorPopupRoute
} from './';

const ENTITY_STATES = [...refPraSectorRoute, ...refPraSectorPopupRoute];

@NgModule({
    imports: [DealmaintenanceserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RefPraSectorComponent,
        RefPraSectorDetailComponent,
        RefPraSectorUpdateComponent,
        RefPraSectorDeleteDialogComponent,
        RefPraSectorDeletePopupComponent
    ],
    entryComponents: [
        RefPraSectorComponent,
        RefPraSectorUpdateComponent,
        RefPraSectorDeleteDialogComponent,
        RefPraSectorDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DealmaintenanceserviceRefPraSectorModule {}
