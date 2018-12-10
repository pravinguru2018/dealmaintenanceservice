/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefRatingSAndPDeleteDialogComponent } from 'app/entities/ref-rating-s-and-p/ref-rating-s-and-p-delete-dialog.component';
import { RefRatingSAndPService } from 'app/entities/ref-rating-s-and-p/ref-rating-s-and-p.service';

describe('Component Tests', () => {
    describe('RefRatingSAndP Management Delete Component', () => {
        let comp: RefRatingSAndPDeleteDialogComponent;
        let fixture: ComponentFixture<RefRatingSAndPDeleteDialogComponent>;
        let service: RefRatingSAndPService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefRatingSAndPDeleteDialogComponent]
            })
                .overrideTemplate(RefRatingSAndPDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefRatingSAndPDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefRatingSAndPService);
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
