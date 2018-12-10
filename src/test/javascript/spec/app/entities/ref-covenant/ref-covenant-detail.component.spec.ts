/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefCovenantDetailComponent } from 'app/entities/ref-covenant/ref-covenant-detail.component';
import { RefCovenant } from 'app/shared/model/ref-covenant.model';

describe('Component Tests', () => {
    describe('RefCovenant Management Detail Component', () => {
        let comp: RefCovenantDetailComponent;
        let fixture: ComponentFixture<RefCovenantDetailComponent>;
        const route = ({ data: of({ refCovenant: new RefCovenant(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefCovenantDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefCovenantDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefCovenantDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refCovenant).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
