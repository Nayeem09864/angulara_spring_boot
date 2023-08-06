import {Component, OnInit} from '@angular/core';
import {Book, BookType} from "../Book";
import {BookService} from "../book.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements  OnInit{
  book: Book=new Book();


  constructor(private bookService:BookService, private route:Router) {
  }
  ngOnInit() {
    this.book.bookType=BookType.FREE;
  }

  saveBook(){
    this.bookService.addBook(this.book).subscribe((data)=>{
      this.goToBookList();
        }, error => console.log(error)
    )
  }

  goToBookList(){
    this.route.navigate(['books'])
  }
  onSubmit(){
    this.saveBook();
    console.log(this.book);

  }

  viewBookClick() {
    this.route.navigate(['books'])
  }

  onSelect() {
    console.log(this.book);
  }
}
