import { TestBed } from '@angular/core/testing';

import { VotoGuard } from './voto.guard';

describe('VotoGuard', () => {
  let guard: VotoGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(VotoGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
