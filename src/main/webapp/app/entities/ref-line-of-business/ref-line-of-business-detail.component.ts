import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefLineOfBusiness } from 'app/shared/model/ref-line-of-business.model';

@Component({
    selector: 'jhi-ref-line-of-business-detail',
    templateUrl: './ref-line-of-business-detail.component.html'
})
export class RefLineOfBusinessDetailComponent implements OnInit {
    refLineOfBusiness: IRefLineOfBusiness;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refLineOfBusiness }) => {
            this.refLineOfBusiness = refLineOfBusiness;
        });
    }

    previousState() {
        window.history.back();
    }
}
