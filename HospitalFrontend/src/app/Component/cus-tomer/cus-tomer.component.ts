import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {PeriodicElement} from '../admin-user/admin-user.component';
import {DilalogUnlockComponent} from '../dilalog-unlock/dilalog-unlock.component';
import {MatDialog} from '@angular/material/dialog';
import {ToastrService} from 'ngx-toastr';
import {OrderDialogComponent} from '../order-dialog/order-dialog.component';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../../Service/user.service';
import {DialalogDeleteComponent} from '../dialalog-delete/dialalog-delete.component';

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
    'position',
    'name',
    'ngaytao',
    'email',
    'symbol',
    'thaoTac',
  ];
  name: string;
  dataSource: any = new MatTableDataSource<PeriodicElement>();
  id: string;
  id_order: string;

  constructor(
    private dialog: MatDialog,
    private toastr: ToastrService,
    private user: UserService,
  ) {
  }

  ngOnInit(): void {
    this.id = localStorage.getItem('id');
    this.doSearh();
  }

  doSearh() {
    this.user.getDanhSachOrder(this.id).subscribe(res => {
      this.dataSource = res.obj
      this.totalItems = this.dataSource.length
      this.paginateData();
    })
  }

  doAdd() {
    const dialogRef = this.dialog.open(OrderDialogComponent, {
      width: '50%',
      height: 'auto',
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.toastr.success('Thêm mới thành công', 'thông báo')
        this.doSearh();
      }
    });
  }

  doEit(item: any) {
    const dialogRef = this.dialog.open(OrderDialogComponent, {
      width: '50%',
      height: 'auto',
      data: item.order_id,
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.toastr.success('Thông báo', 'Cập nhật Lịch khám thành công')
        this.doSearh();
      }
    });
  }

  doDelete(item: any) {
    console.log()
    const dialogRef = this.dialog.open(DialalogDeleteComponent, {
      width: 'auto',
      height: 'auto',
      disableClose: true,
    });
    dialogRef.afterClosed().subscribe((result) => {
      this.user.Delete(this.id, item.order_id).subscribe(res => {
        if (res) {
          this.toastr.success('Xóa thành công', 'Thông báo')
        }
        this.doSearh();
      })
    });
  }

  nameEventHander($event: any) {
    this.opened2 = $event;
  }

  paginateData() {
    const start = this.page * this.pageSize;
    const end = start + this.pageSize;
    this.dataSource = this.dataSource.slice(start, end);
  }

  onChangePage(event: any) {
    this.pageSize = event.pageSize;
    this.page = event.pageIndex;
    this.doSearh();
  }
}
