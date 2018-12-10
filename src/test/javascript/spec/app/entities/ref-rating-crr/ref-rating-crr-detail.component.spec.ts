/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefRatingCrrDetailComponent } from 'app/entities/ref-rating-crr/ref-rating-crr-detail.component';
import { RefRatingCrr } from 'app/shared/model/ref-rating-crr.model';

describe('Component Tests', () => {
    describe('RefRatingCrr Management Detail Component', () => {
        let comp: RefRatingCrrDetailComponent;
        let fixture: ComponentFixture<RefRatingCrrDetailComponent>;
        const route = ({ data: of({ refRatingCrr: new RefRatingCrr(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefRatingCrrDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefRatingCrrDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefRatingCrrDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refRatingCrr).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
