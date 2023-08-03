import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {PeriodicElement} from "../admin-user/admin-user.component";
import {DilalogUnlockComponent} from '../dilalog-unlock/dilalog-unlock.component';
import {MatDialog} from '@angular/material/dialog';
import {ToastrService} from 'ngx-toastr';
import {OrderDialogComponent} from '../order-dialog/order-dialog.component';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-cus-tomer',
  templateUrl: './cus-tomer.component.html',
  styleUrls: ['./cus-tomer.component.scss']
})
export class CusTomerComponent implements OnInit {
  pageSize: number = 10;
  page: number = 0;
  public opened2 = false;
  totalItems: number = 0;
  displayedColumns: string[] = [
    "position",
    "name",
    "ngaytao",
    "email",
    "weight",
    "symbol",
    "thaoTac",
  ];
  name: string;
  dataSource: any = new MatTableDataSource<PeriodicElement>();

  constructor(
    private dialog: MatDialog,
    private toastr: ToastrService,
  ) {
  }

  ngOnInit(): void {
  }

  doSearh() {

  }
  doAdd(){
    const dialogRef = this.dialog.open(OrderDialogComponent, {
      width: '50%',
      height: 'auto',
      // disableClose: true,
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        // this.userService.ActiveUser(id).subscribe((message) => {
        //   if (message.statusCode === 200) {
        //     this.toastr.success("Thông báo","Cập nhật Trạng thái thành công")
        //     this.doSearh();
        //   }
        // });
      }
    });
  }
  nameEventHander($event: any) {
    this.opened2 = $event;
  }

  onChangePage(event: any) {
    this.pageSize = event.pageSize;
    this.page = event.pageIndex;
    this.doSearh();
  }
}
