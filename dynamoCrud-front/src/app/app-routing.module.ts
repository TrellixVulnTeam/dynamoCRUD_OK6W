import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateComponent } from './usuario/create.component';
import { DetailComponent } from './usuario/detail.component';
import { ListComponent } from './usuario/list/list.component';
import { UpdateComponent } from './usuario/update.component';

const routes: Routes = [
  {path:'', component: ListComponent},
  {path:'detail', component: DetailComponent},
  {path:'create', component: CreateComponent},
  {path:'update', component: UpdateComponent},
  {path:'*', redirectTo:'.', pathMatch: 'full'}




];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
