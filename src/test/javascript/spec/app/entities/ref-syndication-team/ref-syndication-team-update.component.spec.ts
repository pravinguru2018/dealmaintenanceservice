/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefSyndicationTeamUpdateComponent } from 'app/entities/ref-syndication-team/ref-syndication-team-update.component';
import { RefSyndicationTeamService } from 'app/entities/ref-syndication-team/ref-syndication-team.service';
import { RefSyndicationTeam } from 'app/shared/model/ref-syndication-team.model';

describe('Component Tests', () => {
    describe('RefSyndicationTeam Management Update Component', () => {
        let comp: RefSyndicationTeamUpdateComponent;
        let fixture: ComponentFixture<RefSyndicationTeamUpdateComponent>;
        let service: RefSyndicationTeamService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefSyndicationTeamUpdateComponent]
            })
                .overrideTemplate(RefSyndicationTeamUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefSyndicationTeamUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefSyndicationTeamService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefSyndicationTeam(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refSyndicationTeam = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefSyndicationTeam();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refSyndicationTeam = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
