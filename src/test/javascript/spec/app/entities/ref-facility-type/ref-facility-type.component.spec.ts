/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefFacilityTypeComponent } from 'app/entities/ref-facility-type/ref-facility-type.component';
import { RefFacilityTypeService } from 'app/entities/ref-facility-type/ref-facility-type.service';
import { RefFacilityType } from 'app/shared/model/ref-facility-type.model';

describe('Component Tests', () => {
    describe('RefFacilityType Management Component', () => {
        let comp: RefFacilityTypeComponent;
        let fixture: ComponentFixture<RefFacilityTypeComponent>;
        let service: RefFacilityTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefFacilityTypeComponent],
                providers: []
            })
                .overrideTemplate(RefFacilityTypeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefFacilityTypeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefFacilityTypeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RefFacilityType(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.refFacilityTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
