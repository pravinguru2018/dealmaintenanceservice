/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefRecourseToClientDeleteDialogComponent } from 'app/entities/ref-recourse-to-client/ref-recourse-to-client-delete-dialog.component';
import { RefRecourseToClientService } from 'app/entities/ref-recourse-to-client/ref-recourse-to-client.service';

describe('Component Tests', () => {
    describe('RefRecourseToClient Management Delete Component', () => {
        let comp: RefRecourseToClientDeleteDialogComponent;
        let fixture: ComponentFixture<RefRecourseToClientDeleteDialogComponent>;
        let service: RefRecourseToClientService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefRecourseToClientDeleteDialogComponent]
            })
                .overrideTemplate(RefRecourseToClientDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefRecourseToClientDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefRecourseToClientService);
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
