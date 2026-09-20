import { Observable } from 'rxjs';


const observable$ = new Observable<number>((subscriber) => {
  console.log('observable executed');
  subscriber.next(1);
  subscriber.next(2);
  subscriber.next(3);
});

// observable$.subscribe(); // only executes observable$

// subscribe to observable immediately
// listen to data
observable$.subscribe({
  next: (value) => console.log(value),
  error: (error) => console.error(error),
  complete: () => console.log('complete'),
});
