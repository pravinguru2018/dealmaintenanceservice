import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DealmaintenanceserviceSharedModule } from 'app/shared';
import {
    TrancheMarginComponent,
    TrancheMarginDetailComponent,
    TrancheMarginUpdateComponent,
    TrancheMarginDeletePopupComponent,
    TrancheMarginDeleteDialogComponent,
    trancheMarginRoute,
    trancheMarginPopupRoute
} from './';

const ENTITY_STATES = [...trancheMarginRoute, ...trancheMarginPopupRoute];

@NgModule({
    imports: [DealmaintenanceserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TrancheMarginComponent,
        TrancheMarginDetailComponent,
        TrancheMarginUpdateComponent,
        TrancheMarginDeleteDialogComponent,
        TrancheMarginDeletePopupComponent
    ],
    entryComponents: [
        TrancheMarginComponent,
        TrancheMarginUpdateComponent,
        TrancheMarginDeleteDialogComponent,
        TrancheMarginDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DealmaintenanceserviceTrancheMarginModule {}
