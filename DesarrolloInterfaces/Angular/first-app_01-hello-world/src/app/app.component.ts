import {Component} from '@angular/core';
import {HomeComponent} from './home/home.component';
import {RouterLink, RouterModule} from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [HomeComponent,RouterModule,],
  template: `
    <main>
      
        <header class="brand-name" >
          <a class="top-start" [routerLink]="['/']">
            <img class="brand-logo" src="/assets/logo.svg" alt="logo" aria-hidden="true" />
          </a>
          <button class="top-end" [routerLink]="['/addHousing']">
            Add Housing
          </button>
        </header>
      
      
        
      <section class="content">
        <router-outlet></router-outlet>
      </section>
    </main>
  `,
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'homes';
}
