/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefRatingFitchDeleteDialogComponent } from 'app/entities/ref-rating-fitch/ref-rating-fitch-delete-dialog.component';
import { RefRatingFitchService } from 'app/entities/ref-rating-fitch/ref-rating-fitch.service';

describe('Component Tests', () => {
    describe('RefRatingFitch Management Delete Component', () => {
        let comp: RefRatingFitchDeleteDialogComponent;
        let fixture: ComponentFixture<RefRatingFitchDeleteDialogComponent>;
        let service: RefRatingFitchService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefRatingFitchDeleteDialogComponent]
            })
                .overrideTemplate(RefRatingFitchDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefRatingFitchDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefRatingFitchService);
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
