import { Component, OnInit } from '@angular/core';
import {Book} from "../Book";
import {BookService} from "../book.service";
import {ActivatedRoute, Router} from "@angular/router";


@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent  implements  OnInit{
    constructor(private bookService:BookService,private activatedRoute:ActivatedRoute,private  route:Router) {
    }
  id:number=0;

  book?: any;

  ngOnInit(): void {
    //this.id=this.route.snapshot.params['id']
    this.id=this.activatedRoute.snapshot.params['id']

    // this.bookService.getBookById(this.id).subscribe(data:Book=>{
    //   this.book=data
    //   console.log(data)
    // })
//may be this id is not coming properly
    this.bookService.getBookById(this.id).subscribe(data=>{
       this.book= data
      console.log(data)
    })
  }

  protected readonly Book = Book;

  viewBookClickfromSingleView() {
    this.route.navigate(['books'])
  }

}
