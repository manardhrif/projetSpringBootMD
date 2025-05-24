import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],  // attention à bien garder le RouterOutlet ici
  templateUrl: './app.component.html',
})
export class AppComponent {}
