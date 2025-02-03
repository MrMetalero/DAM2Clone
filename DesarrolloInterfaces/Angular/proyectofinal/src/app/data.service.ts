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

  private usersLoggedInTokens = new Map<string | undefined, string>([
    [this.usersLoginAccounts.get("alwaysup"),"logintoken1"],
  ]);

  constructor() { 
    
  }
  

  login(username:string | undefined, password:string | undefined){
    // if any of these is undefined, login is false
    if (!username || !password) return false;

    // on match of passwords for user X
    return this.usersLoginAccounts.get(username) === password; 
  }
}
