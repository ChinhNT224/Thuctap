

import { Router, ActivatedRoute,ParamMap} from '@angular/router';

import { Component, OnInit ,ViewChild  } from '@angular/core';
import { MatSidenavModule } from '@angular/material/sidenav';


@Component({
  selector: 'app-sidenavbar',
  templateUrl: './sidenavbar.component.html',
  styleUrls: ['./sidenavbar.component.scss']
})

export class SidenavbarComponent implements OnInit {
  @ViewChild('sidenav', { static: true }) public sidenav: MatSidenavModule;
  isReception = false;
  isAccounting = false;
  isAdmin=false;
  role:string;
  isLogin = false;
  name: any;
  email:any;
  length: any;
  wishlistLength: number;
  constructor(private router:Router,private route:ActivatedRoute,) { }




  ngOnInit() {
    this.name = localStorage.getItem('Name');
    this.role = localStorage.getItem('role');
    this.email=localStorage.getItem('email');
    console.log("email",this.email)
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
   this.role= localStorage.getItem('role');
   console.log('role check sidenav',this.role);
   if (this.role === 'admin')
   {
     this.isAdmin=true;
   }
   if (this.role === 'accounting')
   {
     this.isAccounting=true;
   }
    if (this.role === 'reception')
    {
      this.isAccounting=true;
    }
  }
  User(){
    this.router.navigate(['user']);
  }
  Home(){
    this.router.navigate(['admin']);
  }

  orderStatus() {

  }
}

