import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { authGuard } from './auth.guard';
import { KnowmoreComponent } from './knowmore/knowmore.component';
import { EquipmentComponent } from './equipment/equipment.component';
import { PlanetInfoComponent } from './planet-info/planet-info.component';

export const routes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'login', component: LoginComponent},
    {path: 'dashboard',component: DashboardComponent,canActivate: [authGuard] },
    {path: 'knowmore',component: KnowmoreComponent,canActivate: [authGuard] },
    {path: 'equipment',component: EquipmentComponent,canActivate: [authGuard] },
    {path: 'planet-info',component: PlanetInfoComponent,canActivate: [authGuard] },







    { path: '**', redirectTo: '/login', pathMatch: 'full' } 
    
];
