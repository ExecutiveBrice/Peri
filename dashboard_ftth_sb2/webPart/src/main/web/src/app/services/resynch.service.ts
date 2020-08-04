import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Resynch } from '../models/resynchro.model';
import { Observable} from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ResynchService {

  private resynchUrl = environment.url+'resynch';  // URL to web api

  constructor(
    private http: HttpClient) { }

  /**
   * Récupère l'état de la resynchronisation
   */
  getResynch(): Observable<Resynch> {
    return this.http.get<Resynch>(this.resynchUrl+'/')
  }

}
