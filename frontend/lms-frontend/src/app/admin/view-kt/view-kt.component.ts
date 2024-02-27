import { Component } from '@angular/core';
import { KtService } from '../kt.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-kt',
  templateUrl: './view-kt.component.html',
  styleUrls: ['./view-kt.component.css'],
})
export class ViewKtComponent {
  ktList: any;
  indexNumber: number = 0;
  page: number = 1;
  tableSize: number = 10;
  count: number = 0;
  pageSizes = [10, 20, 30, 40, 50];

  constructor(private ktService: KtService) {}

  ngOnInit() {
    this.getKtList();
  }

  getKtList() {
    this.ktService.getKtList().subscribe((data: any) => {
      this.ktList = data;
    });
  }

  getTableDataChange(event : any){
    this.page = event;
    this.indexNumber = (this.page - 1) * this.tableSize;
    this.getKtList();
  }

  deleteKt(ktId: any) {
    Swal.fire({
      title: 'Confirm Deletion',
      text: 'Are you sure you want to delete this data?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it',
      cancelButtonText: 'No, cancel',
    }).then((result) => {
      if (result.isConfirmed) {
        this.ktService.deleteKt(ktId).subscribe(data =>{
          Swal.fire('Data Deleted', 'The data has been successfully deleted.', 'success');
          this.getKtList();
        },error =>{
          Swal.fire('Error', 'An error occurred while fetching the kt data.', 'error');
        })
      }
    });
  }
  editKt(arg0: any) {
    throw new Error('Method not implemented.');
  }
}
