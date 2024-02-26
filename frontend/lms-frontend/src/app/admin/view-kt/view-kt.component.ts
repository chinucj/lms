import { Component } from '@angular/core';
import { KtService } from '../kt.service';

@Component({
  selector: 'app-view-kt',
  templateUrl: './view-kt.component.html',
  styleUrls: ['./view-kt.component.css']
})
export class ViewKtComponent {
ktList: any;

constructor(private ktService : KtService){}

ngOnInit(){
  this.getKtList();
}

getKtList() {
  this.ktService.getKtList().subscribe((data : any)=>{
    this.ktList = data;
  })
}

deleteKt(arg0: any) {
throw new Error('Method not implemented.');
}
editKt(arg0: any) {
throw new Error('Method not implemented.');
}

}


