import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CrpostPage } from './crpost.page';

const routes: Routes = [
  {
    path: '',
    component: CrpostPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CrpostPageRoutingModule {}
