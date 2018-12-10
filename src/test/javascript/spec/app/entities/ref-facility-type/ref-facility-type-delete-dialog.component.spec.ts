/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefFacilityTypeDeleteDialogComponent } from 'app/entities/ref-facility-type/ref-facility-type-delete-dialog.component';
import { RefFacilityTypeService } from 'app/entities/ref-facility-type/ref-facility-type.service';

describe('Component Tests', () => {
    describe('RefFacilityType Management Delete Component', () => {
        let comp: RefFacilityTypeDeleteDialogComponent;
        let fixture: ComponentFixture<RefFacilityTypeDeleteDialogComponent>;
        let service: RefFacilityTypeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefFacilityTypeDeleteDialogComponent]
            })
                .overrideTemplate(RefFacilityTypeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefFacilityTypeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefFacilityTypeService);
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
