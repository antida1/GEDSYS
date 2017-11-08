import {Component} from '@angular/core';
import {IonicPage, NavController, NavParams} from 'ionic-angular';
import {User} from "../../models/user";
import {Profile} from "../../models/profile";
import {AuthServiceProvider} from "../../providers/auth-service/auth-service";
import {HomePage} from "../home/home";


@IonicPage()
@Component({
    selector: 'page-register',
    templateUrl: 'register.html',
})
export class RegisterPage {
    user = {} as User;
    profile = {} as Profile;

    constructor(private authService: AuthServiceProvider,
                public navCtrl: NavController,
                public navParams: NavParams) {
    }

    register(user: User, profile: Profile) {
        this.authService.register(user, profile).then(() => {
            this.navCtrl.setRoot(HomePage);
        });
    }
}
