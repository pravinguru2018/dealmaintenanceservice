import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefRatingFitch } from 'app/shared/model/ref-rating-fitch.model';
import { RefRatingFitchService } from './ref-rating-fitch.service';
import { RefRatingFitchComponent } from './ref-rating-fitch.component';
import { RefRatingFitchDetailComponent } from './ref-rating-fitch-detail.component';
import { RefRatingFitchUpdateComponent } from './ref-rating-fitch-update.component';
import { RefRatingFitchDeletePopupComponent } from './ref-rating-fitch-delete-dialog.component';
import { IRefRatingFitch } from 'app/shared/model/ref-rating-fitch.model';

@Injectable({ providedIn: 'root' })
export class RefRatingFitchResolve implements Resolve<IRefRatingFitch> {
    constructor(private service: RefRatingFitchService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RefRatingFitch> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefRatingFitch>) => response.ok),
                map((refRatingFitch: HttpResponse<RefRatingFitch>) => refRatingFitch.body)
            );
        }
        return of(new RefRatingFitch());
    }
}

export const refRatingFitchRoute: Routes = [
    {
        path: 'ref-rating-fitch',
        component: RefRatingFitchComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRatingFitches'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-rating-fitch/:id/view',
        component: RefRatingFitchDetailComponent,
        resolve: {
            refRatingFitch: RefRatingFitchResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRatingFitches'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-rating-fitch/new',
        component: RefRatingFitchUpdateComponent,
        resolve: {
            refRatingFitch: RefRatingFitchResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRatingFitches'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-rating-fitch/:id/edit',
        component: RefRatingFitchUpdateComponent,
        resolve: {
            refRatingFitch: RefRatingFitchResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRatingFitches'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refRatingFitchPopupRoute: Routes = [
    {
        path: 'ref-rating-fitch/:id/delete',
        component: RefRatingFitchDeletePopupComponent,
        resolve: {
            refRatingFitch: RefRatingFitchResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRatingFitches'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
