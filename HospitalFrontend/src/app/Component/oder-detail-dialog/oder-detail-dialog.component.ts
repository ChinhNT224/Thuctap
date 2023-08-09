import { Component, OnInit } from '@angular/core';
import {FormBuilder} from "@angular/forms";

@Component({
  selector: 'app-oder-detail-dialog',
  templateUrl: './oder-detail-dialog.component.html',
  styleUrls: ['./oder-detail-dialog.component.scss']
})
export class OderDetailDialogComponent implements OnInit {

  constructor() { }
  formChiTietDK!:FormBuilder
  ngOnInit(): void {
  }

}
