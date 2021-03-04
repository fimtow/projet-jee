import { Plugins } from '@capacitor/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap, map, take } from 'rxjs/operators';
import { BehaviorSubject, from } from 'rxjs';
import { NavController, ToastController } from '@ionic/angular';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public apiUrl: string = 'http://stonks.fimtow.com/application';
  private _cookieValue = new BehaviorSubject<string>('');
  private optionRequete = {
    headers: new HttpHeaders({
      'Access-Control-Allow-Origin': '*'
    }),
    observe: 'events',
    responseType: 'json',
    withCredentials: true
  };

  private _userId: string;
  private _authStatusListener = new BehaviorSubject<boolean>(false);
  private _email: string;
  private toast: any;
  private _authenticated: boolean = false;

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

  set userId(id: string){
    this._userId = id;
  }


  get authStatusListener() {
    return this._authStatusListener.asObservable();
  }

  get cookieValue() {
    return this._cookieValue.asObservable();
  }

  autoLogin() {
    return from(Plugins.Storage.get({ key: 'authData' })).pipe(take(1), map(storedData => {
      if (!storedData || !storedData.value) {
        return false;
      }
      const parsedData = JSON.parse(storedData.value);
      if (parsedData.isAuthenticated) {
        this._authenticated = parsedData.isAuthenticated;
        this._authStatusListener.next(true);
        return true;
      } else {
        return false;
      }

    }));
  }

  authenticate(
    username: string,
    password: string
  ) {
    return this.httpClient.post<any>(
      this.apiUrl + `/signin?username=${username}&password=${password}`, {}
    ).pipe(tap(resData => {
      console.log(resData);
      this._authenticated = resData.success;
      if (this._authenticated) {
        this._authStatusListener.next(true);
        this.userId = resData.id;
        this.storeAuthData();
      }
    }));
  }

  // resetPasswordRequest(email: string) {
  //   return this.httpClient.post<{ message?: string }>(this.apiUrl + '/user/resetRequest', { email }).pipe(take(1), tap(resData => {
  //     this._email = email;
  //     const data = JSON.stringify({
  //       email: email
  //     });
  //     Plugins.Storage.set({
  //       key: 'resetData',
  //       value: data
  //     });
  //   }));
  // }

  logout() {
    this._authenticated = false;
    this._authStatusListener.next(false);
    Plugins.Storage.clear().then(() => {
      this.router.navigateByUrl('/login');
    });
  }

  private storeAuthData() {
    const data = JSON.stringify({
      isAuthenticated: this._authenticated,
      userId: this.userId
    });
    Plugins.Storage.set({
      key: 'authData',
      value: data
    });
  }

  recheckAuth() {
    return from(Plugins.Storage.get({ key: 'authData' })).pipe(take(1), map(storedData => {
      if (!storedData || !storedData.value) {
        return false;
      }
      const parsedData = JSON.parse(storedData.value);
      if (parsedData.isAuthenticated) {
        this._authenticated = parsedData.isAuthenticated;
        this._authStatusListener.next(true);
        return true;
      } else {
        return false;
      }
    }));
  }

  getUserId(){
    return from(Plugins.Storage.get({ key: 'authData' })).pipe(take(1), map(storedData => {
      if (!storedData || !storedData.value) {
        return "";
      }
      const parsedData = JSON.parse(storedData.value);
      if (parsedData.isAuthenticated) {
        return parsedData.userId;
      } else {
        return "";
      }
    }));
  }

  retreive() {
    return this.httpClient.get<any>(
      this.apiUrl + '/test',
      {
        withCredentials: true
      }
    );
  }

}
