/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefOriginationTeamDetailComponent } from 'app/entities/ref-origination-team/ref-origination-team-detail.component';
import { RefOriginationTeam } from 'app/shared/model/ref-origination-team.model';

describe('Component Tests', () => {
    describe('RefOriginationTeam Management Detail Component', () => {
        let comp: RefOriginationTeamDetailComponent;
        let fixture: ComponentFixture<RefOriginationTeamDetailComponent>;
        const route = ({ data: of({ refOriginationTeam: new RefOriginationTeam(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefOriginationTeamDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefOriginationTeamDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefOriginationTeamDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refOriginationTeam).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
