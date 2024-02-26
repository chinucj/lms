import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewKtComponent } from './view-kt.component';

describe('ViewKtComponent', () => {
  let component: ViewKtComponent;
  let fixture: ComponentFixture<ViewKtComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ViewKtComponent]
    });
    fixture = TestBed.createComponent(ViewKtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
