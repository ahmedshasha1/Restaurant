import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Category} from '../model/category';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  baseUrl = 'http://localhost:9090/category';

  constructor(private http: HttpClient) { }
  getAllCategory(): Observable<Category []>{
  return this.http.get<Category []>(this.baseUrl).pipe(
    map(
      response => response
    )
  );
}

  createCategory(name,logoPath,flag):Observable<any>{
    return this.http.post(this.baseUrl+"/create-category",{name,logoPath,flag}).pipe(
      map(
        response => response
      )
    )
}
}
