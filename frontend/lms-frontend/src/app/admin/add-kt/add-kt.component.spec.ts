import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddKtComponent } from './add-kt.component';

describe('AddKtComponent', () => {
  let component: AddKtComponent;
  let fixture: ComponentFixture<AddKtComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddKtComponent]
    });
    fixture = TestBed.createComponent(AddKtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
