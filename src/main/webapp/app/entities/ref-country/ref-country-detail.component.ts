import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefCountry } from 'app/shared/model/ref-country.model';

@Component({
    selector: 'jhi-ref-country-detail',
    templateUrl: './ref-country-detail.component.html'
})
export class RefCountryDetailComponent implements OnInit {
    refCountry: IRefCountry;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refCountry }) => {
            this.refCountry = refCountry;
        });
    }

    previousState() {
        window.history.back();
    }
}
