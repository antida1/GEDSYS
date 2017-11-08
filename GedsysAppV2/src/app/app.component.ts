import {Component, ViewChild} from '@angular/core';
import {ActionSheetController, Events, LoadingController, Nav, Platform} from 'ionic-angular';
import {StatusBar} from '@ionic-native/status-bar';
import {SplashScreen} from '@ionic-native/splash-screen';
import {HttpModule} from '@angular/http';

import {HomePage} from '../pages/home/home';
import {CalendarPage} from "../pages/calendar/calendar";
import {ConfigPage} from "../pages/config/config";
import {DocumentsPage} from "../pages/documents/documents";
import {GedsysApiService} from '../shared/shared';
import {TabsPage} from "../pages/tabs/tabs";
import {LoginPage} from "../pages/login/login";
import {AuthServiceProvider} from "../providers/auth-service/auth-service";
import {DataProvider} from "../providers/data/data";

@Component({
    templateUrl: 'app.html',
    providers: [
        GedsysApiService,
        HttpModule
    ]
})
export class MyApp {
    @ViewChild(Nav) nav: Nav;
    rootPage: any = LoginPage;
    pages: any;
    navStatus: any = false;

    toggleNav() {
        return this.navStatus = !this.navStatus;
    }

    toggle(user, event) {
        if (event && event.target.className == 'button-inner') {
            const actionSheet = this.actionSheetCtrl.create({
                title: 'User options',
                buttons: [
                    {
                        text: 'Log off ' + user.name,
                        role: 'destructive',
                        handler: () => {
                            this.authService.logout();
                            this.dataProvider.profile = {};
                            this.dataProvider.notifications = [];
                            this.dataProvider.documents = [];
                        }
                    },
                    {
                        text: 'Cancel',
                        role: 'cancel',
                    }
                ]
            });
            actionSheet.present();
            return;
        }
    }

    loginPage: any = {title: 'Login', component: LoginPage};
    activePage: any;
    loading: boolean = true;

    checkAuth() {
        let loading = this.loadingCtrl.create({
            content: 'Please wait...'
        });
        loading.present();
        const tempThis = this;
        this.authService.auth(function (err, profileData) {
            if (err) {
                console.error(err);
            } else {
                tempThis.nav.setRoot(HomePage);
                tempThis.events.publish('root:change', 0);
            }
            tempThis.loading = false;
            return loading.dismiss();
        });
    }

    getAmount(notifications) {
        let amount = 0;
        notifications.map(notification => {
            notification.archived ? amount : amount++;
            return notification;
        })
        return amount;
    }


    constructor(private authService: AuthServiceProvider,
                public loadingCtrl: LoadingController,
                public events: Events,
                public actionSheetCtrl: ActionSheetController,
                public platform: Platform,
                public statusBar: StatusBar,
                public splashScreen: SplashScreen,
                public dataProvider: DataProvider) {
        this.checkAuth();
        this.initializeApp();
        this.pages = [
            {title: 'Dashboard', component: HomePage, icon: 'clipboard'},
            {title: 'Notifications', component: TabsPage, icon: 'notifications'},
            {title: 'Documents', component: DocumentsPage, icon: 'copy'},
            {title: 'Calendar', component: CalendarPage, icon: 'calendar'},
            {title: 'Configuration', component: ConfigPage, icon: 'construct'}
        ];
        this.events.subscribe('root:change', (i)=>{
            return this.activePage = i;
        })
    }

    initializeApp() {
        this.platform.ready().then(() => {
            this.statusBar.styleDefault();
            this.splashScreen.hide();
        });
    }

    openPage(page, data, index) {
        this.nav.setRoot(page.component, data);
        this.activePage = index;
    }

    public checkActivePage(index): boolean {
        return index == this.activePage;
    }

}
