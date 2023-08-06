import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Book} from "./Book";
@Injectable({
  providedIn: 'root'
})
export class BookService {

    private baseUrl="http://localhost:8080/api/books";
    //book:Book=Object;
    book?=Book;
    id:number=0;
   constructor(private httpClient: HttpClient) { }

    getPosts(): Observable<Book[]>{
        return this.httpClient.get<Book[]>(`${this.baseUrl}`)
    }

    addBook(book:Book):Observable<Object> {
       return this.httpClient.post(`${this.baseUrl}`,book);
    }

    /*
    updateBook(id:Number,book:Book)) :Observable<Object> {
        return this.httpClient.put(`${this.baseUrl}/update/${id}`,book);
    }
     */
  updateBook(id:number,book:Book):Observable<Object>{
    //return this.httpClient.put((`${this.baseurl}/update/${id}`,this.book));
    return this.httpClient.put(`${this.baseUrl}/update/${id}`,book);
  }


    deleteBook(id:number):Observable <Object>{
        return this.httpClient.delete(`${this.baseUrl}/${id}`)
    }

  getBookById(id: number | undefined):Observable<Object>{
    return this.httpClient.get<Book>(`${this.baseUrl}/id/${id}`);

  }
}
