import { Component }      from '@angular/core';
import { FormsModule }    from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { AddOnePipe } from '../../pipe/add-one-pipe';
import { MailSenderService } from '../../service/mail-sender';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

interface CompanyData {
  id: number;
  companyMail: string;
  parameters: Record<string, string>;
}

interface RequestData {
  username: string;
  password: string;
  subject: string;
  bodydraft: string;
  companyData: CompanyData[];
}


@Component({
  selector: 'app-main',
  standalone: true,
  imports: [ FormsModule, HttpClientModule, AddOnePipe, CommonModule, NgbModule],
  providers: [MailSenderService],
  templateUrl: './main.html',
  styleUrls: ['./main.css']
})
export class MainComponent {
  request: RequestData = {
    username: '',
    password: '',
    subject: '',
    bodydraft: '',
    companyData: [
      {
        id: 0,
        companyMail: '',
        parameters: {
          companyName: ''
        }
      }
    ]
  };

  files: File[] = [];
  responseMessage = '';
  isSuccess = 0; // 0: sending, 1: success, -1: error
  serverStatus = 'Server Down';

  constructor(private mailService: MailSenderService) {
    this.checkServer();
  }

  addCompany() {
    this.request.companyData.push({
      id: this.request.companyData.length,
      companyMail: '',
      parameters: { companyName: ''}
    });
  }

  getParameterKeys(company: CompanyData): string[] {
    return Object.keys(company.parameters);
  }

  removeCompany(i: number) {
    this.request.companyData.splice(i, 1);
  }

   addParameter(company: CompanyData) {
    const key = window.prompt('Add new parameter:');
    if (key && !company.parameters.hasOwnProperty(key)) {
      company.parameters[key] = '';
    }
  }

  removeParameter(company: CompanyData, key: string) {
    delete company.parameters[key];
  }

  onFileSelected(evt: Event) {
    const input = evt.target as HTMLInputElement;
    if (input.files) {
      this.files = Array.from(input.files);
    }
  }

  sendMails() {
    this.responseMessage = 'Mails are sending...';
    this.isSuccess = 0;
    this.mailService
      .sendMailsWithAttachment(this.request, this.files)
      .subscribe({
        next: res => {
          this.responseMessage = res.message;
          this.isSuccess = 1;
        },
        error: err => {
          this.responseMessage = err.error?.message || 'An error occurred while sending emails.';
          this.isSuccess = -1;
        }
      });
  }

  checkServer() {
    this.mailService.checkServer().subscribe({
      next: res => this.serverStatus = res.message,
      error: () => this.serverStatus = 'Server Down'
    });
  }
}
