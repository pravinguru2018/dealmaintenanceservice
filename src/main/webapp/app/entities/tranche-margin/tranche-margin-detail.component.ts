import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITrancheMargin } from 'app/shared/model/tranche-margin.model';

@Component({
    selector: 'jhi-tranche-margin-detail',
    templateUrl: './tranche-margin-detail.component.html'
})
export class TrancheMarginDetailComponent implements OnInit {
    trancheMargin: ITrancheMargin;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ trancheMargin }) => {
            this.trancheMargin = trancheMargin;
        });
    }

    previousState() {
        window.history.back();
    }
}
