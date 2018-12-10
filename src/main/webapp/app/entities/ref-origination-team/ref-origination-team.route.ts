import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefOriginationTeam } from 'app/shared/model/ref-origination-team.model';
import { RefOriginationTeamService } from './ref-origination-team.service';
import { RefOriginationTeamComponent } from './ref-origination-team.component';
import { RefOriginationTeamDetailComponent } from './ref-origination-team-detail.component';
import { RefOriginationTeamUpdateComponent } from './ref-origination-team-update.component';
import { RefOriginationTeamDeletePopupComponent } from './ref-origination-team-delete-dialog.component';
import { IRefOriginationTeam } from 'app/shared/model/ref-origination-team.model';

@Injectable({ providedIn: 'root' })
export class RefOriginationTeamResolve implements Resolve<IRefOriginationTeam> {
    constructor(private service: RefOriginationTeamService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RefOriginationTeam> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefOriginationTeam>) => response.ok),
                map((refOriginationTeam: HttpResponse<RefOriginationTeam>) => refOriginationTeam.body)
            );
        }
        return of(new RefOriginationTeam());
    }
}

export const refOriginationTeamRoute: Routes = [
    {
        path: 'ref-origination-team',
        component: RefOriginationTeamComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefOriginationTeams'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-origination-team/:id/view',
        component: RefOriginationTeamDetailComponent,
        resolve: {
            refOriginationTeam: RefOriginationTeamResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefOriginationTeams'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-origination-team/new',
        component: RefOriginationTeamUpdateComponent,
        resolve: {
            refOriginationTeam: RefOriginationTeamResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefOriginationTeams'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-origination-team/:id/edit',
        component: RefOriginationTeamUpdateComponent,
        resolve: {
            refOriginationTeam: RefOriginationTeamResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefOriginationTeams'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refOriginationTeamPopupRoute: Routes = [
    {
        path: 'ref-origination-team/:id/delete',
        component: RefOriginationTeamDeletePopupComponent,
        resolve: {
            refOriginationTeam: RefOriginationTeamResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefOriginationTeams'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
