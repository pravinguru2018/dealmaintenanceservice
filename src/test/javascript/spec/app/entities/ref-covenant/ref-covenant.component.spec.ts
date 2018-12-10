/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefCovenantComponent } from 'app/entities/ref-covenant/ref-covenant.component';
import { RefCovenantService } from 'app/entities/ref-covenant/ref-covenant.service';
import { RefCovenant } from 'app/shared/model/ref-covenant.model';

describe('Component Tests', () => {
    describe('RefCovenant Management Component', () => {
        let comp: RefCovenantComponent;
        let fixture: ComponentFixture<RefCovenantComponent>;
        let service: RefCovenantService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefCovenantComponent],
                providers: []
            })
                .overrideTemplate(RefCovenantComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefCovenantComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefCovenantService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RefCovenant(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.refCovenants[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
