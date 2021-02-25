import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PimagePageRoutingModule } from './pimage-routing.module';

import { PimagePage } from './pimage.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PimagePageRoutingModule
  ],
  declarations: [PimagePage]
})
export class PimagePageModule {}
