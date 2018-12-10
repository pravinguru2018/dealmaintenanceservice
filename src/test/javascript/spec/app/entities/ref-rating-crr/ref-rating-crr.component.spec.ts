/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefRatingCrrComponent } from 'app/entities/ref-rating-crr/ref-rating-crr.component';
import { RefRatingCrrService } from 'app/entities/ref-rating-crr/ref-rating-crr.service';
import { RefRatingCrr } from 'app/shared/model/ref-rating-crr.model';

describe('Component Tests', () => {
    describe('RefRatingCrr Management Component', () => {
        let comp: RefRatingCrrComponent;
        let fixture: ComponentFixture<RefRatingCrrComponent>;
        let service: RefRatingCrrService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefRatingCrrComponent],
                providers: []
            })
                .overrideTemplate(RefRatingCrrComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefRatingCrrComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefRatingCrrService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RefRatingCrr(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.refRatingCrrs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
