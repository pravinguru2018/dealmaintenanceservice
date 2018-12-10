/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DealmaintenanceserviceTestModule } from '../../../test.module';
import { TrancheComponent } from 'app/entities/tranche/tranche.component';
import { TrancheService } from 'app/entities/tranche/tranche.service';
import { Tranche } from 'app/shared/model/tranche.model';

describe('Component Tests', () => {
    describe('Tranche Management Component', () => {
        let comp: TrancheComponent;
        let fixture: ComponentFixture<TrancheComponent>;
        let service: TrancheService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DealmaintenanceserviceTestModule],
                declarations: [TrancheComponent],
                providers: []
            })
                .overrideTemplate(TrancheComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TrancheComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TrancheService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Tranche(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.tranches[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
