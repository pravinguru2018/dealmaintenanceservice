import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefCovenant } from 'app/shared/model/ref-covenant.model';
import { RefCovenantService } from './ref-covenant.service';

@Component({
    selector: 'jhi-ref-covenant-delete-dialog',
    templateUrl: './ref-covenant-delete-dialog.component.html'
})
export class RefCovenantDeleteDialogComponent {
    refCovenant: IRefCovenant;

    constructor(
        private refCovenantService: RefCovenantService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refCovenantService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refCovenantListModification',
                content: 'Deleted an refCovenant'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-covenant-delete-popup',
    template: ''
})
export class RefCovenantDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refCovenant }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefCovenantDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.refCovenant = refCovenant;
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
