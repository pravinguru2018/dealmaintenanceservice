import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefSeniority } from 'app/shared/model/ref-seniority.model';
import { RefSeniorityService } from './ref-seniority.service';

@Component({
    selector: 'jhi-ref-seniority-delete-dialog',
    templateUrl: './ref-seniority-delete-dialog.component.html'
})
export class RefSeniorityDeleteDialogComponent {
    refSeniority: IRefSeniority;

    constructor(
        private refSeniorityService: RefSeniorityService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refSeniorityService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refSeniorityListModification',
                content: 'Deleted an refSeniority'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-seniority-delete-popup',
    template: ''
})
export class RefSeniorityDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refSeniority }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefSeniorityDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.refSeniority = refSeniority;
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
