import {Component} from '@angular/core';
import {IonicPage, NavController, NavParams} from 'ionic-angular';
import {User} from "../../models/user";
import {Profile} from "../../models/profile";
import {AuthServiceProvider} from "../../providers/auth-service/auth-service";
import {VariablesProvider} from "../../providers/variables/variables";
import {FormBuilder, FormGroup, Validators, AbstractControl} from "@angular/forms";


@IonicPage()
@Component({
    selector: 'page-register',
    templateUrl: 'register.html',
})
export class RegisterPage {
    user = {} as User;
    profile = {} as Profile;
    formGroup: FormGroup;
    username: AbstractControl;
    name: AbstractControl;
    last_name: AbstractControl;
    role: AbstractControl;
    img: AbstractControl;
    email: AbstractControl;
    password: AbstractControl;

    constructor(private formBuilder: FormBuilder,
                private variables: VariablesProvider,
                private authService: AuthServiceProvider,
                public navCtrl: NavController,
                public navParams: NavParams) {
        this.formGroup = this.formBuilder.group({
            username: ['', Validators.required],
            name: ['', Validators.required],
            last_name: ['', Validators.required],
            role: ['', Validators.required],
            img: [''],
            email: ['', Validators.required],
            password: ['', Validators.required]
        });
        this.username = this.formGroup.controls['username'];
        this.name = this.formGroup.controls['name'];
        this.last_name = this.formGroup.controls['last_name'];
        this.role = this.formGroup.controls['role'];
        this.img = this.formGroup.controls['img'];
        this.email = this.formGroup.controls['email'];
        this.password = this.formGroup.controls['password'];
    }

    register(user: User, profile: Profile) {
        !profile.img ? profile.img = null : profile.img;
        let loading = this.variables.loadingTemplate({
            content: 'Registering, please wait...'
        });
        loading.present();
        this.authService.register(user, profile).then((err) => {
            loading.dismiss();
            if (err) {
                let errToast = this.variables.toastTemplate({
                    message: err,
                    cssClass: 'toast-danger',
                    duration: 5000,
                    showCloseButton: true
                });
                console.log(err);
                return errToast.present();
            } else {
                return this.authService.auth(null);
            }
        });
    }
}
