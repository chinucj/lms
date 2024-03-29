import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { SidebarComponent } from '../components/sidebar/sidebar.component';
import { AddKtComponent } from './add-kt/add-kt.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ViewKtComponent } from './view-kt/view-kt.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { AddResourceComponent } from './add-resource/add-resource.component';
import { ViewResourceComponent } from './view-resource/view-resource.component';



@NgModule({
  declarations: [
    DashboardComponent,
    AdminHomeComponent,
    SidebarComponent,
    AddKtComponent,
    ViewKtComponent,
    AddResourceComponent,
    ViewResourceComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    NgxPaginationModule
  ],
 
})
export class AdminModule { }
