import { TestBed } from '@angular/core/testing';

import { QuitGuard } from './quit.guard';

describe('QuitGuard', () => {
  let guard: QuitGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(QuitGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
