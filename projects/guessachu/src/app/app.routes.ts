import { Routes } from '@angular/router';
import { firstRunGuard } from './first-run.guard';
export const routes: Routes = [
    {
        path: '',
        pathMatch: 'full',
        canMatch: [firstRunGuard],
        loadComponent: () =>
            import('./components/home/home').then(component => component.Home),
    },
    {
        path: 'config',
        loadComponent: () =>
            import('./components/configuration/configuration').then(component => component.Configuration),
    },
    {
        path: 'game',
        loadComponent: () =>
            import('./components/gameview/gameview').then(component => component.Gameview),
    },
    {
        path: 'hint',
        loadComponent: () =>
            import('./components/hint/hint').then(component => component.Hint),
    },
    {
        path: 'leaderboard',
        loadComponent: () =>
            import('./components/leaderboard/leaderboard').then(component => component.Leaderboard),
    },
    {
        path: 'save-score',
        loadComponent: () =>
            import('./components/savescore/savescore').then(component => component.Savescore),
    },
    {
        path: 'try-again',
        loadComponent: () =>
            import('./components/tryagain/tryagain').then(component => component.Tryagain),
    },
    { path: '**', redirectTo: '' },
];