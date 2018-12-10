import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefRatingCrr } from 'app/shared/model/ref-rating-crr.model';
import { RefRatingCrrService } from './ref-rating-crr.service';

@Component({
    selector: 'jhi-ref-rating-crr-delete-dialog',
    templateUrl: './ref-rating-crr-delete-dialog.component.html'
})
export class RefRatingCrrDeleteDialogComponent {
    refRatingCrr: IRefRatingCrr;

    constructor(
        private refRatingCrrService: RefRatingCrrService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refRatingCrrService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refRatingCrrListModification',
                content: 'Deleted an refRatingCrr'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-rating-crr-delete-popup',
    template: ''
})
export class RefRatingCrrDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refRatingCrr }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefRatingCrrDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.refRatingCrr = refRatingCrr;
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
