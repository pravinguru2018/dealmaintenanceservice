import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefCountry } from 'app/shared/model/ref-country.model';
import { RefCountryService } from './ref-country.service';
import { RefCountryComponent } from './ref-country.component';
import { RefCountryDetailComponent } from './ref-country-detail.component';
import { RefCountryUpdateComponent } from './ref-country-update.component';
import { RefCountryDeletePopupComponent } from './ref-country-delete-dialog.component';
import { IRefCountry } from 'app/shared/model/ref-country.model';

@Injectable({ providedIn: 'root' })
export class RefCountryResolve implements Resolve<IRefCountry> {
    constructor(private service: RefCountryService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RefCountry> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefCountry>) => response.ok),
                map((refCountry: HttpResponse<RefCountry>) => refCountry.body)
            );
        }
        return of(new RefCountry());
    }
}

export const refCountryRoute: Routes = [
    {
        path: 'ref-country',
        component: RefCountryComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefCountries'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-country/:id/view',
        component: RefCountryDetailComponent,
        resolve: {
            refCountry: RefCountryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefCountries'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-country/new',
        component: RefCountryUpdateComponent,
        resolve: {
            refCountry: RefCountryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefCountries'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ref-country/:id/edit',
        component: RefCountryUpdateComponent,
        resolve: {
            refCountry: RefCountryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefCountries'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refCountryPopupRoute: Routes = [
    {
        path: 'ref-country/:id/delete',
        component: RefCountryDeletePopupComponent,
        resolve: {
            refCountry: RefCountryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefCountries'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
