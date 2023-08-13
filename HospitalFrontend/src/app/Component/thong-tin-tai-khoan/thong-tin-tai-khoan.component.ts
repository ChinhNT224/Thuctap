import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ToastrService } from "ngx-toastr";
import { ActivatedRoute, Router } from "@angular/router";
import { UserService } from "src/app/Service/user.service";
import { Location } from "@angular/common";

@Component({
  selector: "app-thong-tin-tai-khoan",
  templateUrl: "./thong-tin-tai-khoan.component.html",
  styleUrls: ["./thong-tin-tai-khoan.component.scss"],
})
export class ThongTinTaiKhoanComponent implements OnInit {
  userId: any;
  user: any;
  customerId: any;
  customer: any;
  private canGoBack: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router,
    private location: Location,
    private userService: UserService
  ) {
    this.canGoBack = !!this.router.getCurrentNavigation()?.previousNavigation;
  }

  formBCTKCC: FormGroup = this.formBuilder.group({
    Email: [null, [Validators.required, Validators.email]],
    SDT: [],
    Name: [],
    Password: [],
  });

  ngOnInit(): void {
    this.userId = this.route.snapshot.paramMap.get("userId");
    this.customerId = this.route.snapshot.paramMap.get("customerId");
    console.log("userId:", this.userId);
    console.log("customerId:", this.customerId);
    this.getUserAndCustomer();
  }

  getUserAndCustomer() {
    if (this.userId) {
      console.log("Calling DetailUser API with userId:", this.userId);
      this.userService.DetailUser(this.userId).subscribe((response: any) => {
        console.log("DetailUser response:", response);
        this.user = response.obj;
        this.fillFormControls(this.user);
      });
    }

    if (this.customerId) {
      console.log("Calling DetailCustomer API with customerId:", this.customerId);
      this.userService.DetailCustomer(this.customerId).subscribe((response: any) => {
        console.log("DetailCustomer response:", response);
        this.customer = response.obj;
        this.fillFormControls(this.customer);
      });
    }
  }

  fillFormControls(data: any) {
    console.log("Filling form controls with data:", data);
    this.formBCTKCC.controls["Email"].setValue(data.email);
    this.formBCTKCC.controls["SDT"].setValue(data.mobileNumber);
    this.formBCTKCC.controls["Name"].setValue(data.name);
    this.formBCTKCC.controls["Password"].setValue(data.password);
  }

  doUpdate() {
    let data = {
      email: this.formBCTKCC.get("Email").value,
      mobileNumber: this.formBCTKCC.get("SDT").value,
      name: this.formBCTKCC.get("Name").value,
    };

    if (this.user) {
      console.log("Updating user information:", data);
      this.userService.UpdateInfor(data).subscribe((res: any) => {
        console.log("UpdateInfor response:", res);
        if (res.statusCode == 200) {
          this.toastr.success(res.message, "Success");
          window.history.back();
        }
      });
    }

    if (this.customer) {
      console.log("Updating customer information:", data);
      this.userService.UpdateInforCustomer(data).subscribe((res: any) => {
        console.log("UpdateInforCustomer response:", res);
        if (res.statusCode == 200) {
          this.toastr.success(res.message, "Success");
          window.history.back();
        }
      });
    }
  }

  changePassword() {
    if (this.userId) {
      console.log("Redirecting to change password for userId:", this.userId);
      this.router.navigateByUrl("passWord/" + this.userId);
    }
    if (this.customerId) {
      console.log("Redirecting to change password for customerId:", this.customerId);
      this.router.navigateByUrl("passWord/" + this.customerId);
    }
  }

  goBack(): void {
    if (this.canGoBack) {
      console.log("Navigating back.");
      this.location.back();
    }
  }
}
