import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefFacilityType } from 'app/shared/model/ref-facility-type.model';
import { RefFacilityTypeService } from './ref-facility-type.service';

@Component({
    selector: 'jhi-ref-facility-type-delete-dialog',
    templateUrl: './ref-facility-type-delete-dialog.component.html'
})
export class RefFacilityTypeDeleteDialogComponent {
    refFacilityType: IRefFacilityType;

    constructor(
        private refFacilityTypeService: RefFacilityTypeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refFacilityTypeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refFacilityTypeListModification',
                content: 'Deleted an refFacilityType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-facility-type-delete-popup',
    template: ''
})
export class RefFacilityTypeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refFacilityType }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefFacilityTypeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.refFacilityType = refFacilityType;
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
