import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { CrtopicPageRoutingModule } from './crtopic-routing.module';

import { CrtopicPage } from './crtopic.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    CrtopicPageRoutingModule
  ],
  declarations: [CrtopicPage]
})
export class CrtopicPageModule {}
