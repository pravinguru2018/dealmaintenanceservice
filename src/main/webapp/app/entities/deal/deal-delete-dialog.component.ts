import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDeal } from 'app/shared/model/deal.model';
import { DealService } from './deal.service';

@Component({
    selector: 'jhi-deal-delete-dialog',
    templateUrl: './deal-delete-dialog.component.html'
})
export class DealDeleteDialogComponent {
    deal: IDeal;

    constructor(private dealService: DealService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.dealService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'dealListModification',
                content: 'Deleted an deal'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-deal-delete-popup',
    template: ''
})
export class DealDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ deal }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DealDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.deal = deal;
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
