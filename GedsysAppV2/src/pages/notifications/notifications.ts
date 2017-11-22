import {Component} from '@angular/core';
import {AlertController, IonicPage, NavController, NavParams} from 'ionic-angular';
import {NotificationDetailPage} from "../notification-detail/notification-detail";
import {DataProvider} from "../../providers/data/data";
import {VariablesProvider} from "../../providers/variables/variables";

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
        let alert = this.alertCtrl.create({
            title: 'Confirm',
            message: 'Do you want to remove this notification?',
            buttons: [
                {
                    text: 'Cancel',
                    role: 'cancel',
                    handler: () => {

                    }
                },
                {
                    text: 'Remove',
                    handler: () => {
                        const loading = this.variables.loadingTemplate(null);
                        loading.present().then(()=>{
                            let toast = this.variables.toastTemplate({
                                message: `Successfully removed ${notification.title}`,
                                cssClass: 'toast-success',
                                position: 'bottom'
                            });
                            this.dataProvider.notifications.splice(this.dataProvider.notifications.indexOf(notification), 1);
                            loading.dismiss().then(()=>{
                                toast.present().then(()=>{return;});
                            });
                        });


                    }
                }
            ]
        });
        alert.present();

    }

    archive(notification) {
        const loading = this.variables.loadingTemplate({
            content: this.index ?  `Unarchiving...` : `Archiving...`
        });
        loading.present();
        let toast = this.variables.toastTemplate({
            message : this.index ? `Successfully unarchived ${notification.title}` : `Successfully archived ${notification.title}`,
            closeButtonText: 'Ok',
            cssClass: 'toast-success',
            position: 'bottom'
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

    constructor(private alertCtrl: AlertController,
                private dataProvider: DataProvider,
                public variables: VariablesProvider,
                public navCtrl: NavController,
                public navParams: NavParams) {
        this.index = (<any> this.navCtrl).index;
        this.index ? this.filterBool.archived = true : this.filterBool.archived = false;
    }
}
