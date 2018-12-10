import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DealmaintenanceserviceSharedModule } from 'app/shared';
import {
    DealComponent,
    DealDetailComponent,
    DealUpdateComponent,
    DealDeletePopupComponent,
    DealDeleteDialogComponent,
    dealRoute,
    dealPopupRoute
} from './';

const ENTITY_STATES = [...dealRoute, ...dealPopupRoute];

@NgModule({
    imports: [DealmaintenanceserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [DealComponent, DealDetailComponent, DealUpdateComponent, DealDeleteDialogComponent, DealDeletePopupComponent],
    entryComponents: [DealComponent, DealUpdateComponent, DealDeleteDialogComponent, DealDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DealmaintenanceserviceDealModule {}
