import {Component} from '@angular/core';
import {Events, IonicPage, NavController, NavParams, ToastController} from 'ionic-angular';
import {RegisterPage} from "../register/register";
import {User} from "../../models/user";
import {HomePage} from "../home/home";
import {AuthServiceProvider} from "../../providers/auth-service/auth-service";

/**
 * Generated class for the LoginPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
    selector: 'page-login',
    templateUrl: 'login.html',
})
export class LoginPage {
    user = {} as User;

    login(user: User){
        this.authService.login(user).then(()=>{
            return this.events.publish('root:change',0);
        })
    }


    loadRegister() {
        return this.navCtrl.push(RegisterPage);
    }

    constructor(private authService: AuthServiceProvider,
                public toastCtrl: ToastController,
                public events: Events, public navCtrl: NavController,
                public navParams: NavParams) {
        this.events.subscribe('user:max', () => {
            let toast = this.toastCtrl.create({
                message: 'Succesfully archived!',
                duration: 1500,
                position: 'top',
                showCloseButton: true,
                dismissOnPageChange: true,
                cssClass: 'warning'
            });
            toast.present();
        })
    }

}
