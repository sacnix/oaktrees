import { Injectable } from '@angular/core';

const TOKEN_KEY = 'AuthToken';
const USER_NAME_KEY = 'AuthUserName';
const AUTHORITIES_KEY = 'AuthAuthorities';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  roles: Array<string> = [];

  constructor() { }

  public setToken(token: string): void{
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string{
    return <string>sessionStorage.getItem(TOKEN_KEY);
  }

  public setUserName(userName: string): void{
    window.sessionStorage.removeItem(USER_NAME_KEY);
    window.sessionStorage.setItem(USER_NAME_KEY, userName);
  }

  public getUserName(): string{
    return <string>sessionStorage.getItem(USER_NAME_KEY);
  }

  public setAuthorities(authorities: string[]): void{
    window.sessionStorage.removeItem(AUTHORITIES_KEY);
    window.sessionStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
  }

  public getAuthorities(): string[] {
    this.roles = [];
    if (sessionStorage.getItem(AUTHORITIES_KEY)) {
      JSON.parse(<string>sessionStorage.getItem(AUTHORITIES_KEY)).forEach((authority: { authority: string; }) => {
        this.roles.push(authority.authority);
      });
    }
    return this.roles;
  }

  public logOut(): void {
    window.sessionStorage.clear();
  }
}
