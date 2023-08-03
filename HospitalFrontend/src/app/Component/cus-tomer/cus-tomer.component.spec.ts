import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CusTomerComponent } from './cus-tomer.component';

describe('CusTomerComponent', () => {
  let component: CusTomerComponent;
  let fixture: ComponentFixture<CusTomerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CusTomerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CusTomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
