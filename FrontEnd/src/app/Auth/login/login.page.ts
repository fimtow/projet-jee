//import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertController, LoadingController, NavController } from '@ionic/angular';
import { CookieService } from 'ngx-cookie-service';

import '../../../assets/login-animation.js';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  public cookieValue: string = '';
  constructor(
    private authService: AuthService,
    private navCtrl: NavController,
    private alertCtrl: AlertController,
    private router: Router,
    private loadingCtrl: LoadingController,
    private cookieService: CookieService
  ) { }

  ngOnInit() {
  }

  ngAfterViewInit() {
    (window as any).initialize();
  }

  public login(form: NgForm) {
    if (!form.valid) {
      return;
    }
    const username = form.value.username;
    const password = form.value.password;
    this.loadingCtrl.create({
      message: 'Logging in...',
      spinner: 'circular'
    }).then(loadingElement => {
      loadingElement.present();
      this.authService.authenticate(username, password)
        .subscribe(resData => {
          console.log(resData);
          if (resData) {
            loadingElement.dismiss();
            this.navCtrl.navigateRoot('/home');
            // this.cookieValue = this.cookieService.get('JSESSIONID');
            // console.log(this.cookieValue);
          }
          else {
            loadingElement.dismiss();
            loadingElement.dismiss();
          this.alertCtrl.create({
            header: 'Something wrong',
            message: 'Username or password incorrect',
            buttons: ['Ok']
          }).then(alertElement => {
            alertElement.present();
          });
            console.log("Login failed");
            // this.modalCtrl.create({
            //   component: VerifyEmailComponent,
            //   componentProps: {
            //     'email': email,
            //     'password': password,
            //     'role': role
            //   }
            // }).then(modalElement => {
            //   loadingElement.dismiss();
            //   modalElement.present();
            // });
          }
        }, err => {
          console.log(err);
          loadingElement.dismiss();
          this.alertCtrl.create({
            header: 'An error has occured',
            message: err.error.message ? err.error.message : 'Failed to communicate with server. Please try again later...',
            buttons: ['Ok']
          }).then(alertElement => {
            alertElement.present();
          });
        });
    });

  }

  login0(form: NgForm) {
    console.log(form.value);
  }

}
