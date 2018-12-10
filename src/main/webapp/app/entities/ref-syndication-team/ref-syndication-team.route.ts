import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefSyndicationTeam } from 'app/shared/model/ref-syndication-team.model';
import { RefSyndicationTeamService } from './ref-syndication-team.service';
import { RefSyndicationTeamComponent } from './ref-syndication-team.component';
import { RefSyndicationTeamDetailComponent } from './ref-syndication-team-detail.component';
import { RefSyndicationTeamUpdateComponent } from './ref-syndication-team-update.component';
import { RefSyndicationTeamDeletePopupComponent } from './ref-syndication-team-delete-dialog.component';
import { IRefSyndicationTeam } from 'app/shared/model/ref-syndication-team.model';

@Injectable({ providedIn: 'root' })
export class RefSyndicationTeamResolve implements Resolve<IRefSyndicationTeam> {
    constructor(private service: RefSyndicationTeamService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RefSyndicationTeam> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefSyndicationTeam>) => response.ok),
                map((refSyndicationTeam: HttpResponse<RefSyndicationTeam>) => refSyndicationTeam.body)
            );
        }
        return of(new RefSyndicationTeam());
    }
}

export const refSyndicationTeamRoute: Routes = [
    {
        path: 'ref-syndication-team',
        component: RefSyndicationTeamComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefSyndicationTeams'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-syndication-team/:id/view',
        component: RefSyndicationTeamDetailComponent,
        resolve: {
            refSyndicationTeam: RefSyndicationTeamResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefSyndicationTeams'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-syndication-team/new',
        component: RefSyndicationTeamUpdateComponent,
        resolve: {
            refSyndicationTeam: RefSyndicationTeamResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefSyndicationTeams'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-syndication-team/:id/edit',
        component: RefSyndicationTeamUpdateComponent,
        resolve: {
            refSyndicationTeam: RefSyndicationTeamResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefSyndicationTeams'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refSyndicationTeamPopupRoute: Routes = [
    {
        path: 'ref-syndication-team/:id/delete',
        component: RefSyndicationTeamDeletePopupComponent,
        resolve: {
            refSyndicationTeam: RefSyndicationTeamResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefSyndicationTeams'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
