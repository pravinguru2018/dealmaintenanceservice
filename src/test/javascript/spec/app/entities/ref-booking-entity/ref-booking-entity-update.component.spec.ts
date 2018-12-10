/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefBookingEntityUpdateComponent } from 'app/entities/ref-booking-entity/ref-booking-entity-update.component';
import { RefBookingEntityService } from 'app/entities/ref-booking-entity/ref-booking-entity.service';
import { RefBookingEntity } from 'app/shared/model/ref-booking-entity.model';

describe('Component Tests', () => {
    describe('RefBookingEntity Management Update Component', () => {
        let comp: RefBookingEntityUpdateComponent;
        let fixture: ComponentFixture<RefBookingEntityUpdateComponent>;
        let service: RefBookingEntityService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefBookingEntityUpdateComponent]
            })
                .overrideTemplate(RefBookingEntityUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefBookingEntityUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefBookingEntityService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefBookingEntity(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refBookingEntity = entity;
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
                    const entity = new RefBookingEntity();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refBookingEntity = entity;
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
