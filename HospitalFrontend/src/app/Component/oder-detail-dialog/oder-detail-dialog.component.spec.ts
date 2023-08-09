import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OderDetailDialogComponent } from './oder-detail-dialog.component';

describe('OderDetailDialogComponent', () => {
  let component: OderDetailDialogComponent;
  let fixture: ComponentFixture<OderDetailDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OderDetailDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OderDetailDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
