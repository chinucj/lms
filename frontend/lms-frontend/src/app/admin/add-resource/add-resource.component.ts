import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ResourceService } from '../resource.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-resource',
  templateUrl: './add-resource.component.html',
  styleUrls: ['./add-resource.component.css']
})
export class AddResourceComponent {
  UserMasterForm: any;

  userId: any;
  isReadOnly : boolean = false;
  constructor(
    private fb: FormBuilder,
    private service: ResourceService,
    private router: Router,
    private actRoute: ActivatedRoute
  ) {
    this.UserMasterForm = this.fb.group({
      fullName: [''],

      contactNumber: [''],

      emailId: [{ value: '', disabled : this.userId != null } , Validators.required],

      location: [''],

      designationId: [''],
    });
  }

  designationtList: any;

  ngOnInit(): void {
    this.getDesignationList();
    this.userId = this.actRoute.snapshot.params['id'];
    if (this.userId > 0) {
      this.editResource();
      this.isReadOnly = true;
      console.log('Email field is disabled:', this.UserMasterForm.get('emailId').disabled);
    } else {
      this.userId = 0;
    }
  }
  editResource() {
    this.service.getUserById(this.userId).subscribe((data: any) => {
      this.UserMasterForm.patchValue({
        fullName: data.VCH_FULL_NAME,
        contactNumber: data.VCH_CONTACT_NO,
        emailId: data.VCH_EMAIL_ID,
        location: data.VCH_LOCATION,
        designationId: data.INT_DESGN_ID,
      });
    });
  }

  getDesignationList() {
    this.service.getDesignaionList().subscribe((responseData: any) => {
      this.designationtList = responseData;
    });
  }

  submit() {
    const formData = this.UserMasterForm.value;
    this.UserMasterForm.value.userId = this.userId;
    if(this.userId > 0){
      Swal.fire({
        title: 'Are you sure?',
        text: 'Please Check the details once again!',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes, save it!',
        cancelButtonText: 'No, cancel!',
      }).then((result) => {
        if (result.isConfirmed) {
          this.service.saveUserMaster(formData).subscribe((responseData: any) => {
            Swal.fire('Success', 'Data updated successfully!', 'success');
            this.router.navigate(['/admin/view-resource']);
          });
        } else if (result.dismiss === Swal.DismissReason.cancel) {
          Swal.fire('Cancelled', 'Data was not updated.', 'info');
        }
      });
    }else{
      Swal.fire({
        title: 'Are you sure?',
        text: 'Please check your email once again before saving!',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes, save it!',
        cancelButtonText: 'No, cancel!',
      }).then((result) => {
        if (result.isConfirmed) {
          this.service.saveUserMaster(formData).subscribe((responseData: any) => {
            Swal.fire('Success', 'Data saved successfully! Password sent to '+ responseData.emailId, 'success');
            this.router.navigate(['/admin/view-resource']);
          });
        } else if (result.dismiss === Swal.DismissReason.cancel) {
          Swal.fire('Cancelled', 'Data was not saved.', 'info');
        }
      });
    }
    
  }
}
