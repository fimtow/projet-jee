import { Component, OnInit } from '@angular/core';
import { Power2, TweenLite, TweenMax, Expo } from "gsap";

import '../../../assets/login-animation.js';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  ngAfterViewInit() {
    (window as any).initialize();
  }
  
}
