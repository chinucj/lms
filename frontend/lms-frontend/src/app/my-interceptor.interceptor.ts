import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class MyInterceptorInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const userToken = sessionStorage.getItem('tokan');

    if (userToken != undefined && userToken != '') {
      request = request.clone({
        setHeaders: {
          'Authorization': `Bearer ${userToken}`,
          'withCredentials': 'true',
        },
      });
    } else {
      request = request.clone({
        setHeaders: {
          'Content-Type': 'application/json;charset=UTF-8',
          'Encrypted': 'true',
        },
      });
    }

    return next.handle(request);
  }
}
