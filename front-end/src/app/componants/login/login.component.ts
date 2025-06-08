import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../../service/auth/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  messageAr:string='';
  messageEn:string='';

  roles :string
  rolesExist :string
  userRole: string[] ;

  constructor(private authService:AuthService,private router: Router) { }

  ngOnInit(): void {
  }


  login(email:String , password:String) {
    if(email ===''){
      this.messageAr="من فضلك ادخل بريد الكترونى صحيح"
      this.messageEn="Please, Enter the correct email"
      this.extracted();
      return
    }
    if(password ===''){
      this.messageAr="من فضلك ادخل الرقم السرى الخاص بك"
      this.messageEn="Please, Enter your password"
      this.extracted();
      return
    }
    debugger
    this.authService.login(email,password).subscribe(
      (response) => {
        if(response && 'status' in response && response.status === 'NOT_ACCEPTABLE' ){
          this.messageAr = response.bundleMessage.message_ar;
          this.messageEn = response.bundleMessage.message_en;
          this.extracted();
        } else {
          sessionStorage.setItem("token", 'Bearer ' + response.token)
          sessionStorage.setItem('roles', 'Bearer ' + response.roles)
          // this.roles = sessionStorage.getItem(response.roles)
          //
          //   const roleList = this.roles.split(',');
          //    const roleFound = roleList.find(role => role.includes('USER'));
          //    if (roleFound) {
          //      this.rolesExist  = roleFound.substring(5).toLowerCase().trim(); // "user" أو "admin"
          //      this.userRole.push(this.rolesExist);
          //    }

          this.router.navigateByUrl('/products');
        }
      }
    )

  }



  private extracted() {
    setTimeout(() => {
      this.messageAr = '';
      this.messageEn = '';
    }, 5000);
  }
}
