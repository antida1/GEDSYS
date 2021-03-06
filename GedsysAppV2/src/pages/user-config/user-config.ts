import {Component} from '@angular/core';
import {Events, IonicPage, NavController, NavParams} from 'ionic-angular';
import {GedsysApiService} from "../../shared/gedsys-api.service";
import {DataProvider} from "../../providers/data/data";

@IonicPage()
@Component({
    selector: 'page-user-config',
    templateUrl: 'user-config.html',
})
export class UserConfigPage {
    data: any;
    user: any;

    public updateInfo(index) {
        return this.service.updateAppConfig(this.data, index);
    }

    constructor(/* tslint:disable */
                private dataProvider: DataProvider,/* tslint:enable */
                public events: Events,
                public service: GedsysApiService,
                public navCtrl: NavController,
                public navParams: NavParams) {
        this.user = navParams.data;
        this.events.subscribe('info:update', (index) => {
            index == 0 ? this.updateInfo(index) : null;
        })
    }

}
