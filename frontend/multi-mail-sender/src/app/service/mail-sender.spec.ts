import { TestBed } from '@angular/core/testing';

import { MailSender } from './mail-sender';

describe('MailSender', () => {
  let service: MailSender;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MailSender);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
