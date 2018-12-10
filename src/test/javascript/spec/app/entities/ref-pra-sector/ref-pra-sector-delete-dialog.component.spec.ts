/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefPraSectorDeleteDialogComponent } from 'app/entities/ref-pra-sector/ref-pra-sector-delete-dialog.component';
import { RefPraSectorService } from 'app/entities/ref-pra-sector/ref-pra-sector.service';

describe('Component Tests', () => {
    describe('RefPraSector Management Delete Component', () => {
        let comp: RefPraSectorDeleteDialogComponent;
        let fixture: ComponentFixture<RefPraSectorDeleteDialogComponent>;
        let service: RefPraSectorService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefPraSectorDeleteDialogComponent]
            })
                .overrideTemplate(RefPraSectorDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefPraSectorDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefPraSectorService);
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
