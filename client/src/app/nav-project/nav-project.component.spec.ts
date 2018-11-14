import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NavProjectComponent } from './nav-project.component';

describe('NavProjectComponent', () => {
  let component: NavProjectComponent;
  let fixture: ComponentFixture<NavProjectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NavProjectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavProjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
