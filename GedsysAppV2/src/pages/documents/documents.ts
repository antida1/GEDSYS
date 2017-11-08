import {Component} from '@angular/core';
import {IonicPage, NavController, NavParams} from 'ionic-angular';
import {DocumentDetailPage} from "../document-detail/document-detail";
import {DataProvider} from "../../providers/data/data";

/**
 * Generated class for the DocumentsPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
    selector: 'page-documents',
    templateUrl: 'documents.html',
})
export class DocumentsPage {

    hiddenHeader: any = false;

    contentScroll(event) {
        event.directionY == 'down' ? this.hiddenHeader = true : this.hiddenHeader = false;
        event.scrollTop < 40 ? this.hiddenHeader = false : null;
        return true;
    }

    filterStr: any = {title: ''};
    filter(event) {
        return this.filterStr.title = event.target.value;
    }

    refresh(comp) {
        setTimeout(() => comp.complete(), 1000);
    }

    more(){

    }

    viewDoc(url) {
        this.navCtrl.push(DocumentDetailPage, {url: url});
    }

    constructor(private dataProvider: DataProvider, public navCtrl: NavController, public navParams: NavParams) {
    }

    ionViewDidLoad() {
    }

}
