import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PinvitPage } from './pinvit.page';

const routes: Routes = [
  {
    path: '',
    component: PinvitPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PinvitPageRoutingModule {}
