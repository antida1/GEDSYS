import {Component} from '@angular/core';
import {
    ActionSheetController, AlertController, IonicPage, ModalController, NavController,
    NavParams
} from 'ionic-angular';
import {DocumentDetailPage} from "../document-detail/document-detail";
import {DataProvider} from "../../providers/data/data";
import {DocumentReplyPage} from "../document-reply/document-reply";
import {VariablesProvider} from "../../providers/variables/variables";
import {DocumentForwardPage} from "../document-forward/document-forward";

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

    more(document){
        let actionSheet = this.actionSheetCtrl.create({
            title: document.title + '\'s options',
            buttons: [
                {
                    text: 'Remove',
                    role: 'destructive',
                    handler: () => {
                        this.remove(document);
                    }
                },
                {
                    text: 'Reply',
                    handler: () => {
                        this.reply(document);
                    }
                },
                {
                    text: 'Forward',
                    handler: () => {
                        this.forward(document);
                    }
                },
                {
                    text: 'View',
                    handler: () => {
                        this.viewDoc(document);
                    }
                },
                {
                    text: 'Cancel',
                    role: 'cancel',
                    handler: () => {
                    }
                }
            ]
        });
        return actionSheet.present();
    }

    remove(document){
        let confirm = this.alertCtrl.create({
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
                        const loading = this.variables.loadingTemplate(null);
                        loading.present().then(()=>{
                            let toast = this.variables.toastTemplate({
                                message: `Successfully removed ${document.title}`,
                                cssClass: 'toast-success',
                                position: 'bottom'
                            });
                            this.dataProvider.documents.splice(this.dataProvider.documents.indexOf(document),1);
                            loading.dismiss().then(()=>{
                                toast.present().then(()=>{return;});
                            });
                        });


                    }
                }
            ]
        });
        return confirm.present();
    }

    reply(document){
        let profileModal = this.modalCtrl.create(DocumentReplyPage, document);
        return profileModal.present();
    }
    forward(document){
        let profileModal = this.modalCtrl.create(DocumentForwardPage, document);
        return profileModal.present();
    }

    viewDoc(document) {
        return this.navCtrl.push(DocumentDetailPage, document);
    }

    constructor(/* tslint:disable */
                private variables: VariablesProvider, /* tslint:enable */
                private alertCtrl: AlertController,
                private actionSheetCtrl: ActionSheetController,
                private modalCtrl: ModalController,
                private dataProvider: DataProvider,
                public navCtrl: NavController,
                public navParams: NavParams) {
    }

    ionViewDidLoad() {
    }

}
