
import { AdminComponent } from './Component/admin/admin/admin.component';
import { RegistrationComponent } from './Component/auth/registration/registration.component';
import { LoginComponentComponent } from './Component/auth/login-component/login-component.component';
import { ForgetPasswordComponent } from './Component/auth/forget-password/forget-password.component';
import { ResetPasswordComponent } from './Component/auth/reset-password/reset-password.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PagenotfoundComponent } from './component/pagenotfound/pagenotfound.component';
import { AdminUserComponent } from './Component/admin-user/admin-user.component';
import { ThongTinTaiKhoanComponent } from './Component/thong-tin-tai-khoan/thong-tin-tai-khoan.component';
import { DilalogChangePassWordComponent } from './Component/dilalog-change-pass-word/dilalog-change-pass-word.component';
import {LoginCustomerComponent} from './Component/auth/login-customer/login-customer.component';
import {Customer} from "./Model/customer.model";
import {CusTomerComponent} from "./Component/cus-tomer/cus-tomer.component";

const routes: Routes = [

  {
    path: '', redirectTo: 'books',
    pathMatch: 'full'
  },
  {path: 'update-password/:token', component: ResetPasswordComponent},
  {path: 'forget-password', component: ForgetPasswordComponent},
  {path: 'login', component: LoginComponentComponent},
  {path: 'register', component: RegistrationComponent},
  {path: 'admin', component: AdminComponent},
  {path: 'register', component: RegistrationComponent},
  {path:'loginCustomer',component:LoginCustomerComponent},
  {path:'user',component:AdminUserComponent},
  {path:'userid/:userId',component:ThongTinTaiKhoanComponent},
  {path:'passWord/:userId',component:DilalogChangePassWordComponent},
  {path:'Customer',component:CusTomerComponent},
  {path: '**', component: PagenotfoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
