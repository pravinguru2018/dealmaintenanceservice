import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefFacilityType } from 'app/shared/model/ref-facility-type.model';

@Component({
    selector: 'jhi-ref-facility-type-detail',
    templateUrl: './ref-facility-type-detail.component.html'
})
export class RefFacilityTypeDetailComponent implements OnInit {
    refFacilityType: IRefFacilityType;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refFacilityType }) => {
            this.refFacilityType = refFacilityType;
        });
    }

    previousState() {
        window.history.back();
    }
}
