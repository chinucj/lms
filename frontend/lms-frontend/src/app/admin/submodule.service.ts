import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SubmoduleService {

  constructor(private http : HttpClient) { 
  }

  getSubModuleForKt(){
    return this.http.get("http://localhost:9090/sub-module-kt");
  }
}
