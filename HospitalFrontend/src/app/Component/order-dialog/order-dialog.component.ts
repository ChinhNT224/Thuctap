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
    SDT: [null, [Validators.required]],
    NGAY_HEN: [null, [Validators.required]],
    NGAY_TAO: [Validators.required],
    NGAY_TN: [Validators.required],
    GIO_HEN: [Validators.required]
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
  }

  doSave() {

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
      ngay_hen: this.formDkKham.controls['NGAY_HEN'].value,
      gio_hen: this.formDkKham.controls['GIO_HEN'].value.concat(':00'),
      ngay_tao: this.formDkKham.controls['NGAY_TAO'].value,
      ngay_tiep_nhan: this.formDkKham.controls['NGAY_TN'].value
    }
    this.user.AddOrder(data, this.id).subscribe(res => {
      if (res) {
        this.dialogRef.close(data);
      } else {
        this.toastr.error('Thêm mới thất bại')
      }
    })

  }

}
