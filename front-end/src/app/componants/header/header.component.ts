import { Component } from '@angular/core';
import {Category} from '../../../model/category';
import {CategoryService} from '../../../service/category.service';
import {ProductsService} from '../../../service/products.service';
import {Products} from '../../../model/products';
import {Router} from '@angular/router';
import {AuthService} from '../../../service/auth/auth.service';
import {CreateProductComponent} from '../create-product/create-product.component';
import {LoginComponent} from '../login/login.component';
import {OrderDetailsComponent} from '../order-details/order-details.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  category: Category []=[];
  product: Products []=[];

  userRole : string[];



  constructor(private categoryService: CategoryService,private router:Router,private authService:AuthService){}

  ngOnInit(): void {
    this.loadAllCategory();

  }
  loadAllCategory(){
    this.categoryService.getAllCategory().subscribe(
      response => (
        this.category = response
      )
    );
  }

  search(key){
    this.router.navigateByUrl("/products/"+key);
  }


  logOut() {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('roles');
    this.router.navigateByUrl("/login")
  }

  isUserLogin(){
    return this.authService.isUserLogIn();
  }

  isUserAdmin(){
    return this.authService.isUserAdmin();
  }

  login() {
    this.router.navigateByUrl("/login")
  }








}
