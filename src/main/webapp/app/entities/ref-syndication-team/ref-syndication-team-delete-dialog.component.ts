import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefSyndicationTeam } from 'app/shared/model/ref-syndication-team.model';
import { RefSyndicationTeamService } from './ref-syndication-team.service';

@Component({
    selector: 'jhi-ref-syndication-team-delete-dialog',
    templateUrl: './ref-syndication-team-delete-dialog.component.html'
})
export class RefSyndicationTeamDeleteDialogComponent {
    refSyndicationTeam: IRefSyndicationTeam;

    constructor(
        private refSyndicationTeamService: RefSyndicationTeamService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refSyndicationTeamService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refSyndicationTeamListModification',
                content: 'Deleted an refSyndicationTeam'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-syndication-team-delete-popup',
    template: ''
})
export class RefSyndicationTeamDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refSyndicationTeam }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefSyndicationTeamDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.refSyndicationTeam = refSyndicationTeam;
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
