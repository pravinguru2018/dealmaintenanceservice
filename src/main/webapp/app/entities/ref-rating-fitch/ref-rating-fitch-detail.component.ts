import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefRatingFitch } from 'app/shared/model/ref-rating-fitch.model';

@Component({
    selector: 'jhi-ref-rating-fitch-detail',
    templateUrl: './ref-rating-fitch-detail.component.html'
})
export class RefRatingFitchDetailComponent implements OnInit {
    refRatingFitch: IRefRatingFitch;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refRatingFitch }) => {
            this.refRatingFitch = refRatingFitch;
        });
    }

    previousState() {
        window.history.back();
    }
}
