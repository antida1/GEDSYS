import {Component} from '@angular/core';
import {Events, IonicPage, NavController, NavParams} from 'ionic-angular';
import {CalendarComponentOptions} from "ion2-calendar";
import * as moment from 'moment';
import {GedsysApiService} from "../../shared/gedsys-api.service";
import * as $ from 'jquery';
import {NotificationDetailPage} from "../notification-detail/notification-detail";
import {DataProvider} from "../../providers/data/data";

/**
 * Generated class for the CalendarPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
    selector: 'page-calendar',
    templateUrl: 'calendar.html',
})
export class CalendarPage {
    hiddenHeader: any = false;

    contentScroll(event) {
        event.directionY == 'down' ? this.hiddenHeader = true : this.hiddenHeader = false;
        event.scrollTop == 0 ? this.hiddenHeader = false : null;
        return true;
    }

    date: any = [];
    type: 'string';
    optionsRange: CalendarComponentOptions = {
        from: 1,
        pickMode: 'multi',
        weekStart: 1,
        color: 'primary',
        showToggleButtons: true,
    };
    notifications: any = [];
    currentDay: any;
    currentMonth: any;
    currentYear: any;
    currentDate: any;

    onChange($event) {
        this.currentMonth = moment($event.newMonth).format('MM');
        this.currentYear = moment($event.newMonth).format('YYYY');
        this.currentDate = moment($event.newMonth).format('DD');
    }

    loadNotificationDetail(notification) {
        return this.navCtrl.push(NotificationDetailPage, notification);
    }

    updateCalendar(user) {
        this.date = [];
        this.notifications = this.dataProvider.notifications;
        this.notifications.map(notification => {
            let tempDate = moment(moment(notification.date.max).format('DD/MM/YYYY'), 'DD/MM/YYYY').unix() * (10 ** 3);
            if (this.date.indexOf(tempDate) < 0) {
                this.date.push(tempDate);
            }
            return notification;
        });
    }

    constructor(private dataProvider: DataProvider,
                public events: Events,
                private service: GedsysApiService,
                public navCtrl: NavController,
                public navParams: NavParams) {
        this.updateCalendar(dataProvider.profile);
        this.currentDay = moment().format('MMMM Do');
        this.currentMonth = moment().format('MM');
        this.currentYear = moment().format('YYYY');
        this.currentDate = moment().format('DD');
    }

    ionViewDidLoad() {
    }

}
