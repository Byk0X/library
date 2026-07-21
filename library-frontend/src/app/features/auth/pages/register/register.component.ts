import { Component, inject } from '@angular/core';
import { MatFormField, MatLabel, MatInput, MatError } from "@angular/material/input";
import { MatCardModule } from '@angular/material/card';
import { FormGroup, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButton } from '@angular/material/button';
import { AuthService } from '../../auth.service';
import { RegisterRequest } from '../../models/register-request';
import { passwordMatchValidator } from '../../validators/password-match.validator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, RouterLink } from '@angular/router';


@Component({
    selector: 'app-register',
    templateUrl: `./register.component.html`,
    styleUrl: `./register.component.css`,
    imports: [MatFormField, MatLabel, MatInput, MatError, MatCardModule, MatButton, ReactiveFormsModule, RouterLink],
})
export class RegisterComponent {

    authService = inject(AuthService);
    router = inject(Router)

    private snackBar = inject(MatSnackBar);

    registerForm = new FormGroup({

        email: new FormControl('', [Validators.required, Validators.email]),
        password: new FormControl('', [Validators.required, Validators.minLength(8), Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^a-zA-Z0-9]).*$/)]),
        confirmPassword: new FormControl('', Validators.required),
        firstName: new FormControl('', Validators.required),
        lastName: new FormControl('', Validators.required)

    }, {
        validators: passwordMatchValidator
    });

    register() {

        const formValue = this.registerForm.value;

        const registerData: RegisterRequest = {
            email: formValue.email ?? '',
            password: formValue.password ?? '',
            firstName: formValue.firstName ?? '',
            lastName: formValue.lastName ?? ''

        }

        this.authService.register(registerData).subscribe({
            next: response => {
                console.log(response);

                this.snackBar.open("Pomyślnie zarejestrowano",
                    "OK",
                    {
                        duration: 3000
                    }
                );

                this.router.navigate(['/login']);

            },
            error: err => {
                console.error(err);

                this.snackBar.open("Rejestracja nieudana",
                    'OK',
                    {
                        duration: 3000,
                        panelClass: ['error-snackbar']
                    }
                );
            }
        });


    }

}