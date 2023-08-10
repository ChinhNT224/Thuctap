import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { TokenService } from 'src/app/Service/token.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss']
})
export class ToolbarComponent implements OnInit {
  constructor( private token: TokenService,
               private route: Router,
               private toastr: ToastrService
    ) { }

  @Output() toggleEvent = new EventEmitter<boolean>();


  opened = false;

  name: any;
  id: any;
  isUser = false;
  isSeller = false;
  isAdmin = false;
  userId:any;
  role: string;
  length: any;
  bookName: string;
  totalItem;
  isbudget = false;
  isLogin = false;
 @Input() output: any;
 @Input() function: any;


  wishlistLength: number;

  ontoggel(input: any) {
    console.log('input' + input);
    this.toggleEvent.emit(input);
    this.opened = !this.opened;
  }

  ngOnInit() {
    this.name = localStorage.getItem('Name');
    this.userId=localStorage.getItem('id');
    console.log('idUser',this.userId)
    this.role = localStorage.getItem('role');
    console.log('role check toolbar', this.role);
    if (this.role === 'admin') {
     this.isAdmin = true;
     this.isLogin = true;
   }
    if (this.role === 'accounting') {
     this.isSeller = true;
     this.isLogin = true;
   }
    if (this.role === 'reception') {
     this.isUser = true;
     this.isLogin = true;
     console.log('is user ', this.isUser);
   }
    else {
      this.isLogin = true;
    }
  }
  logout(event: MouseEvent) {
    console.log('loggout function called');
    event.preventDefault();
    this.token.remove();
    this.token.logedIn(false);
    this.route.navigateByUrl('/login');
  }
  logoutComponent(event: MouseEvent) {
    console.log('loggout function called');
    event.preventDefault();
    this.token.remove();
    this.token.logedIn(false);
    this.route.navigateByUrl('/loginCustomer');
  }
  getUpdatedNotes(event) {
  this.ngOnInit();
  }
  detailUser(){
    console.log('Redirected to page no ' + this.userId);
    this.route.navigateByUrl('userid/' + this.userId);
  }




}
