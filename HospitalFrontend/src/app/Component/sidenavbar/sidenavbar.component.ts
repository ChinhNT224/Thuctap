

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
  isSeller = false;
  isUser = false;
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
    if (this.role === 'seller') {
     this.isSeller = true;
     this.isLogin = true;
   }
    if (this.role === 'user') {
     this.isUser = true;
     this.isLogin = true;
     console.log('is user ', this.isUser);
   }
   this.role= localStorage.getItem('role');
   console.log('role check sidenav',this.role);
   if (this.role === 'admin')
   {
     this.isAdmin=true;
   }
   if (this.role === 'seller')
   {
     this.isSeller=true;
   }
  }
  User(){
    this.router.navigate(['user']);
  }
  Home(){
    this.router.navigate(['chart']);
  }

  orderStatus() {

  }
}

