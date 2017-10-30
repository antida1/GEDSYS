import {Component, ViewChild} from '@angular/core';
import {ActionSheetController, Events, Nav, Platform} from 'ionic-angular';
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

@Component({
    templateUrl: 'app.html',
    providers: [
        GedsysApiService,
        HttpModule
    ]
})
export class MyApp {
    @ViewChild(Nav) nav: Nav;
    rootPage: any = HomePage;
    pages: Array<{ title: string, component: any }>;
    notifications: any = [];
    navStatus: any = true;

    toggleNav() {
        return this.navStatus = !this.navStatus;
    }

    toggle(user, index, event) {
        if (event && event.target.className == 'button-inner') {
            const actionSheet = this.actionSheetCtrl.create({
                title: 'User options',
                buttons: [
                    {
                        text: 'Log off ' + user.name,
                        role: 'destructive',
                        handler: () => {
                            if (this.userActive == index) {
                                this.userActive = undefined;
                                this.events.publish('user:logOff');
                            } else if (this.userActive > index) {
                                this.userActive--;
                            }
                            this.users.splice(index, 1);
                            this.notifications.splice(index, 1);
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
        this.userActive = index;
        this.events.publish('userActive:change', index);
        this.events.publish('user:updated', this.users[index]);
        this.users.map(userMap => {
            userMap.active = false;
            return userMap;
        });
        user.active = !user.active;
        this.navStatus = !this.navStatus;
        this.notifications[index] = 0;
        this.users[this.userActive].notifications.map(notification => {
            notification.archived ? this.notifications[index] : this.notifications[index]++;
            return notification;
        });
    }

    loginPage: any = {title: 'Login', component: LoginPage};
    userActive: any;
    users: any;
    activePage:any;


    constructor(public events: Events, public actionSheetCtrl: ActionSheetController, public platform: Platform, public statusBar: StatusBar, public splashScreen: SplashScreen, public service: GedsysApiService) {
        this.initializeApp();
        this.pages = [
            {title: 'Home', component: HomePage},
            {title: 'Notifications', component: TabsPage},
            {title: 'Documents', component: DocumentsPage},
            {title: 'Calendar', component: CalendarPage},
            {title: 'Configuration', component: ConfigPage}
        ];
        if (this.userActive == undefined) {
            this.navStatus = false;
        }
        this.users = this.service.users;
        this.users.map((user, i) => {
            this.notifications[i] = 0;
            user.notifications.map((notification) => {
                notification.archived ? this.notifications : this.notifications[i]++;
                return notification;
            });
            return user;
        });
        this.events.subscribe('user:updated', (user, index) => {
            this.users[index] = user;
            this.notifications[index] = 0;
            user.notifications.map((notification) => {
                notification.archived ? this.notifications : this.notifications[index]++;
                return notification;
            });
        });
        this.events.subscribe('user:login', () => {
            if (this.users.length < 5) {
                let tempUsr = {
                    name: 'Alejandro Chaves',
                    username: 'alejandrochvs',
                    password: 'alejandrochvs123',
                    img: 'https://instagram.feoh4-1.fna.fbcdn.net/t51.2885-19/s320x320/16228980_370071743368546_5430692052201373696_a.jpg',
                    role: 'Developer',
                    active: false,
                    id: 10,
                    notifications: [
                        {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        },
                        {
                            title: 'Notificacion 5 - user 1',
                            archived: true,
                            date: {
                                created: 1501001600,
                                max: 1508303200
                            }
                        },
                        {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        },
                        {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        }, {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        }, {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        }, {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        }, {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        }, {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        }, {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        }, {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        }, {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        }, {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        }, {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        },
                        {
                            title: 'Notificacion 5 - user 1',
                            archived: true,
                            date: {
                                created: 1501001600,
                                max: 1508303200
                            }
                        },
                        {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        },
                        {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        },
                        {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        },
                        {
                            title: 'Notificacion 5 - user 1',
                            archived: true,
                            date: {
                                created: 1501001600,
                                max: 1508303200
                            }
                        },
                        {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        },
                        {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        },
                        {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        },
                        {
                            title: 'Notificacion 5 - user 1',
                            archived: true,
                            date: {
                                created: 1501001600,
                                max: 1508303200
                            }
                        },
                        {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        },
                        {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        },
                        {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        },
                        {
                            title: 'Notificacion 5 - user 1',
                            archived: true,
                            date: {
                                created: 1501001600,
                                max: 1508303200
                            }
                        },
                        {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        },
                        {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        },
                        {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        },
                        {
                            title: 'Notificacion 5 - user 1',
                            archived: true,
                            date: {
                                created: 1501001600,
                                max: 1508303200
                            }
                        },
                        {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        },
                        {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        },
                        {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        },
                        {
                            title: 'Notificacion 5 - user 1',
                            archived: true,
                            date: {
                                created: 1501001600,
                                max: 1508303200
                            }
                        },
                        {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        },
                        {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        },
                        {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        },
                        {
                            title: 'Notificacion 5 - user 1',
                            archived: true,
                            date: {
                                created: 1501001600,
                                max: 1508303200
                            }
                        },
                        {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        },
                        {
                            title: 'Notificacion 1 - user 1',
                            archived: false,
                            date: {
                                created: 1505001600,
                                max: 1508803200
                            }
                        },
                        {
                            title: 'Notificacion 2 - user 1',
                            archived: true,
                            date: {
                                created: 1505021600,
                                max: 1508802200
                            }
                        },
                        {
                            title: 'Notificacion 3 - user 1',
                            archived: false,
                            date: {
                                created: 1505101600,
                                max: 1509803200
                            }
                        },
                        {
                            title: 'Notificacion 4 - user 1',
                            archived: false,
                            date: {
                                created: 1505002300,
                                max: 1508805200
                            }
                        }
                    ]
                };
                console.log(this.userActive);
                if (!this.userActive) {
                    tempUsr.active = true;
                    this.userActive = 0;
                }
                this.users.push(tempUsr);
                this.events.publish('user:updated', tempUsr, this.users.indexOf(tempUsr));
            } else {
                this.events.publish('user:max');
            }
        });
        this.events.subscribe('root:change',(page)=>{
            this.activePage = page;
        })
    }

    initializeApp() {
        this.platform.ready().then(() => {
            // Okay, so the platform is ready and our plugins are available.
            // Here you can do any higher level native things you might need.
            this.statusBar.styleDefault();
            this.splashScreen.hide();
        });
    }

    openPage(page, data, index) {
        this.nav.setRoot(page.component, data);
        this.activePage = index;
    }
    public checkActivePage(index): boolean{
        return index == this.activePage;
    }
}
