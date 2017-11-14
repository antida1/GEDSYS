import {Component} from '@angular/core';
import {Events, IonicPage, NavController, NavParams} from 'ionic-angular';
import {RegisterPage} from "../register/register";
import {User} from "../../models/user";
import {AuthServiceProvider} from "../../providers/auth-service/auth-service";
import {VariablesProvider} from "../../providers/variables/variables";
import {DataProvider} from "../../providers/data/data";
import {FormBuilder, FormGroup, Validators, AbstractControl} from "@angular/forms";

@IonicPage()
@Component({
    selector: 'page-login',
    templateUrl: 'login.html',
})
export class LoginPage {
    user = {} as User;
    formGroup: FormGroup;
    email: AbstractControl;
    password: AbstractControl;

    login(user: User) {
        let loading = this.variables.loadingTemplate({content: 'Logging in, please wait...'});
        loading.present();
        this.authService.login(user).then((err) => {
            loading.dismiss();
            if (err) {
                let toast = this.variables.toastTemplate({
                    message: err,
                    cssClass: 'error-toast',
                    duration: 5000,
                    showCloseButton: true
                });
                toast.present();
            } else {
                return;

            }
        });
    }

    loadRegister() {
        return this.navCtrl.push(RegisterPage);
    }

    constructor(private formBuilder: FormBuilder,
                private dataProvider: DataProvider,
                private variables: VariablesProvider,
                private authService: AuthServiceProvider,
                public events: Events,
                public navCtrl: NavController,
                public navParams: NavParams) {
        this.formGroup = formBuilder.group({
            email: ['', Validators.required],
            password: ['', Validators.required]
        });
        this.email = this.formGroup.controls['email'];
        this.password = this.formGroup.controls['password'];
    }

}
