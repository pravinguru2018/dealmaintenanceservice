/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefLineOfBusinessDetailComponent } from 'app/entities/ref-line-of-business/ref-line-of-business-detail.component';
import { RefLineOfBusiness } from 'app/shared/model/ref-line-of-business.model';

describe('Component Tests', () => {
    describe('RefLineOfBusiness Management Detail Component', () => {
        let comp: RefLineOfBusinessDetailComponent;
        let fixture: ComponentFixture<RefLineOfBusinessDetailComponent>;
        const route = ({ data: of({ refLineOfBusiness: new RefLineOfBusiness(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefLineOfBusinessDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefLineOfBusinessDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefLineOfBusinessDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refLineOfBusiness).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
