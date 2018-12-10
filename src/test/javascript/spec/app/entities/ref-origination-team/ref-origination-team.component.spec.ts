/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefOriginationTeamComponent } from 'app/entities/ref-origination-team/ref-origination-team.component';
import { RefOriginationTeamService } from 'app/entities/ref-origination-team/ref-origination-team.service';
import { RefOriginationTeam } from 'app/shared/model/ref-origination-team.model';

describe('Component Tests', () => {
    describe('RefOriginationTeam Management Component', () => {
        let comp: RefOriginationTeamComponent;
        let fixture: ComponentFixture<RefOriginationTeamComponent>;
        let service: RefOriginationTeamService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefOriginationTeamComponent],
                providers: []
            })
                .overrideTemplate(RefOriginationTeamComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefOriginationTeamComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefOriginationTeamService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RefOriginationTeam(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.refOriginationTeams[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
