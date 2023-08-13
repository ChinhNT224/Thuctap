import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

import { ActivatedRoute, Router } from "@angular/router";
import { UserService } from "src/app/Service/user.service";
import { Location } from "@angular/common";
import { ToastrService } from "ngx-toastr";

@Component({
  selector: "app-dilalog-change-pass-word",
  templateUrl: "./dilalog-change-pass-word.component.html",
  styleUrls: ["./dilalog-change-pass-word.component.scss"],
})
export class DilalogChangePassWordComponent implements OnInit {
  userId: any;
  customerId: any;
  hide = true;
  private canGoBack: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router,
    private readonly location: Location,
    private userService: UserService
  ) {
    this.canGoBack = !!this.router.getCurrentNavigation()?.previousNavigation;
  }

  formBCTKCC: FormGroup = this.formBuilder.group({
    Password: [null, [Validators.required, Validators.minLength(8)]],
  });

  ngOnInit(): void {
    this.userId = this.route.snapshot.paramMap.get("userId");
    this.customerId = this.route.snapshot.paramMap.get("customerId");
    console.log("userId:", this.userId); // Kiểm tra xem customerId đã được gán chính xác không
    console.log("customerId:", this.customerId); // Kiểm tra xem customerId đã được gán chính xác không
  }

  doUpdate() {
    if (this.formBCTKCC.invalid) {
      this.formBCTKCC.markAllAsTouched();
      this.toastr.warning("Thông báo", "Kiểm tra thông tin đầu vào");
      return;
    }

    if (this.userId) {
      let data = {
        password: this.formBCTKCC.get("Password").value,
      };
      console.log("Updating user password with userId:", this.userId);
      this.userService.UpdatepassWord(this.userId, data).subscribe((res: any) => {
        console.log("UpdatepassWord response:", res); // Log response từ UpdatepassWord API
        if (res.statusCode == 200) {
          this.toastr.success("Thông báo", res.message);
          window.history.back();
        }
      });
    }

    if (this.customerId) {
      console.log("Updating customer password with customerId:", this.customerId);
      let data = {
        password: this.formBCTKCC.get("Password").value,
      };
      this.userService.UpdatepassWordCustomer(this.customerId, data).subscribe((res: any) => {
        console.log("UpdatepassWordCustomer response:", res); // Log response từ UpdatepassWordCustomer API
        if (res.statusCode == 200) {
          this.toastr.success("Thông báo", res.message);
          window.history.back();
        }
      });
    }
  }

  goBack(): void {
    if (this.canGoBack) {
      this.location.back();
    }
  }
}
