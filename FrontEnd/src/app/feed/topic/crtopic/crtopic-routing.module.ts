import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CrtopicPage } from './crtopic.page';

const routes: Routes = [
  {
    path: '',
    component: CrtopicPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CrtopicPageRoutingModule {}
