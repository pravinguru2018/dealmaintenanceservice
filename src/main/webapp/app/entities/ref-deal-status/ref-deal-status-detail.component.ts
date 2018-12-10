import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefDealStatus } from 'app/shared/model/ref-deal-status.model';

@Component({
    selector: 'jhi-ref-deal-status-detail',
    templateUrl: './ref-deal-status-detail.component.html'
})
export class RefDealStatusDetailComponent implements OnInit {
    refDealStatus: IRefDealStatus;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refDealStatus }) => {
            this.refDealStatus = refDealStatus;
        });
    }

    previousState() {
        window.history.back();
    }
}
