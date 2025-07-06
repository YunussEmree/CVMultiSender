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

interface RequestModel {
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
  request: RequestModel = {
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
  isSuccess = false;
  serverStatus = 'Bağlantı durumu kontrol edilmedi';

  constructor(private mailService: MailSenderService) {}

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
    const key = window.prompt('Yeni parametre adı girin:');
    if (key && !company.parameters.hasOwnProperty(key)) {
      company.parameters[key] = '';
    }
  }

  testRequest() {
    // TODO: This method will be implemented later
    // ! This request is for testing mail sending functionality with the current data to the user's own email
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
    this.responseMessage = 'Gönderiliyor…';
    this.isSuccess = false;
    this.mailService
      .sendMailsWithAttachment(this.request, this.files)
      .subscribe({
        next: res => {
          this.responseMessage = res.message;
          this.isSuccess = true;
        },
        error: err => {
          this.responseMessage = err.error?.message || 'Bir hata oluştu';
          this.isSuccess = false;
        }
      });
  }

  checkServer() {
    this.mailService.checkServer().subscribe({
      next: res => this.serverStatus = res.message,
      error: () => this.serverStatus = 'Sunucuya bağlanılamadı'
    });
  }
}
