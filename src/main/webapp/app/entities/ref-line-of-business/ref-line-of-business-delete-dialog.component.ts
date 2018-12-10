import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefLineOfBusiness } from 'app/shared/model/ref-line-of-business.model';
import { RefLineOfBusinessService } from './ref-line-of-business.service';

@Component({
    selector: 'jhi-ref-line-of-business-delete-dialog',
    templateUrl: './ref-line-of-business-delete-dialog.component.html'
})
export class RefLineOfBusinessDeleteDialogComponent {
    refLineOfBusiness: IRefLineOfBusiness;

    constructor(
        private refLineOfBusinessService: RefLineOfBusinessService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refLineOfBusinessService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refLineOfBusinessListModification',
                content: 'Deleted an refLineOfBusiness'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-line-of-business-delete-popup',
    template: ''
})
export class RefLineOfBusinessDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refLineOfBusiness }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefLineOfBusinessDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.refLineOfBusiness = refLineOfBusiness;
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
