import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Filter } from '../../models/filterParam.model';
import { Order } from '../../models/orderParam.model';
import { environment } from 'src/environments/environment';


import { faAngleUp, faAngleDown } from '@fortawesome/free-solid-svg-icons';

import { OrderService } from '../../services/order.service';
import { FilterService } from '../../services/filter.service';
import { Olt } from 'src/app/models/olt.model';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  /**
   * Définition des variables du composant
   */
  param: string = "";
  page: number = 1
  faAngleUp = faAngleUp;
  faAngleDown = faAngleDown;
  listNbPages: number[] = []
  orderList: string[] = []
  selectedNb: number;
  tmpData: any[] = [];

  @Input()
  data: any[];

  @Input()
  orderArgs: Order[] = [];

  @Input()
  columnArgs: Filter[] = [];

  @Output()
  selectedItem = new EventEmitter<Olt>();


  constructor(
    private filterService: FilterService,
    private orderService: OrderService) { }

  /**
   * Initilisation des variables, filtre et ordonancement du composant
   */
  ngOnInit() {
    this.listNbPages = environment.listNbPages
    this.selectedNb = this.listNbPages[0]

    this.orderArgs.forEach(element => {
      this.orderList.push(element.column + "_" + element.direction)
    });

    var idInterval = setInterval(() => {
      if (this.data.length > 0) {
        this.tmpData = this.filterService.filter(this.data, this.columnArgs)
        this.tmpData = this.orderService.order(this.tmpData, this.orderArgs)
        clearInterval(idInterval);
      }
    }, 100)

  }


/**
 * Permet de transferer l'élement sléectionné au composant parent (global ou detail)
 * @param item 
 */
  selectItem(item: Olt) {
    this.selectedItem.emit(item)
  }


  /**
   * Met à jour les couleurs des cases du tableau en fonction des seuils fixé
   * @param filterArg 
   * @param item 
   */
  getColor(filterArg: Filter, item: Olt) {
    if (filterArg.id == 'pmOk' && item[filterArg.id] > -1) {
      return environment.colorGood;
    }

    if (filterArg.id == "pmHs" && item[filterArg.id] > environment.thresholdAlerte) {
      return environment.colorAlerte;
    } else if (filterArg.id == "pmHs" && item[filterArg.id] > environment.thresholdWarning) {
      return environment.colorWarning;
    } else if (filterArg.id == "pmHs" && item[filterArg.id] > -1) {
      return environment.colorGood;
    }
  }




  /**
   * Met à jour les filtres en fonction des retours du composant search
   * @param filterArg 
   */
  updateFilter(filterArg: Filter) {
    this.columnArgs.forEach(element => {
      if (element.id == filterArg.id) {
        if (element.rule == "Retirer le filtre") {
          element.rule = ""
          element.valeur = ""
        } else {
          element.rule = filterArg.rule
          element.valeur = element.valeur
        }
      }
    });
    this.tmpData = this.filterService.filter(this.data, this.columnArgs)
    this.tmpData = this.orderService.order(this.tmpData, this.orderArgs)

  }


  /**
   * Met à jour les paramètres d'ordonancement en mettant à jour la liste d'objets orderArgs, traité par le orderService
   * @param columnName 
   */
  order(columnName: string, type:string) {
    //Si c'est le meme on ne change que le sens
    if (this.orderArgs[this.orderArgs.length - 1].column == columnName) {
      this.orderArgs[this.orderArgs.length - 1].direction == "asc" ? this.orderArgs[this.orderArgs.length - 1].direction = "desc" : this.orderArgs[this.orderArgs.length - 1].direction = "asc"
    } else {
      //Sinon, on enlève le dernier et on rajoute le nouveau
      this.orderArgs = this.orderArgs.filter(function (e) { return e.column !== columnName; })

      this.orderArgs.push({ column: columnName, direction: "asc", type:type})
      if (this.orderArgs.length > 3) {
        this.orderArgs.shift()
      }
    }

    //On recrée la liste pour les entêtes de colonne
    this.orderList = []
    this.orderArgs.forEach(element => {
      this.orderList.push(element.column + "_" + element.direction)
    });

    this.tmpData = this.orderService.order(this.tmpData, this.orderArgs)
  }




}
