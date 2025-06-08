import { Component, OnInit } from '@angular/core';
import {ChefsService} from '../../../service/chefs.service';
import {Chefs} from '../../../model/chefs';

@Component({
  selector: 'app-chefs',
  templateUrl: './chefs.component.html',
  styleUrls: ['./chefs.component.css']
})
export class ChefsComponent implements OnInit {
  chefs: Chefs [];
  pageNo = 1;
  pageSize = 10;
  collectionSize:number;
  constructor(private chefsService:ChefsService) { }

  ngOnInit(): void {
    this.loadAllChefs();
  }

  loadAllChefs(){
    // @ts-ignore
    // @ts-ignore
    this.chefsService.getAllChefs(this.pageNo,this.pageSize).subscribe(
      response => {
        // @ts-ignore
        this.chefs = response.chefs
        // @ts-ignore
        this.collectionSize = response.totalPageSize
      }
    );
  }
  do(){
    alert("ok")
  }
}
