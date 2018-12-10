/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefBookingEntityDetailComponent } from 'app/entities/ref-booking-entity/ref-booking-entity-detail.component';
import { RefBookingEntity } from 'app/shared/model/ref-booking-entity.model';

describe('Component Tests', () => {
    describe('RefBookingEntity Management Detail Component', () => {
        let comp: RefBookingEntityDetailComponent;
        let fixture: ComponentFixture<RefBookingEntityDetailComponent>;
        const route = ({ data: of({ refBookingEntity: new RefBookingEntity(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefBookingEntityDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefBookingEntityDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefBookingEntityDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refBookingEntity).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
