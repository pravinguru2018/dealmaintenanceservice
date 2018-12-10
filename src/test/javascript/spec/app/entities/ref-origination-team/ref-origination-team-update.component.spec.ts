/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefOriginationTeamUpdateComponent } from 'app/entities/ref-origination-team/ref-origination-team-update.component';
import { RefOriginationTeamService } from 'app/entities/ref-origination-team/ref-origination-team.service';
import { RefOriginationTeam } from 'app/shared/model/ref-origination-team.model';

describe('Component Tests', () => {
    describe('RefOriginationTeam Management Update Component', () => {
        let comp: RefOriginationTeamUpdateComponent;
        let fixture: ComponentFixture<RefOriginationTeamUpdateComponent>;
        let service: RefOriginationTeamService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefOriginationTeamUpdateComponent]
            })
                .overrideTemplate(RefOriginationTeamUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefOriginationTeamUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefOriginationTeamService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefOriginationTeam(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refOriginationTeam = entity;
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
                    const entity = new RefOriginationTeam();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refOriginationTeam = entity;
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
