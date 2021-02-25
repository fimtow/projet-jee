import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PtextPageRoutingModule } from './ptext-routing.module';

import { PtextPage } from './ptext.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PtextPageRoutingModule
  ],
  declarations: [PtextPage]
})
export class PtextPageModule {}
