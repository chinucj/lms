import { Component } from '@angular/core';
import { ResourceService } from '../resource.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-resource',
  templateUrl: './view-resource.component.html',
  styleUrls: ['./view-resource.component.css']
})
export class ViewResourceComponent {


  usersList: any;

  constructor(private service: ResourceService , private router : Router) {}

  ngOnInit() {
    this.getUsersList();
  }
  getUsersList() {
    this.service.getUsersList().subscribe((data: any) => {
      console.log(data);

      this.usersList = data;
    });
  }

  editUser(id: any) {
    this.router.navigateByUrl('/admin/edit-resource/'+ id);
  }

  deleteUser(id: any) {
    Swal.fire({
      title: 'Are you sure?',
      text: 'You won\'t be able to revert this!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'No, keep it'
    }).then((result) => {
      if (result.isConfirmed) {
        this.service.deleteUserById(id).subscribe((data)=>{
          Swal.fire(
            'Deleted!',
            'Your data has been deleted.',
            'success'
          );
          this.getUsersList();
        },(error)=>{
          Swal.fire('Error','Something went wrong !','error');
        })
        
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire(
          'Cancelled',
          'Your data is safe :)',
          'error'
        );
      }
    });
    }
}
