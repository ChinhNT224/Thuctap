import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {TokenService} from '../../Service/token.service';
import {UserService} from '../../Service/user.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-order-dialog',
  templateUrl: './order-dialog.component.html',
  styleUrls: ['./order-dialog.component.scss']
})
export class OrderDialogComponent implements OnInit {

  constructor(
    private fromBuilder: FormBuilder,
    private user: UserService,
    private dialogRef: MatDialogRef<OrderDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private token: TokenService,
    private toastr: ToastrService,
  ) {
  }

  formDkKham: FormGroup = this.fromBuilder.group({
    HO_TEN: [null, [Validators.required]],
    GIOI_TINH: [null, [Validators.required]],
    NGAY_SINH: [null, [Validators.required]],
    Email: [null, [Validators.required]],
    Health_insurance: [null, [Validators.required]],
    SDT: [null, [Validators.required]],
    NGAY_HEN: [null, [Validators.required]],
    GIO_HEN: [ null,[Validators.required]]
  });
  gioiTinh: any = [
    {name: 'nam', value: 'NAM'},
    {name: 'nữ', value: 'NU'}
  ]
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
         this.formDkKham.controls['HO_TEN'].setValue(response.obj.ho_ten_nguoi_benh)
         this.formDkKham.controls['GIOI_TINH'].setValue(response.obj.gioi_tinh)
         this.formDkKham.controls['NGAY_SINH'].setValue(response.obj.ngay_sinh)
         this.formDkKham.controls['Email'].setValue(response.obj.email)
         this.formDkKham.controls['Health_insurance'].setValue(response.obj.health_insurance)
         this.formDkKham.controls['SDT'].setValue(response.obj.dien_thoai)
         this.formDkKham.controls['NGAY_HEN'].setValue(response.obj.ngay_hen)
         this.formDkKham.controls['GIO_HEN'].setValue(response.obj.gio_hen)
       }
    })
 }
  doSave(type:number) {
    debugger
    if (this.formDkKham.invalid) {
      this.formDkKham.markAllAsTouched();
      this.toastr.warning(
        'Thông báo',
        'Kiểm tra thông tin đầu vào'
      );
      return;
    }
    let data = {
      ho_ten_nguoi_benh: this.formDkKham.controls['HO_TEN'].value,
      gioi_tinh: this.formDkKham.controls['GIOI_TINH'].value,
      ngay_sinh: this.formDkKham.controls['NGAY_SINH'].value,
      dien_thoai: this.formDkKham.controls['SDT'].value,
      email: this.formDkKham.controls['Email'].value,
      health_insurance: this.formDkKham.controls['Health_insurance'].value,
      ngay_hen: this.formDkKham.controls['NGAY_HEN'].value,
      gio_hen: type==1?this.formDkKham.controls['GIO_HEN'].value.concat(':00'):this.formDkKham.controls['GIO_HEN'].value,
      trang_thai:'CHO_XAC_NHAN'
    }
    console.log("daa",JSON.stringify(data))
    if(type = 1){
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
