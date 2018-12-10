import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRefFacilityType } from 'app/shared/model/ref-facility-type.model';
import { Principal } from 'app/core';
import { RefFacilityTypeService } from './ref-facility-type.service';

@Component({
    selector: 'jhi-ref-facility-type',
    templateUrl: './ref-facility-type.component.html'
})
export class RefFacilityTypeComponent implements OnInit, OnDestroy {
    refFacilityTypes: IRefFacilityType[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private refFacilityTypeService: RefFacilityTypeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.refFacilityTypeService.query().subscribe(
            (res: HttpResponse<IRefFacilityType[]>) => {
                this.refFacilityTypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRefFacilityTypes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRefFacilityType) {
        return item.id;
    }

    registerChangeInRefFacilityTypes() {
        this.eventSubscriber = this.eventManager.subscribe('refFacilityTypeListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
