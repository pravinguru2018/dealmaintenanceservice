import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefRatingMoodys } from 'app/shared/model/ref-rating-moodys.model';
import { RefRatingMoodysService } from './ref-rating-moodys.service';

@Component({
    selector: 'jhi-ref-rating-moodys-delete-dialog',
    templateUrl: './ref-rating-moodys-delete-dialog.component.html'
})
export class RefRatingMoodysDeleteDialogComponent {
    refRatingMoodys: IRefRatingMoodys;

    constructor(
        private refRatingMoodysService: RefRatingMoodysService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refRatingMoodysService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refRatingMoodysListModification',
                content: 'Deleted an refRatingMoodys'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-rating-moodys-delete-popup',
    template: ''
})
export class RefRatingMoodysDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refRatingMoodys }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefRatingMoodysDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.refRatingMoodys = refRatingMoodys;
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
