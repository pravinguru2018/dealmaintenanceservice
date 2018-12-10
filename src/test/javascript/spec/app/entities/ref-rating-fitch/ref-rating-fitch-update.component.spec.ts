/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefRatingFitchUpdateComponent } from 'app/entities/ref-rating-fitch/ref-rating-fitch-update.component';
import { RefRatingFitchService } from 'app/entities/ref-rating-fitch/ref-rating-fitch.service';
import { RefRatingFitch } from 'app/shared/model/ref-rating-fitch.model';

describe('Component Tests', () => {
    describe('RefRatingFitch Management Update Component', () => {
        let comp: RefRatingFitchUpdateComponent;
        let fixture: ComponentFixture<RefRatingFitchUpdateComponent>;
        let service: RefRatingFitchService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefRatingFitchUpdateComponent]
            })
                .overrideTemplate(RefRatingFitchUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefRatingFitchUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefRatingFitchService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefRatingFitch(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refRatingFitch = entity;
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
                    const entity = new RefRatingFitch();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refRatingFitch = entity;
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
