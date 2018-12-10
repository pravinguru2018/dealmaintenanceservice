/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefDealStatusDeleteDialogComponent } from 'app/entities/ref-deal-status/ref-deal-status-delete-dialog.component';
import { RefDealStatusService } from 'app/entities/ref-deal-status/ref-deal-status.service';

describe('Component Tests', () => {
    describe('RefDealStatus Management Delete Component', () => {
        let comp: RefDealStatusDeleteDialogComponent;
        let fixture: ComponentFixture<RefDealStatusDeleteDialogComponent>;
        let service: RefDealStatusService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefDealStatusDeleteDialogComponent]
            })
                .overrideTemplate(RefDealStatusDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefDealStatusDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefDealStatusService);
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
