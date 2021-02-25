import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PimagePage } from './pimage.page';

const routes: Routes = [
  {
    path: '',
    component: PimagePage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PimagePageRoutingModule {}
