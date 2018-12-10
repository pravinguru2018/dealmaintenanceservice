/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { DealService } from 'app/entities/deal/deal.service';
import { IDeal, Deal } from 'app/shared/model/deal.model';

describe('Service Tests', () => {
    describe('Deal Service', () => {
        let injector: TestBed;
        let service: DealService;
        let httpMock: HttpTestingController;
        let elemDefault: IDeal;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(DealService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Deal(
                0,
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                currentDate,
                'AAAAAAA',
                'AAAAAAA',
                currentDate,
                currentDate,
                currentDate,
                currentDate,
                currentDate,
                'AAAAAAA',
                false,
                'AAAAAAA',
                currentDate,
                currentDate
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        regulatoryApprovalDate: currentDate.format(DATE_TIME_FORMAT),
                        creditApprovalDate: currentDate.format(DATE_TIME_FORMAT),
                        allocationDate: currentDate.format(DATE_TIME_FORMAT),
                        syndicationLaunchDate: currentDate.format(DATE_TIME_FORMAT),
                        commitmentDate: currentDate.format(DATE_TIME_FORMAT),
                        creditApprovalOffRiskDate: currentDate.format(DATE_TIME_FORMAT),
                        createdOn: currentDate.format(DATE_TIME_FORMAT),
                        updatedBy: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Deal', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        regulatoryApprovalDate: currentDate.format(DATE_TIME_FORMAT),
                        creditApprovalDate: currentDate.format(DATE_TIME_FORMAT),
                        allocationDate: currentDate.format(DATE_TIME_FORMAT),
                        syndicationLaunchDate: currentDate.format(DATE_TIME_FORMAT),
                        commitmentDate: currentDate.format(DATE_TIME_FORMAT),
                        creditApprovalOffRiskDate: currentDate.format(DATE_TIME_FORMAT),
                        createdOn: currentDate.format(DATE_TIME_FORMAT),
                        updatedBy: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        regulatoryApprovalDate: currentDate,
                        creditApprovalDate: currentDate,
                        allocationDate: currentDate,
                        syndicationLaunchDate: currentDate,
                        commitmentDate: currentDate,
                        creditApprovalOffRiskDate: currentDate,
                        createdOn: currentDate,
                        updatedBy: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Deal(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Deal', async () => {
                const returnedFromService = Object.assign(
                    {
                        dealId: 1,
                        projectName: 'BBBBBB',
                        clientSponsor: 'BBBBBB',
                        targetAsset: 'BBBBBB',
                        syndicateOwner: 'BBBBBB',
                        dealPipelineId: 'BBBBBB',
                        originationContact: 'BBBBBB',
                        relationshipManager: 'BBBBBB',
                        llf: 'BBBBBB',
                        regulatoryApprovalRequired: 'BBBBBB',
                        regulatoryApprovalObtained: 'BBBBBB',
                        countryOfIncorporation: 'BBBBBB',
                        regulatoryApprovalDate: currentDate.format(DATE_TIME_FORMAT),
                        dealDescription: 'BBBBBB',
                        updateNote: 'BBBBBB',
                        creditApprovalDate: currentDate.format(DATE_TIME_FORMAT),
                        allocationDate: currentDate.format(DATE_TIME_FORMAT),
                        syndicationLaunchDate: currentDate.format(DATE_TIME_FORMAT),
                        commitmentDate: currentDate.format(DATE_TIME_FORMAT),
                        creditApprovalOffRiskDate: currentDate.format(DATE_TIME_FORMAT),
                        creditOfficer: 'BBBBBB',
                        isActive: true,
                        createdBy: 'BBBBBB',
                        createdOn: currentDate.format(DATE_TIME_FORMAT),
                        updatedBy: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        regulatoryApprovalDate: currentDate,
                        creditApprovalDate: currentDate,
                        allocationDate: currentDate,
                        syndicationLaunchDate: currentDate,
                        commitmentDate: currentDate,
                        creditApprovalOffRiskDate: currentDate,
                        createdOn: currentDate,
                        updatedBy: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Deal', async () => {
                const returnedFromService = Object.assign(
                    {
                        dealId: 1,
                        projectName: 'BBBBBB',
                        clientSponsor: 'BBBBBB',
                        targetAsset: 'BBBBBB',
                        syndicateOwner: 'BBBBBB',
                        dealPipelineId: 'BBBBBB',
                        originationContact: 'BBBBBB',
                        relationshipManager: 'BBBBBB',
                        llf: 'BBBBBB',
                        regulatoryApprovalRequired: 'BBBBBB',
                        regulatoryApprovalObtained: 'BBBBBB',
                        countryOfIncorporation: 'BBBBBB',
                        regulatoryApprovalDate: currentDate.format(DATE_TIME_FORMAT),
                        dealDescription: 'BBBBBB',
                        updateNote: 'BBBBBB',
                        creditApprovalDate: currentDate.format(DATE_TIME_FORMAT),
                        allocationDate: currentDate.format(DATE_TIME_FORMAT),
                        syndicationLaunchDate: currentDate.format(DATE_TIME_FORMAT),
                        commitmentDate: currentDate.format(DATE_TIME_FORMAT),
                        creditApprovalOffRiskDate: currentDate.format(DATE_TIME_FORMAT),
                        creditOfficer: 'BBBBBB',
                        isActive: true,
                        createdBy: 'BBBBBB',
                        createdOn: currentDate.format(DATE_TIME_FORMAT),
                        updatedBy: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        regulatoryApprovalDate: currentDate,
                        creditApprovalDate: currentDate,
                        allocationDate: currentDate,
                        syndicationLaunchDate: currentDate,
                        commitmentDate: currentDate,
                        creditApprovalOffRiskDate: currentDate,
                        createdOn: currentDate,
                        updatedBy: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Deal', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
