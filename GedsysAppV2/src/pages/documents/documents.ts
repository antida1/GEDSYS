import {Component} from '@angular/core';
import {IonicPage, NavController, NavParams} from 'ionic-angular';
import {DocumentDetailPage} from "../document-detail/document-detail";

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
    refresh(comp) {
        setTimeout(() => comp.complete(), 1000);
    }

    pdfSrc: any = [
        'https://alejandrochvs.github.io/GEDSYS/docs/Doc1.pdf',
        'https://alejandrochvs.github.io/GEDSYS/docs/Doc2.pdf',
        'https://alejandrochvs.github.io/GEDSYS/docs/Doc3.pdf',
        'https://alejandrochvs.github.io/GEDSYS/docs/Doc4.pdf',
        'https://alejandrochvs.github.io/GEDSYS/docs/Doc5.pdf',
        'https://alejandrochvs.github.io/GEDSYS/docs/Doc6.pdf'
    ];
    viewDoc(url){
        this.navCtrl.push(DocumentDetailPage,{url : url});
    }
    constructor(public navCtrl: NavController, public navParams: NavParams) {
    }

    ionViewDidLoad() {
    }

}
