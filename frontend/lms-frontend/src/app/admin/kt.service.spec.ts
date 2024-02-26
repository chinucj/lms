import { TestBed } from '@angular/core/testing';

import { KtService } from './kt.service';

describe('KtService', () => {
  let service: KtService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KtService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
