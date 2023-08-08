import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-accounting',
  templateUrl: './accounting.component.html',
  styleUrls: ['./accounting.component.scss']
})
export class AccountingComponent implements OnInit {
  totalRegistrations = 100;
  totalConfirmations = 80;
  newRegistrations = 20;
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
  registrations = [
    {
      customer: 'Khách hàng A',
      confirmationPerson: 'Người A',
      registrationTime: '2023-08-01',
      confirmationTime: '2023-08-05'
    },
    // Thêm các dữ liệu đơn đăng ký khác tại đây
  ];
  nameEventHander($event: any) {
    this.opened2 = $event;
  }

  ngOnInit(): void {
  }

  onChangePage(event: any) {
    this.pageSize = event.pageSize;
    this.page = event.pageIndex;
    this.doSearh();
  }

  doSearh() {

  }
}
