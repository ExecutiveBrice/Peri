import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { PmService } from '../../services/pm.service';
import { RefreshService } from '../../services/refresh.service';
import { Order } from '../../models/orderParam.model';
import { Pm } from 'src/app/models/pm.model';
import { Filter } from 'src/app/models/filterParam.model';
import { environment } from 'src/environments/environment';
import { MessageService } from '../../services/message.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit, OnDestroy {

  /**
   * Définition des variables du composant
   */
  refreshSubscription: Subscription;

  pmHs: any[] = [];
  orderHsArgs: Order[];
  columnHsArgs: Filter[];

  pmOk: any[] = [];
  orderOkArgs: Order[];
  columnOkArgs: Filter[];

  constructor(
    private route: ActivatedRoute,
    private pmService: PmService,
    private refreshService: RefreshService,
    private messageService: MessageService) { }

  /**
   * Initilisation des variables et des observables du composant
   */
  ngOnInit() {
    this.orderHsArgs = environment.orderDetailHs;
    this.orderOkArgs = environment.orderDetailOk;
    this.columnHsArgs = environment.columnDetailHsNameId;
    this.columnOkArgs = environment.columnDetailOkNameId;

    this.refreshService.refreshInit();

    this.subscribeRefreshService()
    this.getDataFromUrlParam()
  }


  /**
   * Récupération des PMs en fonction du parametre transmis
   */
  getDataFromUrlParam() {
    this.route.paramMap.subscribe(params => {
      let param = params.get("id").split('$')
      if (param[0] == "olt") {
        this.getPmForOlt(param[1])
      } else if (param[0] == "pm") {
        this.getPmForPm(param[1])
      }
    })

    setTimeout(() => {
      this.messageService.clearRedMessage()
      this.messageService.clearBlueMessage()
      this.messageService.clearGreenMessage()
    }, environment.clearMessageTimer)
  }


  /**
   * Récupération des PM pour la vue OLT si aucune resynchronisation en cours
   * @param oltId 
   */
  getPmForOlt(oltId) {
    
    var idInterval = setInterval(() => {
      if (!environment.isResynchInProgress) {
        this.messageService.addBlueMessage("Actualisation des données en cours");
        this.pmService.getPmListForOltId(oltId)
          .subscribe(data => {
            if(data.pmHs.length == 0 && data.pmOk.length == 0){
              this.messageService.addRedMessage("Aucun PM trouvé pour cet identifiant : " + oltId)
            }else{
            this.updatedItem(this.pmHs, data.pmHs, "pmHS")
            this.updatedItem(this.pmOk, data.pmOk, "pmOK")
            }
          },
          (error: any) => {
            this.messageService.addRedMessage("Une erreur est survenue lors de la récupération des PMs de l'OLT " + oltId)
          });
        clearInterval(idInterval);
      }
    }, 100)
  }


  /**
   * Récupération des PM pour la vue PM
   * @param pmId 
   */
  getPmForPm(pmId) {
    var idInterval = setInterval(() => {
      if (!environment.isResynchInProgress) {
        this.messageService.addBlueMessage("Actualisation des données en cours");
        this.pmService.getPmListForPmId(pmId)
          .subscribe(data => {
            if(data.pmHs.length == 0 && data.pmOk.length == 0){
              this.messageService.addRedMessage("Aucun PM trouvé pour cet identifiant : " + pmId)
            }else{
            this.updatedItem(this.pmHs, data.pmHs, "pmHS")
            this.updatedItem(this.pmOk, data.pmOk, "pmOK")
            }
          },
          (error: any) => {
            this.messageService.addRedMessage("Une erreur est survenue lors de la récupération des PMs du PM " + pmId)
          });
        clearInterval(idInterval);
      }
    }, 100)
  }



  /**
   * Mise à jour des listes pour l'affichage
   * @param originalDataList 
   * @param sourceDataList 
   */
  updatedItem(originalDataList: Pm[], sourceDataList: Pm[], typeActualisation: string ) {
    originalDataList.splice(0, originalDataList.length)
    sourceDataList.forEach(newItem => {
      originalDataList.push(newItem)
    })

    this.messageService.addGreenMessage("Actualisation des "+typeActualisation+" terminée");

    setTimeout(() => {
      this.messageService.clearRedMessage()
      this.messageService.clearBlueMessage()
      this.messageService.clearGreenMessage()
    }, environment.clearMessageTimer)

  }


  /**
   * Souscription au service d'observation du rafraichissement des données
   */
  subscribeRefreshService() {
    this.refreshSubscription = this.refreshService.refresh.subscribe({
      complete: () => {
        this.getDataFromUrlParam()
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
