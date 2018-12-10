import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefRatingFitch } from 'app/shared/model/ref-rating-fitch.model';
import { RefRatingFitchService } from './ref-rating-fitch.service';

@Component({
    selector: 'jhi-ref-rating-fitch-delete-dialog',
    templateUrl: './ref-rating-fitch-delete-dialog.component.html'
})
export class RefRatingFitchDeleteDialogComponent {
    refRatingFitch: IRefRatingFitch;

    constructor(
        private refRatingFitchService: RefRatingFitchService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refRatingFitchService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refRatingFitchListModification',
                content: 'Deleted an refRatingFitch'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-rating-fitch-delete-popup',
    template: ''
})
export class RefRatingFitchDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refRatingFitch }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefRatingFitchDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.refRatingFitch = refRatingFitch;
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
