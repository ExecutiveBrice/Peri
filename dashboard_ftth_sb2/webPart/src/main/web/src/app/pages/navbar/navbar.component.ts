import {Component} from '@angular/core';
import { Router } from '@angular/router';



@Component({
  selector: 'ngbd-nav-basic',
  templateUrl: './navbar.component.html',
  styleUrls: [ './navbar.component.css' ]
})
export class NgbdNavBasic {

  active = 1;

  
  constructor(
    private router: Router) {}

    /**
     * Routage vers la vue détaillé par Olt
     * @param term 
     */
    searchForOlt(term: string): void {
      this.router.navigate(['detail/', 'olt$'+term ]);
    }

    
    /**
     * Routage vers la vue détaillé par Pm
     * @param term 
     */
    searchForPm(term: string): void {
      this.router.navigate(['detail/', 'pm$'+term ]);
    }
}