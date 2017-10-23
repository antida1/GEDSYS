import {Component} from '@angular/core';
import {Events, IonicPage, NavController, NavParams, ToastController} from 'ionic-angular';

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
    login() {
        return this.events.publish('user:login');
    }

    constructor(public toastCtrl: ToastController, public events: Events, public navCtrl: NavController, public navParams: NavParams) {
        this.events.subscribe('user:max',()=>{
            let toast = this.toastCtrl.create({
                message: 'Succesfully archived!',
                duration: 1500,
                position: 'top',
                showCloseButton: true,
                dismissOnPageChange: true,
                cssClass : 'warning'
            });
            toast.present();
        })
    }

    ionViewDidLoad() {
    }

}
