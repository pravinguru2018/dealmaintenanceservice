import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefRecourseToClient } from 'app/shared/model/ref-recourse-to-client.model';
import { RefRecourseToClientService } from './ref-recourse-to-client.service';
import { RefRecourseToClientComponent } from './ref-recourse-to-client.component';
import { RefRecourseToClientDetailComponent } from './ref-recourse-to-client-detail.component';
import { RefRecourseToClientUpdateComponent } from './ref-recourse-to-client-update.component';
import { RefRecourseToClientDeletePopupComponent } from './ref-recourse-to-client-delete-dialog.component';
import { IRefRecourseToClient } from 'app/shared/model/ref-recourse-to-client.model';

@Injectable({ providedIn: 'root' })
export class RefRecourseToClientResolve implements Resolve<IRefRecourseToClient> {
    constructor(private service: RefRecourseToClientService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RefRecourseToClient> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefRecourseToClient>) => response.ok),
                map((refRecourseToClient: HttpResponse<RefRecourseToClient>) => refRecourseToClient.body)
            );
        }
        return of(new RefRecourseToClient());
    }
}

export const refRecourseToClientRoute: Routes = [
    {
        path: 'ref-recourse-to-client',
        component: RefRecourseToClientComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRecourseToClients'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-recourse-to-client/:id/view',
        component: RefRecourseToClientDetailComponent,
        resolve: {
            refRecourseToClient: RefRecourseToClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRecourseToClients'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-recourse-to-client/new',
        component: RefRecourseToClientUpdateComponent,
        resolve: {
            refRecourseToClient: RefRecourseToClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRecourseToClients'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-recourse-to-client/:id/edit',
        component: RefRecourseToClientUpdateComponent,
        resolve: {
            refRecourseToClient: RefRecourseToClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRecourseToClients'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refRecourseToClientPopupRoute: Routes = [
    {
        path: 'ref-recourse-to-client/:id/delete',
        component: RefRecourseToClientDeletePopupComponent,
        resolve: {
            refRecourseToClient: RefRecourseToClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRecourseToClients'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
