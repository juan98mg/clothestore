import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FooterColumnTextComponent } from './footer-column-text.component';

describe('FooterColumnTextComponent', () => {
  let component: FooterColumnTextComponent;
  let fixture: ComponentFixture<FooterColumnTextComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FooterColumnTextComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FooterColumnTextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
