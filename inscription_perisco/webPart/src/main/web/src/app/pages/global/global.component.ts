import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { OltService } from '../../services/olt.service';
import { PmService } from '../../services/pm.service';
import { RefreshService } from '../../services/refresh.service';
import { Order } from '../../models/orderParam.model';
import { environment } from 'src/environments/environment';
import { Filter } from 'src/app/models/filterParam.model';
import { Olt } from 'src/app/models/olt.model';
import { Router } from '@angular/router';
import { MessageService } from '../../services/message.service';


@Component({
  selector: 'app-global',
  templateUrl: './global.component.html',
  styleUrls: ['./global.component.css']
})
export class GlobalComponent implements OnInit, OnDestroy {

  /**
   * Définition des variables du composant
   */
  param: string = "";
  refreshSubscription: Subscription;

  actualisationInProgress: boolean
  columnArgs: Filter[];
  orderArgs: Order[];
  olts: Olt[] = [];

  constructor(
    private router: Router,
    private oltService: OltService,
    private refreshService: RefreshService,
    private pmService: PmService,
    private messageService: MessageService
  ) { }

  /**
   * Initilisation des variables et des observables du composant
   */
  ngOnInit() {
    this.columnArgs = environment.columnGlobalNameId;
    this.orderArgs = environment.orderGlobal;

    this.refreshService.refreshInit();

    this.subscribeRefreshService()
    this.getData()
  }


  /**
   * Récupération / MAJ des données si aucune resynchronisation
   */
  getData() {
    var idInterval = setInterval(() => {
      if (!environment.isResynchInProgress) {
        if (this.actualisationInProgress) {
          this.messageService.clearOrangeMessage()
          this.messageService.addOrangeMessage("Actualisation déjà en cours");
        } else {
          this.messageService.addBlueMessage("Actualisation des données en cours");
          this.oltService.getAllOlts()
            .subscribe(olts => {
              this.updatedItem(this.olts, olts)
            });
        }
        clearInterval(idInterval);
      }
    }, 100)
  }


  /**
   * Mise à jour de la liste des OLTs par la nouvelle liste d'OLTs et récupération des comptages asynchrone
   * @param originalDataList 
   * @param sourceDataList 
   */
  updatedItem(originalDataList: Olt[], sourceDataList: Olt[]) {
    this.actualisationInProgress = true;

    originalDataList.splice(0, originalDataList.length)
    sourceDataList.forEach(newItem => {
      originalDataList.push(newItem)
      this.updateAttributes(newItem)
    })

    this.actualisationInProgress = false;

    this.messageService.addGreenMessage("Actualisation terminée");

    setTimeout(() => {
      this.messageService.clearRedMessage()
      this.messageService.clearBlueMessage()
      this.messageService.clearGreenMessage()
    }, environment.clearMessageTimer)

  }


  /**
   * Mise à jour de l'OLT par les comptages des PM HS et OK par OLT
   * @param olt 
   */
  updateAttributes(olt: Olt) {
    return new Promise((resolve, reject) => {
      this.pmService.getPmOkpmHsforOlt(olt.id).subscribe(pmOkHs => {
        olt.pmOk = pmOkHs[0]
        olt.pmHs = pmOkHs[1]
        resolve();
      },
        (error: any) => {
          this.messageService.addRedMessage("Une erreur est survenue lors de l'actualisation de l'OLT " + olt.id)
          resolve()
        });
    });
  }



  /**
   * Routage vers la vue détaillée
   * @param olt 
   */
  selectingItem(olt: Olt) {
    this.router.navigate(['detail/', 'olt$' + olt.id]);
  }


  /**
   * Souscription au service d'observation du rafraichissement des données
   */
  subscribeRefreshService() {
    this.refreshSubscription = this.refreshService.refresh.subscribe({
      complete: () => {
        this.getData()
        this.subscribeRefreshService()
      }
    });
  }


  /**
   * Désouscrire l'abonnement en cas de changment de page
   */
  ngOnDestroy() {
    this.refreshSubscription.unsubscribe();
  }



}
