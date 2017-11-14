import { Component } from '@angular/core';
import {IonicPage, NavController, NavParams, ViewController} from 'ionic-angular';

@IonicPage()
@Component({
  selector: 'page-document-reply',
  templateUrl: 'document-reply.html',
})
export class DocumentReplyPage {
  data:any;

  constructor(private viewCtrl: ViewController,
              public navCtrl: NavController,
              public navParams: NavParams) {
    this.data = navParams.data;
  }
  close(){
    this.viewCtrl.dismiss();
  }

}
