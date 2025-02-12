import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
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

  // Key for session storage
  private readonly SESSION_STORAGE_KEY = 'loggedInUser';

  constructor() {
    const storedUser = sessionStorage.getItem(this.SESSION_STORAGE_KEY);
    if (storedUser) {
      this.loggedInUser = storedUser;
    }
  }

  private loggedInUser: string | null = null;

  login(username: string | undefined, password: string | undefined): boolean {
    let isValid = false;

    if (username && password && this.usersLoginAccounts.get(username) === password) {
      isValid = true;
      this.loggedInUser = username;
      sessionStorage.setItem(this.SESSION_STORAGE_KEY, username);
    }

    return isValid;
  }

  public getLoggedInUser(): string | null {
    return this.loggedInUser;
  }

  logout(): void {
    this.loggedInUser = null;
    sessionStorage.removeItem(this.SESSION_STORAGE_KEY);
  }

  hasValidSession(): boolean {
    return this.loggedInUser !== null;
  }
}