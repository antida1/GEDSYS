import {Component} from '@angular/core';
import {IonicPage, NavController, NavParams} from 'ionic-angular';

/**
 * Generated class for the DocumentDetailPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
    selector: 'page-document-detail',
    templateUrl: 'document-detail.html',
})
export class DocumentDetailPage {
    pdfSrc: any;
    pinch: any;
    pinchEvent(event){
        return this.pinch = event;
    }

    constructor(public navCtrl: NavController, public navParams: NavParams) {
        this.pdfSrc = navParams.data.url;
    }

    ionViewDidLoad() {

    }

}
