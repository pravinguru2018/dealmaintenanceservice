/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefCountryComponent } from 'app/entities/ref-country/ref-country.component';
import { RefCountryService } from 'app/entities/ref-country/ref-country.service';
import { RefCountry } from 'app/shared/model/ref-country.model';

describe('Component Tests', () => {
    describe('RefCountry Management Component', () => {
        let comp: RefCountryComponent;
        let fixture: ComponentFixture<RefCountryComponent>;
        let service: RefCountryService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefCountryComponent],
                providers: []
            })
                .overrideTemplate(RefCountryComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefCountryComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefCountryService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RefCountry(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.refCountries[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
