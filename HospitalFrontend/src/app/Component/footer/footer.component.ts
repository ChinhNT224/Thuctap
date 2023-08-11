import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {
  isAccounting = false;
  opened = true;
  public opened2 = false;

  isReception = false;
  isAdmin = false;
  role: string;
  isLogin = false;
  constructor(
    private titleService: Title
  ) { }

  ngOnInit(): void {
    this.role = localStorage.getItem('role');
    this.setTitle('Hospital');
    console.log('role check toolbar', this.role);
    if (this.role === 'admin') {
     this.isAdmin = true;
     this.isLogin = true;
   }
    if (this.role === 'accounting') {
     this.isAccounting = true;
     this.isLogin = true;
   }
    if (this.role === 'reception') {
     this.isReception = true;
     this.isLogin = true;
     console.log('is user ', this.isReception);
   }
  }
  public setTitle( dashboard: string) {
    this.titleService.setTitle( dashboard );
    }
}
