/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefLineOfBusinessComponent } from 'app/entities/ref-line-of-business/ref-line-of-business.component';
import { RefLineOfBusinessService } from 'app/entities/ref-line-of-business/ref-line-of-business.service';
import { RefLineOfBusiness } from 'app/shared/model/ref-line-of-business.model';

describe('Component Tests', () => {
    describe('RefLineOfBusiness Management Component', () => {
        let comp: RefLineOfBusinessComponent;
        let fixture: ComponentFixture<RefLineOfBusinessComponent>;
        let service: RefLineOfBusinessService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefLineOfBusinessComponent],
                providers: []
            })
                .overrideTemplate(RefLineOfBusinessComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefLineOfBusinessComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefLineOfBusinessService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RefLineOfBusiness(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.refLineOfBusinesses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
