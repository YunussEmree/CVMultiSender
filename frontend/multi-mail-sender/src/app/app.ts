import { Component } from '@angular/core';
import { MainComponent } from "./pages/main/main";

@Component({
  selector: 'app-root',
  imports: [MainComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected title = 'multi-mail-sender';
}
