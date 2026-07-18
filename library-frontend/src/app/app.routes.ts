import { Routes } from '@angular/router';
import { RegisterComponent } from './features/auth/pages/register/register.component';
import { LoginComponent } from './features/auth/pages/login/login.component';

export const routes: Routes = [
    {
        path: 'register',
        title: 'Rejestracja',
        component: RegisterComponent,
    },
    {
        path: 'login',
        title: 'Logowanie',
        component: LoginComponent,
    }
];
