import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

import { Pm } from '../models/pm.model';


@Injectable({ providedIn: 'root' })
export class PmService {


  private pmUrl = environment.url + 'pm';

  constructor(
    private http: HttpClient) { }

  /**
   * Récupération les comptages des pm OK et HS pour un olt
   * @param oltId 
   */
  getPmOkpmHsforOlt(oltId: string): Observable<number[]> {
    let parametres = new HttpParams();
    parametres = parametres.append('id', oltId);
    return this.http.get<number[]>(this.pmUrl + '/PmOkpmHsforOlt', { params: parametres })

  }


  /**
   * Récupération des PM pour un olt
   * @param oltId 
   */
  getPmListForOltId(oltId: string): Observable<any> {
    let parametres = new HttpParams();
    parametres = parametres.append('id', oltId);
    return this.http.get<any>(this.pmUrl + '/PmListForOltId', { params: parametres })
  }


  /**
   * Récupération des PM pour un PM
   * @param pmId 
   */
  getPmListForPmId(pmId: string): Observable<{ "pmHs": Pm[], "pmOk": Pm[] }> {
    let parametres = new HttpParams();
    parametres = parametres.append('id', pmId);
    return this.http.get<{ "pmHs": Pm[], "pmOk": Pm[] }>(this.pmUrl + '/PmListForPmId', { params: parametres })
  }



}
