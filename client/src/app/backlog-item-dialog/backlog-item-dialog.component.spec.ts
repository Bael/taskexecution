import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BacklogItemDialogComponent } from './backlog-item-dialog.component';

describe('BacklogItemDialogComponent', () => {
  let component: BacklogItemDialogComponent;
  let fixture: ComponentFixture<BacklogItemDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BacklogItemDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BacklogItemDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
