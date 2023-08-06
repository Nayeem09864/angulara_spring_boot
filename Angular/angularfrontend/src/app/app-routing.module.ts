import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ViewsComponent} from "./views/views.component";
import {AddComponent} from "./add/add.component";
import {ViewComponent} from "./view/view.component";
import {UpdateComponent} from "./update/update.component";

const routes: Routes = [
  {
    path: 'books',
    component:ViewsComponent
  },
  {
    path: 'books/add',
    component:AddComponent
  },
  {
    path: 'books/view/:id',
    component:ViewComponent
  },
  {
    path: 'books/update/:id',
    component:UpdateComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
