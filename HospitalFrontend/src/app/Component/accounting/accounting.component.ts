import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-accounting',
  templateUrl: './accounting.component.html',
  styleUrls: ['./accounting.component.scss']
})
export class AccountingComponent implements OnInit {
  totalRegistrations = 100;
  totalConfirmations = 80;
  newRegistrations = 20;

  registrations = [
    {
      customer: 'Khách hàng A',
      confirmationPerson: 'Người A',
      registrationTime: '2023-08-01',
      confirmationTime: '2023-08-05'
    },
    // Thêm các dữ liệu đơn đăng ký khác tại đây
  ];

  performAction(registration: any) {
    // Hàm thực hiện thao tác khi nhấn nút Thực hiện
    console.log('Thực hiện thao tác cho đơn đăng ký:', registration);
  }

  ngOnInit(): void {
  }
}
