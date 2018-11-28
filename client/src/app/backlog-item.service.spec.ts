import { TestBed, inject } from '@angular/core/testing';

import { BacklogItemService } from './backlog-item.service';

describe('BacklogItemService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BacklogItemService]
    });
  });

  it('should be created', inject([BacklogItemService], (service: BacklogItemService) => {
    expect(service).toBeTruthy();
  }));
});
