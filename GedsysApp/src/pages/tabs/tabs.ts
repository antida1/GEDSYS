import {Component} from '@angular/core';
import {Events, IonicPage, NavController, NavParams} from 'ionic-angular';
import {NotificationsPage} from "../notifications/notifications";
import {GedsysApiService} from "../../shared/gedsys-api.service";
import {DataProvider} from "../../providers/data/data";


@IonicPage()
@Component({
    selector: 'page-tabs',
    templateUrl: 'tabs.html',
})
export class TabsPage {
    notificationsView: any = NotificationsPage;

    getAmount(notifications){
        let amount = 0;
        notifications.map(notification =>{
            notification.archived ? amount:amount++;
            return notification;
        });
        return amount;
    }

    getArchived(notifications){
        let amount = 0;
        notifications.map(notification =>{
            notification.archived ? amount++:amount;
            return notification;
        });
        return amount;
    }

    constructor(private dataProvider: DataProvider,
                public events: Events,
                private service: GedsysApiService,
                public navCtrl: NavController,
                public navParams: NavParams) {
    }

    ionViewDidEnter() {

    }

}
