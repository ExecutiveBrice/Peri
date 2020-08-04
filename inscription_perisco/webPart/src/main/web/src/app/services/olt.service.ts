import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { Observable} from 'rxjs';
import { environment } from 'src/environments/environment';
import { Olt } from '../models/olt.model';


@Injectable({ providedIn: 'root' })
export class OltService {

  private oltUrl = environment.url+'olt';

  constructor(
    private http: HttpClient) { }

/**
 * Récupère la liste complète des Olts avec leurs attributs
 */
  getAllOlts(): Observable<Olt[]> {
    return this.http.get<Olt[]>(this.oltUrl+'/all')
  }


}
