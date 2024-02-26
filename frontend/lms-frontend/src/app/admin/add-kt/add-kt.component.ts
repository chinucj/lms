import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonService } from '../common.service';
import { ProjectService } from '../project.service';
import Swal from 'sweetalert2';
import { KtService } from '../kt.service';

@Component({
  selector: 'app-add-kt',
  templateUrl: './add-kt.component.html',
  styleUrls: ['./add-kt.component.css']
})
export class AddKtComponent {

  ktForm!: FormGroup;
  projectList : any;
  id: any;

  constructor(private formBuilder: FormBuilder , private service : CommonService , 
    private projectService : ProjectService,
    private ktService : KtService,
    ){
    this.ktForm = this.formBuilder.group({
      ktName: ['', Validators.required],
      ktType: ['', Validators.required],
      ktFormat: ['', Validators.required],
      ktFilePath: ['', Validators.required],
      projectId: ['', Validators.required]
    });
  }

  ngOnInit(){
    this.getAllProjects();
  }



  getAllProjects() {
    this.projectService.getAllProjects().subscribe((data : any)=>{
      this.projectList = data;
    })
  }

  

  uploadFile(event: any) {
    if (event.target != null) {
      let file = event.target.files[0];
      let fileId = event.target.id;
      const fileData = new FormData();
      fileData.append('file', file);
      this.service.setTempFile(fileData).subscribe(
        (data: any) => {
          if (this.ktForm.controls[fileId]) {
            this.ktForm.controls[fileId].setValue(data.fileName);
          } else {
            console.error(`Control with id '${fileId}' not found in form.`);
          }
        },
        (error: any) => {
          alert("Something went wrong");
        }
      );
    }
  }

  onSubmit() {
    let errorFlag = 0;
    // const empName = this.ktForm.get('empName');
    // const empMobileNo = this.ktForm.get('empMobileNo');
    // const empJoiningDate = this.ktForm.get('empJoiningDate');
    // const empSalary = this.ktForm.get('empSalary');
    // const deptId = this.ktForm.get('deptId');
    // const stateId = this.ktForm.get('stateId');
    // const cityId = this.ktForm.get('cityId');

    // if (empName?.invalid && errorFlag === 0) {
    //   errorFlag = 1;
    //   empName.markAsTouched();
    // }
    // if (empMobileNo?.invalid && errorFlag === 0) {
    //   errorFlag = 1;
    //   empMobileNo.markAsTouched();
    // }
    // if (empJoiningDate?.invalid && errorFlag === 0) {
    //   errorFlag = 1;
    //   empJoiningDate.markAsTouched();
    // }
    // if (empSalary?.invalid && errorFlag === 0) {
    //   errorFlag = 1;
    //   empSalary.markAsTouched();
    // }
    // if (deptId?.invalid && errorFlag === 0) {
    //   errorFlag = 1;
    //   deptId.markAsTouched();
    // }
    // if (stateId?.invalid && errorFlag === 0) {
    //   errorFlag = 1;
    //   stateId.markAsTouched();
    // }
    // if (cityId?.invalid && errorFlag === 0) {
    //   errorFlag = 1;
    //   cityId.markAsTouched();
    // }
    if (errorFlag === 0) {
      this.ktForm.value.empId = this.id;
      Swal.fire({
        title: 'Save Data',
        text: 'Are you sure you want to save this data?',
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: 'Yes, save it',
        cancelButtonText: 'No, cancel',
      }).then((result) => {
        if (result.isConfirmed) {
          this.ktService.saveKt(this.ktForm.value).subscribe(
            (data) => {
              Swal.fire(
                'Data Saved!',
                'Your data has been saved successfully.',
                'success'
              );
            },
            (error) => {
              Swal.fire(
                'Error!',
                'There is some internal server error',
                'warning'
              );
            }
          );
          if (this.id != 0) {
            Swal.fire(
              'Data Updated!',
              'Your data has been updated successfully.',
              'success'
            );
          } else {
            Swal.fire(
              'Data Saved!',
              'Your data has been saved successfully.',
              'success'
            );
          }
          //this.router.navigate(['/emp-list']);
        } else if (result.dismiss === Swal.DismissReason.cancel) {
          Swal.fire('Cancelled', 'Your data was not saved.', 'info');
        }
      });
    }
  }
  
}

