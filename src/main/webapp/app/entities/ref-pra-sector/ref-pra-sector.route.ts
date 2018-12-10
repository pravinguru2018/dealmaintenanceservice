import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefPraSector } from 'app/shared/model/ref-pra-sector.model';
import { RefPraSectorService } from './ref-pra-sector.service';
import { RefPraSectorComponent } from './ref-pra-sector.component';
import { RefPraSectorDetailComponent } from './ref-pra-sector-detail.component';
import { RefPraSectorUpdateComponent } from './ref-pra-sector-update.component';
import { RefPraSectorDeletePopupComponent } from './ref-pra-sector-delete-dialog.component';
import { IRefPraSector } from 'app/shared/model/ref-pra-sector.model';

@Injectable({ providedIn: 'root' })
export class RefPraSectorResolve implements Resolve<IRefPraSector> {
    constructor(private service: RefPraSectorService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RefPraSector> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefPraSector>) => response.ok),
                map((refPraSector: HttpResponse<RefPraSector>) => refPraSector.body)
            );
        }
        return of(new RefPraSector());
    }
}

export const refPraSectorRoute: Routes = [
    {
        path: 'ref-pra-sector',
        component: RefPraSectorComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefPraSectors'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-pra-sector/:id/view',
        component: RefPraSectorDetailComponent,
        resolve: {
            refPraSector: RefPraSectorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefPraSectors'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-pra-sector/new',
        component: RefPraSectorUpdateComponent,
        resolve: {
            refPraSector: RefPraSectorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefPraSectors'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-pra-sector/:id/edit',
        component: RefPraSectorUpdateComponent,
        resolve: {
            refPraSector: RefPraSectorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefPraSectors'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refPraSectorPopupRoute: Routes = [
    {
        path: 'ref-pra-sector/:id/delete',
        component: RefPraSectorDeletePopupComponent,
        resolve: {
            refPraSector: RefPraSectorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefPraSectors'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
