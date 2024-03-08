import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AddKtComponent } from './add-kt/add-kt.component';
import { ViewKtComponent } from './view-kt/view-kt.component';
import { AddResourceComponent } from './add-resource/add-resource.component';
import { ViewResourceComponent } from './view-resource/view-resource.component';

const routes: Routes = [
  {
    path : 'admin',
    component : AdminHomeComponent,
    children : [
      {
        path : 'dashboard',
        component : DashboardComponent
      },
      {
        path : 'add-kt',
        component : AddKtComponent
      },
      {
        path : 'view-kt',
        component : ViewKtComponent
      },
      {
        path : 'edit-kt/:id',
        component : AddKtComponent
      },
      {
        path : 'add-resource',
        component : AddResourceComponent
      },
      {
        path : 'view-resource',
        component : ViewResourceComponent
      },
      {
        path : 'edit-resource/:id',
        component : AddResourceComponent
      }
    ]
  },
 
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
