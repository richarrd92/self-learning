import { HttpRequest, HttpHandlerFn, HttpEvent, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';
import { AuthService } from './auth';

/**
 * HTTP interceptor that attaches the user's authentication token (Bearer token)
 * to all outgoing HTTP requests and handles authentication-related errors globally.
 *
 * Features:
 *  - Adds 'Authorization: Bearer <token>' header if a token exists in AuthService.
 *  - Catches HTTP 401 (Unauthorized) and 403 (Forbidden) responses.
 *      - Logs the user out via AuthService.
 *      - Redirects the user to the home or login page.
 *  - Logs other HTTP errors and rethrows them for further handling.
 *
 * @param req - The outgoing HttpRequest being intercepted.
 * @param next - The HttpHandler function to forward the request to.
 * @returns Observable<HttpEvent<any>> - The stream of HTTP events with token attached and error handling.
 */
export const authInterceptor: HttpInterceptorFn = (
  req: HttpRequest<any>,
  next: HttpHandlerFn
): Observable<HttpEvent<any>> => {
  const router = inject(Router);
  const authService = inject(AuthService);

  const token = authService.getToken(); // get token

  // Clone request and add token
  const cloned = token
    ? req.clone({ headers: req.headers.set('Authorization', `Bearer ${token}`) })
    : req;

  return next(cloned).pipe(
    catchError((err) => {
      if (err.status === 401 || err.status === 403) {
        console.warn('Auth error! Redirecting to login.');
        authService.setLoggedOut();
        router.navigate(['/']); // redirect
      } else {
        console.warn('HTTP request failed.');
      }

      // Rethrow error
      return throwError(() => err);
    })
  );
};
