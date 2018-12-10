/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefRatingSAndPDetailComponent } from 'app/entities/ref-rating-s-and-p/ref-rating-s-and-p-detail.component';
import { RefRatingSAndP } from 'app/shared/model/ref-rating-s-and-p.model';

describe('Component Tests', () => {
    describe('RefRatingSAndP Management Detail Component', () => {
        let comp: RefRatingSAndPDetailComponent;
        let fixture: ComponentFixture<RefRatingSAndPDetailComponent>;
        const route = ({ data: of({ refRatingSAndP: new RefRatingSAndP(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefRatingSAndPDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefRatingSAndPDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefRatingSAndPDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refRatingSAndP).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
