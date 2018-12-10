/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefCountryDetailComponent } from 'app/entities/ref-country/ref-country-detail.component';
import { RefCountry } from 'app/shared/model/ref-country.model';

describe('Component Tests', () => {
    describe('RefCountry Management Detail Component', () => {
        let comp: RefCountryDetailComponent;
        let fixture: ComponentFixture<RefCountryDetailComponent>;
        const route = ({ data: of({ refCountry: new RefCountry(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefCountryDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefCountryDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefCountryDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refCountry).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
