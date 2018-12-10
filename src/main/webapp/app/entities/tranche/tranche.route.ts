import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Tranche } from 'app/shared/model/tranche.model';
import { TrancheService } from './tranche.service';
import { TrancheComponent } from './tranche.component';
import { TrancheDetailComponent } from './tranche-detail.component';
import { TrancheUpdateComponent } from './tranche-update.component';
import { TrancheDeletePopupComponent } from './tranche-delete-dialog.component';
import { ITranche } from 'app/shared/model/tranche.model';

@Injectable({ providedIn: 'root' })
export class TrancheResolve implements Resolve<ITranche> {
    constructor(private service: TrancheService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Tranche> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Tranche>) => response.ok),
                map((tranche: HttpResponse<Tranche>) => tranche.body)
            );
        }
        return of(new Tranche());
    }
}

export const trancheRoute: Routes = [
    {
        path: 'tranche',
        component: TrancheComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tranches'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tranche/:id/view',
        component: TrancheDetailComponent,
        resolve: {
            tranche: TrancheResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tranches'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tranche/new',
        component: TrancheUpdateComponent,
        resolve: {
            tranche: TrancheResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tranches'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tranche/:id/edit',
        component: TrancheUpdateComponent,
        resolve: {
            tranche: TrancheResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tranches'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tranchePopupRoute: Routes = [
    {
        path: 'tranche/:id/delete',
        component: TrancheDeletePopupComponent,
        resolve: {
            tranche: TrancheResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tranches'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
