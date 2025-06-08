import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../../service/auth/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  messageAr: string = '';
  messageEn: string='';

  constructor(private authService:AuthService,  private router:Router) { }

  ngOnInit(): void {
  }

  createUser(name,email,phone,password,confirmPassword) {

    if(name === ''){
      this.messageAr = "يجب ادخال الاسم"
      this.messageEn = "please enter your name"

      this.extracted();
      return;
    }
    if(email === ''){
      this.messageAr = "يجب ادخال البريد الالكترونى"
      this.messageEn = "please enter your Email"

      this.extracted();
      return;
    }
    if(phone === ''){
      this.messageAr = "يجب ادخال رقم الهاتف المحمول "
      this.messageEn = "please enter your phone number"

      this.extracted();
      return;
    }
    if(password === ''||confirmPassword === ''){
      this.messageAr = "يجب ادخال الرقم السرى"
      this.messageEn = "please enter your password"

      this.extracted();
      return;
    }
    if(  confirmPassword !== password){
      this.messageAr = "الرقم السري غير متطابق";
      this.messageEn = "password not matched";
      this.extracted();
      return;
    }
    debugger
    this.authService.createAccount(name,email,phone,password).subscribe(
      response => {
            if (response && 'status' in response && response.status === 'NOT_ACCEPTABLE') {
              // @ts-ignore
              this.messageAr = response.bundleMessage.message_ar;
              // @ts-ignore
              this.messageEn = response.bundleMessage.message_en;
              this.extracted();

        } else {
              this.router.navigateByUrl('/login');
            }
            },
    (error) => {
      console.error("Error from API:", error);
      this.messageAr = "حدث خطأ أثناء إنشاء الحساب";
      this.messageEn = "An error occurred while creating the account";
      this.extracted();
    }
      );
  }

  private extracted() {
    setTimeout(() => {
      this.messageAr = '';
      this.messageEn = '';
    }, 3000);
  }
}

