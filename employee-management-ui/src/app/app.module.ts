import { EmployeesService } from './core/services/employees.service';
import { importsRoot } from './app.imports';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { declarationsRoot } from './app.declarations';
import { entryComponentRoot } from './app.entryComponents';

@NgModule({
  declarations: declarationsRoot,
  imports: importsRoot,
  providers: [EmployeesService],
  bootstrap: [AppComponent],
  entryComponents: entryComponentRoot,
})
export class AppModule {}
