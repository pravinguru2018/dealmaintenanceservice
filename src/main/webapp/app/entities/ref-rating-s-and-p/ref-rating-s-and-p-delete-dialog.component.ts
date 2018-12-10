import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefRatingSAndP } from 'app/shared/model/ref-rating-s-and-p.model';
import { RefRatingSAndPService } from './ref-rating-s-and-p.service';

@Component({
    selector: 'jhi-ref-rating-s-and-p-delete-dialog',
    templateUrl: './ref-rating-s-and-p-delete-dialog.component.html'
})
export class RefRatingSAndPDeleteDialogComponent {
    refRatingSAndP: IRefRatingSAndP;

    constructor(
        private refRatingSAndPService: RefRatingSAndPService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refRatingSAndPService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refRatingSAndPListModification',
                content: 'Deleted an refRatingSAndP'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-rating-s-and-p-delete-popup',
    template: ''
})
export class RefRatingSAndPDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refRatingSAndP }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefRatingSAndPDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.refRatingSAndP = refRatingSAndP;
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
