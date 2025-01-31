import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private usersLoginAccounts = new Map<String, String>([
    ['user1', 'password1'],
    ['user2', 'password2'],
    ['user3', 'password3'],
    ['user4', 'password4'],
    ['user5', 'password5'],
    ['user6', 'password6'],
  ]);

  constructor() { 
    
  }
  

  login(username:String | undefined, password:String | undefined){
    if (!username || !password) return false;
    return this.usersLoginAccounts.get(username) === password;
  }
}
