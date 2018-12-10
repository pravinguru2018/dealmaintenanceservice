/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefRecourseToClientUpdateComponent } from 'app/entities/ref-recourse-to-client/ref-recourse-to-client-update.component';
import { RefRecourseToClientService } from 'app/entities/ref-recourse-to-client/ref-recourse-to-client.service';
import { RefRecourseToClient } from 'app/shared/model/ref-recourse-to-client.model';

describe('Component Tests', () => {
    describe('RefRecourseToClient Management Update Component', () => {
        let comp: RefRecourseToClientUpdateComponent;
        let fixture: ComponentFixture<RefRecourseToClientUpdateComponent>;
        let service: RefRecourseToClientService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefRecourseToClientUpdateComponent]
            })
                .overrideTemplate(RefRecourseToClientUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefRecourseToClientUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefRecourseToClientService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefRecourseToClient(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refRecourseToClient = entity;
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
                    const entity = new RefRecourseToClient();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refRecourseToClient = entity;
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
