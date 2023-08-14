import {Component, OnInit} from '@angular/core';
import {UserService} from '../../Service/user.service';
import {Excel, ExcelService} from '../../Service/excel.service';
import {Title} from "@angular/platform-browser";
import {MatDialog} from "@angular/material/dialog";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-admin-accounting',
  templateUrl: './admin-accounting.component.html',
  styleUrls: ['./admin-accounting.component.scss']
})
export class AdminAccountingComponent implements OnInit {
  totalRegistrations :number;
  isAdmin = false;
  totalConfirmations:number ;
  newRegistrationsnum:number=0;
  public opened2 = false;
  pageSize: number = 10;
  page: number = 0;
  totalItems: number = 0;
  displayedColumns: string[] = [
    'stt',
    'khachhang',
    'nguoixacnhan',
    'thoigiandki',
    'thoigianxacnhan',
    'trangthai',
  ];
  registrations: any = [];

  constructor(
    private titleService: Title,
    private dialog: MatDialog,
    private toastr: ToastrService,
    private userService: UserService,
    private _exporHelperService: ExcelService,

  ) {
  }
  isLogin = false;
  role: string;

  ngOnInit(): void {
    this.doSearh();
    this.role = localStorage.getItem("role");
    this.setTitle("Hospital");
    console.log("role check toolbar", this.role);
    if (this.role === "admin") {
      this.isAdmin = true;
      this.isLogin = true;
    }
  }
  onChangePage(event: any) {
    this.pageSize = event.pageSize;
    this.page = event.pageIndex;
    this.doSearh();
  }

  public setTitle(dashboard: string) {
    this.titleService.setTitle(dashboard);
  }
  nameEventHander($event: any) {
    this.opened2 = $event;
    console.log("2", this.opened2);
  }
  doExport(){


    const header: string[] = [
      "STT",
      "Khách hàng",
      "tên người tạo",
      "tên người xác nhận",
      "ngày tạo",
      "ngày tiếp nhận",
      "Trạng thái"
    ];
    const keys: string[] = [
      "STT",
      "nguoi_benh",
      "nguoi_tao_don",
      "nguoi_xac_nhan",
      "ngay_tao",
      "ngay_tiep_nhan",
      "trangThai",
    ];
    const width: any[] = [10, 20, 30, 20, 20,20,20];
    this.registrations.forEach((obj,item)=>{
      obj.STT=item+1
    })

    let excel: Excel = {
      title: "Thống kê đơn đăng ký",
      subTitle: null,
      workSheet: null,
      keys: keys,
      widths: width,
      data: this.registrations,
      groupHeaders: null,
      groupMerge: null,
      sheetName: "Đơn đăng ký",
      headers:header
    };
    let arrayExcel = [];
    arrayExcel.push(excel);

    let timeSpan = new Date().toISOString();
    this._exporHelperService.generateExcel("Thống kê đăng kí" + timeSpan, arrayExcel);
  }

  doSearh() {
    debugger
    this.userService.getAccounting().subscribe((res: any) => {
      this.registrations = res;
      this.totalItems = res.length
      var tmp=0;
      for (let i=0 ;i< this.registrations.length;i++){
        if(this.registrations[i].trangThai=='confirmed'){
          tmp++;
        }
      }
      this.totalConfirmations=tmp;
      this.paginateData()
    })
  }

  viewTrangThai(row: any) {
    switch (row['trangThai']) {
      case 'Từ chối':
        return 'Từ chối ';
      case 'Đã xác nhận':
        return 'Đã xác nhận';
      default:
        return null;
    }
  }

  paginateData() {
    const start = this.page * this.pageSize;
    const end = start + this.pageSize;
    this.registrations = this.registrations.slice(start, end);
  }
}
