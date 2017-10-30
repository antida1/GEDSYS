import {Component} from '@angular/core';
import {Events, IonicPage, NavController, NavParams} from 'ionic-angular';
import {NotificationsPage} from "../notifications/notifications";
import {GedsysApiService} from "../../shared/gedsys-api.service";

/**
 * Generated class for the TabsPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
    selector: 'page-tabs',
    templateUrl: 'tabs.html',
})
export class TabsPage {
    unarchived: any = NotificationsPage;
    notificationsCount: any = 0;
    archivedCount: any = 0;
    data: any = {
        user : '',
        index : 0
    };

    constructor(public events: Events, private service : GedsysApiService, public navCtrl: NavController, public navParams: NavParams){
        this.navParams.data.notifications.map(notification => {
            notification.archived ? this.archivedCount++ : this.notificationsCount++;
            return notification;
        });
        this.data.index = this.service.activeUser;
        this.data.user = this.service.users[this.data.index];
        this.events.subscribe('user:updated',(user,index)=>{
            this.data.user = user;
            this.data.index = index;
            this.notificationsCount = 0;
            this.archivedCount = 0;
            this.data.user.notifications.map(notification => {
                notification.archived ? this.archivedCount++ : this.notificationsCount++;
            })
        })
    }

    ionViewDidEnter() {

    }

}
