import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostsearchedComponent } from './mostsearched.component';

describe('MostsearchedComponent', () => {
  let component: MostsearchedComponent;
  let fixture: ComponentFixture<MostsearchedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MostsearchedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MostsearchedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
