import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefSeniority } from 'app/shared/model/ref-seniority.model';
import { RefSeniorityService } from './ref-seniority.service';
import { RefSeniorityComponent } from './ref-seniority.component';
import { RefSeniorityDetailComponent } from './ref-seniority-detail.component';
import { RefSeniorityUpdateComponent } from './ref-seniority-update.component';
import { RefSeniorityDeletePopupComponent } from './ref-seniority-delete-dialog.component';
import { IRefSeniority } from 'app/shared/model/ref-seniority.model';

@Injectable({ providedIn: 'root' })
export class RefSeniorityResolve implements Resolve<IRefSeniority> {
    constructor(private service: RefSeniorityService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RefSeniority> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefSeniority>) => response.ok),
                map((refSeniority: HttpResponse<RefSeniority>) => refSeniority.body)
            );
        }
        return of(new RefSeniority());
    }
}

export const refSeniorityRoute: Routes = [
    {
        path: 'ref-seniority',
        component: RefSeniorityComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefSeniorities'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-seniority/:id/view',
        component: RefSeniorityDetailComponent,
        resolve: {
            refSeniority: RefSeniorityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefSeniorities'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-seniority/new',
        component: RefSeniorityUpdateComponent,
        resolve: {
            refSeniority: RefSeniorityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefSeniorities'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-seniority/:id/edit',
        component: RefSeniorityUpdateComponent,
        resolve: {
            refSeniority: RefSeniorityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefSeniorities'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refSeniorityPopupRoute: Routes = [
    {
        path: 'ref-seniority/:id/delete',
        component: RefSeniorityDeletePopupComponent,
        resolve: {
            refSeniority: RefSeniorityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefSeniorities'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
