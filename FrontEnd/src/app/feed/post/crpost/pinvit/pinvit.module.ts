import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PinvitPageRoutingModule } from './pinvit-routing.module';

import { PinvitPage } from './pinvit.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PinvitPageRoutingModule
  ],
  declarations: [PinvitPage]
})
export class PinvitPageModule {}
