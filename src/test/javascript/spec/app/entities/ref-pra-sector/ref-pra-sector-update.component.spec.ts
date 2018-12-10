/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefPraSectorUpdateComponent } from 'app/entities/ref-pra-sector/ref-pra-sector-update.component';
import { RefPraSectorService } from 'app/entities/ref-pra-sector/ref-pra-sector.service';
import { RefPraSector } from 'app/shared/model/ref-pra-sector.model';

describe('Component Tests', () => {
    describe('RefPraSector Management Update Component', () => {
        let comp: RefPraSectorUpdateComponent;
        let fixture: ComponentFixture<RefPraSectorUpdateComponent>;
        let service: RefPraSectorService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefPraSectorUpdateComponent]
            })
                .overrideTemplate(RefPraSectorUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefPraSectorUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefPraSectorService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefPraSector(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refPraSector = entity;
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
                    const entity = new RefPraSector();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refPraSector = entity;
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
