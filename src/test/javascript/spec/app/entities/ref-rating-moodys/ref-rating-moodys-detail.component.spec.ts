/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefRatingMoodysDetailComponent } from 'app/entities/ref-rating-moodys/ref-rating-moodys-detail.component';
import { RefRatingMoodys } from 'app/shared/model/ref-rating-moodys.model';

describe('Component Tests', () => {
    describe('RefRatingMoodys Management Detail Component', () => {
        let comp: RefRatingMoodysDetailComponent;
        let fixture: ComponentFixture<RefRatingMoodysDetailComponent>;
        const route = ({ data: of({ refRatingMoodys: new RefRatingMoodys(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefRatingMoodysDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefRatingMoodysDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefRatingMoodysDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refRatingMoodys).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
