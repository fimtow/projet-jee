import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Plugins } from '@capacitor/core';
import { NavController } from '@ionic/angular';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class QuitGuard implements CanActivate {
  constructor(private authService: AuthService, private navCtrl: NavController) {}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      return Plugins.Storage.get({ key: 'authData' }).then(storedData => {
        if (storedData && storedData.value) {
          const parsedData = JSON.parse(storedData.value);
          if (parsedData && parsedData.isAuthenticated) {
            this.navCtrl.back();
            return false;
          }
          return true;
        }
        return true;
      })
  }
  
}
