import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefRatingCrr } from 'app/shared/model/ref-rating-crr.model';

@Component({
    selector: 'jhi-ref-rating-crr-detail',
    templateUrl: './ref-rating-crr-detail.component.html'
})
export class RefRatingCrrDetailComponent implements OnInit {
    refRatingCrr: IRefRatingCrr;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refRatingCrr }) => {
            this.refRatingCrr = refRatingCrr;
        });
    }

    previousState() {
        window.history.back();
    }
}
