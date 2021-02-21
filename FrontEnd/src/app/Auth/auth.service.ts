import { Plugins } from '@capacitor/core';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap, map, take } from 'rxjs/operators';
import { BehaviorSubject, from } from 'rxjs';
import { NavController, ToastController } from '@ionic/angular';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public apiUrl: string = 'http://localhost:3000';

  private _userId: string;
  private _accessToken: string = null;
  private _authStatusListener = new BehaviorSubject<boolean>(false);
  private _email: string;
  private expiresIn: number;
  private tokenTimer: any;
  private toast: any;
  private _profileSetListener = new BehaviorSubject<boolean>(false);

  constructor(
    private httpClient: HttpClient,
    private navCtrl: NavController,
    private router: Router,
    private toastCtrl: ToastController
  ) { }

  get email() {
    return this._email;
  }

  get userId() {
    return this._userId;
  }

  get token() {
    return this._accessToken;
  }

  get authStatusListener() {
    return this._authStatusListener.asObservable();
  }


  autoLogin() {
    return from(Plugins.Storage.get({ key: 'authData' })).pipe(take(1), map(storedData => {
      if (!storedData || !storedData.value) {
        return false;
      }
      const parsedData = JSON.parse(storedData.value) as {
        userId: string,
        email: string,
        token: string,
        set: boolean,
        expirationDate: string
      };
      const expirationDate = new Date(parsedData.expirationDate);
      if (expirationDate <= new Date()) {
        this.logout();
        return false;
      }
      this._accessToken = parsedData.token;
      this._email = parsedData.email;
      this._userId = parsedData.userId;
      this.expiresIn = (expirationDate.getTime() - (new Date()).getTime()) / 1000;
      this._profileSetListener.next(parsedData.set);
      this._authStatusListener.next(true);
      const timeout = setTimeout(() => this.httpClient.post<{ message?: string, token?: string, expiresIn?: number }>(this.apiUrl + '/user/token', { id: parsedData.userId }).subscribe(resData => {
        this._accessToken = resData.token;
        this.expiresIn = resData.expiresIn;
        this.storeAuthData(parsedData.email);
        clearTimeout(timeout);
        clearInterval(this.tokenTimer);
        this.tokenTimer = setInterval(() => {
          if (this.userId != parsedData.userId) {
            clearInterval(this.tokenTimer);
            return;
          }
          this.httpClient.post<{ message?: string, token?: string, expiresIn?: number }>(this.apiUrl + '/user/token', { id: parsedData.userId }).subscribe(resData => {
            this._accessToken = resData.token;
            this.expiresIn = resData.expiresIn;
            this.storeAuthData(parsedData.email);
          }, err => {
            this.logout();
            this.toastCtrl.create({
              message: err.error.message,
              position: "bottom",
              duration: 4000
            }).then(toastElement => {
              if (this.toast) {
                this.toast.dismiss();
              }
              this.toast = toastElement;
              toastElement.present();
            });
          });
        }, (this.expiresIn - 30) * 1000);
      }, err => {
        clearTimeout(timeout);
        clearInterval(this.tokenTimer);
        this.logout();
        this.toastCtrl.create({
          message: err.error.message,
          position: "bottom",
          duration: 4000
        }).then(toastElement => {
          if (this.toast) {
            this.toast.dismiss();
          }
          this.toast = toastElement;
          toastElement.present();
          clearTimeout(timeout);
        });
      }), this.expiresIn * 1000);
      return true;
    }));
  }

  authenticate(
    email: string,
    password: string,
    requestType: 'login' | 'signup'
  ) {
    return this.httpClient.post<{ message?: string, id?: string, token?: string, expiresIn?: number, verified?: boolean, profileSet?: boolean }>(
    this.apiUrl + `/user/${requestType}`, { email, password })
    .pipe(take(1), tap(resData => {
      this._accessToken = resData.token;
      if (this._accessToken) {
        this._email = email;
        this._userId = resData.id;
        this._authStatusListener.next(true);
        this.expiresIn = resData.expiresIn;
        this._profileSetListener.next(resData.profileSet);
        this.storeAuthData(email);
        clearInterval(this.tokenTimer);
        this.tokenTimer = setInterval(() => {
          if (this.userId != resData.id) {
            clearInterval(this.tokenTimer);
            return;
          }
          this.httpClient.post<{ message?: string, token?: string, expiresIn?: number }>(this.apiUrl + '/user/token', { id: this.userId }).subscribe(resData => {
            this._accessToken = resData.token;
            this.expiresIn = resData.expiresIn;
            this.storeAuthData(email);
          }, err => {
            this.logout();
            this.toastCtrl.create({
              message: err.error.message,
              position: "bottom",
              duration: 4000
            }).then(toastElement => {
              if (this.toast) {
                this.toast.dismiss();
              }
              this.toast = toastElement;
              toastElement.present();
            });
          });
        }, (this.expiresIn - 30) * 1000);
      }
    }));
  }

  resetPasswordRequest(email: string) {
    return this.httpClient.post<{ message?: string }>(this.apiUrl + '/user/resetRequest', { email }).pipe(take(1), tap(resData => {
      this._email = email;
      const data = JSON.stringify({
        email: email
      });
      Plugins.Storage.set({
        key: 'resetData',
        value: data
      });
    }));
  }

  logout() {
    this._userId = null;
    this._accessToken = null;
    this._authStatusListener.next(false);
    clearInterval(this.tokenTimer);
    this.tokenTimer = null;
    Plugins.Storage.clear().then(() => {
      if (!this.router.url.includes("/home") && !this.router.url.includes("/login") && !this.router.url.includes("/register"))
        this.navCtrl.navigateRoot("/login");
    });
  }

  private storeAuthData(email: string) {
    this.profileSet.pipe(take(1), tap(status => {
      const data = JSON.stringify({
        userId: this.userId,
        email: email,
        token: this.token,
        set: status, 
        expirationDate: new Date(new Date().getTime() + (this.expiresIn - 30) * 1000).toISOString()
      });
      Plugins.Storage.set({
        key: 'authData',
        value: data
      });
    })).subscribe();
  }
}
