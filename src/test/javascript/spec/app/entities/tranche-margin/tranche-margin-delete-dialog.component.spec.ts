/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { TrancheMarginDeleteDialogComponent } from 'app/entities/tranche-margin/tranche-margin-delete-dialog.component';
import { TrancheMarginService } from 'app/entities/tranche-margin/tranche-margin.service';

describe('Component Tests', () => {
    describe('TrancheMargin Management Delete Component', () => {
        let comp: TrancheMarginDeleteDialogComponent;
        let fixture: ComponentFixture<TrancheMarginDeleteDialogComponent>;
        let service: TrancheMarginService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [TrancheMarginDeleteDialogComponent]
            })
                .overrideTemplate(TrancheMarginDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TrancheMarginDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TrancheMarginService);
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
