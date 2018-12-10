/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefRatingCrrDeleteDialogComponent } from 'app/entities/ref-rating-crr/ref-rating-crr-delete-dialog.component';
import { RefRatingCrrService } from 'app/entities/ref-rating-crr/ref-rating-crr.service';

describe('Component Tests', () => {
    describe('RefRatingCrr Management Delete Component', () => {
        let comp: RefRatingCrrDeleteDialogComponent;
        let fixture: ComponentFixture<RefRatingCrrDeleteDialogComponent>;
        let service: RefRatingCrrService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefRatingCrrDeleteDialogComponent]
            })
                .overrideTemplate(RefRatingCrrDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefRatingCrrDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefRatingCrrService);
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
