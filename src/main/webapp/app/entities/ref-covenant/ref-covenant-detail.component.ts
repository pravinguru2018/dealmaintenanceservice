import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefCovenant } from 'app/shared/model/ref-covenant.model';

@Component({
    selector: 'jhi-ref-covenant-detail',
    templateUrl: './ref-covenant-detail.component.html'
})
export class RefCovenantDetailComponent implements OnInit {
    refCovenant: IRefCovenant;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refCovenant }) => {
            this.refCovenant = refCovenant;
        });
    }

    previousState() {
        window.history.back();
    }
}
