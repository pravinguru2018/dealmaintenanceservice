/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefRatingMoodysComponent } from 'app/entities/ref-rating-moodys/ref-rating-moodys.component';
import { RefRatingMoodysService } from 'app/entities/ref-rating-moodys/ref-rating-moodys.service';
import { RefRatingMoodys } from 'app/shared/model/ref-rating-moodys.model';

describe('Component Tests', () => {
    describe('RefRatingMoodys Management Component', () => {
        let comp: RefRatingMoodysComponent;
        let fixture: ComponentFixture<RefRatingMoodysComponent>;
        let service: RefRatingMoodysService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefRatingMoodysComponent],
                providers: []
            })
                .overrideTemplate(RefRatingMoodysComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefRatingMoodysComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefRatingMoodysService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RefRatingMoodys(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.refRatingMoodys[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
