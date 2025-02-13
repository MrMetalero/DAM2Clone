import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoorRevealComponent } from './door-reveal.component';

describe('DoorRevealComponent', () => {
  let component: DoorRevealComponent;
  let fixture: ComponentFixture<DoorRevealComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DoorRevealComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DoorRevealComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
