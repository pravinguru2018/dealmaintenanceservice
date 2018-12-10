/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefSeniorityUpdateComponent } from 'app/entities/ref-seniority/ref-seniority-update.component';
import { RefSeniorityService } from 'app/entities/ref-seniority/ref-seniority.service';
import { RefSeniority } from 'app/shared/model/ref-seniority.model';

describe('Component Tests', () => {
    describe('RefSeniority Management Update Component', () => {
        let comp: RefSeniorityUpdateComponent;
        let fixture: ComponentFixture<RefSeniorityUpdateComponent>;
        let service: RefSeniorityService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefSeniorityUpdateComponent]
            })
                .overrideTemplate(RefSeniorityUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefSeniorityUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefSeniorityService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefSeniority(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refSeniority = entity;
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
                    const entity = new RefSeniority();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refSeniority = entity;
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
