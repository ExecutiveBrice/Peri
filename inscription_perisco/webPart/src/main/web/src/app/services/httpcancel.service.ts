import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable()
export class HttpCancelService {

  private pendingHTTPRequests$ = new Subject<void>();

  constructor() { }

  /**
   * permet l'annulation des requetes HTTP lors d'un changement de page
   */
  public cancelPendingRequests() {
    this.pendingHTTPRequests$.next();
  }

  public onCancelPendingRequests() {
    return this.pendingHTTPRequests$.asObservable();
  }

}