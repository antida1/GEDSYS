import {Component} from '@angular/core';
import {
    ActionSheetController, Events, IonicPage, LoadingController, NavController, NavParams,
    ToastController
} from 'ionic-angular';
import {NotificationDetailPage} from "../notification-detail/notification-detail";

@IonicPage()
@Component({
    selector: 'page-notifications',
    templateUrl: 'notifications.html',
})
export class NotificationsPage {
    user: any = [];
    curPage: any = 0;
    pagesPer: any = 15;
    filterStr: any = {title : ''};
    ammount: any = 0;
    index: any;
    userInd : any;
    loadNotificationDetail(notification){
        this.navCtrl.push(NotificationDetailPage,notification);
    }
    checkColor(notification){
        let now = new Date().getTime();
        if (notification.date.max - now < 0){
            return 'dark';
        }else if (notification.date.max - now < 86400000){
            return 'danger';
        }

    }
    remove(notification) {
        const loading = this.loadingCtrl.create({
            content: 'Deleting, please wait....'
        });
        loading.present();
        let toast = this.toastCtrl.create({
            message: 'Succesfully deleted!',
            duration: 1500,
            position: 'top',
            showCloseButton: true,
            dismissOnPageChange: true,
            cssClass : 'toast-success'
        });
        this.user.notifications.splice(this.user.notifications.indexOf(notification),1);
        this.events.publish('user:updated',this.user,this.userInd);
        loading.dismiss();
        toast.present();
    }
    archive(notification) {
        const loading = this.loadingCtrl.create({
            content: 'Archiving, please wait....'
        });
        loading.present();
        let toast = this.toastCtrl.create({
            message: this.index ? 'Succesfully unarchived!' : 'Succesfully archived',
            duration: 1500,
            position: 'top',
            showCloseButton: true,
            closeButtonText : 'OK',
            dismissOnPageChange: true,
            cssClass : 'success'
        });
        this.index ? notification.archived = false : notification.archived = true;
        this.events.publish('user:updated',this.user,this.userInd)
        loading.dismiss();
        toast.present();
    }

    filter(event) {
        this.filterStr.title = event.target.value;
    }

    refresh(comp) {
        setTimeout(()=> comp.complete(),1000);
    }

    constructor(private events: Events, public loadingCtrl: LoadingController, public toastCtrl: ToastController,public navCtrl: NavController, public navParams: NavParams, public actionSheetCtrl: ActionSheetController) {
        this.user = this.navParams.data.user;
        this.index = (<any> this.navCtrl).index;
        this.userInd = this.navParams.data.index;
        this.user.notifications.map(notification =>{
            if (this.index){
                notification.archived ? this.ammount++ : this.ammount;
            }else{
                notification.archived ? this.ammount : this.ammount++;
            }
            return notification;
        });
        this.events.subscribe('user:updated',(user,index)=>{
            this.user = user;
            this.userInd = index;
            this.ammount = 0;
            this.user.notifications.map(notification =>{
                if (this.index){
                    notification.archived ? this.ammount++ : this.ammount;
                }else{
                    notification.archived ? this.ammount : this.ammount++;
                }
                return notification;
            });
        });
    }


    ionViewDidEnter() {
    }
    ionViewDidUpdate() {
    }
}
