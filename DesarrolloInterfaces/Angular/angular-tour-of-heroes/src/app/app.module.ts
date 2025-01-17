import { bootstrapApplication, BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here

import { AppComponent } from './app.component';
import { HeroesComponent } from './heroes/heroes.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HeroesComponent
  ],
  providers: [],
})
export class AppModule { }

bootstrapApplication(AppComponent)

