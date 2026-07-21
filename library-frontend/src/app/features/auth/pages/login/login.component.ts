import { Component } from "@angular/core";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from '@angular/material/input';


@Component({
    selector: 'app-login',
    templateUrl: `./login.component.html`,
    styleUrl: `./login.component.css`,
    imports: [MatFormFieldModule, MatInputModule],
})
export class LoginComponent {

}
