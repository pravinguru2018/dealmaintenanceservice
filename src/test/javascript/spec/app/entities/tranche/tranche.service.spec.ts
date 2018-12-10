/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TrancheService } from 'app/entities/tranche/tranche.service';
import { ITranche, Tranche } from 'app/shared/model/tranche.model';

describe('Service Tests', () => {
    describe('Tranche Service', () => {
        let injector: TestBed;
        let service: TrancheService;
        let httpMock: HttpTestingController;
        let elemDefault: ITranche;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(TrancheService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Tranche(
                0,
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                0,
                0,
                currentDate,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                'AAAAAAA',
                'AAAAAAA',
                0,
                'AAAAAAA',
                currentDate,
                'AAAAAAA',
                currentDate
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        bridgeTakeoutDate: currentDate.format(DATE_TIME_FORMAT),
                        createdOn: currentDate.format(DATE_TIME_FORMAT),
                        updatedOn: currentDate.format(DATE_TIME_FORMAT)
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

            it('should create a Tranche', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        bridgeTakeoutDate: currentDate.format(DATE_TIME_FORMAT),
                        createdOn: currentDate.format(DATE_TIME_FORMAT),
                        updatedOn: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        bridgeTakeoutDate: currentDate,
                        createdOn: currentDate,
                        updatedOn: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Tranche(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Tranche', async () => {
                const returnedFromService = Object.assign(
                    {
                        trancheId: 1,
                        trancheName: 'BBBBBB',
                        fundedDuringSyndication: 'BBBBBB',
                        debtTakeout: 'BBBBBB',
                        tenorYears: 1,
                        tenorMonths: 1,
                        bridgeTakeoutDate: currentDate.format(DATE_TIME_FORMAT),
                        creditApprovedLcymStructure: 1,
                        creditApprovedLcymCommit: 1,
                        creditApprovedLcymHold: 1,
                        finalApprovedLcymStructure: 1,
                        finalApprovedLcymCommit: 1,
                        finalApprovedLcymHold: 1,
                        marketRiskLcymEconomic: 1,
                        marketRiskLcymLegal: 1,
                        marketRiskLcymSettlement: 1,
                        currentBridgeHoldLcym: 1,
                        tenorHighYieldBondYears: 1,
                        tenorHighYieldBondMonths: 1,
                        bondCapRate: 1,
                        refMarginRateName: 'BBBBBB',
                        currency: 'BBBBBB',
                        sortOrder: 1,
                        createdBy: 'BBBBBB',
                        createdOn: currentDate.format(DATE_TIME_FORMAT),
                        updatedBy: 'BBBBBB',
                        updatedOn: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        bridgeTakeoutDate: currentDate,
                        createdOn: currentDate,
                        updatedOn: currentDate
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

            it('should return a list of Tranche', async () => {
                const returnedFromService = Object.assign(
                    {
                        trancheId: 1,
                        trancheName: 'BBBBBB',
                        fundedDuringSyndication: 'BBBBBB',
                        debtTakeout: 'BBBBBB',
                        tenorYears: 1,
                        tenorMonths: 1,
                        bridgeTakeoutDate: currentDate.format(DATE_TIME_FORMAT),
                        creditApprovedLcymStructure: 1,
                        creditApprovedLcymCommit: 1,
                        creditApprovedLcymHold: 1,
                        finalApprovedLcymStructure: 1,
                        finalApprovedLcymCommit: 1,
                        finalApprovedLcymHold: 1,
                        marketRiskLcymEconomic: 1,
                        marketRiskLcymLegal: 1,
                        marketRiskLcymSettlement: 1,
                        currentBridgeHoldLcym: 1,
                        tenorHighYieldBondYears: 1,
                        tenorHighYieldBondMonths: 1,
                        bondCapRate: 1,
                        refMarginRateName: 'BBBBBB',
                        currency: 'BBBBBB',
                        sortOrder: 1,
                        createdBy: 'BBBBBB',
                        createdOn: currentDate.format(DATE_TIME_FORMAT),
                        updatedBy: 'BBBBBB',
                        updatedOn: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        bridgeTakeoutDate: currentDate,
                        createdOn: currentDate,
                        updatedOn: currentDate
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

            it('should delete a Tranche', async () => {
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
