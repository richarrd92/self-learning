import { Observable } from 'rxjs';

// option 1: never emits anything
const emptyObservable$ = new Observable( subscriber => {} );

emptyObservable$.subscribe();

// option 2: never emits anything
const oneValueObservable$ = new Observable<string>(subscriber => {
  subscriber.next('one value observable');
});

oneValueObservable$.subscribe(value => {
  console.log(value);
});


const buttonObject = document.querySelector('button');

const buttonObservable$ = new Observable<MouseEvent>(subscriber => {
  buttonObject?.addEventListener('click', (event) => {
    subscriber.next(event);
  });
});

// instant subscription
buttonObservable$.subscribe(event => {
  console.log('button was clicked', event.x, event.y);
});

// five second delay
setTimeout(() => {
  buttonObservable$.subscribe(event => {
    console.log('button was clicked', event.x, event.y);
  });
}, 5000);
