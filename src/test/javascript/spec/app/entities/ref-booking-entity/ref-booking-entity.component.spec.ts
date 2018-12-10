/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefBookingEntityComponent } from 'app/entities/ref-booking-entity/ref-booking-entity.component';
import { RefBookingEntityService } from 'app/entities/ref-booking-entity/ref-booking-entity.service';
import { RefBookingEntity } from 'app/shared/model/ref-booking-entity.model';

describe('Component Tests', () => {
    describe('RefBookingEntity Management Component', () => {
        let comp: RefBookingEntityComponent;
        let fixture: ComponentFixture<RefBookingEntityComponent>;
        let service: RefBookingEntityService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefBookingEntityComponent],
                providers: []
            })
                .overrideTemplate(RefBookingEntityComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefBookingEntityComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefBookingEntityService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RefBookingEntity(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.refBookingEntities[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
