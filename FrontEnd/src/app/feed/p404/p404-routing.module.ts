import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { P404Page } from './p404.page';

const routes: Routes = [
  {
    path: '',
    component: P404Page
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class P404PageRoutingModule {}
