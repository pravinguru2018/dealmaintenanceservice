/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefLineOfBusinessDeleteDialogComponent } from 'app/entities/ref-line-of-business/ref-line-of-business-delete-dialog.component';
import { RefLineOfBusinessService } from 'app/entities/ref-line-of-business/ref-line-of-business.service';

describe('Component Tests', () => {
    describe('RefLineOfBusiness Management Delete Component', () => {
        let comp: RefLineOfBusinessDeleteDialogComponent;
        let fixture: ComponentFixture<RefLineOfBusinessDeleteDialogComponent>;
        let service: RefLineOfBusinessService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefLineOfBusinessDeleteDialogComponent]
            })
                .overrideTemplate(RefLineOfBusinessDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefLineOfBusinessDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefLineOfBusinessService);
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
