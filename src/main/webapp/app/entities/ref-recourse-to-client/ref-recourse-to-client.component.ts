import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRefRecourseToClient } from 'app/shared/model/ref-recourse-to-client.model';
import { Principal } from 'app/core';
import { RefRecourseToClientService } from './ref-recourse-to-client.service';

@Component({
    selector: 'jhi-ref-recourse-to-client',
    templateUrl: './ref-recourse-to-client.component.html'
})
export class RefRecourseToClientComponent implements OnInit, OnDestroy {
    refRecourseToClients: IRefRecourseToClient[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private refRecourseToClientService: RefRecourseToClientService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.refRecourseToClientService.query().subscribe(
            (res: HttpResponse<IRefRecourseToClient[]>) => {
                this.refRecourseToClients = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRefRecourseToClients();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRefRecourseToClient) {
        return item.id;
    }

    registerChangeInRefRecourseToClients() {
        this.eventSubscriber = this.eventManager.subscribe('refRecourseToClientListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
