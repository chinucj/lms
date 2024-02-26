import { Component } from '@angular/core';
import { KtService } from '../kt.service';

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

  deleteKt(arg0: any) {
    throw new Error('Method not implemented.');
  }
  editKt(arg0: any) {
    throw new Error('Method not implemented.');
  }
}
