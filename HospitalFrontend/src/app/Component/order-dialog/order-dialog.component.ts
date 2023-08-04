import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {TokenService} from '../../Service/token.service';
import {UserService} from '../../Service/user.service';

@Component({
  selector: 'app-order-dialog',
  templateUrl: './order-dialog.component.html',
  styleUrls: ['./order-dialog.component.scss']
})
export class OrderDialogComponent implements OnInit {

  constructor(
    private fromBuilder: FormBuilder,
    private user: UserService,
    private token: TokenService,
  ) { }
  formDkKham: FormGroup = this.fromBuilder.group({
    HO_TEN: [null, [Validators.required]],
    GIOI_TINH: [null, [Validators.required]],
    BHYT: [null, [Validators.required]],
    NGAY_SINH: [null, [Validators.required]],
    Email: [null, []],
    SDT: [null, []],
    NGAY_HEN: [null, []],
    GIO_HEN:[]
  });
  gioiTinh:any=[
    {name:'nam' , value:'NAM'},
    {name:'nữ',value: 'NU'}
  ]
  ngOnInit(): void {
  }
  doSave(){

  }

}
