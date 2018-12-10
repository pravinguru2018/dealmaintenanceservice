import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefRatingSAndP } from 'app/shared/model/ref-rating-s-and-p.model';
import { RefRatingSAndPService } from './ref-rating-s-and-p.service';
import { RefRatingSAndPComponent } from './ref-rating-s-and-p.component';
import { RefRatingSAndPDetailComponent } from './ref-rating-s-and-p-detail.component';
import { RefRatingSAndPUpdateComponent } from './ref-rating-s-and-p-update.component';
import { RefRatingSAndPDeletePopupComponent } from './ref-rating-s-and-p-delete-dialog.component';
import { IRefRatingSAndP } from 'app/shared/model/ref-rating-s-and-p.model';

@Injectable({ providedIn: 'root' })
export class RefRatingSAndPResolve implements Resolve<IRefRatingSAndP> {
    constructor(private service: RefRatingSAndPService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RefRatingSAndP> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefRatingSAndP>) => response.ok),
                map((refRatingSAndP: HttpResponse<RefRatingSAndP>) => refRatingSAndP.body)
            );
        }
        return of(new RefRatingSAndP());
    }
}

export const refRatingSAndPRoute: Routes = [
    {
        path: 'ref-rating-s-and-p',
        component: RefRatingSAndPComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRatingSAndPS'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-rating-s-and-p/:id/view',
        component: RefRatingSAndPDetailComponent,
        resolve: {
            refRatingSAndP: RefRatingSAndPResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRatingSAndPS'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-rating-s-and-p/new',
        component: RefRatingSAndPUpdateComponent,
        resolve: {
            refRatingSAndP: RefRatingSAndPResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRatingSAndPS'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-rating-s-and-p/:id/edit',
        component: RefRatingSAndPUpdateComponent,
        resolve: {
            refRatingSAndP: RefRatingSAndPResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRatingSAndPS'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refRatingSAndPPopupRoute: Routes = [
    {
        path: 'ref-rating-s-and-p/:id/delete',
        component: RefRatingSAndPDeletePopupComponent,
        resolve: {
            refRatingSAndP: RefRatingSAndPResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRatingSAndPS'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
