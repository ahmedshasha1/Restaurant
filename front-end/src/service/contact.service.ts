import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ContactService {
  baseUrl='http://localhost:9090/contact-info'

  constructor(private httpClient:HttpClient) { }

  saveMessage(name,email,subject,message):Observable<any>{
    return this.httpClient.post(this.baseUrl,{name,email,subject,message}).pipe(
      map(
        response => response
      )
    )
  }
}
