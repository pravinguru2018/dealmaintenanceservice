import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRefOriginationTeam } from 'app/shared/model/ref-origination-team.model';
import { Principal } from 'app/core';
import { RefOriginationTeamService } from './ref-origination-team.service';

@Component({
    selector: 'jhi-ref-origination-team',
    templateUrl: './ref-origination-team.component.html'
})
export class RefOriginationTeamComponent implements OnInit, OnDestroy {
    refOriginationTeams: IRefOriginationTeam[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private refOriginationTeamService: RefOriginationTeamService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.refOriginationTeamService.query().subscribe(
            (res: HttpResponse<IRefOriginationTeam[]>) => {
                this.refOriginationTeams = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRefOriginationTeams();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRefOriginationTeam) {
        return item.id;
    }

    registerChangeInRefOriginationTeams() {
        this.eventSubscriber = this.eventManager.subscribe('refOriginationTeamListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
