import { Component, OnInit } from '@angular/core';
import { NavController, PopoverController } from '@ionic/angular';
import { AuthService } from 'src/app/Auth/auth.service';

@Component({
  selector: 'app-popover',
  templateUrl: './popover.component.html',
  styleUrls: ['./popover.component.scss'],
})
export class PopoverComponent implements OnInit {

  constructor(private navCtrl: NavController, private authService: AuthService, private popoverController: PopoverController) { }

  ngOnInit() {}

  goToProfile(){
    this.popoverController.dismiss();
    this.navCtrl.navigateRoot('/userpage');
  }

  signout(){
    this.popoverController.dismiss();
    this.authService.logout();
  }
}
