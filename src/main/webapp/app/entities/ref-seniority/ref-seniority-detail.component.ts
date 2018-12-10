import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefSeniority } from 'app/shared/model/ref-seniority.model';

@Component({
    selector: 'jhi-ref-seniority-detail',
    templateUrl: './ref-seniority-detail.component.html'
})
export class RefSeniorityDetailComponent implements OnInit {
    refSeniority: IRefSeniority;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refSeniority }) => {
            this.refSeniority = refSeniority;
        });
    }

    previousState() {
        window.history.back();
    }
}
