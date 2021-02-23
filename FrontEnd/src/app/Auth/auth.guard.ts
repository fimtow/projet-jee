import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { NavController } from '@ionic/angular';
import { Observable, of } from 'rxjs';
import { take, switchMap, tap } from 'rxjs/operators';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private navCtrl: NavController) {}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.authService.authStatusListener.pipe(take(1), switchMap(isAuthenticated => {
      // console.log(isAuthenticated);
      if (!isAuthenticated) {
        return this.authService.autoLogin();
      }
      return of(isAuthenticated);
    }), tap(isAuthenticated => {
      if (!isAuthenticated) {
        this.navCtrl.navigateRoot('/login');
      }
    }));
  }

}