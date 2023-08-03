import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {PeriodicElement} from "../admin-user/admin-user.component";

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
    "email",
    "ngaytao",
    "chucnang",
    "weight",
    "symbol",
    "thaoTac",
  ];
  name: string;
  dataSource: any = new MatTableDataSource<PeriodicElement>();

  constructor() {
  }

  ngOnInit(): void {
  }

  doSearh() {

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
