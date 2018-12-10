import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { DealmaintenanceserviceRefSyndicationTeamModule } from './ref-syndication-team/ref-syndication-team.module';
import { DealmaintenanceserviceRefDealStatusModule } from './ref-deal-status/ref-deal-status.module';
import { DealmaintenanceserviceRefOriginationTeamModule } from './ref-origination-team/ref-origination-team.module';
import { DealmaintenanceserviceRefLineOfBusinessModule } from './ref-line-of-business/ref-line-of-business.module';
import { DealmaintenanceserviceRefRecourseToClientModule } from './ref-recourse-to-client/ref-recourse-to-client.module';
import { DealmaintenanceserviceRefRatingCrrModule } from './ref-rating-crr/ref-rating-crr.module';
import { DealmaintenanceserviceRefRatingMoodysModule } from './ref-rating-moodys/ref-rating-moodys.module';
import { DealmaintenanceserviceRefRatingSAndPModule } from './ref-rating-s-and-p/ref-rating-s-and-p.module';
import { DealmaintenanceserviceRefRatingFitchModule } from './ref-rating-fitch/ref-rating-fitch.module';
import { DealmaintenanceserviceRefCountryModule } from './ref-country/ref-country.module';
import { DealmaintenanceserviceRefPraSectorModule } from './ref-pra-sector/ref-pra-sector.module';
import { DealmaintenanceserviceDealModule } from './deal/deal.module';
import { DealmaintenanceserviceRefFacilityTypeModule } from './ref-facility-type/ref-facility-type.module';
import { DealmaintenanceserviceRefBookingEntityModule } from './ref-booking-entity/ref-booking-entity.module';
import { DealmaintenanceserviceRefSeniorityModule } from './ref-seniority/ref-seniority.module';
import { DealmaintenanceserviceRefCovenantModule } from './ref-covenant/ref-covenant.module';
import { DealmaintenanceserviceTrancheModule } from './tranche/tranche.module';
import { DealmaintenanceserviceTrancheMarginModule } from './tranche-margin/tranche-margin.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        DealmaintenanceserviceRefSyndicationTeamModule,
        DealmaintenanceserviceRefDealStatusModule,
        DealmaintenanceserviceRefOriginationTeamModule,
        DealmaintenanceserviceRefLineOfBusinessModule,
        DealmaintenanceserviceRefRecourseToClientModule,
        DealmaintenanceserviceRefRatingCrrModule,
        DealmaintenanceserviceRefRatingMoodysModule,
        DealmaintenanceserviceRefRatingSAndPModule,
        DealmaintenanceserviceRefRatingFitchModule,
        DealmaintenanceserviceRefCountryModule,
        DealmaintenanceserviceRefPraSectorModule,
        DealmaintenanceserviceDealModule,
        DealmaintenanceserviceRefFacilityTypeModule,
        DealmaintenanceserviceRefBookingEntityModule,
        DealmaintenanceserviceRefSeniorityModule,
        DealmaintenanceserviceRefCovenantModule,
        DealmaintenanceserviceTrancheModule,
        DealmaintenanceserviceTrancheMarginModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DealmaintenanceserviceEntityModule {}
