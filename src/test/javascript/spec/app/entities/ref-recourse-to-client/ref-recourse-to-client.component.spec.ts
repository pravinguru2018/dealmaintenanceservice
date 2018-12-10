/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { RefRecourseToClientComponent } from 'app/entities/ref-recourse-to-client/ref-recourse-to-client.component';
import { RefRecourseToClientService } from 'app/entities/ref-recourse-to-client/ref-recourse-to-client.service';
import { RefRecourseToClient } from 'app/shared/model/ref-recourse-to-client.model';

describe('Component Tests', () => {
    describe('RefRecourseToClient Management Component', () => {
        let comp: RefRecourseToClientComponent;
        let fixture: ComponentFixture<RefRecourseToClientComponent>;
        let service: RefRecourseToClientService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [RefRecourseToClientComponent],
                providers: []
            })
                .overrideTemplate(RefRecourseToClientComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefRecourseToClientComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefRecourseToClientService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RefRecourseToClient(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.refRecourseToClients[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
