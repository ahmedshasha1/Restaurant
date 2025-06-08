import { Component, OnInit } from '@angular/core';
import {ContactService} from '../../../service/contact.service';
import {Router} from '@angular/router';
import {map} from 'rxjs/operators';

@Component({
  selector: 'app-contact-info',
  templateUrl: './contact-info.component.html',
  styleUrls: ['./contact-info.component.css']
})
export class ContactInfoComponent implements OnInit {
  result= '';
  messageAr: string = '';
  messageEn: string='';

  constructor(private contactService:ContactService,private router :Router) { }

  ngOnInit(): void {
  }


  saveMessage(name,email,subject,message) {

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

    if(subject === ''){
      this.messageAr = "يجب ادخال الموضوع"
      this.messageEn = "please enter the Subject"

      this.extracted();
      return;
    }
    if(message === ''){
      this.messageAr = "يجب ادخال الرسالة"
      this.messageEn = "please enter your Message"

      this.extracted();
      return;
    }
    debugger
    this.contactService.saveMessage(name, email, subject, message).subscribe(
      response => {
        if(response && 'status' in response && response.status === 'NOT_ACCEPTABLE'  ){
          this.messageAr = response.bundleMessage.message_ar;
          this.messageEn = response.bundleMessage.message_en;
          this.extracted();

        }
        else {
          debugger
          console.log("Message sent successfully", response);
          this.extractedMessage()
          this.router.navigateByUrl('/products');
        }

      },
      (error) => {
        console.error("Error from API:", error);
        this.messageAr = "حدث خطأ أثناء ارسال الرسالة";
        this.messageEn = "An error occurred while sending the message";
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
  private extractedMessage() {
    setTimeout(() => {
      this.result = 'Thanks for contacting us'
    }, 5000);
  }
  }
