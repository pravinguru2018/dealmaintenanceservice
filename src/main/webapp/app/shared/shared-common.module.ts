import { NgModule } from '@angular/core';

import { DealmaintenanceserviceSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [DealmaintenanceserviceSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [DealmaintenanceserviceSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class DealmaintenanceserviceSharedCommonModule {}
