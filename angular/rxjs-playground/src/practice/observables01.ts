import { Observable } from 'rxjs';
import type { Observer } from 'rxjs'; // must be loaded as type

// type Observer<T> = {
//   next: (value: T) => void;
//   error: (error: any) => void;
//   complete: () => void;
// }

// Think of Observable → Observer → subscribe() like a YouTube channel and a viewer.

// Observable = the thing producing/sending data
const observable$ = new Observable<number>((subscriber) => {
  console.log('observable executed');
  subscriber.next(1);
  subscriber.next(2);
  subscriber.next(3);
});

// Observer = the thing that says what to do when data arrives
const observer: Observer<number> = {
  next: (value: number) => console.log(value),
  error: (error: any) => console.error(error),
  complete: () => console.log('complete'),
};

// subscribe() = the act of becoming a listener/viewer
observable$.subscribe(observer);
