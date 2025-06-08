import { Injectable } from '@angular/core';
import {CartOrder} from '../model/cart-order';
import {BehaviorSubject, Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  order: CartOrder[] = [];
  totalOrderSize: Subject<number> = new BehaviorSubject<number>(0);
  totalOrderPrice: Subject<number> = new BehaviorSubject<number>(0);


  constructor() { }

  addToCart(order : CartOrder){
    let isExist :boolean = false;
    let orderExist : CartOrder =undefined;

    if(this.order.length !== 0){
      orderExist =this.order.find(or => or.id == order.id)
      }
    isExist = (orderExist != undefined);
    if(isExist){
      orderExist.quantity++;
    }else {
      this.order.push(order);
    }
    this.calculateTotal()
  }

  calculateTotal(){
    let totalNumber : number = 0;
    let totalPrice : number = 0;
    for(let temp of this.order){
      totalNumber += temp.quantity;
      totalPrice += temp.price * temp.quantity;
    }
    this.totalOrderSize.next(totalNumber);
    this.totalOrderPrice.next(totalPrice);
  }


  removeOrder(order: CartOrder){
    const index = this.order.findIndex(o => o.id === order.id)
    if(index > -1){
      this.order.splice(index,1)
    }
    this.calculateTotal();
  }

  decreaseOrder(order : CartOrder){
    order.quantity--;

    if(order.quantity === 0 ){
      this.removeOrder(order)
    }
    this.calculateTotal()
  }
}
