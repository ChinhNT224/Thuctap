import { TestBed } from '@angular/core/testing';

import { TokenCustomerServiceService } from './token-customer-service.service';

describe('TokenCustomerServiceService', () => {
  let service: TokenCustomerServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TokenCustomerServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
