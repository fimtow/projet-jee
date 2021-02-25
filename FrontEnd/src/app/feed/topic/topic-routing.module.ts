import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from 'src/app/Auth/auth.guard';

import { TopicPage } from './topic.page';

const routes: Routes = [
  {
    path: '',
    component: TopicPage
  },
  {
    path: 'create',
    loadChildren: () => import('./crtopic/crtopic.module').then( m => m.CrtopicPageModule),
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TopicPageRoutingModule {}
