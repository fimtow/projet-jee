import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { TopicPage } from './topic.page';

const routes: Routes = [
  {
    path: '',
    component: TopicPage
  },
  {
    path: 'crtopic',
    loadChildren: () => import('./crtopic/crtopic.module').then( m => m.CrtopicPageModule)
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TopicPageRoutingModule {}
