/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefDealStatusUpdateComponent } from 'app/entities/ref-deal-status/ref-deal-status-update.component';
import { RefDealStatusService } from 'app/entities/ref-deal-status/ref-deal-status.service';
import { RefDealStatus } from 'app/shared/model/ref-deal-status.model';

describe('Component Tests', () => {
    describe('RefDealStatus Management Update Component', () => {
        let comp: RefDealStatusUpdateComponent;
        let fixture: ComponentFixture<RefDealStatusUpdateComponent>;
        let service: RefDealStatusService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefDealStatusUpdateComponent]
            })
                .overrideTemplate(RefDealStatusUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefDealStatusUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefDealStatusService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefDealStatus(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refDealStatus = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefDealStatus();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refDealStatus = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
