/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefSeniorityComponent } from 'app/entities/ref-seniority/ref-seniority.component';
import { RefSeniorityService } from 'app/entities/ref-seniority/ref-seniority.service';
import { RefSeniority } from 'app/shared/model/ref-seniority.model';

describe('Component Tests', () => {
    describe('RefSeniority Management Component', () => {
        let comp: RefSeniorityComponent;
        let fixture: ComponentFixture<RefSeniorityComponent>;
        let service: RefSeniorityService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefSeniorityComponent],
                providers: []
            })
                .overrideTemplate(RefSeniorityComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefSeniorityComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefSeniorityService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RefSeniority(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.refSeniorities[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
