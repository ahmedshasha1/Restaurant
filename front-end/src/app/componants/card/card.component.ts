import {Component, OnInit} from '@angular/core';
import {CartService} from '../../../service/cart.service';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})

export class CardComponent implements OnInit{
  totalSize : number = 0;
  totalPrice : number = 0;

  constructor(private cartService:CartService) {
  }

  ngOnInit(): void {
    this.getTotals()
  }
  getTotals(){
    this.cartService.totalOrderSize.subscribe(
      response => {
        this.totalSize = response
      }
    )
    this.cartService.totalOrderPrice.subscribe(
      response =>{
        this.totalPrice = response;
      }
    )
  }





}
