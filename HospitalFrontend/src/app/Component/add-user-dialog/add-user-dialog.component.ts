import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../Service/user.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {TokenService} from "../../Service/token.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-add-user-dialog',
  templateUrl: './add-user-dialog.component.html',
  styleUrls: ['./add-user-dialog.component.scss']
})
export class AddUserDialogComponent implements OnInit {

  constructor(
    private fromBuilder: FormBuilder,
    private user: UserService,
    private dialogRef: MatDialogRef<AddUserDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private token: TokenService,
    private toastr: ToastrService,
  ) {
  }

  formThemNV: FormGroup = this.fromBuilder.group({
    HO_TEN: [null, [Validators.required]],
    Mat_Khau: [null, [Validators.required]],
    Email: [null, [Validators.required]],
    SDT: [null, [Validators.required]],
  });
  id: string;
  id_custemer:string
  ngOnInit(): void {
    this.id = localStorage.getItem('id')
    this.id_custemer=this.data
    console.log(this.id_custemer)
    this.doDetail(this.id_custemer)
  }
  doDetail(id:string){
    this.user.Detail(id).subscribe((response:any)=>{
      if(response){
        this.formThemNV.controls['HO_TEN'].setValue(response.obj.ho_ten_nguoi_benh)
        this.formThemNV.controls['Mat_Khau'].setValue(response.obj.ngay_sinh)
        this.formThemNV.controls['Email'].setValue(response.obj.email)
        this.formThemNV.controls['SDT'].setValue(response.obj.dien_thoai)
      }
    })
  }
  doSave(type:number) {
    debugger
    if (this.formThemNV.invalid) {
      this.formThemNV.markAllAsTouched();
      this.toastr.warning(
        'Thông báo',
        'Kiểm tra thông tin đầu vào'
      );
      return;
    }
    let data = {
      ho_ten_nguoi_benh: this.formThemNV.controls['HO_TEN'].value,
      password: this.formThemNV.controls['Mat_Khau'].value,
      dien_thoai: this.formThemNV.controls['SDT'].value,
      email: this.formThemNV.controls['Email'].value,
    }
    if(type == 1){
      this.user.AddOrder(data, this.id).subscribe(res => {
        if (res) {
          this.dialogRef.close(data);
        } else {
          this.toastr.error('Thêm mới thất bại')
        }
      })
    }
    else {
      this.user.UpdateOrder(data, this.id,this.id_custemer).subscribe(res => {
        if (res) {
          this.dialogRef.close(data);
        } else {
          this.toastr.error('Cập nhật thất bại')
        }
      })
    }

  }

}
