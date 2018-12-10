/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefLineOfBusinessUpdateComponent } from 'app/entities/ref-line-of-business/ref-line-of-business-update.component';
import { RefLineOfBusinessService } from 'app/entities/ref-line-of-business/ref-line-of-business.service';
import { RefLineOfBusiness } from 'app/shared/model/ref-line-of-business.model';

describe('Component Tests', () => {
    describe('RefLineOfBusiness Management Update Component', () => {
        let comp: RefLineOfBusinessUpdateComponent;
        let fixture: ComponentFixture<RefLineOfBusinessUpdateComponent>;
        let service: RefLineOfBusinessService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefLineOfBusinessUpdateComponent]
            })
                .overrideTemplate(RefLineOfBusinessUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefLineOfBusinessUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefLineOfBusinessService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefLineOfBusiness(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refLineOfBusiness = entity;
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
                    const entity = new RefLineOfBusiness();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refLineOfBusiness = entity;
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
