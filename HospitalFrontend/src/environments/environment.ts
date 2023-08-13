// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: true,

  BASE_URL: 'http://localhost:8080',
  DetailOrder: 'DetailOrder/',
  DeleteOrder: 'customer/',

  getAllorder: 'reception/AllOrder',
  getAccounting: 'accounting/confirmed-orders',
  refuseOrder:'reception/orders/',
  confirmOrder:'reception/orders',
  customerOrder: '',
  CartUrl: 'http://localhost:8080/',
  addUrl: 'customers/addcustomer',
  addOrder: 'customer/',
  updateOrder: 'customer/',
  custemerOrder: 'customer/',
  quantity: 'http://localhost:8080/',
  USER_REGISTRATION: 'registration',
  USER_LOGIN: 'login',
  USER_FORGET_PASSWORD: 'forgotpassword',
  USER_RESETPASSWORD: 'update',
  UserSearch: 'user/SearchData',
  ActiveUser: 'ActiveUser',
  DeleteUser: 'DeleteUser',
  adminUrl: 'http://localhost:8080/',
  GetUsser: 'DetailUser',
  UpdatePassword: 'updatePassword',
  Updateinfor: 'updateInfo',
  loginCustomer: 'customer/login',
  GetCustomer: 'DetailCustomer',
  UpdatePasswordCustomer: 'updatePasswordCustomer',
  UpdateinforCustomer: 'updateInfoCustomer',
};
