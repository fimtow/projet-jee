import { Component, Input, OnInit } from '@angular/core';
import { NavController, PopoverController } from '@ionic/angular';
import { take, switchMap, tap } from 'rxjs/operators';
import { AuthService } from 'src/app/Auth/auth.service';
import { FeedService } from 'src/app/feed/feed.service';
import { PopoverComponent } from './popover/popover.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  public isAuth: boolean = false;
  public username: string = '';
  public userId : string = '';

  public searchbarShown: boolean = false;
  public searchTop: string = "";

  constructor(private navCtrl: NavController, private authService: AuthService, private popoverController: PopoverController, private feedService: FeedService) { }

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
    this.authService.authStatusListener.subscribe(data => {
      this.isAuth = data;
    })
    this.authService.getUserId().subscribe(id => {
      this.feedService.getUserInfo(id).subscribe(data=>{
        if(data.success){
          this.username = data.user.username;
        }
      });
    });
  }

  goToHome(){
    this.navCtrl.navigateRoot('/')
  }

  search(){
    if(this.searchTop == ""){
      this.navCtrl.navigateRoot('/home');
    } else {
      var url = "/search/"+this.searchTop;
      this.navCtrl.navigateRoot(url);
    }
  }

}
