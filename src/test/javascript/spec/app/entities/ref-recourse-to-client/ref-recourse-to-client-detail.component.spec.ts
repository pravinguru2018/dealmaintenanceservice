/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefRecourseToClientDetailComponent } from 'app/entities/ref-recourse-to-client/ref-recourse-to-client-detail.component';
import { RefRecourseToClient } from 'app/shared/model/ref-recourse-to-client.model';

describe('Component Tests', () => {
    describe('RefRecourseToClient Management Detail Component', () => {
        let comp: RefRecourseToClientDetailComponent;
        let fixture: ComponentFixture<RefRecourseToClientDetailComponent>;
        const route = ({ data: of({ refRecourseToClient: new RefRecourseToClient(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefRecourseToClientDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefRecourseToClientDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefRecourseToClientDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refRecourseToClient).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
