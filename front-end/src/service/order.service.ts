import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {OrderDetails} from '../model/order-details';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  baseUrl = 'http://localhost:9090/orders/';


  constructor(private http:HttpClient) { }

  saveOrder(totalPrice,totalQuantity ,productsId):Observable<any>{
    return this.http.post(this.baseUrl+'saveOrder',{totalPrice,totalQuantity ,productsId}).pipe(
      map(
        response => response
      )
    )
  }

  getOrderDetails(code): Observable<OrderDetails> {
    return this.http.get<OrderDetails>(this.baseUrl + "orderDetails/code/" + code).pipe(
      map(
        response => response
      )
    )
  }

  getRequestOrdersRelatedToUser(): Observable<OrderDetails[]> {
    return this.http.get<OrderDetails[]>(this.baseUrl + "userOrderDetails").pipe(
      map(
        response => response
      )
    )
  }
  getAllRequestOrders(): Observable<OrderDetails[]> {
    return this.http.get<OrderDetails[]>(this.baseUrl + "allOrderDetails").pipe(
      map(
        response => response
      )
    )
  }


}
