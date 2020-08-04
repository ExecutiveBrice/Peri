import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import { DetailComponent }      from './pages/detail/detail.component';
import { GlobalComponent } from './pages/global/global.component';


const routes: Routes = [
  { path: '', redirectTo: '/', pathMatch: 'full' },
  { path: '', component: GlobalComponent },
  { path: 'detail/:id', component: DetailComponent },

];

@NgModule({
  imports: [ RouterModule.forRoot(routes,
    { useHash: true }) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
