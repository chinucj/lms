import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  constructor(private http : HttpClient) { }

  setTempFile(fileName : any){
    return this.http.post<any>('http://localhost:9090/setTempFile',fileName);
  }
}
