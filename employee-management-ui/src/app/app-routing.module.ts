import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeGetComponent } from './core/components/employee-get/employee-get.component';

const routes: Routes = [
  {
    path: '**',
    component: EmployeeGetComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
