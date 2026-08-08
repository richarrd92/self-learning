import { Routes } from '@angular/router';

/**
 * Application route definitions.
 * 
 * - '' → Lazy-loads the Homepage component.
 * - 'dashboard' → Lazy-loads the Dashboard component.
 * - '**' → Wildcard/fallback route that redirects to the homepage.
 * 
 * Notes:
 *  - Uses lazy loading via `loadComponent` for better performance.
 *  - Wildcard route ensures undefined paths redirect to a valid page.
 */
export const routes: Routes = [
  {
    path: '',
    loadComponent: () => import('./pages/homepage/homepage').then((m) => m.Homepage),
  },
  {
    path: 'dashboard',
    loadComponent: () => import('./pages/dashboard/dashboard').then((m) => m.Dashboard),
  },
  {
    path: 'profile',
    loadComponent: () => import('./pages/profile/profile').then((m) => m.Profile),
  },
  {
    path: '**',
    redirectTo: '', // fallback route
  },
];
