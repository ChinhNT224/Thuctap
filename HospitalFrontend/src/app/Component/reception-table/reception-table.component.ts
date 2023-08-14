import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {PeriodicElement} from "../admin-user/admin-user.component";
import {MatDialog} from "@angular/material/dialog";
import {ToastrService} from "ngx-toastr";
import {UserService} from "../../Service/user.service";
import {OrderDialogComponent} from '../order-dialog/order-dialog.component';
import {OderDetailDialogComponent} from "../oder-detail-dialog/oder-detail-dialog.component";


@Component({
  selector: 'app-reception-table',
  templateUrl: './reception-table.component.html',
  styleUrls: ['./reception-table.component.scss']
})
export class ReceptionTableComponent implements OnInit {
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
  id: string;
  name: string;
  dataSource: any = new MatTableDataSource<PeriodicElement>();
  trangThai: string = 'ALL'
  listTrangThai: any = [
    {name: 'Tất cả', value: 'ALL'},
    {name: 'Chờ xác nhận', value: 'CHO_XAC_NHAN'},
    {name: 'Đã xác nhận', value: 'DA_XAC_NHAN'},
  ]

  constructor(
    private dialog: MatDialog,
    private toastr: ToastrService,
    private user: UserService,
  ) {
  }

  ngOnInit(): void {
    this.doSearh();
    this.id = localStorage.getItem('id');
    this.name = localStorage.getItem('Name')
    console.log(this.name)
  }

  doSearh() {
    this.user.GetAllOrder().subscribe((res: any) => {
      this.dataSource = res.obj
      this.totalItems = this.dataSource.length
      this.paginateData();
    })
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

  doXacNhan(item: any) {
    let data = {
      userId: this.id,
      username: this.name,
      role: "reception",
      ngay_tiep_nhan: new Date().getTime(),
    };

    this.user.Confirm(item.order_id, data).subscribe(res => {
      if (res) {
        this.toastr.success('Xác nhận thành công', 'thông báo');
        this.doSearh();
      } else {
        this.toastr.error('Thất bại', 'thông báo');
      }
    });
  }




  doHuy(item: any) {
    let data = {
      userId: this.id,
      username: this.name,
      role: "reception",
      ngay_tiep_nhan: new Date().getTime(),
    }
    this.user.refuse(item.order_id, data).subscribe(res => {
      if (res) {
        this.toastr.success('Hủy thành công', 'thông báo')
        this.doSearh();
      } else {
        this.toastr.error('Hủy thất bại', 'thông báo')
      }
    })
  }

  doView(item: any) {
    let param = {
      id_order: item.order_id,
      id: item.customerId
    }
    const dialogRef = this.dialog.open(OderDetailDialogComponent, {
      width: '50%',
      height: 'auto',
      data: param
    });
    dialogRef.afterClosed().subscribe((result) => {

    });
  }
}
