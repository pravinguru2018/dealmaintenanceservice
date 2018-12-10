import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TrancheMargin } from 'app/shared/model/tranche-margin.model';
import { TrancheMarginService } from './tranche-margin.service';
import { TrancheMarginComponent } from './tranche-margin.component';
import { TrancheMarginDetailComponent } from './tranche-margin-detail.component';
import { TrancheMarginUpdateComponent } from './tranche-margin-update.component';
import { TrancheMarginDeletePopupComponent } from './tranche-margin-delete-dialog.component';
import { ITrancheMargin } from 'app/shared/model/tranche-margin.model';

@Injectable({ providedIn: 'root' })
export class TrancheMarginResolve implements Resolve<ITrancheMargin> {
    constructor(private service: TrancheMarginService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TrancheMargin> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TrancheMargin>) => response.ok),
                map((trancheMargin: HttpResponse<TrancheMargin>) => trancheMargin.body)
            );
        }
        return of(new TrancheMargin());
    }
}

export const trancheMarginRoute: Routes = [
    {
        path: 'tranche-margin',
        component: TrancheMarginComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TrancheMargins'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tranche-margin/:id/view',
        component: TrancheMarginDetailComponent,
        resolve: {
            trancheMargin: TrancheMarginResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TrancheMargins'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tranche-margin/new',
        component: TrancheMarginUpdateComponent,
        resolve: {
            trancheMargin: TrancheMarginResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TrancheMargins'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tranche-margin/:id/edit',
        component: TrancheMarginUpdateComponent,
        resolve: {
            trancheMargin: TrancheMarginResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TrancheMargins'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const trancheMarginPopupRoute: Routes = [
    {
        path: 'tranche-margin/:id/delete',
        component: TrancheMarginDeletePopupComponent,
        resolve: {
            trancheMargin: TrancheMarginResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TrancheMargins'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
