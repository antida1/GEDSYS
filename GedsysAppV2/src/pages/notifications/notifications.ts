import {Component} from '@angular/core';
import {IonicPage, LoadingController, NavController, NavParams, ToastController} from 'ionic-angular';
import {NotificationDetailPage} from "../notification-detail/notification-detail";
import {DataProvider} from "../../providers/data/data";

@IonicPage()
@Component({
    selector: 'page-notifications',
    templateUrl: 'notifications.html',
})
export class NotificationsPage {
    index: any;

    loadNotificationDetail(notification) {
        this.navCtrl.push(NotificationDetailPage, notification);
    }

    checkColor(notification) {
        let now = new Date().getTime();
        if (notification.date.max - now < 0) {
            return 'dark';
        } else if (notification.date.max - now < 86400000) {
            return 'danger';
        }

    }

    remove(notification) {
        const loading = this.loadingCtrl.create({
            content: 'Deleting, please wait....'
        });
        loading.present();
        let toast = this.toastCtrl.create({
            message: 'Succesfully deleted!',
            duration: 1500,
            position: 'top',
            showCloseButton: true,
            dismissOnPageChange: true,
            cssClass: 'toast-success'
        });
        this.dataProvider.notifications.splice(this.dataProvider.notifications.indexOf(notification), 1);
        loading.dismiss();
        toast.present();
    }

    archive(notification) {
        const loading = this.loadingCtrl.create({
            content: 'Archiving, please wait....'
        });
        loading.present();
        let toast = this.toastCtrl.create({
            message: this.index ? 'Succesfully unarchived!' : 'Succesfully archived',
            duration: 1500,
            position: 'top',
            showCloseButton: true,
            closeButtonText: 'OK',
            dismissOnPageChange: true,
            cssClass: 'success'
        });
        this.index ? notification.archived = false : notification.archived = true;
        loading.dismiss();
        toast.present();
    }

    filterStr: any = {title: ''};
    filterBool: any = {archived : false};


    refresh(comp) {
        setTimeout(() => comp.complete(), 1000);
    }

    hiddenHeader: any = false;

    contentScroll(event) {
        event.directionY == 'down' ? this.hiddenHeader = true : this.hiddenHeader = false;
        event.scrollTop < 40 ? this.hiddenHeader = false : null;
        return true;
    }

    getAmount(notifications, archived) {
        let amount = 0;
        if (notifications.length == 0 || notifications.length == undefined) {
            return 0;
        } else {
            if (archived) {
                notifications.map(notification => {
                    notification.archived ? amount++ : amount;
                    return notification;
                });
            } else {
                notifications.map(notification => {
                    notification.archived ? amount : amount++;
                    return notification;
                });
            }
            return amount;
        }

    }

    constructor(private dataProvider: DataProvider,
                public loadingCtrl: LoadingController,
                public toastCtrl: ToastController,
                public navCtrl: NavController,
                public navParams: NavParams) {
        this.index = (<any> this.navCtrl).index;
        this.index ? this.filterBool.archived = true : this.filterBool.archived = false;
    }
}
