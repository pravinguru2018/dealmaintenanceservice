/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefSeniorityDeleteDialogComponent } from 'app/entities/ref-seniority/ref-seniority-delete-dialog.component';
import { RefSeniorityService } from 'app/entities/ref-seniority/ref-seniority.service';

describe('Component Tests', () => {
    describe('RefSeniority Management Delete Component', () => {
        let comp: RefSeniorityDeleteDialogComponent;
        let fixture: ComponentFixture<RefSeniorityDeleteDialogComponent>;
        let service: RefSeniorityService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefSeniorityDeleteDialogComponent]
            })
                .overrideTemplate(RefSeniorityDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefSeniorityDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefSeniorityService);
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
