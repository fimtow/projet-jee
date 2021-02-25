import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PtextPage } from './ptext.page';

const routes: Routes = [
  {
    path: '',
    component: PtextPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PtextPageRoutingModule {}
