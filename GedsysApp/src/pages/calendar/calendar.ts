import {Component, ViewChild} from '@angular/core';
import {Content, IonicPage, NavController, NavParams} from 'ionic-angular';
import {NotificationDetailPage} from "../notification-detail/notification-detail";
import {DataProvider} from "../../providers/data/data";
import {CalendarMonthViewDay} from 'angular-calendar';
import {setHours, setMinutes} from 'date-fns';

@IonicPage()
@Component({
    selector: 'page-calendar',
    templateUrl: 'calendar.html',
})
export class CalendarPage {
    hiddenHeader: any = false;
    @ViewChild(Content) content: Content;
    activeDayIsOpen: boolean = false;

    contentScroll(event) {
        event.directionY == 'down' ? this.hiddenHeader = true : this.hiddenHeader = false;
        event.scrollTop <= 40 ? this.hiddenHeader = false : null;
        return true;
    }

    loadNotificationDetail(notification) {
        return this.navCtrl.push(NotificationDetailPage, notification.event.meta);
    }

    changeDay(event) {
        this.date = new Date(event.day.date);
        if (this.selectedDay) {
            delete this.selectedDay.cssClass;
        }
        if(this.selectedDay == event.day){
            this.selectedDay = null;
            return this.activeDayIsOpen = false;
        }
        event.day.cssClass = 'cal-day-selected';
        this.selectedDay = event.day;
        this.activeDayIsOpen = true;
        return;
    }

    /*viewOnDay() {
        let offset = document.getElementById('divider').offsetTop;
        return this.content.scrollTo(0, offset, 1000);
    }*/

    beforeMonthViewRender({body}: { body: CalendarMonthViewDay[] }): void {
        body.forEach(day => {
            if (
                this.selectedDay &&
                day.date.getTime() === this.selectedDay.date.getTime()
            ) {
                day.cssClass = 'cal-day-selected';
                this.selectedDay = day;
            }
        });
    }

    selectedDay: any = {
        date: new Date()
    };
    date = new Date();
    events: any = [
        {
            end: setHours(new Date(),1),
            title: 'Event example 1',
            color: {
                primary: 'lightsalmon',
                secondary: 'black'
            },
            meta: {
                archived: false,
                date: {max: Date.now()},
                title: "Notification 3",
            }
        }
    ];

    constructor(private dataProvider: DataProvider,
                public navCtrl: NavController,
                public navParams: NavParams) {
        this.dataProvider.notifications.map(notification => {
            this.events.push({
                start: setHours(new Date(notification.date.max),1),
                title: notification.title,
                color: {
                    primary: 'lightsalmon',
                    secondary: 'black'
                },
                meta: notification
            });
        });
    }

}
