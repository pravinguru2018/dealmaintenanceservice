/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefOriginationTeamDeleteDialogComponent } from 'app/entities/ref-origination-team/ref-origination-team-delete-dialog.component';
import { RefOriginationTeamService } from 'app/entities/ref-origination-team/ref-origination-team.service';

describe('Component Tests', () => {
    describe('RefOriginationTeam Management Delete Component', () => {
        let comp: RefOriginationTeamDeleteDialogComponent;
        let fixture: ComponentFixture<RefOriginationTeamDeleteDialogComponent>;
        let service: RefOriginationTeamService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefOriginationTeamDeleteDialogComponent]
            })
                .overrideTemplate(RefOriginationTeamDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefOriginationTeamDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefOriginationTeamService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
