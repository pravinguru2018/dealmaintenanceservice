import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefRatingMoodys } from 'app/shared/model/ref-rating-moodys.model';
import { RefRatingMoodysService } from './ref-rating-moodys.service';
import { RefRatingMoodysComponent } from './ref-rating-moodys.component';
import { RefRatingMoodysDetailComponent } from './ref-rating-moodys-detail.component';
import { RefRatingMoodysUpdateComponent } from './ref-rating-moodys-update.component';
import { RefRatingMoodysDeletePopupComponent } from './ref-rating-moodys-delete-dialog.component';
import { IRefRatingMoodys } from 'app/shared/model/ref-rating-moodys.model';

@Injectable({ providedIn: 'root' })
export class RefRatingMoodysResolve implements Resolve<IRefRatingMoodys> {
    constructor(private service: RefRatingMoodysService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RefRatingMoodys> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefRatingMoodys>) => response.ok),
                map((refRatingMoodys: HttpResponse<RefRatingMoodys>) => refRatingMoodys.body)
            );
        }
        return of(new RefRatingMoodys());
    }
}

export const refRatingMoodysRoute: Routes = [
    {
        path: 'ref-rating-moodys',
        component: RefRatingMoodysComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRatingMoodys'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-rating-moodys/:id/view',
        component: RefRatingMoodysDetailComponent,
        resolve: {
            refRatingMoodys: RefRatingMoodysResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRatingMoodys'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-rating-moodys/new',
        component: RefRatingMoodysUpdateComponent,
        resolve: {
            refRatingMoodys: RefRatingMoodysResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRatingMoodys'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-rating-moodys/:id/edit',
        component: RefRatingMoodysUpdateComponent,
        resolve: {
            refRatingMoodys: RefRatingMoodysResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRatingMoodys'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refRatingMoodysPopupRoute: Routes = [
    {
        path: 'ref-rating-moodys/:id/delete',
        component: RefRatingMoodysDeletePopupComponent,
        resolve: {
            refRatingMoodys: RefRatingMoodysResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefRatingMoodys'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
