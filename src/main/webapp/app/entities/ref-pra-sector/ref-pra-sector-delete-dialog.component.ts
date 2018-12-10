import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefPraSector } from 'app/shared/model/ref-pra-sector.model';
import { RefPraSectorService } from './ref-pra-sector.service';

@Component({
    selector: 'jhi-ref-pra-sector-delete-dialog',
    templateUrl: './ref-pra-sector-delete-dialog.component.html'
})
export class RefPraSectorDeleteDialogComponent {
    refPraSector: IRefPraSector;

    constructor(
        private refPraSectorService: RefPraSectorService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refPraSectorService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refPraSectorListModification',
                content: 'Deleted an refPraSector'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-pra-sector-delete-popup',
    template: ''
})
export class RefPraSectorDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refPraSector }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefPraSectorDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.refPraSector = refPraSector;
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
