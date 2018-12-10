/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefPraSectorDetailComponent } from 'app/entities/ref-pra-sector/ref-pra-sector-detail.component';
import { RefPraSector } from 'app/shared/model/ref-pra-sector.model';

describe('Component Tests', () => {
    describe('RefPraSector Management Detail Component', () => {
        let comp: RefPraSectorDetailComponent;
        let fixture: ComponentFixture<RefPraSectorDetailComponent>;
        const route = ({ data: of({ refPraSector: new RefPraSector(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefPraSectorDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefPraSectorDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefPraSectorDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refPraSector).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
