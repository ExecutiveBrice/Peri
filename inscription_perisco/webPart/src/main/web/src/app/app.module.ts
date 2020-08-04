import { NgModule, APP_INITIALIZER  }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpClientModule }    from '@angular/common/http';

import { NgbPaginationModule, NgbNavModule, NgbDropdownModule} from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule }     from './app-routing.module';
import { AppComponent }         from './app.component';
import { DetailComponent }   from './pages/detail/detail.component';
import { GlobalComponent }   from './pages/global/global.component';

import { NgbdNavBasic } from './pages/navbar/navbar.component';
import { MessagesComponent }    from './pages/messages/messages.component';
import { SearchComponent }    from './pages/search/search.component';
import { TableComponent }    from './pages/table/table.component';
import { RefreshService }    from './services/refresh.service';
import { HttpCancelService } from './services/httpcancel.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { ManageHttpInterceptor } from './managehttp.interceptor';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

/**
 * Permet d'initié l'observation de la resynchronisation
 * @param refreshService 
 */
export function initializeApp(refreshService: RefreshService) {
  return () => refreshService.resynchPingInit();
}
@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    FontAwesomeModule,
    NgbDropdownModule,
    NgbPaginationModule,
    NgbNavModule
  ],
  declarations: [
    AppComponent,
    
    MessagesComponent,
    DetailComponent,
    GlobalComponent,
    SearchComponent,
    TableComponent,
    NgbdNavBasic
  ],
  providers: [
    RefreshService,
    { provide: APP_INITIALIZER,
      useFactory: initializeApp,
      deps: [RefreshService], multi: true },
      HttpCancelService,
    { provide: HTTP_INTERCEPTORS, useClass: ManageHttpInterceptor, multi: true }
 ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
