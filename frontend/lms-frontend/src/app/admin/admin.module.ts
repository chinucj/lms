import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { SidebarComponent } from '../components/sidebar/sidebar.component';
import { AddKtComponent } from './add-kt/add-kt.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ViewKtComponent } from './view-kt/view-kt.component';



@NgModule({
  declarations: [
    DashboardComponent,
    AdminHomeComponent,
    SidebarComponent,
    AddKtComponent,
    ViewKtComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ],
 
})
export class AdminModule { }
