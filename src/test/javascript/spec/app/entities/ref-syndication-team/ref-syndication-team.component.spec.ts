/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefSyndicationTeamComponent } from 'app/entities/ref-syndication-team/ref-syndication-team.component';
import { RefSyndicationTeamService } from 'app/entities/ref-syndication-team/ref-syndication-team.service';
import { RefSyndicationTeam } from 'app/shared/model/ref-syndication-team.model';

describe('Component Tests', () => {
    describe('RefSyndicationTeam Management Component', () => {
        let comp: RefSyndicationTeamComponent;
        let fixture: ComponentFixture<RefSyndicationTeamComponent>;
        let service: RefSyndicationTeamService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefSyndicationTeamComponent],
                providers: []
            })
                .overrideTemplate(RefSyndicationTeamComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefSyndicationTeamComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefSyndicationTeamService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RefSyndicationTeam(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.refSyndicationTeams[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
