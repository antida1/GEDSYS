import {Component} from '@angular/core';
import {Events, IonicPage, NavController, NavParams} from 'ionic-angular';
import {UserConfigPage} from "../user-config/user-config";
import {AppConfigPage} from "../app-config/app-config";

/**
 * Generated class for the ConfigPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
    selector: 'page-config',
    templateUrl: 'config.html',
})
export class ConfigPage {
    page1: any;
    page2: any;
    data: any;
    index: any = 0;
    updateInfo(){
        return this.events.publish('info:update',this.index);
    }
    listenTabs(event){
        return this.index = event.index;
    }
    constructor(public events: Events, public navCtrl: NavController, public navParams: NavParams) {
        this.page1 = UserConfigPage;
        this.page2 = AppConfigPage;
        this.data = navParams.data;
    }

}
