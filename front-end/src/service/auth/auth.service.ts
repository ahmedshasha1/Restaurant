import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  baseUrl='http://localhost:9090/auth';



  constructor(private http:HttpClient) { }

  createAccount(name ,  email ,phone , password):Observable<any>{
    return this.http.post(this.baseUrl+'/create-user',{name, email , phone , password}).pipe(
      map(
        response => response
      )
    )
  }

  login(email , password):Observable<any>{
    return this.http.post(this.baseUrl+'/login',{email , password}).pipe(
      map(
        response => response
      )
    )
  }

  isUserLogIn(){
    return sessionStorage.getItem("token") != null &&  sessionStorage.getItem("token") != undefined;
  }

  isUserAdmin(){
    const roles = sessionStorage.getItem('roles');

    if (roles && roles.includes('ADMIN')) {
      return true
    } else {
      return false;
    }
  }

  isUser():Observable<any>{
   return  this.http.get(this.baseUrl+"/login").pipe(
      map(
        response => response
      )
    );

  }



}
