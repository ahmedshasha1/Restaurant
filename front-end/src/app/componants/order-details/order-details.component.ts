import { Component, OnInit } from '@angular/core';
import {OrderService} from '../../../service/order.service';
import {CardDetailsComponent} from '../card-details/card-details.component';
import {ActivatedRoute, Router} from '@angular/router';
import { OrderDetails } from 'src/model/order-details';
import {AuthService} from '../../../service/auth/auth.service';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {

  details: OrderDetails = null;
  allOrderDetails: OrderDetails[] = [];

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private orderService: OrderService, private authService: AuthService) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(
      () => this.orderDetails()
    )
  }
  orderDetails(){
    debugger
    let codeExist = this.activatedRoute.snapshot.paramMap.has('code')
    if(codeExist){
      let code = this.activatedRoute.snapshot.paramMap.get('code')
      if(code === 'user'){
        this.orderService.getRequestOrdersRelatedToUser().subscribe(
          res => {
            this.allOrderDetails = res
          }
        )
      }else if(code === 'alluser'){
        this.orderService.getAllRequestOrders().subscribe(
          res =>{
           this.allOrderDetails = res
          }
        )
      }
      else if(code){
        this.orderService.getOrderDetails(code).subscribe(
          res => {
            this.details =new  OrderDetails();
            this.details = res
        }
        )
      }
      else {
        this.router.navigateByUrl('/products')
      }
    }
  }






}
