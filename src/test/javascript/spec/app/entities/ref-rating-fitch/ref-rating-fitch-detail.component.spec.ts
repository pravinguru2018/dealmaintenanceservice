/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefRatingFitchDetailComponent } from 'app/entities/ref-rating-fitch/ref-rating-fitch-detail.component';
import { RefRatingFitch } from 'app/shared/model/ref-rating-fitch.model';

describe('Component Tests', () => {
    describe('RefRatingFitch Management Detail Component', () => {
        let comp: RefRatingFitchDetailComponent;
        let fixture: ComponentFixture<RefRatingFitchDetailComponent>;
        const route = ({ data: of({ refRatingFitch: new RefRatingFitch(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefRatingFitchDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefRatingFitchDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefRatingFitchDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refRatingFitch).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
