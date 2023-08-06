import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule} from "@angular/forms";
import { ViewsComponent } from './views/views.component';
import { HttpClientModule } from '@angular/common/http';
import { AddComponent } from './add/add.component';
import { DeleteComponent } from './delete/delete.component';
import { UpdateComponent } from './update/update.component';

import { ViewComponent } from './view/view.component';


@NgModule({
  declarations: [
    AppComponent,
    ViewsComponent,
    AddComponent,
    DeleteComponent,
    UpdateComponent,

    ViewComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
