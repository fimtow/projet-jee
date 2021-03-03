import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from 'src/app/Auth/auth.guard';

import { PostPage } from './post.page';

const routes: Routes = [
  {
    path: '',
    component: PostPage
  }
  // {
  //   path: 'notfound',
  //   loadChildren: () => import('./p404/p404.module').then( m => m.P404PageModule)
  // }
  // {
  //   path: 'create',
  //   loadChildren: () => import('./crpost/crpost.module').then( m => m.CrpostPageModule),
  //   canActivate: [AuthGuard]
  // }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PostPageRoutingModule {}
