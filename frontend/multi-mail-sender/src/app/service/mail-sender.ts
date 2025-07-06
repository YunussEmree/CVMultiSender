import { Injectable } from '@angular/core';
import { HttpClient }   from '@angular/common/http';
import { Observable }   from 'rxjs';

interface ApiResponse {
  message: string;
  data: any;
}

@Injectable({
  providedIn: 'root'
})
export class MailSenderService {
  private readonly baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  sendMailsWithAttachment(request: any, files: File[]): Observable<ApiResponse> {
    const form = new FormData();
    form.append('request', new Blob([JSON.stringify(request)], { type: 'application/json' }));
    files.forEach(f => form.append('files', f, f.name));
    return this.http.post<ApiResponse>(`${this.baseUrl}/send-mails-with-attachment`, form);
  } //TODO: Mock data will be removed and real data will be used
  // ! There is a mock data in anywhere, find it and remove it

  checkServer(): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(`${this.baseUrl}/health`);
  }
}
