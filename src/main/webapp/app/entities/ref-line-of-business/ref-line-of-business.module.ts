import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DealmaintenanceserviceSharedModule } from 'app/shared';
import {
    RefLineOfBusinessComponent,
    RefLineOfBusinessDetailComponent,
    RefLineOfBusinessUpdateComponent,
    RefLineOfBusinessDeletePopupComponent,
    RefLineOfBusinessDeleteDialogComponent,
    refLineOfBusinessRoute,
    refLineOfBusinessPopupRoute
} from './';

const ENTITY_STATES = [...refLineOfBusinessRoute, ...refLineOfBusinessPopupRoute];

@NgModule({
    imports: [DealmaintenanceserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RefLineOfBusinessComponent,
        RefLineOfBusinessDetailComponent,
        RefLineOfBusinessUpdateComponent,
        RefLineOfBusinessDeleteDialogComponent,
        RefLineOfBusinessDeletePopupComponent
    ],
    entryComponents: [
        RefLineOfBusinessComponent,
        RefLineOfBusinessUpdateComponent,
        RefLineOfBusinessDeleteDialogComponent,
        RefLineOfBusinessDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DealmaintenanceserviceRefLineOfBusinessModule {}
