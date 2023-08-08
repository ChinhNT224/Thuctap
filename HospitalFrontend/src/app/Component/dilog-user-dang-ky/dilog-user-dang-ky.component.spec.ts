import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DilogUserDangKyComponent } from './dilog-user-dang-ky.component';

describe('DilogUserDangKyComponent', () => {
  let component: DilogUserDangKyComponent;
  let fixture: ComponentFixture<DilogUserDangKyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DilogUserDangKyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DilogUserDangKyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
