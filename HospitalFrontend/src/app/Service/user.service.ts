import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, Subject} from 'rxjs';
import {environment} from 'src/environments/environment';
import {HttpserviceService} from './httpservice.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = environment.BASE_URL;

  constructor(private http: HttpClient, private httpService: HttpserviceService) {
  }

  private _autoRefresh$ = new Subject();
  private httpOtions = {
    headers: new HttpHeaders({'content-type': 'application/json'})
  };

  getAccountingByTimePeriod(timePeriod: string) {
    const url = `${this.baseUrl}/accounting/order-stats?timePeriod=${timePeriod}`;
    return this.http.get(url);
  }
  public signUp(data: any) {
    return this.http.post(`${this.baseUrl}/registration`, data);
  }

  public signUpCustomer(data: any) {
    return this.http.post(`${this.baseUrl}/customer/registration`, data);
  }

  public CustermDetail(id:string, idCustem :string){
    return this.http.get(`${this.baseUrl}/reception/customer/${id}/orders/${idCustem}`);
  }
  public Confirm(id:string,data:any){
    return this.http.post(`${this.baseUrl}/${environment.confirmOrder}/${id}/confirm`, data);
  }
  public refuse(id:string,data:any){
    return this.http.post(`${this.baseUrl}/${environment.confirmOrder}/${id}/refused`, data);
  }
  public GetAllOrder() {
    return this.http.get(`${this.baseUrl}/${environment.getAllorder}`);
  }

  public AddOrder(data: any, id: string) {
    return this.http.post(`${this.baseUrl}/${environment.addOrder}${id}/orders`, data);
  }

  public getAccounting() {
    return this.http.get(`${this.baseUrl}/${environment.getAccounting}`);
  }

  public UpdateOrder(data: any, id: string, orderId: string) {
    return this.http.put(`${this.baseUrl}/${environment.updateOrder}${id}/orders/${orderId}`, data);
  }

  public Detail(id: string ) {
    return this.http.get(`${this.baseUrl}/${environment.DetailOrder}${id}`)
  }

  public Delete(idcustem: any, id: string) {
    return this.http.delete(`${this.baseUrl}/${environment.DeleteOrder}${idcustem}/orders/${id}`);
  }

  public signIn(data: any) {
    return this.http.post(`${this.baseUrl}/user/login`, data);
  }

  public SigInCustemer(data: any) {
    return this.http.post(`${this.baseUrl}/customer/login`, data);
  }

  verifyUserByToken(token) {
    return this.http.post(`${this.baseUrl}/user/verify/`, token);
  }

  forgetPassword(data) {
    return this.http.post(`${this.baseUrl}/user/forgotpassword`, data);
  }

  public updatePassword(updatePassword: any, token: string): Observable<any> {
    return this.http.put(`${this.baseUrl}/user/update/${token}`,
      updatePassword,
    );
  }

  public getDanhSachOrder(id: string, trangthai: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${environment.custemerOrder}${id}/${trangthai}`);
  }

  public SearchUser(data: any) {
    return this.http.post(`${environment.BASE_URL}/${environment.UserSearch}`, data)
  }

  public ActiveUser(userId: number) {
    return this.httpService
      // tslint:disable-next-line: max-line-length
      .get(`${environment.BASE_URL}/${environment.ActiveUser}/${userId}`, {headers: new HttpHeaders({token: localStorage.token})})
      .pipe(
      );
  }

  public DeleteUser(userId: number) {
    return this.httpService
      // tslint:disable-next-line: max-line-length
      .get(`${environment.BASE_URL}/${environment.DeleteUser}/${userId}`, {headers: new HttpHeaders({token: localStorage.token})})
      .pipe(
      );
  }

  public DetailUser(userId: number) {
    return this.httpService
      // tslint:disable-next-line: max-line-length
      .get(`${environment.BASE_URL}/${environment.GetUsser}/${userId}`, {headers: new HttpHeaders({token: localStorage.token})})
      .pipe(
      );
  }


  public UpdatepassWord(userId: number, data: any) {
    return this.http
      .put(`${environment.BASE_URL}/${environment.UpdatePassword}/${userId}`, data, {headers: new HttpHeaders({token: localStorage.token})});
  }

  public UpdateInfor(data: any) {
    return this.http
      .put(`${environment.BASE_URL}/${environment.Updateinfor}`, data, {headers: new HttpHeaders({token: localStorage.token})});
  }

  public DetailCustomer(customerId: any) {
    return this.http
      // tslint:disable-next-line: max-line-length
      .get(`${environment.BASE_URL}/${environment.GetCustomer}/${customerId}`)
    ;
  }
  public UpdatepassWordCustomer(customerId: any, data: any):Observable<any> {
    return this.http
      .put(`${environment.BASE_URL}/${environment.UpdatePasswordCustomer}/${customerId}`, data);
  }

  public UpdateInforCustomer(data: any) {
    return this.http
      .put(`${environment.BASE_URL}/${environment.UpdateinforCustomer}`, data);
  }
}
