import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefRatingSAndP } from 'app/shared/model/ref-rating-s-and-p.model';

@Component({
    selector: 'jhi-ref-rating-s-and-p-detail',
    templateUrl: './ref-rating-s-and-p-detail.component.html'
})
export class RefRatingSAndPDetailComponent implements OnInit {
    refRatingSAndP: IRefRatingSAndP;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refRatingSAndP }) => {
            this.refRatingSAndP = refRatingSAndP;
        });
    }

    previousState() {
        window.history.back();
    }
}
