import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GlobalToolbarComponent } from './global-toolbar.component';

describe('GlobalToolbarComponent', () => {
  let component: GlobalToolbarComponent;
  let fixture: ComponentFixture<GlobalToolbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GlobalToolbarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GlobalToolbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
