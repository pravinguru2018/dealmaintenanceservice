import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefFacilityType } from 'app/shared/model/ref-facility-type.model';
import { RefFacilityTypeService } from './ref-facility-type.service';
import { RefFacilityTypeComponent } from './ref-facility-type.component';
import { RefFacilityTypeDetailComponent } from './ref-facility-type-detail.component';
import { RefFacilityTypeUpdateComponent } from './ref-facility-type-update.component';
import { RefFacilityTypeDeletePopupComponent } from './ref-facility-type-delete-dialog.component';
import { IRefFacilityType } from 'app/shared/model/ref-facility-type.model';

@Injectable({ providedIn: 'root' })
export class RefFacilityTypeResolve implements Resolve<IRefFacilityType> {
    constructor(private service: RefFacilityTypeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RefFacilityType> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefFacilityType>) => response.ok),
                map((refFacilityType: HttpResponse<RefFacilityType>) => refFacilityType.body)
            );
        }
        return of(new RefFacilityType());
    }
}

export const refFacilityTypeRoute: Routes = [
    {
        path: 'ref-facility-type',
        component: RefFacilityTypeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefFacilityTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-facility-type/:id/view',
        component: RefFacilityTypeDetailComponent,
        resolve: {
            refFacilityType: RefFacilityTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefFacilityTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-facility-type/new',
        component: RefFacilityTypeUpdateComponent,
        resolve: {
            refFacilityType: RefFacilityTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefFacilityTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-facility-type/:id/edit',
        component: RefFacilityTypeUpdateComponent,
        resolve: {
            refFacilityType: RefFacilityTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefFacilityTypes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refFacilityTypePopupRoute: Routes = [
    {
        path: 'ref-facility-type/:id/delete',
        component: RefFacilityTypeDeletePopupComponent,
        resolve: {
            refFacilityType: RefFacilityTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefFacilityTypes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
