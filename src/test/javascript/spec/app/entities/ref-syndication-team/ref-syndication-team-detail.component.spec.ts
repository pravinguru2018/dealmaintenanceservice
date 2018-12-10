/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefSyndicationTeamDetailComponent } from 'app/entities/ref-syndication-team/ref-syndication-team-detail.component';
import { RefSyndicationTeam } from 'app/shared/model/ref-syndication-team.model';

describe('Component Tests', () => {
    describe('RefSyndicationTeam Management Detail Component', () => {
        let comp: RefSyndicationTeamDetailComponent;
        let fixture: ComponentFixture<RefSyndicationTeamDetailComponent>;
        const route = ({ data: of({ refSyndicationTeam: new RefSyndicationTeam(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefSyndicationTeamDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefSyndicationTeamDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefSyndicationTeamDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refSyndicationTeam).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
