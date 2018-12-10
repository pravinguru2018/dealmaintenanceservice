/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { TrancheMarginUpdateComponent } from 'app/entities/tranche-margin/tranche-margin-update.component';
import { TrancheMarginService } from 'app/entities/tranche-margin/tranche-margin.service';
import { TrancheMargin } from 'app/shared/model/tranche-margin.model';

describe('Component Tests', () => {
    describe('TrancheMargin Management Update Component', () => {
        let comp: TrancheMarginUpdateComponent;
        let fixture: ComponentFixture<TrancheMarginUpdateComponent>;
        let service: TrancheMarginService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [TrancheMarginUpdateComponent]
            })
                .overrideTemplate(TrancheMarginUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TrancheMarginUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TrancheMarginService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TrancheMargin(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.trancheMargin = entity;
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
                    const entity = new TrancheMargin();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.trancheMargin = entity;
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
