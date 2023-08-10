import { Component, OnInit } from '@angular/core';
import { Router , ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {

  name: string = null;
  private param: any;
  unverified = false;
  orders = false;
  review = false;
  constructor(private router: ActivatedRoute) { }

  ngOnInit(): void {
  }

}
