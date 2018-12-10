/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefPraSectorComponent } from 'app/entities/ref-pra-sector/ref-pra-sector.component';
import { RefPraSectorService } from 'app/entities/ref-pra-sector/ref-pra-sector.service';
import { RefPraSector } from 'app/shared/model/ref-pra-sector.model';

describe('Component Tests', () => {
    describe('RefPraSector Management Component', () => {
        let comp: RefPraSectorComponent;
        let fixture: ComponentFixture<RefPraSectorComponent>;
        let service: RefPraSectorService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefPraSectorComponent],
                providers: []
            })
                .overrideTemplate(RefPraSectorComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefPraSectorComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefPraSectorService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RefPraSector(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.refPraSectors[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
