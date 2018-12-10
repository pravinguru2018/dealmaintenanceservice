import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefDealStatus } from 'app/shared/model/ref-deal-status.model';
import { RefDealStatusService } from './ref-deal-status.service';
import { RefDealStatusComponent } from './ref-deal-status.component';
import { RefDealStatusDetailComponent } from './ref-deal-status-detail.component';
import { RefDealStatusUpdateComponent } from './ref-deal-status-update.component';
import { RefDealStatusDeletePopupComponent } from './ref-deal-status-delete-dialog.component';
import { IRefDealStatus } from 'app/shared/model/ref-deal-status.model';

@Injectable({ providedIn: 'root' })
export class RefDealStatusResolve implements Resolve<IRefDealStatus> {
    constructor(private service: RefDealStatusService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RefDealStatus> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefDealStatus>) => response.ok),
                map((refDealStatus: HttpResponse<RefDealStatus>) => refDealStatus.body)
            );
        }
        return of(new RefDealStatus());
    }
}

export const refDealStatusRoute: Routes = [
    {
        path: 'ref-deal-status',
        component: RefDealStatusComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefDealStatuses'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-deal-status/:id/view',
        component: RefDealStatusDetailComponent,
        resolve: {
            refDealStatus: RefDealStatusResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefDealStatuses'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-deal-status/new',
        component: RefDealStatusUpdateComponent,
        resolve: {
            refDealStatus: RefDealStatusResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefDealStatuses'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-deal-status/:id/edit',
        component: RefDealStatusUpdateComponent,
        resolve: {
            refDealStatus: RefDealStatusResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefDealStatuses'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refDealStatusPopupRoute: Routes = [
    {
        path: 'ref-deal-status/:id/delete',
        component: RefDealStatusDeletePopupComponent,
        resolve: {
            refDealStatus: RefDealStatusResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefDealStatuses'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
