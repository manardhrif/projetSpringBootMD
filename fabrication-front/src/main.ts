import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { appConfig } from './app/app.config'; // <-- assure-toi que c'est bien 'appConfig' ici

bootstrapApplication(AppComponent, appConfig)
  .catch(err => console.error(err));
