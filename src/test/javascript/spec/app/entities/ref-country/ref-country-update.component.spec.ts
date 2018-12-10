/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefCountryUpdateComponent } from 'app/entities/ref-country/ref-country-update.component';
import { RefCountryService } from 'app/entities/ref-country/ref-country.service';
import { RefCountry } from 'app/shared/model/ref-country.model';

describe('Component Tests', () => {
    describe('RefCountry Management Update Component', () => {
        let comp: RefCountryUpdateComponent;
        let fixture: ComponentFixture<RefCountryUpdateComponent>;
        let service: RefCountryService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefCountryUpdateComponent]
            })
                .overrideTemplate(RefCountryUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefCountryUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefCountryService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefCountry(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refCountry = entity;
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
                    const entity = new RefCountry();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refCountry = entity;
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
