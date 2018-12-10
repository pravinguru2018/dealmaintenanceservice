/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefDealStatusComponent } from 'app/entities/ref-deal-status/ref-deal-status.component';
import { RefDealStatusService } from 'app/entities/ref-deal-status/ref-deal-status.service';
import { RefDealStatus } from 'app/shared/model/ref-deal-status.model';

describe('Component Tests', () => {
    describe('RefDealStatus Management Component', () => {
        let comp: RefDealStatusComponent;
        let fixture: ComponentFixture<RefDealStatusComponent>;
        let service: RefDealStatusService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefDealStatusComponent],
                providers: []
            })
                .overrideTemplate(RefDealStatusComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefDealStatusComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefDealStatusService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RefDealStatus(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.refDealStatuses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
