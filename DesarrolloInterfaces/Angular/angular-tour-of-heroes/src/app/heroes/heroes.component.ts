import { Component, OnInit } from '@angular/core';
import { Hero } from '../hero';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HEROES } from '../mock-heroes';

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css'],
  imports:[
    CommonModule,FormsModule, 
  ]
})
export class HeroesComponent implements OnInit {
  selectedHero!: Hero;
  onSelect(hero: Hero):void {
    this.selectedHero = hero;
  }
  heroes = HEROES;

  constructor() { }

  ngOnInit() {
  }

}