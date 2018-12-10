/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefFacilityTypeUpdateComponent } from 'app/entities/ref-facility-type/ref-facility-type-update.component';
import { RefFacilityTypeService } from 'app/entities/ref-facility-type/ref-facility-type.service';
import { RefFacilityType } from 'app/shared/model/ref-facility-type.model';

describe('Component Tests', () => {
    describe('RefFacilityType Management Update Component', () => {
        let comp: RefFacilityTypeUpdateComponent;
        let fixture: ComponentFixture<RefFacilityTypeUpdateComponent>;
        let service: RefFacilityTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefFacilityTypeUpdateComponent]
            })
                .overrideTemplate(RefFacilityTypeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefFacilityTypeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefFacilityTypeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefFacilityType(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refFacilityType = entity;
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
                    const entity = new RefFacilityType();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refFacilityType = entity;
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
