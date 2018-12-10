/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefRatingSAndPComponent } from 'app/entities/ref-rating-s-and-p/ref-rating-s-and-p.component';
import { RefRatingSAndPService } from 'app/entities/ref-rating-s-and-p/ref-rating-s-and-p.service';
import { RefRatingSAndP } from 'app/shared/model/ref-rating-s-and-p.model';

describe('Component Tests', () => {
    describe('RefRatingSAndP Management Component', () => {
        let comp: RefRatingSAndPComponent;
        let fixture: ComponentFixture<RefRatingSAndPComponent>;
        let service: RefRatingSAndPService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefRatingSAndPComponent],
                providers: []
            })
                .overrideTemplate(RefRatingSAndPComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefRatingSAndPComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefRatingSAndPService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RefRatingSAndP(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.refRatingSAndPS[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
