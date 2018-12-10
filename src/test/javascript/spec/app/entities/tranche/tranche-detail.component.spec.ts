/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { TrancheDetailComponent } from 'app/entities/tranche/tranche-detail.component';
import { Tranche } from 'app/shared/model/tranche.model';

describe('Component Tests', () => {
    describe('Tranche Management Detail Component', () => {
        let comp: TrancheDetailComponent;
        let fixture: ComponentFixture<TrancheDetailComponent>;
        const route = ({ data: of({ tranche: new Tranche(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [TrancheDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TrancheDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TrancheDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tranche).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
