import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VideoLauncherComponent } from './video-launcher.component';

describe('VideoLauncherComponent', () => {
  let component: VideoLauncherComponent;
  let fixture: ComponentFixture<VideoLauncherComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VideoLauncherComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VideoLauncherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
