import { ajax } from 'rxjs/ajax';
import { forkJoin } from 'rxjs';

// simulate multiple API calls using forkJoin using random name api endpoints
// const api1$ = ajax('https://randomuser.me/api/?results=1');
// const api2$ = ajax('https://randomuser.me/api/?results=1');
// const api3$ = ajax('https://randomuser.me/api/?results=1');

forkJoin({
  api1: ajax('https://randomuser.me/api/?results=1'),
  api2: ajax('https://randomuser.me/api/?results=1'),
  api3: ajax('https://randomuser.me/api/?results=1')
}).subscribe((response) => {
  console.log(response);
});
