import { Component,OnInit } from '@angular/core';
import {BookService} from "../book.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements  OnInit{
  id:number=0;
  book:any;
  constructor(private bookService:BookService,private activatedRoute :ActivatedRoute,private router:Router) {
  }
  ngOnInit() {
    this.id=this.activatedRoute.snapshot.params['id'];
    this.bookService.getBookById(this.id).subscribe((data)=>{
      this.book=data;
      console.log(data);
    }, error => console.log(error)
    )
  }


  onSubmit() {
    this.bookService.updateBook(this.id,this.book).subscribe((data)=>{
      this.goToBookList()
        //location.reload()
    },error => console.log(error)
    )
    //this.router.navigate(['books'])
  }
  goToBookList(){
    this.router.navigate(['books']);
  }
}
