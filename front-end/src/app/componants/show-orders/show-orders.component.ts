import { Component, OnInit } from '@angular/core';
import {OrderService} from '../../../service/order.service';
import {OrderDetails} from '../../../model/order-details';

@Component({
  selector: 'app-show-orders',
  templateUrl: './show-orders.component.html',
  styleUrls: ['./show-orders.component.css']
})
export class ShowOrdersComponent implements OnInit {
  orderDetails :OrderDetails[];

  constructor(private orderService:OrderService) { }

  ngOnInit(): void {
  }

  getRequestOrdersRelatedToUser(){
    this.orderService.getRequestOrdersRelatedToUser().subscribe(
      response => {
        this.orderDetails = response
      }
    )

  }

}
