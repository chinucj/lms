import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AddKtComponent } from './add-kt/add-kt.component';
import { ViewKtComponent } from './view-kt/view-kt.component';

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
      }
    ]
  },
 
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
