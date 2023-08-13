import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ToolbarComponent} from './Component/toolbar/toolbar.component';
import {MatInputModule} from '@angular/material/input';
import {MatCardModule} from '@angular/material/card';
import {MatSidenavModule} from '@angular/material/sidenav';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatPaginatorModule} from '@angular/material/paginator';
import {HttpClient} from '@angular/common/http';
import {HttpClientModule} from '@angular/common/http';

import {MatToolbarModule} from '@angular/material/toolbar';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatBadgeModule} from '@angular/material/badge';
import {MatSelectModule} from '@angular/material/select';

import {MatStepperModule} from '@angular/material/stepper';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatRadioModule} from '@angular/material/radio';

import {FlexLayoutModule} from '@angular/flex-layout';
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule, MatIconRegistry} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {PagenotfoundComponent} from './component/pagenotfound/pagenotfound.component';
import {ForgetPasswordComponent} from './Component/auth/forget-password/forget-password.component';
import {ResetPasswordComponent} from './Component/auth/reset-password/reset-password.component';
import {AdminComponent} from './Component/admin/admin/admin.component';
import {SpineerComponent} from './Component/spineer/spineer.component';
import {LoginComponentComponent} from './Component/auth/login-component/login-component.component';
import {RegistrationComponent} from './Component/auth/registration/registration.component';
import {FooterComponent} from './Component/footer/footer.component';
import {SidenavbarComponent} from './Component/sidenavbar/sidenavbar.component';
import {MatDialogModule} from '@angular/material/dialog';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatCarouselModule} from '@ngmodule/material-carousel';
import {AdminUserComponent} from './Component/admin-user/admin-user.component';
import {MatTableModule} from '@angular/material/table';
import {ToolbarUserComponent} from './Component/toolbar-user/toolbar-user.component';
import {MatDividerModule} from '@angular/material/divider';
import {DilalogUnlockComponent} from './Component/dilalog-unlock/dilalog-unlock.component';
import {DialalogDeleteComponent} from './Component/dialalog-delete/dialalog-delete.component';

import {ThongTinTaiKhoanComponent} from './Component/thong-tin-tai-khoan/thong-tin-tai-khoan.component';
import {DilalogChangePassWordComponent} from './Component/dilalog-change-pass-word/dilalog-change-pass-word.component';

import {ToastrModule} from 'ngx-toastr';

import {LoginCustomerComponent} from './Component/auth/login-customer/login-customer.component';
import { CusTomerComponent } from './Component/cus-tomer/cus-tomer.component'
import {OrderDialogComponent} from './Component/order-dialog/order-dialog.component';
import { ReceptionTableComponent } from './Component/reception-table/reception-table.component';
import { DilogUserDangKyComponent } from './Component/dilog-user-dang-ky/dilog-user-dang-ky.component';
import { AccountingComponent } from './Component/accounting/accounting.component';
import { OderDetailDialogComponent } from './Component/oder-detail-dialog/oder-detail-dialog.component';
import { DilalogUserComponent } from './Component/dilalog-user/dilalog-user.component';
import { HomeComponent } from './Component/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    PagenotfoundComponent,
    ForgetPasswordComponent,
    ResetPasswordComponent,
    AdminComponent,
    SpineerComponent,
    LoginComponentComponent,
    RegistrationComponent,
    FooterComponent,
    SidenavbarComponent,
    AdminUserComponent,
    ToolbarUserComponent,
    DilalogUnlockComponent,
    DialalogDeleteComponent,
    ThongTinTaiKhoanComponent,
    DilalogChangePassWordComponent,
    LoginCustomerComponent,
    OrderDialogComponent,
    CusTomerComponent,
    DilalogUserComponent,
    ReceptionTableComponent,
    DilogUserDangKyComponent,
    AccountingComponent,
    OderDetailDialogComponent,
    HomeComponent,

  ],
  imports: [
    MatBadgeModule,
    BrowserModule,
    MatToolbarModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatPaginatorModule,
    FormsModule,
    MatCardModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatSnackBarModule,
    MatStepperModule,
    MatCheckboxModule,
    MatRadioModule,
    MatIconModule,
    FlexLayoutModule,
    MatMenuModule,
    MatButtonModule,
    MatSidenavModule,
    MatSelectModule,
    MatTooltipModule,
    MatDialogModule,
    MatTableModule,
    MatDividerModule,
    ToastrModule.forRoot(),
    MatCarouselModule.forRoot() // ---------- Important

  ],
  providers: [HttpClient],
  bootstrap: [AppComponent],

})
export class AppModule {
  constructor(matIconRegistry: MatIconRegistry) {
    matIconRegistry.registerFontClassAlias('fontawesome', 'fa');
  }
}
