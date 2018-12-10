import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRefSeniority } from 'app/shared/model/ref-seniority.model';
import { Principal } from 'app/core';
import { RefSeniorityService } from './ref-seniority.service';

@Component({
    selector: 'jhi-ref-seniority',
    templateUrl: './ref-seniority.component.html'
})
export class RefSeniorityComponent implements OnInit, OnDestroy {
    refSeniorities: IRefSeniority[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private refSeniorityService: RefSeniorityService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.refSeniorityService.query().subscribe(
            (res: HttpResponse<IRefSeniority[]>) => {
                this.refSeniorities = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRefSeniorities();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRefSeniority) {
        return item.id;
    }

    registerChangeInRefSeniorities() {
        this.eventSubscriber = this.eventManager.subscribe('refSeniorityListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
