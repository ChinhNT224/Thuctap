import { Component, OnInit } from '@angular/core';
import { WishlistService } from 'src/app/Service/wishlist.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

import { CartService } from 'src/app/Service/cart.service';

@Component({
  selector: 'app-wish',
  templateUrl: './wish.component.html',
  styleUrls: ['./wish.component.scss']
})
export class WishComponent implements OnInit {

  constructor( private cartService: CartService, private wishlistService: WishlistService , private route: Router,
               private matSnackBar: ToastrService) { }

  // tslint:disable-next-line: variable-name
  book_id: number;
  error: any;
  books = [];
  WishListdetails = new Array<any>();

  countbook: number;

  bookcount: number;
  no: number;
  ngOnInit(): void {
    this.booksFromWishList();
    this.BookCount();
  }

  booksFromWishList() {
    this.wishlistService.getWishllistBooks().subscribe((Response) => {
      console.log('no of books in array ' + Response.obj.length);
      this.countbook = Response.obj.length;
      console.log('wishlist books' , Response.obj);
      console.log('---response', Response);
      this.books = Response.obj;

      // tslint:disable-next-line: prefer-for-of
      for (let i = 0; i < Response.obj.length; i++) {

      console.log('bookName : ' +  Response.obj[0].booksList[0].bookName);
      console.log('bookDetails : ' +  Response.obj[0].booksList[0].bookDetails);
      console.log('authorName : ' +  Response.obj[0].booksList[0].authorName);
      console.log('noOfBooks : ' +  Response.obj[0].booksList[0].noOfBooks);
      console.log('image : ' +  Response.obj[0].booksList[0].image);
      console.log('price : ' +  Response.obj[0].booksList[0].price);
      console.log('bookId : ' +  Response.obj[0].booksList[0].bookId);
      console.log('pricess : ' +  Response.obj[0].booksList[0].priceSale);
      console.log('books are ' + this.books);

      const p = {bookName: Response.obj[i].booksList[0].bookName , bookDetails: Response.obj[i].bookDetails,
         authorName: Response.obj[i].booksList[0].authorName,
        noOfBooks: Response.obj[i].booksList[0].noOfBooks,
        image: Response.obj[i].booksList[0].image,  price: Response.obj[i].booksList[0].price ,
        bookId: Response.obj[i].booksList[0].bookId,
        priceSale:Response.obj[0].booksList[0].priceSale
      };

      this.WishListdetails.push(p);
      console.log('after push ', this.WishListdetails);
    }

     });

  }
  BookCount() {
    this.wishlistService.getWishlistCount().subscribe(
      (Response: any) => {
        console.log('book count = ' + Response.obj);
        this.bookcount = Response.obj;
        this.matSnackBar.success(Response.message, 'undo');
      },
      (error: any) => {
        console.error(error);
        console.log(error.error.message);
        this.matSnackBar.error(error.error.message, 'undo');
      }
    );
  }

  remoiveFromWish(BookId: any) {
    console.log('removeing bookId ' + BookId);

    this.wishlistService.removeFromWishList(BookId).subscribe(
      (response: any) => {

        this.matSnackBar.success('Book removed from wish list', 'success');
        window.location.reload();
        sessionStorage.removeItem(BookId);
        },
      (error: any) => {
        this.matSnackBar.error(error.error.message, 'failed');
      }
    );
  }

  addToWish(orderId: any) {
    console.log('removeing bookId ' + orderId);

    this.wishlistService.addToWishlist(orderId).subscribe(
      (response: any) => {
        this.matSnackBar.success('Book removed from wish list', 'success');

        },
      (error: any) => {
        this.matSnackBar.error(error.error.message, 'failed');
      }
    );
  }


  addtobag( bookId: any) {
    if (localStorage.getItem('token') === null) {
      this.matSnackBar.warning('Please Login first', 'ok');
      sessionStorage.setItem(bookId, bookId);
      this.route.navigateByUrl('login');
    }

    sessionStorage.setItem(bookId, bookId);

    this.cartService.addToCart(bookId).subscribe(
      data => this.handleResponse(data),
      error => this.handleError(error)
    );
  }
  handleResponse(data: any) {
    console.log(data);
    window.location.reload();
    this.matSnackBar.success('Book added successfully Into Cart' , 'ok');
}

handleError(error: any) {
  this.error = error.error.message;
  console.log(error);
  window.location.reload();
  this.matSnackBar.error(this.error);
}
}
