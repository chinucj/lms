import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private http : HttpClient) { }

  getAllProjects(){
    return this.http.get<any>('http://localhost:9090/projects');
  }
}
