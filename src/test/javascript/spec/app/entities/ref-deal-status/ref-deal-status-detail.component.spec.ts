/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefDealStatusDetailComponent } from 'app/entities/ref-deal-status/ref-deal-status-detail.component';
import { RefDealStatus } from 'app/shared/model/ref-deal-status.model';

describe('Component Tests', () => {
    describe('RefDealStatus Management Detail Component', () => {
        let comp: RefDealStatusDetailComponent;
        let fixture: ComponentFixture<RefDealStatusDetailComponent>;
        const route = ({ data: of({ refDealStatus: new RefDealStatus(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefDealStatusDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefDealStatusDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefDealStatusDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refDealStatus).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
