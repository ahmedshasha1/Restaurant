import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Products} from '../model/products';
import {map} from 'rxjs/operators';
import {Category} from '../model/category';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  baseUrl='http://localhost:9090/product';

  constructor(private http: HttpClient) { }

  getAllProducts(pageNo,pageSize): Observable<Products []>{
    return this.http.get<Products []>(this.baseUrl+'/pageNumber/'+pageNo+'/pageSize/'+pageSize).pipe(
      map(
        response => response
      )
    );
  }
  getProductsByCategoryId(categoryId,pageNo,pageSize): Observable<Products []>{
    return this.http.get<Products []>(this.baseUrl+'/category/get-products-by-category-id/'+categoryId
    +'/pageNumber/'+pageNo+'/pageSize/'+pageSize).pipe(
      map(
        response => response
      )
    );
  }

  search(key,pageNo,pageSize): Observable<Products []>{
    return this.http.get<Products []>(this.baseUrl+'/search/'+key+'/pageNumber/'+pageNo+'/pageSize/'+pageSize).pipe(
      map(
        response => response
      )
    );
  }

  createProducts(name: string, logoPath: string, description: string, price: number, category: { id: number }):Observable<any>{
    return this.http.post(this.baseUrl+'/create-product',{name,logoPath,description,price,category}).pipe(
      map(
        res => res
      )
    )
  }

}
