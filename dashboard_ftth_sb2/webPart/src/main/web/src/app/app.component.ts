import { Component } from '@angular/core';
import { Subscription } from 'rxjs';
import { RefreshService } from './services/refresh.service';
import { MessageService } from './services/message.service';
import { environment } from 'src/environments/environment';
import { formatDate } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  resynchSubscription: Subscription;

  constructor(
    private refreshService: RefreshService,
    private messageService: MessageService) {

    this.subscribeResynchService()

  }




/**
 * Permet d'afficher un message si une resynchro est en cours
 */
  subscribeResynchService() {
    this.resynchSubscription = this.refreshService.resynch.subscribe({
      next: (value) => {
        if (this.messageService.orangeMessages.length == 0) {
          let jstoday = formatDate(environment.resynchroDate, 'dd-MM-yyyy hh:mm:ss', 'en-EN');
          this.messageService.addOrangeMessage(`Resynchronisation en cours depuis le `+ jstoday );
        }
      },
      complete: () => {
        this.messageService.clearOrangeMessage()
        setTimeout(() => {
          this.subscribeResynchService()
        }, environment.resynchTimer)
      }

    });
  }


}
