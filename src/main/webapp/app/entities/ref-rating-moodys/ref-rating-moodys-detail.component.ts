import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefRatingMoodys } from 'app/shared/model/ref-rating-moodys.model';

@Component({
    selector: 'jhi-ref-rating-moodys-detail',
    templateUrl: './ref-rating-moodys-detail.component.html'
})
export class RefRatingMoodysDetailComponent implements OnInit {
    refRatingMoodys: IRefRatingMoodys;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refRatingMoodys }) => {
            this.refRatingMoodys = refRatingMoodys;
        });
    }

    previousState() {
        window.history.back();
    }
}
