import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DealmaintenanceserviceSharedModule } from 'app/shared';
import {
    TrancheComponent,
    TrancheDetailComponent,
    TrancheUpdateComponent,
    TrancheDeletePopupComponent,
    TrancheDeleteDialogComponent,
    trancheRoute,
    tranchePopupRoute
} from './';

const ENTITY_STATES = [...trancheRoute, ...tranchePopupRoute];

@NgModule({
    imports: [DealmaintenanceserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TrancheComponent,
        TrancheDetailComponent,
        TrancheUpdateComponent,
        TrancheDeleteDialogComponent,
        TrancheDeletePopupComponent
    ],
    entryComponents: [TrancheComponent, TrancheUpdateComponent, TrancheDeleteDialogComponent, TrancheDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DealmaintenanceserviceTrancheModule {}
