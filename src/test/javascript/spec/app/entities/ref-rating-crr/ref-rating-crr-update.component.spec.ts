/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefRatingCrrUpdateComponent } from 'app/entities/ref-rating-crr/ref-rating-crr-update.component';
import { RefRatingCrrService } from 'app/entities/ref-rating-crr/ref-rating-crr.service';
import { RefRatingCrr } from 'app/shared/model/ref-rating-crr.model';

describe('Component Tests', () => {
    describe('RefRatingCrr Management Update Component', () => {
        let comp: RefRatingCrrUpdateComponent;
        let fixture: ComponentFixture<RefRatingCrrUpdateComponent>;
        let service: RefRatingCrrService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefRatingCrrUpdateComponent]
            })
                .overrideTemplate(RefRatingCrrUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefRatingCrrUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefRatingCrrService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefRatingCrr(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refRatingCrr = entity;
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
                    const entity = new RefRatingCrr();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refRatingCrr = entity;
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
