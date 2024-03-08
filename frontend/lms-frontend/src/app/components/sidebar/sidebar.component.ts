import { Component } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
})
export class SidebarComponent {
  constructor(private route: Router) {}

  username = sessionStorage.getItem('fullName');

  ngOnInit(){}
  logout() {
    Swal.fire({
      title: 'Are you sure?',
      text: 'Do you want to logout?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Yes, logout',
      cancelButtonText: 'No, stay logged in',
    }).then((result) => {
      if (result.isConfirmed) {
        sessionStorage.clear();
        this.route.navigateByUrl('/login');
      }
    });
  }
}
