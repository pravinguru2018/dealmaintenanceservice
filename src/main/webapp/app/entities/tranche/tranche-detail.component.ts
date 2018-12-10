import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITranche } from 'app/shared/model/tranche.model';

@Component({
    selector: 'jhi-tranche-detail',
    templateUrl: './tranche-detail.component.html'
})
export class TrancheDetailComponent implements OnInit {
    tranche: ITranche;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tranche }) => {
            this.tranche = tranche;
        });
    }

    previousState() {
        window.history.back();
    }
}
