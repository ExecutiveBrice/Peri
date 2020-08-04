import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class MessageService {


  /**
   * Gestion d'affichage des messages rouges
   */
  redMessages: string[] = [];
  addRedMessage(message: string) {
    this.redMessages.push(message);
  }

  clearRedMessage() {
    this.redMessages = [];
  }


  /**
   * Gestion d'affichage des messages orange
   */
  orangeMessages: string[] = [];
  addOrangeMessage(message: string) {
    this.orangeMessages.push(message);
  }

  clearOrangeMessage() {
    this.orangeMessages = [];
  }


  /**
   * Gestion d'affichage des messages bleu
   */
  blueMessages: string[] = [];
  addBlueMessage(message: string) {
    this.blueMessages.push(message);
  }

  clearBlueMessage() {
    this.blueMessages = [];
  }


  /**
   * Gestion d'affichage des messages vert
   */
  greenMessages: string[] = [];
  addGreenMessage(message: string) {
    this.greenMessages.push(message);
  }
  
  clearGreenMessage() {
    this.greenMessages = [];
  }
}
