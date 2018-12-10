import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRefCovenant } from 'app/shared/model/ref-covenant.model';
import { Principal } from 'app/core';
import { RefCovenantService } from './ref-covenant.service';

@Component({
    selector: 'jhi-ref-covenant',
    templateUrl: './ref-covenant.component.html'
})
export class RefCovenantComponent implements OnInit, OnDestroy {
    refCovenants: IRefCovenant[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private refCovenantService: RefCovenantService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.refCovenantService.query().subscribe(
            (res: HttpResponse<IRefCovenant[]>) => {
                this.refCovenants = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRefCovenants();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRefCovenant) {
        return item.id;
    }

    registerChangeInRefCovenants() {
        this.eventSubscriber = this.eventManager.subscribe('refCovenantListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
