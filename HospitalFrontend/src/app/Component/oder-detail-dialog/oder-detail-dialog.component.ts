import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {UserService} from "../../Service/user.service";

@Component({
  selector: 'app-oder-detail-dialog',
  templateUrl: './oder-detail-dialog.component.html',
  styleUrls: ['./oder-detail-dialog.component.scss']
})
export class OderDetailDialogComponent implements OnInit {

  constructor(
    private fromBuilder: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private user: UserService,
  ) { }
  formChiTietDK: FormGroup = this.fromBuilder.group({
    HO_TEN: [null, [Validators.required]],
    GIOI_TINH: [null, [Validators.required]],
    NGAY_SINH: [null, [Validators.required]],
    Email: [null, [Validators.required]],
    Health_insurance: [null, [Validators.required]],
    Dia_chi: [null, [Validators.required]],
    SDT: [null, [Validators.required]],
    NGAY_HEN: [null, [Validators.required]],
    GIO_HEN: [ null,[Validators.required]],
    TenNGuoiTao:[],
    EmailNT:[],
    SDTNT:[]
  });
  gioiTinh: any = [
    {name: 'nam', value: 'NAM'},
    {name: 'nữ', value: 'NU'}
  ]
  id: string;
  id_custemer:string;
  ngOnInit(): void {
    this.id = this.data.id
    this.id_custemer=this.data.id_order
    this.doDetail();
  }

  doDetail(){
    this.user.CustermDetail(this.id,this.id_custemer).subscribe((response:any)=>{
        if(response){
          this.formChiTietDK.controls['HO_TEN'].setValue(response.obj.ho_ten_nguoi_benh)
          this.formChiTietDK.controls['GIOI_TINH'].setValue(response.obj.gioi_tinh)
          this.formChiTietDK.controls['NGAY_SINH'].setValue(response.obj.ngay_sinh)
          this.formChiTietDK.controls['Email'].setValue(response.obj.email)
          this.formChiTietDK.controls['Dia_chi'].setValue(response.obj.dia_chi)
          this.formChiTietDK.controls['Health_insurance'].setValue(response.obj.health_insurance)
          this.formChiTietDK.controls['SDT'].setValue(response.obj.dien_thoai)
          this.formChiTietDK.controls['NGAY_HEN'].setValue(response.obj.ngay_hen)
          this.formChiTietDK.controls['GIO_HEN'].setValue(response.obj.gio_hen)
          this.formChiTietDK.controls['TenNGuoiTao'].setValue(response.obj.userCreatedBy.name)
          this.formChiTietDK.controls['EmailNT'].setValue(response.obj.userCreatedBy.email)
          this.formChiTietDK.controls['SDTNT'].setValue(response.obj.userCreatedBy.mobileNumber)
        }
    })
  }

}
