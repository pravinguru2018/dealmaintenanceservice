import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefLineOfBusiness } from 'app/shared/model/ref-line-of-business.model';
import { RefLineOfBusinessService } from './ref-line-of-business.service';
import { RefLineOfBusinessComponent } from './ref-line-of-business.component';
import { RefLineOfBusinessDetailComponent } from './ref-line-of-business-detail.component';
import { RefLineOfBusinessUpdateComponent } from './ref-line-of-business-update.component';
import { RefLineOfBusinessDeletePopupComponent } from './ref-line-of-business-delete-dialog.component';
import { IRefLineOfBusiness } from 'app/shared/model/ref-line-of-business.model';

@Injectable({ providedIn: 'root' })
export class RefLineOfBusinessResolve implements Resolve<IRefLineOfBusiness> {
    constructor(private service: RefLineOfBusinessService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RefLineOfBusiness> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefLineOfBusiness>) => response.ok),
                map((refLineOfBusiness: HttpResponse<RefLineOfBusiness>) => refLineOfBusiness.body)
            );
        }
        return of(new RefLineOfBusiness());
    }
}

export const refLineOfBusinessRoute: Routes = [
    {
        path: 'ref-line-of-business',
        component: RefLineOfBusinessComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefLineOfBusinesses'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-line-of-business/:id/view',
        component: RefLineOfBusinessDetailComponent,
        resolve: {
            refLineOfBusiness: RefLineOfBusinessResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefLineOfBusinesses'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-line-of-business/new',
        component: RefLineOfBusinessUpdateComponent,
        resolve: {
            refLineOfBusiness: RefLineOfBusinessResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefLineOfBusinesses'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-line-of-business/:id/edit',
        component: RefLineOfBusinessUpdateComponent,
        resolve: {
            refLineOfBusiness: RefLineOfBusinessResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefLineOfBusinesses'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refLineOfBusinessPopupRoute: Routes = [
    {
        path: 'ref-line-of-business/:id/delete',
        component: RefLineOfBusinessDeletePopupComponent,
        resolve: {
            refLineOfBusiness: RefLineOfBusinessResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefLineOfBusinesses'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
