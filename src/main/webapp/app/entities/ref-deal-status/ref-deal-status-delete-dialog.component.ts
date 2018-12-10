import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefDealStatus } from 'app/shared/model/ref-deal-status.model';
import { RefDealStatusService } from './ref-deal-status.service';

@Component({
    selector: 'jhi-ref-deal-status-delete-dialog',
    templateUrl: './ref-deal-status-delete-dialog.component.html'
})
export class RefDealStatusDeleteDialogComponent {
    refDealStatus: IRefDealStatus;

    constructor(
        private refDealStatusService: RefDealStatusService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refDealStatusService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refDealStatusListModification',
                content: 'Deleted an refDealStatus'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-deal-status-delete-popup',
    template: ''
})
export class RefDealStatusDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refDealStatus }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefDealStatusDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.refDealStatus = refDealStatus;
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
