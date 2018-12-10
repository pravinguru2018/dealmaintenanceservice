/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefCovenantUpdateComponent } from 'app/entities/ref-covenant/ref-covenant-update.component';
import { RefCovenantService } from 'app/entities/ref-covenant/ref-covenant.service';
import { RefCovenant } from 'app/shared/model/ref-covenant.model';

describe('Component Tests', () => {
    describe('RefCovenant Management Update Component', () => {
        let comp: RefCovenantUpdateComponent;
        let fixture: ComponentFixture<RefCovenantUpdateComponent>;
        let service: RefCovenantService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefCovenantUpdateComponent]
            })
                .overrideTemplate(RefCovenantUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefCovenantUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefCovenantService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefCovenant(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refCovenant = entity;
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
                    const entity = new RefCovenant();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refCovenant = entity;
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
