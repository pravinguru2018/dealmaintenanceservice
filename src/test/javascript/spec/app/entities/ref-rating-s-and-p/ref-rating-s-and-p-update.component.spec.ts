/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefRatingSAndPUpdateComponent } from 'app/entities/ref-rating-s-and-p/ref-rating-s-and-p-update.component';
import { RefRatingSAndPService } from 'app/entities/ref-rating-s-and-p/ref-rating-s-and-p.service';
import { RefRatingSAndP } from 'app/shared/model/ref-rating-s-and-p.model';

describe('Component Tests', () => {
    describe('RefRatingSAndP Management Update Component', () => {
        let comp: RefRatingSAndPUpdateComponent;
        let fixture: ComponentFixture<RefRatingSAndPUpdateComponent>;
        let service: RefRatingSAndPService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefRatingSAndPUpdateComponent]
            })
                .overrideTemplate(RefRatingSAndPUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefRatingSAndPUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefRatingSAndPService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefRatingSAndP(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refRatingSAndP = entity;
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
                    const entity = new RefRatingSAndP();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refRatingSAndP = entity;
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
