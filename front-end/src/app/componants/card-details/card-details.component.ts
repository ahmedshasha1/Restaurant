import {Component, OnInit} from '@angular/core';
import {CartOrder} from '../../../model/cart-order';
import {CartService} from '../../../service/cart.service';
import {OrderService} from '../../../service/order.service';
import {map} from 'rxjs/operators';
import {Router} from '@angular/router';

@Component({
  selector: 'app-card-details',
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.css']
})
export class CardDetailsComponent implements OnInit{

  order: CartOrder[] = [];
  totalPrice: number = 0;
  totalQuantity: number = 0;
  constructor(private cartService: CartService, private orderService: OrderService, private router: Router) {
  }

  ngOnInit(): void {
    this.getOrders()
  }


  getOrders(){
    this.order = this.cartService.order;
  }


  increaseOrder(order: CartOrder) {
    this.cartService.addToCart(order)
  }

  deCreaseOrder(order: CartOrder) {
    this.cartService.decreaseOrder(order)
  }


  removeOrder(order: CartOrder){
    this.cartService.removeOrder(order)
  }

  createOrder(){
    debugger
    const productsIds = this.cartService.order.map(order => order.id);

    this.cartService.totalOrderPrice.subscribe(
      value => this.totalPrice = value
    )

    this.cartService.totalOrderSize.subscribe(
      value => this.totalQuantity = value
    )

    this.orderService.saveOrder(this.totalPrice, this.totalQuantity, productsIds).subscribe(
      value => {
        alert("your code is " + value.code);
        this.cartService.order = [];
        this.cartService.totalOrderSize.next(0);
        this.cartService.totalOrderPrice.next(0);
        this.router.navigateByUrl("/orderDetails/" + value.code);
      },
      error => {

        console.error("Error occurred while placing the order:", error);
        alert("Failed to place order. Please try again later.");
      }
    )
  }

}
