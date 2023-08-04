import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenCustomerServiceService {
  public valideUser = false;
  public loggedIn = false;
  constructor() { }
  public handle(data) {
    this.set(data);
  }
  // setting token in local
  public set(data) {
    localStorage.setItem('message', data.message);
    localStorage.setItem('email', data.obj.email);
    localStorage.setItem('Name', data.obj.name);
    localStorage.setItem('phone', data.obj.mobileNumber);
    localStorage.setItem('id',data.obj.customerId)
  }
  // getting token from the local storage
  public get() {
    return localStorage.getItem('message');
  }
  // Removing item from the local storage
  remove() {
    localStorage.removeItem('message');
    localStorage.removeItem('email');
    localStorage.removeItem('Name');
    localStorage.removeItem('role');
    localStorage.removeItem('phone');
    localStorage.removeItem('customerId');
    sessionStorage.clear();
  }
  logedIn(value: boolean) {
    if ( this.get() != null) {
      return this.loggedIn = true;
    }
  }
  loggedStatus() {
    return this.logedIn(this.loggedIn);
  }
}
