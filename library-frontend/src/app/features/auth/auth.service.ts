import { inject, Injectable } from '@angular/core';
import { RegisterRequest } from './models/register-request';
import { LoginRequest } from './models/login-request';
import { HttpClient } from '@angular/common/http';


@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private url = 'http://localhost:8080/api/auth';

    private http = inject(HttpClient);

    register(data: RegisterRequest) {

        console.log(data);

        return this.http.post(
            `${this.url}/register`,
            data
        );
    }

    login(data: LoginRequest) {
        return this.http.post(
            `${this.url}/login`,
            data
        );
    }


}