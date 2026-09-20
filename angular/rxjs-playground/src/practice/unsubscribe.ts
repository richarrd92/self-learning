import { Observable } from 'rxjs';

// simulate api calls
const observable$ = new Observable<string>((subscriber) => {
  setTimeout(() => {
    subscriber.next('first api call: takes 4 seconds');
  }, 4000);
  setTimeout(() => {
    subscriber.next('second api call: takes 2 seconds');
  }, 2000);
  setTimeout(() => {
    subscriber.next('third api call: takes 10 seconds');
  }, 10000);
  setTimeout(() => {
    subscriber.next('fourth api call: takes 1 seconds');
  }, 1000);
  subscriber.next('fith api call: takes 0 seconds');
});



const subscription = observable$.subscribe({
  next: (value) => console.log(value),
  complete: () => console.log('complete'),
});

// should not get third api
setTimeout(() => {
  subscription.unsubscribe();
}, 5000);

setTimeout(() => {
  console.log('was subscription unsubscribed?', subscription.closed);
}, 15000);
