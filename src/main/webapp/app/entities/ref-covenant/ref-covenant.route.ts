import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefCovenant } from 'app/shared/model/ref-covenant.model';
import { RefCovenantService } from './ref-covenant.service';
import { RefCovenantComponent } from './ref-covenant.component';
import { RefCovenantDetailComponent } from './ref-covenant-detail.component';
import { RefCovenantUpdateComponent } from './ref-covenant-update.component';
import { RefCovenantDeletePopupComponent } from './ref-covenant-delete-dialog.component';
import { IRefCovenant } from 'app/shared/model/ref-covenant.model';

@Injectable({ providedIn: 'root' })
export class RefCovenantResolve implements Resolve<IRefCovenant> {
    constructor(private service: RefCovenantService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RefCovenant> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefCovenant>) => response.ok),
                map((refCovenant: HttpResponse<RefCovenant>) => refCovenant.body)
            );
        }
        return of(new RefCovenant());
    }
}

export const refCovenantRoute: Routes = [
    {
        path: 'ref-covenant',
        component: RefCovenantComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefCovenants'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-covenant/:id/view',
        component: RefCovenantDetailComponent,
        resolve: {
            refCovenant: RefCovenantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefCovenants'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-covenant/new',
        component: RefCovenantUpdateComponent,
        resolve: {
            refCovenant: RefCovenantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefCovenants'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-covenant/:id/edit',
        component: RefCovenantUpdateComponent,
        resolve: {
            refCovenant: RefCovenantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefCovenants'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refCovenantPopupRoute: Routes = [
    {
        path: 'ref-covenant/:id/delete',
        component: RefCovenantDeletePopupComponent,
        resolve: {
            refCovenant: RefCovenantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefCovenants'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
