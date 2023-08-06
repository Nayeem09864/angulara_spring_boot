import { Component,OnInit } from '@angular/core';
import {BookService} from "../book.service";
import {ActivatedRoute, Router} from '@angular/router';
import {Book} from "../Book";

@Component({
  selector: 'app-views',
  templateUrl: './views.component.html',
  styleUrls: ['./views.component.css']
})
export class ViewsComponent implements OnInit{
  id:number=0;

  books: Book[] | undefined;
  constructor(private bookService:BookService, private route:ActivatedRoute,private router:Router){

  }

  ngOnInit(): void {
    this.id=this.route.snapshot.params['id']
    this.getAllBook()
  }

  getAllBook(){
    this.bookService.getPosts().subscribe(data=>{
      this.books=data
      console.log(data)
    }, error => console.log(error))
  }

  addBookClick() {
    this.router.navigate(['books/add'])
  }

    updateBook2(id: number) {
      this.router.navigate(['books/update',id])
    }


    deleteBook2(id: number) {
        this.bookService.deleteBook(id).subscribe((data)=>{
          this.getAllBook();
            console.log(data);
        });
    }

    viewBook(id: number) {
        this.router.navigate(['books/view',id])
    }
}
