import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ResourceService {
  userMasterSaveUrl!: string;
  designationDetailsUrl: any;
  constructor(private httpClient: HttpClient) {}

  //Resource Management

  saveUserMaster(usermaster: any): Observable<any> {
    return this.httpClient.post("http://localhost:9090/userMaster", usermaster);
  }

  getDesignaionList() {
    return this.httpClient.get("http://localhost:9090/designation");
  }

  getUsersList() {
    return this.httpClient.get("http://localhost:9090/userMaster");
  }

  deleteUserById(id : any){
    return  this.httpClient.delete(`http://localhost:9090/userMaster/${id}`);
  }

  getUserById(id : any){
    return this.httpClient.get(`http://localhost:9090/userMaster/${id}`);
  }
}
