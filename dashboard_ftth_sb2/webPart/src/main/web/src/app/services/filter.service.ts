
import { Injectable } from '@angular/core';
import { Filter } from '../models/filterParam.model';


@Injectable({ providedIn: 'root' })
export class FilterService {

/**
 * Filtre la liste grace aux paramètres
 * @param list 
 * @param filterParams 
 */
  public filter(list, filterParams: Filter[]) {
    filterParams.forEach(filterParam => {
      if (filterParam.type == "string") {
        if (filterParam.rule && filterParam.valeur.length > 0) {
          if (filterParam.rule == "Egal") {
            list = list ? list.filter(item => item[filterParam.id] && item[filterParam.id].toLowerCase() == filterParam.valeur.toLowerCase()) : [];
          } else if (filterParam.rule == "Contient") {
            list = list ? list.filter(item =>
              item[filterParam.id] && item[filterParam.id].search(new RegExp(filterParam.valeur, 'i')) > -1) : [];
          } else if (filterParam.rule == "Different") {
            list = list ? list.filter(item =>
              item[filterParam.id] && item[filterParam.id].search(new RegExp(filterParam.valeur, 'i')) < 0) : [];
          }
        } else {
          console.log("pas de règle ou text trop court " + filterParam.rule +" "+ filterParam.valeur)
        }
      } else {
        if (filterParam.rule && filterParam.valeur.length == 0) {
          if (filterParam.rule == "=") {
            list = list ? list.filter(item =>
              item[filterParam.id] &&  item[filterParam.id] == filterParam.valeur) : [];
          } else if (filterParam.rule == "<") {
            list = list ? list.filter(item =>
              item[filterParam.id] && item[filterParam.id] < filterParam.valeur) : [];
          } else if (filterParam.rule == ">") {
            list = list ? list.filter(item =>
              item[filterParam.id] && item[filterParam.id] > filterParam.valeur) : [];
          }
        } else {
          console.log("pas de règle ou valeur inexistante"+ filterParam.rule +" "+ filterParam.valeur)
        }
      }
    });
    return list;
  }

}
