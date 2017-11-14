import {Component} from '@angular/core';
import {IonicPage, NavController, NavParams} from 'ionic-angular';


@IonicPage()
@Component({
    selector: 'page-notification-detail',
    templateUrl: 'notification-detail.html',
})
export class NotificationDetailPage {
    notification: any;

    checkColor(notification) {
        let now = new Date().getTime();
        if (notification.date.max - now < 0) {
            return 'dark';
        } else if (notification.date.max - now < 86400000) {
            return 'danger';
        }

    }

    constructor(public navCtrl: NavController, public navParams: NavParams) {
        this.notification = this.navParams.data;
    }
}
