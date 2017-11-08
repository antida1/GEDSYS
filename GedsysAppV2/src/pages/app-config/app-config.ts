import {Component, Injectable} from '@angular/core';
import {Events, IonicPage, NavController, NavParams} from 'ionic-angular';
import {GedsysApiService} from "../../shared/gedsys-api.service";
import {DataProvider} from "../../providers/data/data";

/**
 * Generated class for the AppConfigPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
    selector: 'page-app-config',
    templateUrl: 'app-config.html',
})
export class AppConfigPage {
    data: any;

    public updateInfo(index) {
        return this.service.updateAppConfig(this.data, index);
    }

    constructor(private service: GedsysApiService,
                public events: Events,
                public dataProvider: DataProvider,
                public navCtrl: NavController,
                public navParams: NavParams) {
        this.data = this.dataProvider.app_config;
        this.events.subscribe('info:update', (index) => {
            index == 1 ? this.updateInfo(index) : null;
        })
    }


}
