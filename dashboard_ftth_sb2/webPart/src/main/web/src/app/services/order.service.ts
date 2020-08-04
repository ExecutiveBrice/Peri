
import { Injectable } from '@angular/core';
import { Order } from '../models/orderParam.model';


@Injectable({ providedIn: 'root' })
export class OrderService {

  /**
   * Met en ordre une liste en fonction des paramètres nom de colonne et direction
   * @param liste 
   * @param orderParams 
   */
  public order(liste: any[], orderParams: Order[]): any[] {

    if (!liste || liste.length <= 1) {
      return liste;
    } // array with only one item
    if (!orderParams || orderParams.length == 0) {
      return liste;
    } // no sorting param

    orderParams.forEach(orderParam => {
      liste.sort((a, b) => {
        if (orderParam.type == "number") {
          if (a[orderParam.column] < b[orderParam.column]) {
            return 1
          } else {
            return -1
          }
        } else {
          if (!a[orderParam.column]) {
            a[orderParam.column] = " "
          }
          if (!b[orderParam.column]) {
            b[orderParam.column] = " "
          }
          return a[orderParam.column].localeCompare(b[orderParam.column], "fr", { sensitivity: 'base' })
        }
      });
      if (orderParam.direction == "desc") {
        liste.reverse()
      }
    })

    return liste;
  };



}
