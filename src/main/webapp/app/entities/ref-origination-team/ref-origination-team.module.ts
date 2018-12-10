import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DealmaintenanceserviceSharedModule } from 'app/shared';
import {
    RefOriginationTeamComponent,
    RefOriginationTeamDetailComponent,
    RefOriginationTeamUpdateComponent,
    RefOriginationTeamDeletePopupComponent,
    RefOriginationTeamDeleteDialogComponent,
    refOriginationTeamRoute,
    refOriginationTeamPopupRoute
} from './';

const ENTITY_STATES = [...refOriginationTeamRoute, ...refOriginationTeamPopupRoute];

@NgModule({
    imports: [DealmaintenanceserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RefOriginationTeamComponent,
        RefOriginationTeamDetailComponent,
        RefOriginationTeamUpdateComponent,
        RefOriginationTeamDeleteDialogComponent,
        RefOriginationTeamDeletePopupComponent
    ],
    entryComponents: [
        RefOriginationTeamComponent,
        RefOriginationTeamUpdateComponent,
        RefOriginationTeamDeleteDialogComponent,
        RefOriginationTeamDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DealmaintenanceserviceRefOriginationTeamModule {}
