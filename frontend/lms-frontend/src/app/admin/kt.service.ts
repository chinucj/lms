import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class KtService {
  

  constructor(private http : HttpClient) { }

  saveKt(ktData : any){
    return this.http.post('http://localhost:9090/kt' , ktData);
  }

  getKtList() {
    return this.http.get("http://localhost:9090/kt");
  }
}
