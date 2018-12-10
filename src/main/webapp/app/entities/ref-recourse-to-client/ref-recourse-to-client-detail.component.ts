import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefRecourseToClient } from 'app/shared/model/ref-recourse-to-client.model';

@Component({
    selector: 'jhi-ref-recourse-to-client-detail',
    templateUrl: './ref-recourse-to-client-detail.component.html'
})
export class RefRecourseToClientDetailComponent implements OnInit {
    refRecourseToClient: IRefRecourseToClient;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refRecourseToClient }) => {
            this.refRecourseToClient = refRecourseToClient;
        });
    }

    previousState() {
        window.history.back();
    }
}
