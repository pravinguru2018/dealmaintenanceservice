import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefSyndicationTeam } from 'app/shared/model/ref-syndication-team.model';

@Component({
    selector: 'jhi-ref-syndication-team-detail',
    templateUrl: './ref-syndication-team-detail.component.html'
})
export class RefSyndicationTeamDetailComponent implements OnInit {
    refSyndicationTeam: IRefSyndicationTeam;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refSyndicationTeam }) => {
            this.refSyndicationTeam = refSyndicationTeam;
        });
    }

    previousState() {
        window.history.back();
    }
}
