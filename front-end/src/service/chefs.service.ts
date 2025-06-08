import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Chefs} from '../model/chefs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ChefsService {
  baseUrl='http://localhost:9090/chefs';

  constructor(private http: HttpClient) { }
  getAllChefs(pageNo,pageSize): Observable<Chefs []>{
    return this.http.get<Chefs []>(this.baseUrl+'/pageNumber/'+pageNo+'/pageSize/'+pageSize).pipe(
      map(
        response => response
      )
    );

  }
}
