import { Component, OnInit } from '@angular/core';
import {CategoryService} from '../../../service/category.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create-category',
  templateUrl: './create-category.component.html',
  styleUrls: ['./create-category.component.css']
})
export class CreateCategoryComponent implements OnInit {

  messageAr: string = '';
  messageEn: string = '';


  constructor(private categoryService: CategoryService,private router :Router) {
  }

  ngOnInit(): void {
  }

  createCategory(name, logoPath, flag) {
    if(name === ''){
      this.messageAr = "يجب ادخال الاسم"
      this.messageEn = "please enter Category's name"

      this.extracted();
      return;
    }
    if(logoPath === ''){
      this.messageAr = "يجب ادخال صورة المنتج"
      this.messageEn = "please enter Category's logo "

      this.extracted();
      return;
    }
    if(flag === ''){
      this.messageEn = "please enter Product's flag "

      this.extracted();
      return;
    }
    this.categoryService.createCategory(name, logoPath, flag).subscribe(
      response => {
        if (response && 'status' in response && response.status === 'NOT_ACCEPTABLE') {
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
        this.messageAr = "حدث خطأ أثناءاضافة صنف جديد ";
        this.messageEn = "An error occurred while adding new Category";
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
