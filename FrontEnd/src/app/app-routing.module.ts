import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './Auth/auth.guard';
import { QuitGuard } from './Auth/quit.guard';

const routes: Routes = [
  {
    path: 'home',
    loadChildren: () => import('./home/home.module').then( m => m.HomePageModule),
    canActivate: [AuthGuard]
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'homepage',
    loadChildren: () => import('./home-page/home-page.module').then( m => m.HomePagePageModule),
    canActivate: [QuitGuard]
  },
  {
    path: 'login',
    loadChildren: () => import('./Auth/login/login.module').then( m => m.LoginPageModule),
    canActivate: [QuitGuard]
  },
  {
    path: 'signup',
    loadChildren: () => import('./Auth/signup/signup.module').then( m => m.SignupPageModule),
    canActivate: [QuitGuard]
  },
  {
    path: 'profile/:id',
    loadChildren: () => import('./user-page/user-page.module').then( m => m.UserPagePageModule)
  },
  {
    path: 'topic/:id',
    loadChildren: () => import('./feed/topic/topic.module').then( m => m.TopicPageModule)
  },
  {
    path: 'post/:id',
    loadChildren: () => import('./feed/post/post.module').then( m => m.PostPageModule)
  },
  {
    path: 'create',
    children : [
      {
        path: 'post/:id',
        loadChildren: () => import('./feed/post/crpost/crpost.module').then( m => m.CrpostPageModule)
      },
      {
        path: 'topic',
        loadChildren: () => import('./feed/topic/crtopic/crtopic.module').then( m => m.CrtopicPageModule)
      }
    ],
    canActivate: [AuthGuard]
  },
  {
    path: 'error',
    children: [
      {
        path: 'notfound',
        loadChildren: () => import('./feed/p404/p404.module').then( m => m.P404PageModule)
      }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules})
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
