/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefFacilityTypeDetailComponent } from 'app/entities/ref-facility-type/ref-facility-type-detail.component';
import { RefFacilityType } from 'app/shared/model/ref-facility-type.model';

describe('Component Tests', () => {
    describe('RefFacilityType Management Detail Component', () => {
        let comp: RefFacilityTypeDetailComponent;
        let fixture: ComponentFixture<RefFacilityTypeDetailComponent>;
        const route = ({ data: of({ refFacilityType: new RefFacilityType(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefFacilityTypeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefFacilityTypeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefFacilityTypeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refFacilityType).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
