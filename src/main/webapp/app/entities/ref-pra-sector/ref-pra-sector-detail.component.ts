import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefPraSector } from 'app/shared/model/ref-pra-sector.model';

@Component({
    selector: 'jhi-ref-pra-sector-detail',
    templateUrl: './ref-pra-sector-detail.component.html'
})
export class RefPraSectorDetailComponent implements OnInit {
    refPraSector: IRefPraSector;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refPraSector }) => {
            this.refPraSector = refPraSector;
        });
    }

    previousState() {
        window.history.back();
    }
}
