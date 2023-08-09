import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DilalogUserComponent } from './dilalog-user.component';

describe('DilalogUserComponent', () => {
  let component: DilalogUserComponent;
  let fixture: ComponentFixture<DilalogUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DilalogUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DilalogUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
