import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private usersLoginAccounts = new Map<string, string>([
    ['user1', '1234'],
    ['user2', '1234'],
    ['user3', '1234'],
    ['user4', '1234'],
    ['user5', '1234'],
    ['user6', '1234'],
    ['alwaysup', '1234'],
  ]);

  //En desuso por ahora
  public usersLoggedInTokens = new Map<string | undefined, string>([
    //Para tener siempre un usuario con logintoken. Debug
    [this.usersLoginAccounts.get("alwaysup"),"logintoken1"],
  ]);

  //Usando para la autenticación
  private loggedInUser: string | null = null;

  constructor() { 
    
  }
  

  login(username: string | undefined, password: string | undefined): boolean {
    let isValid = false;
  
    if (username && password && this.usersLoginAccounts.get(username) === password) {
      isValid = true;
      this.loggedInUser = username;
    }
  
    return isValid;
  }

  //desuso
  storeNewLoginToken(usernameLogin:string){
    this.usersLoggedInTokens.set(usernameLogin,this.randomTokenGenerator())
  }

  //desuso
  randomTokenGenerator(): string {
    return Date.now().toString(36) + Math.random().toString(36).slice(2, 10);
  }

  getLoggedInUser(): string | null {
    return this.loggedInUser;
  }

  logout() {
    this.loggedInUser = null;
  }

  hasValidSession(): boolean {
    if (this.loggedInUser != null) {
      return true
    }else{
      return false
    }
 
}
}
