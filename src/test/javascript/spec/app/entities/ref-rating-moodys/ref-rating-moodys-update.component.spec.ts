/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefRatingMoodysUpdateComponent } from 'app/entities/ref-rating-moodys/ref-rating-moodys-update.component';
import { RefRatingMoodysService } from 'app/entities/ref-rating-moodys/ref-rating-moodys.service';
import { RefRatingMoodys } from 'app/shared/model/ref-rating-moodys.model';

describe('Component Tests', () => {
    describe('RefRatingMoodys Management Update Component', () => {
        let comp: RefRatingMoodysUpdateComponent;
        let fixture: ComponentFixture<RefRatingMoodysUpdateComponent>;
        let service: RefRatingMoodysService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefRatingMoodysUpdateComponent]
            })
                .overrideTemplate(RefRatingMoodysUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefRatingMoodysUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefRatingMoodysService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefRatingMoodys(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refRatingMoodys = entity;
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
                    const entity = new RefRatingMoodys();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refRatingMoodys = entity;
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
