/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefBookingEntityDeleteDialogComponent } from 'app/entities/ref-booking-entity/ref-booking-entity-delete-dialog.component';
import { RefBookingEntityService } from 'app/entities/ref-booking-entity/ref-booking-entity.service';

describe('Component Tests', () => {
    describe('RefBookingEntity Management Delete Component', () => {
        let comp: RefBookingEntityDeleteDialogComponent;
        let fixture: ComponentFixture<RefBookingEntityDeleteDialogComponent>;
        let service: RefBookingEntityService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefBookingEntityDeleteDialogComponent]
            })
                .overrideTemplate(RefBookingEntityDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefBookingEntityDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefBookingEntityService);
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
