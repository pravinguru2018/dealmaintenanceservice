import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefRatingCrr } from 'app/shared/model/ref-rating-crr.model';
import { RefRatingCrrService } from './ref-rating-crr.service';
import { RefRatingCrrComponent } from './ref-rating-crr.component';
import { RefRatingCrrDetailComponent } from './ref-rating-crr-detail.component';
import { RefRatingCrrUpdateComponent } from './ref-rating-crr-update.component';
import { RefRatingCrrDeletePopupComponent } from './ref-rating-crr-delete-dialog.component';
import { IRefRatingCrr } from 'app/shared/model/ref-rating-crr.model';

@Injectable({ providedIn: 'root' })
export class RefRatingCrrResolve implements Resolve<IRefRatingCrr> {
    constructor(private service: RefRatingCrrService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RefRatingCrr> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefRatingCrr>) => response.ok),
                map((refRatingCrr: HttpResponse<RefRatingCrr>) => refRatingCrr.body)
            );
        }
        return of(new RefRatingCrr());
    }
}

export const refRatingCrrRoute: Routes = [
    {
        path: 'ref-rating-crr',
        component: RefRatingCrrComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRatingCrrs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-rating-crr/:id/view',
        component: RefRatingCrrDetailComponent,
        resolve: {
            refRatingCrr: RefRatingCrrResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRatingCrrs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-rating-crr/new',
        component: RefRatingCrrUpdateComponent,
        resolve: {
            refRatingCrr: RefRatingCrrResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRatingCrrs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-rating-crr/:id/edit',
        component: RefRatingCrrUpdateComponent,
        resolve: {
            refRatingCrr: RefRatingCrrResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRatingCrrs'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refRatingCrrPopupRoute: Routes = [
    {
        path: 'ref-rating-crr/:id/delete',
        component: RefRatingCrrDeletePopupComponent,
        resolve: {
            refRatingCrr: RefRatingCrrResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRatingCrrs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
