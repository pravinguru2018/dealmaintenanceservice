/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefSeniorityDetailComponent } from 'app/entities/ref-seniority/ref-seniority-detail.component';
import { RefSeniority } from 'app/shared/model/ref-seniority.model';

describe('Component Tests', () => {
    describe('RefSeniority Management Detail Component', () => {
        let comp: RefSeniorityDetailComponent;
        let fixture: ComponentFixture<RefSeniorityDetailComponent>;
        const route = ({ data: of({ refSeniority: new RefSeniority(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefSeniorityDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefSeniorityDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefSeniorityDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refSeniority).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
