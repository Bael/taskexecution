import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BacklogItemListComponent } from './backlog-item-list.component';

describe('BacklogItemListComponent', () => {
  let component: BacklogItemListComponent;
  let fixture: ComponentFixture<BacklogItemListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BacklogItemListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BacklogItemListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
