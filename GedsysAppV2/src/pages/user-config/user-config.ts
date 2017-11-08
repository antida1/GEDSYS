import {Component} from '@angular/core';
import {Events, IonicPage, NavController, NavParams} from 'ionic-angular';
import {GedsysApiService} from "../../shared/gedsys-api.service";
import {DataProvider} from "../../providers/data/data";

/**
 * Generated class for the UserConfigPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

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

    constructor(private dataProvider: DataProvider, public events: Events, public service: GedsysApiService, public navCtrl: NavController, public navParams: NavParams) {
        this.user = navParams.data;
        this.data = this.dataProvider.user_config;
        this.events.subscribe('info:update', (index) => {
            index == 0 ? this.updateInfo(index) : null;
        })
    }

}
