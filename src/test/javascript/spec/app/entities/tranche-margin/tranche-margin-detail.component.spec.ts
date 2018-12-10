/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { TrancheMarginDetailComponent } from 'app/entities/tranche-margin/tranche-margin-detail.component';
import { TrancheMargin } from 'app/shared/model/tranche-margin.model';

describe('Component Tests', () => {
    describe('TrancheMargin Management Detail Component', () => {
        let comp: TrancheMarginDetailComponent;
        let fixture: ComponentFixture<TrancheMarginDetailComponent>;
        const route = ({ data: of({ trancheMargin: new TrancheMargin(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [TrancheMarginDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TrancheMarginDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TrancheMarginDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.trancheMargin).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
