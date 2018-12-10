/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { TrancheUpdateComponent } from 'app/entities/tranche/tranche-update.component';
import { TrancheService } from 'app/entities/tranche/tranche.service';
import { Tranche } from 'app/shared/model/tranche.model';

describe('Component Tests', () => {
    describe('Tranche Management Update Component', () => {
        let comp: TrancheUpdateComponent;
        let fixture: ComponentFixture<TrancheUpdateComponent>;
        let service: TrancheService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [TrancheUpdateComponent]
            })
                .overrideTemplate(TrancheUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TrancheUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TrancheService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Tranche(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tranche = entity;
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
                    const entity = new Tranche();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tranche = entity;
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
