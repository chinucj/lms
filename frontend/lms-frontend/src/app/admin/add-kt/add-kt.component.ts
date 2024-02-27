import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonService } from '../common.service';
import { ProjectService } from '../project.service';
import Swal from 'sweetalert2';
import { KtService } from '../kt.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-add-kt',
  templateUrl: './add-kt.component.html',
  styleUrls: ['./add-kt.component.css']
})
export class AddKtComponent {

  ktForm!: FormGroup;
  projectList : any;
  id: any;
  viewFileUrl:string="http://localhost:9090/viewFile";

  constructor(private formBuilder: FormBuilder , private service : CommonService , 
    private projectService : ProjectService,
    private ktService : KtService,
    private activatedRoute : ActivatedRoute,
    private router : Router
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
    this.id = this.activatedRoute.snapshot.paramMap.get('id');

    if(this.id >0){

      this.getKtById(this.id);

    }

     else{

       this.id=0;

     }
  }
  getKtById(id:any){

    this.ktService.getKtById(id).subscribe((data:any)=>{
 
      this.ktForm.patchValue({
 
       ktName:data.VCH_KT_NAME,
 
       ktType:data.VCH_KT_TYPE,
 
       ktFormat:data.VCH_KT_FORMAT,
 
       ktFilePath:data.VCH_KT_PATH,
 
       projectId:data.INT_PROJECT_ID
 
      });
 
    }); 
 
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
      let fileType = this.ktForm.get('ktFormat')?.value;
      console.log(fileType);
      const fileData = new FormData();
      fileData.append('file', file);
      fileData.append('fileType' , fileType);
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

      this.ktForm.value.ktId = this.id;

      alert(this.ktForm.value.ktId);

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

              if (this.id != 0) {

                Swal.fire(

                  'Data Updated!',

                  'Your data has been updated successfully.',

                  'success'

                );

               this.router.navigate(['/admin/view-kt']);

              } else {

                Swal.fire(

                  'Data Saved!',

                  'Your data has been saved successfully.',

                  'success'

                );

               

                

            // Reset form controls to blank values

              this.ktForm.reset();

              // Clear file input field

              const fileInput: HTMLInputElement | null = document.querySelector('#ktFilePath');

              if (fileInput) {

                fileInput.value = '';

              }

              }

            },

            (error) => {

              Swal.fire(

                'Error!',

                'There is some internal server error',

                'warning'

              );

            }

          );

          

          //this.router.navigate(['/emp-list']);

        } else if (result.dismiss === Swal.DismissReason.cancel) {

          Swal.fire('Cancelled', 'Your data was not saved.', 'info');

        }

      });

    }

  }


  cancel(){

    this.router.navigate(['/admin/view-kt']);

  }
  
}

