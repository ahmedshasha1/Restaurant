import { Component, OnInit } from '@angular/core';
import {ProductsService} from '../../../service/products.service';
import {Router} from '@angular/router';
import {Category} from '../../../model/category';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {
  messageAr: string = '';
  messageEn: string='';


  constructor(private productsService:ProductsService,private router:Router) { }

  ngOnInit(): void {
  }

  createProduct(name,logoPath,description,price,categoryId){
    if(name === ''){
      this.messageAr = "يجب ادخال الاسم"
      this.messageEn = "please enter Product's name"

      this.extracted();
      return;
    }
    if(logoPath === ''){
      this.messageAr = "يجب ادخال صورة المنتج"
      this.messageEn = "please enter Product's image "

      this.extracted();
      return;
    }
    if(description === ''){
      this.messageAr = "يجب ادخال وصف المنتج"
      this.messageEn = "please enter Product's description"

      this.extracted();
      return;
    }
    if(price === ''){
      this.messageAr = "يجب ادخال سعر المنتج"
      this.messageEn = "please enter Product's price"

      this.extracted();
      return;
    }

    if(categoryId === ''){
      this.messageAr = "يجب ادخال تصنيف المنتج"
      this.messageEn = "please enter Product's category"

      this.extracted();
      return;
    }

    const category = { id: +categoryId };

    // @ts-ignore
    this.productsService.createProducts(name,logoPath,description,price,category).subscribe(
      response => {
        // @ts-ignore
        if (response && 'status' in response && response.status === 'NOT_ACCEPTABLE') {
          // @ts-ignore
          this.messageAr = response.bundleMessage.message_ar;
          // @ts-ignore
          this.messageEn = response.bundleMessage.message_en;
          this.extracted();

        } else {
          this.router.navigateByUrl('/products');
        }
      },
      (error) => {
        console.error("Error from API:", error);
        this.messageAr = "حدث خطأ أثناءاضافة منتج جديد ";
        this.messageEn = "An error occurred while adding new products";
        this.extracted();
      }
    );
  }

  private extracted() {
    setTimeout(() => {
      this.messageAr = '';
      this.messageEn = '';
    }, 5000);
  }

}
