import {Component} from '@angular/core';
import {IonicPage, NavController, NavParams} from 'ionic-angular';
import {CalendarComponentOptions} from "ion2-calendar";

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
    date: any = [];
    type: 'string';
    optionsRange: CalendarComponentOptions = {
        pickMode: 'multi',
        weekStart : 1
    };

    onChange($event) {
        console.log($event);
    }

    constructor(public navCtrl: NavController, public navParams: NavParams) {
    }

    ionViewDidLoad() {
    }

}
