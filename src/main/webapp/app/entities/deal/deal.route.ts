import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Deal } from 'app/shared/model/deal.model';
import { DealService } from './deal.service';
import { DealComponent } from './deal.component';
import { DealDetailComponent } from './deal-detail.component';
import { DealUpdateComponent } from './deal-update.component';
import { DealDeletePopupComponent } from './deal-delete-dialog.component';
import { IDeal } from 'app/shared/model/deal.model';

@Injectable({ providedIn: 'root' })
export class DealResolve implements Resolve<IDeal> {
    constructor(private service: DealService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Deal> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Deal>) => response.ok),
                map((deal: HttpResponse<Deal>) => deal.body)
            );
        }
        return of(new Deal());
    }
}

export const dealRoute: Routes = [
    {
        path: 'deal',
        component: DealComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Deals'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'deal/:id/view',
        component: DealDetailComponent,
        resolve: {
            deal: DealResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Deals'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'deal/new',
        component: DealUpdateComponent,
        resolve: {
            deal: DealResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Deals'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'deal/:id/edit',
        component: DealUpdateComponent,
        resolve: {
            deal: DealResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Deals'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const dealPopupRoute: Routes = [
    {
        path: 'deal/:id/delete',
        component: DealDeletePopupComponent,
        resolve: {
            deal: DealResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Deals'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
