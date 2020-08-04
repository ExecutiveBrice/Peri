import { Component, EventEmitter, OnInit, Input, Output } from '@angular/core';
import { Filter } from '../../models/filterParam.model';
import { environment } from 'src/environments/environment';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  /**
   * Définition des variables du composant
   */
  selection: string[];

  @Input()
  filterArg: Filter;

  @Output()
  selectedFilterArg = new EventEmitter<Filter>();


  constructor() { }

  /**
   * Initilisation des variables du composant
   */
  ngOnInit() {
    if (this.filterArg.type == "string") {
      this.selection = environment.selectionText;
    } else {
      this.selection = environment.selectionNumber;
    }
  }


  /**
   * Permet de setter la valeur du filtre dans l'objet filterArg et de l'envoyer au tableau
   * @param filterText 
   */
  search(filterText: string) {
    this.filterArg.valeur = filterText;
    this.selectedFilterArg.emit(this.filterArg)
  }


  /**
   * Permet de setter la valeur de la règle dans l'objet filterArg et de l'envoyer au tableau
   * @param filterRule 
   */
  ChangeSelected(filterRule: string) {
    this.filterArg.rule = filterRule
    this.selectedFilterArg.emit(this.filterArg);
  }
}
