import { Component, Input, OnInit } from '@angular/core';
import { NavController, PopoverController } from '@ionic/angular';
import { take, switchMap, tap } from 'rxjs/operators';
import { AuthService } from 'src/app/Auth/auth.service';
import { PopoverComponent } from './popover/popover.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  public isAuth: boolean = false;

  constructor(private navCtrl: NavController, private authService: AuthService, private popoverController: PopoverController) {
    // this.authService.autoLogin().subscribe(data => {
    //   this.isAuth = data;
    //   // console.log("subscribing auth from header: ",data);
    // })
    
   }

   async presentPopover(ev: any) {
    const popover = await this.popoverController.create({
      component: PopoverComponent,
      cssClass: 'popover-class',
      event: ev,
      translucent: true
    });
    return await popover.present();
  }
  ngOnInit() {
    // this.authService.authStatusListener.pipe(take(1), tap(isAuthent => {
    //   this.isAuth = isAuthent;
    //   console.log(this.isAuth);
    // })).subscribe();
    // this.authService.autoLogin().subscribe(data => {
    //   this.isAuth = data;
    //   // console.log("subscribing auth from header: ",data);
    // })
    this.authService.authStatusListener.subscribe(data => {
      this.isAuth = data;
    })
  }

  goToHome(){
    this.navCtrl.navigateRoot('/')
  }
}
