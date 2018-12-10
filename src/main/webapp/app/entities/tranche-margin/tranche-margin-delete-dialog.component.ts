import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITrancheMargin } from 'app/shared/model/tranche-margin.model';
import { TrancheMarginService } from './tranche-margin.service';

@Component({
    selector: 'jhi-tranche-margin-delete-dialog',
    templateUrl: './tranche-margin-delete-dialog.component.html'
})
export class TrancheMarginDeleteDialogComponent {
    trancheMargin: ITrancheMargin;

    constructor(
        private trancheMarginService: TrancheMarginService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.trancheMarginService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'trancheMarginListModification',
                content: 'Deleted an trancheMargin'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tranche-margin-delete-popup',
    template: ''
})
export class TrancheMarginDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ trancheMargin }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TrancheMarginDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.trancheMargin = trancheMargin;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
