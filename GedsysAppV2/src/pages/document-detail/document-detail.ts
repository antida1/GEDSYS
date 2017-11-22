import {Component} from '@angular/core';
import {AlertController, IonicPage, ModalController, NavController, NavParams} from 'ionic-angular';
import {VariablesProvider} from "../../providers/variables/variables";
import {DataProvider} from "../../providers/data/data";
import {DocumentReplyPage} from "../document-reply/document-reply";
import {DocumentForwardPage} from "../document-forward/document-forward";

@IonicPage()
@Component({
    selector: 'page-document-detail',
    templateUrl: 'document-detail.html',
})
export class DocumentDetailPage {
    data: any;
    docLoader: any;
    loading: boolean = true;
    progressPercent: number;
    pdfInfo: any;

    complete(event) {
        this.loading = false;
        this.pdfInfo = event.pdfInfo;
        return this.docLoader.dismiss();
    }

    progress(event) {
        return this.progressPercent = Math.ceil((event.loaded / event.total) * 100);
    }

    err(err) {
        let errToast = this.variables.toastTemplate({
            title: err,
            cssClass: 'toast-danger',
            duration: 3000
        });
        this.docLoader.dismiss();
        return errToast.present();
    }

    remove(document) {
        let alert = this.alertCtrl.create({
            title: 'Confirm',
            message: 'Do you want to remove this document?',
            buttons: [
                {
                    text: 'Cancel',
                    role: 'cancel',
                    handler: () => {

                    }
                },
                {
                    text: 'Remove',
                    handler: () => {
                        let toast = this.variables.toastTemplate({
                            message: `Successfully removed ${document.title}`,
                            cssClass: 'toast-success',
                            position: 'bottom'
                        });
                        this.dataProvider.documents.splice(this.dataProvider.documents.indexOf(document), 1);
                        toast.present();
                        this.navCtrl.pop();
                    }
                }
            ]
        });
        alert.present();
    }

    reply(document) {
        let profileModal = this.modalCtrl.create(DocumentReplyPage, document);
        profileModal.present();
    }

    forward(document) {
        let profileModal = this.modalCtrl.create(DocumentForwardPage, document);
        profileModal.present();
    }

    constructor(private alertCtrl:AlertController,
                private modalCtrl: ModalController,
                private dataProvider: DataProvider,
                private variables: VariablesProvider,
                public navCtrl: NavController,
                public navParams: NavParams) {
        this.data = navParams.data;
        this.docLoader = this.variables.loadingTemplate({
            content: `Loading document...`
        });
        this.docLoader.present();
    }
}
