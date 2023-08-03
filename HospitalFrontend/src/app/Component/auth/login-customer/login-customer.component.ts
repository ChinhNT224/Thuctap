import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../Service/user.service';
import {TokenService} from '../../../Service/token.service';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-login-customer',
  templateUrl: './login-customer.component.html',
  styleUrls: ['./login-customer.component.scss']
})
export class LoginCustomerComponent implements OnInit {
  public error = null;
  public hide = true;
  public valideUser = false;
  public tokenValue = null;
  public isLoading = false;
  public form = {
    email: null,
    password: null,
  };

  constructor(private user: UserService,
              private token: TokenService,
              private route: Router,
              private toastr: ToastrService,
              private titleService: Title
  ) {

  }
  handleError(error: { error: any; }) {
    this.isLoading = false;
    this.error = error.error.message;
    if ( error.error.status === 0) {
      console.log('please connect database');
    }
    this.toastr.error(this.error);
    console.log(error);
  }
  ngOnInit() {
  }
  onSubmit() {
    this.isLoading = true;
    this.user.SigInCustemer(this.form).subscribe(
      data => this.handleResponse(data),
      error => this.handleError(error)
    );
  }

  handleResponse(data) {
    console.log(data);
    this.isLoading = false;
    console.log('user is --->' + data);
    this.toastr.success('Sucessfully Login');
      localStorage.setItem('role', 'user');
      this.route.navigateByUrl('Customer');
  }


}
