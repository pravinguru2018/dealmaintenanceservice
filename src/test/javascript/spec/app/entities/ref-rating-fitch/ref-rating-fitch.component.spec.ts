/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefRatingFitchComponent } from 'app/entities/ref-rating-fitch/ref-rating-fitch.component';
import { RefRatingFitchService } from 'app/entities/ref-rating-fitch/ref-rating-fitch.service';
import { RefRatingFitch } from 'app/shared/model/ref-rating-fitch.model';

describe('Component Tests', () => {
    describe('RefRatingFitch Management Component', () => {
        let comp: RefRatingFitchComponent;
        let fixture: ComponentFixture<RefRatingFitchComponent>;
        let service: RefRatingFitchService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefRatingFitchComponent],
                providers: []
            })
                .overrideTemplate(RefRatingFitchComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefRatingFitchComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefRatingFitchService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RefRatingFitch(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.refRatingFitches[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
