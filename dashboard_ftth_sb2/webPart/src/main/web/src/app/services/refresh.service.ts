import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { ResynchService } from '../services/resynch.service';
import { MessageService } from './message.service';
import { environment } from 'src/environments/environment';


@Injectable({ providedIn: 'root' })
export class RefreshService {

  refresh: Observable<{ isRefreshTime: false }>;
  gettingData: Observable<{ isResynchInProgress: false }>;
  resynch: Observable<{ isResynchInProgress: false }>;

  constructor(private resynchService: ResynchService, private messageService: MessageService) { }

  /**
   * Observe si un rafraichissement des données est nécéssaire
   */
  refreshInit() {
    this.refresh = Observable.create((observer) => {
      var id = setTimeout(() => {
        this.messageService.clearBlueMessage()
        this.messageService.addBlueMessage(`Rafraichissement des données initiée`);
        observer.complete();
        clearTimeout(id);
      }, environment.refreshTimer)

    })
  }

  /**
   * Observe si une resynchronisation est en cours sur le serveur
   */
  resynchPingInit() {
    this.resynch = Observable.create((observer) => {
      var id = setInterval(() => {
        this.resynchService.getResynch().subscribe(data => { environment.isResynchInProgress = data.resynch
          if (environment.isResynchInProgress == true) {
            environment.resynchroDate = data.date,
            observer.next({ isResynchInProgress: environment.isResynchInProgress })
          } else {
            clearInterval(id);
            observer.complete();
          }
        });

      }, environment.resynchRefresh)
    })
  }


}
