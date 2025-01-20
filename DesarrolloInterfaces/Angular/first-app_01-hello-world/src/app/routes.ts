import { Component } from '@angular/core';
import {Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {DetailsComponent} from './details/details.component';
import { AddHouseComponentComponent } from './add-house-component/add-house-component.component';

const routeConfig: Routes = [
    {
      path: '',
      component: HomeComponent,
      title: 'Home page',
    },
    {
      path: 'details/:id',
      component: DetailsComponent,
      title: 'Home details',
    },
    {
      path: 'addHousing',
      component: AddHouseComponentComponent,
      title: 'Add House',

    },

  ];
  export default routeConfig;