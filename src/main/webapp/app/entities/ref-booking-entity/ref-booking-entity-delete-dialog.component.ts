import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefBookingEntity } from 'app/shared/model/ref-booking-entity.model';
import { RefBookingEntityService } from './ref-booking-entity.service';

@Component({
    selector: 'jhi-ref-booking-entity-delete-dialog',
    templateUrl: './ref-booking-entity-delete-dialog.component.html'
})
export class RefBookingEntityDeleteDialogComponent {
    refBookingEntity: IRefBookingEntity;

    constructor(
        private refBookingEntityService: RefBookingEntityService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refBookingEntityService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refBookingEntityListModification',
                content: 'Deleted an refBookingEntity'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-booking-entity-delete-popup',
    template: ''
})
export class RefBookingEntityDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refBookingEntity }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefBookingEntityDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.refBookingEntity = refBookingEntity;
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
