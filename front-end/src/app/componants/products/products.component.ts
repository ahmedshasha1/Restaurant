import {Component, OnInit} from '@angular/core';
import {Products} from '../../../model/products';
import {ProductsService} from '../../../service/products.service';
import {ActivatedRoute} from '@angular/router';
import {CategoryService} from '../../../service/category.service';
import {CartOrder} from '../../../model/cart-order';
import {CartService} from '../../../service/cart.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  products: Products [];
  messageAr: string;
  messageEn: string;
  pageNo = 1;
  pageSize = 10;
  collectionSize:number;

  constructor(private productsService: ProductsService, private activatedRoute: ActivatedRoute , private cartService : CartService) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(
      () => this.finalProduct(this.pageNo)
    )

  }

  finalProduct(pageNo) {
    debugger
    let categoryIdExist = this.activatedRoute.snapshot.paramMap.has('id');
    let keyExixt = this.activatedRoute.snapshot.paramMap.has('key');
    if (categoryIdExist) {
      let idCategory = this.activatedRoute.snapshot.paramMap.get('id');
      this.loadProductsByCategoryId(idCategory,pageNo);

    } else if (keyExixt && this.activatedRoute.snapshot.paramMap.get('key') != '') {
      let key = this.activatedRoute.snapshot.paramMap.get('key');
      this.search(key,pageNo);

    } else {
      this.loadAllProducts(pageNo);
    }
  }

  loadAllProducts(pageNo) {
    debugger
    this.productsService.getAllProducts(pageNo,this.pageSize).subscribe(
      response => {
        // @ts-ignore
        this.products = response.products
        // @ts-ignore
        this.collectionSize=response.totalPageSize
      }
    );
  }

  loadProductsByCategoryId(categoryId,pageNo) {
    this.productsService.getProductsByCategoryId(categoryId,pageNo,this.pageSize).subscribe(
      response => {
        // @ts-ignore
        this.products = response.products


          // @ts-ignore
          if (response.products.length === 0) {
            this.messageAr = '🌟 ترقبوا! شيء مذهل قادم قريبًا. 🚀';
            this.messageEn = '🌟 Stay tuned! Something amazing is coming soon. 🚀';
          }
        // @ts-ignore
        this.collectionSize=response.totalPageSize
      }
    );
  }

  // @ts-ignore
  search(key,pageNo) {
    // @ts-ignore
    this.productsService.search(key,pageNo,this.pageSize).subscribe(
      response => {
        // @ts-ignore
        if (response && 'status' in response && response.status == 'NOT_ACCEPTABLE') {
          this.products = [];
          // @ts-ignore
          this.messageAr = response.bundleMessage.message_ar;
          // @ts-ignore
          this.messageEn = response.bundleMessage.message_en;
        } else {
          // @ts-ignore
          this.products = response.products
          // @ts-ignore
          this.collectionSize=response.totalPageSize

        }
      }
    )
  }

  do(){
    this.finalProduct(this.pageNo)
  }

  addToCart(product : Products) {
    let order = new CartOrder(product);
    this.cartService.addToCart(order);
  }
}
