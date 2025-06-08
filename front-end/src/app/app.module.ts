import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from './app.component';
import {NgModule} from '@angular/core';
import {ProductsComponent} from './componants/products/products.component';
import {HeaderComponent} from './componants/header/header.component';
import {CategoryComponent} from './componants/category/category.component';
import {CardDetailsComponent} from './componants/card-details/card-details.component';
import {CardComponent} from './componants/card/card.component';
import {BrowserModule} from '@angular/platform-browser';
import {FooterComponent} from './componants/footer/footer.component';
import { ChefsComponent } from './componants/chefs/chefs.component';
import { ContactInfoComponent } from './componants/contact-info/contact-info.component';
import {APP_BASE_HREF} from '@angular/common';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './componants/login/login.component';
import { SignUpComponent } from './componants/sign-up/sign-up.component';
import {AuthInterceptor} from '../service/interceptor/auth.interceptor';
import {LoginGuard} from '../service/activator/login.guard';
import {ScreenGuard} from '../service/activator/screen.guard';
import { OrderDetailsComponent } from './componants/order-details/order-details.component';
import { CreateProductComponent } from './componants/create-product/create-product.component';
import { CreateCategoryComponent } from './componants/create-category/create-category.component';
import { ShowOrdersComponent } from './componants/show-orders/show-orders.component';

// name : type
// http://localhost:4200/
export const routes: Routes = [

  // http://localhost:4200/active
  {path: 'category/:id', component: ProductsComponent, canActivate:[ScreenGuard]},
  {path: 'products/:key', component: ProductsComponent, canActivate:[ScreenGuard]},
  {path: 'products', component: ProductsComponent, canActivate:[ScreenGuard]},
  {path: 'cardDetails', component: CardDetailsComponent, canActivate:[ScreenGuard]},
  {path: 'contact-info', component: ContactInfoComponent, canActivate:[ScreenGuard]},
  {path: 'login', component: LoginComponent, canActivate:[LoginGuard]},
  {path: 'sign-up', component: SignUpComponent, canActivate:[LoginGuard]},
  {path: 'chefs', component: ChefsComponent, canActivate:[ScreenGuard]},
  {path: 'orderDetails/:code', component: OrderDetailsComponent, canActivate: [ScreenGuard]},
  // {path: 'orderRequest/:code', component: OrderDetailsComponent, canActivate: [ScreenGuard]},
  // {path: 'allOrderRequest/:code', component: OrderDetailsComponent, canActivate: [ScreenGuard]},
  {path: 'create-product', component: CreateProductComponent, canActivate:[ScreenGuard]},
  {path: 'create-category', component: CreateCategoryComponent, canActivate:[ScreenGuard]},
  //{path: 'user-orders', component: ShowOrdersComponent, canActivate:[ScreenGuard]},

  // http://localhost:4200/
  {path: '', redirectTo: '/products', pathMatch: 'full'},

  // if user enter thing without all routes
  {path: '**', redirectTo: '/products', pathMatch: 'full'}

];

/*
*   // http://localhost:4200/
  {path: '', component:OrderItemsComponent}
* */
@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    HeaderComponent,
    CategoryComponent,
    CardDetailsComponent,
    CardComponent,
    FooterComponent,
    ChefsComponent,
    ContactInfoComponent,
    LoginComponent,
    SignUpComponent,
    OrderDetailsComponent,
    CreateProductComponent,
    CreateCategoryComponent,
    ShowOrdersComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [
    { provide: APP_BASE_HREF, useValue: '/' }
    ,{provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor,multi: true},
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
