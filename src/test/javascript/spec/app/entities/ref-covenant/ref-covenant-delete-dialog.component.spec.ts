/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefCovenantDeleteDialogComponent } from 'app/entities/ref-covenant/ref-covenant-delete-dialog.component';
import { RefCovenantService } from 'app/entities/ref-covenant/ref-covenant.service';

describe('Component Tests', () => {
    describe('RefCovenant Management Delete Component', () => {
        let comp: RefCovenantDeleteDialogComponent;
        let fixture: ComponentFixture<RefCovenantDeleteDialogComponent>;
        let service: RefCovenantService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefCovenantDeleteDialogComponent]
            })
                .overrideTemplate(RefCovenantDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefCovenantDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefCovenantService);
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
