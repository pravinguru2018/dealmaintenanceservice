import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRefSyndicationTeam } from 'app/shared/model/ref-syndication-team.model';
import { Principal } from 'app/core';
import { RefSyndicationTeamService } from './ref-syndication-team.service';

@Component({
    selector: 'jhi-ref-syndication-team',
    templateUrl: './ref-syndication-team.component.html'
})
export class RefSyndicationTeamComponent implements OnInit, OnDestroy {
    refSyndicationTeams: IRefSyndicationTeam[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private refSyndicationTeamService: RefSyndicationTeamService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.refSyndicationTeamService.query().subscribe(
            (res: HttpResponse<IRefSyndicationTeam[]>) => {
                this.refSyndicationTeams = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRefSyndicationTeams();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRefSyndicationTeam) {
        return item.id;
    }

    registerChangeInRefSyndicationTeams() {
        this.eventSubscriber = this.eventManager.subscribe('refSyndicationTeamListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
