import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CrpostPage } from './crpost.page';

const routes: Routes = [
  {
    path: '',
    component: CrpostPage
  }
  // {
  //   path: 'pimage',
  //   loadChildren: () => import('./pimage/pimage.module').then( m => m.PimagePageModule)
  // },
  // {
  //   path: 'ptext',
  //   loadChildren: () => import('./ptext/ptext.module').then( m => m.PtextPageModule)
  // },
  // {
  //   path: 'pinvit',
  //   loadChildren: () => import('./pinvit/pinvit.module').then( m => m.PinvitPageModule)
  // }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CrpostPageRoutingModule {}
