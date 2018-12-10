import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefOriginationTeam } from 'app/shared/model/ref-origination-team.model';
import { RefOriginationTeamService } from './ref-origination-team.service';

@Component({
    selector: 'jhi-ref-origination-team-delete-dialog',
    templateUrl: './ref-origination-team-delete-dialog.component.html'
})
export class RefOriginationTeamDeleteDialogComponent {
    refOriginationTeam: IRefOriginationTeam;

    constructor(
        private refOriginationTeamService: RefOriginationTeamService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refOriginationTeamService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refOriginationTeamListModification',
                content: 'Deleted an refOriginationTeam'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-origination-team-delete-popup',
    template: ''
})
export class RefOriginationTeamDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refOriginationTeam }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefOriginationTeamDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.refOriginationTeam = refOriginationTeam;
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
