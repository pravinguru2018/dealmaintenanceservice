import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefOriginationTeam } from 'app/shared/model/ref-origination-team.model';

@Component({
    selector: 'jhi-ref-origination-team-detail',
    templateUrl: './ref-origination-team-detail.component.html'
})
export class RefOriginationTeamDetailComponent implements OnInit {
    refOriginationTeam: IRefOriginationTeam;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refOriginationTeam }) => {
            this.refOriginationTeam = refOriginationTeam;
        });
    }

    previousState() {
        window.history.back();
    }
}
