import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PostPage } from './post.page';

const routes: Routes = [
  {
    path: '',
    component: PostPage
  },
  {
    path: 'crpost',
    loadChildren: () => import('./crpost/crpost.module').then( m => m.CrpostPageModule)
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PostPageRoutingModule {}
