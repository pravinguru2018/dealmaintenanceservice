import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefBookingEntity } from 'app/shared/model/ref-booking-entity.model';
import { RefBookingEntityService } from './ref-booking-entity.service';
import { RefBookingEntityComponent } from './ref-booking-entity.component';
import { RefBookingEntityDetailComponent } from './ref-booking-entity-detail.component';
import { RefBookingEntityUpdateComponent } from './ref-booking-entity-update.component';
import { RefBookingEntityDeletePopupComponent } from './ref-booking-entity-delete-dialog.component';
import { IRefBookingEntity } from 'app/shared/model/ref-booking-entity.model';

@Injectable({ providedIn: 'root' })
export class RefBookingEntityResolve implements Resolve<IRefBookingEntity> {
    constructor(private service: RefBookingEntityService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RefBookingEntity> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefBookingEntity>) => response.ok),
                map((refBookingEntity: HttpResponse<RefBookingEntity>) => refBookingEntity.body)
            );
        }
        return of(new RefBookingEntity());
    }
}

export const refBookingEntityRoute: Routes = [
    {
        path: 'ref-booking-entity',
        component: RefBookingEntityComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefBookingEntities'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-booking-entity/:id/view',
        component: RefBookingEntityDetailComponent,
        resolve: {
            refBookingEntity: RefBookingEntityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefBookingEntities'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-booking-entity/new',
        component: RefBookingEntityUpdateComponent,
        resolve: {
            refBookingEntity: RefBookingEntityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefBookingEntities'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-booking-entity/:id/edit',
        component: RefBookingEntityUpdateComponent,
        resolve: {
            refBookingEntity: RefBookingEntityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefBookingEntities'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refBookingEntityPopupRoute: Routes = [
    {
        path: 'ref-booking-entity/:id/delete',
        component: RefBookingEntityDeletePopupComponent,
        resolve: {
            refBookingEntity: RefBookingEntityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefBookingEntities'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
