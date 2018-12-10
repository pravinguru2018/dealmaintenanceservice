import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefCountry } from 'app/shared/model/ref-country.model';
import { RefCountryService } from './ref-country.service';

@Component({
    selector: 'jhi-ref-country-delete-dialog',
    templateUrl: './ref-country-delete-dialog.component.html'
})
export class RefCountryDeleteDialogComponent {
    refCountry: IRefCountry;

    constructor(private refCountryService: RefCountryService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refCountryService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refCountryListModification',
                content: 'Deleted an refCountry'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-country-delete-popup',
    template: ''
})
export class RefCountryDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refCountry }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefCountryDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.refCountry = refCountry;
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
