import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Events, ToastController} from "ionic-angular";
import {Storage} from "@ionic/storage";
import {AngularFireDatabase} from "angularfire2/database";
import {DataProvider} from "../providers/data/data";

@Injectable()
export class GedsysApiService {
    private baseURL = "https://gedsys-8e06b.firebaseio.com/";


    public getProfile(user) {
        return new Promise(resolve => {
            this.http.get(`${this.baseURL}/profile/${user}.json`)
                .subscribe(res => resolve(res.json()));
        }).then(profile => this.dataProvider.profile = profile);
    }

    public postProfile(uid, profile) {
        this.fireDatabase.object(`profile/${uid}`)
            .set(profile)
            .then((err) => {
                    if (err) {
                        console.log(err);
                    }
                    let documents = [
                        {title: 'Test document', url: 'http://google.com/'}
                    ];
                    this.patchDocuments(uid, documents).then(() => {
                        let notifications = [
                            {title: 'Test notification', archived: false, date : { max: Date.now()}}
                        ];
                        this.patchNotifications(uid,notifications).then(()=>{
                            return;
                        })
                    })
                }
            )
    };


    public getNotifications(user) {
        return new Promise(resolve => {
            this.http.get(`${this.baseURL}/notifications/${user}.json`)
                .subscribe(res => resolve(res.json()));
        }).then(notifications => this.dataProvider.notifications = notifications || []);
    }

    public getDocuments(user) {
        return new Promise(resolve => {
            this.http.get(`${this.baseURL}/documents/${user}.json`)
                .subscribe(res => resolve(res.json()));
        }).then(documents => this.dataProvider.documents = documents || []);
    }

    public patchNotifications(user, data) {
        let patch = {[user]: data};
        return new Promise(resolve => {
            this.http.patch(`${this.baseURL}/notifications.json`, patch, {})
                .subscribe(res => resolve(res.json()));
        })
    }

    public patchDocuments(user, data) {
        let patch = {[user]: data};
        return new Promise(resolve => {
            this.http.patch(`${this.baseURL}/documents.json`, patch, {})
                .subscribe(res => resolve(res.json()));
        })
    }


    public updateAppConfig(data, index) {
        let toast;
        if (index == 0) {
            this.storage.set('user_config', data);
            toast = this.toastCtrl.create({
                message: 'User info updated succesfully',
                duration: 3000
            });
        } else {
            this.storage.set('app_config', data);
            toast = this.toastCtrl.create({
                message: 'App configuration updated succesfully',
                duration: 3000
            });
        }
        return toast.present();

    }

    constructor(private dataProvider: DataProvider,
                private fireDatabase: AngularFireDatabase,
                public toastCtrl: ToastController,
                private storage: Storage,
                public events: Events,
                private http: Http) {
        this.storage.get('app_config').then((data) => {
            let config: any = [
                {
                    last_updated : 1510778647529,
                    title: 'App',
                    options: [
                        /*{
                            title: 'option1',
                            data: null,
                            type: 'input',
                            icon: 'cog'
                        },
                        {
                            title: 'option2',
                            data: null,
                            type: 'textarea',
                            icon: 'cog'
                        },
                        {
                            title: 'option3',
                            data: true,
                            type: 'range',
                            icon: 'cog'
                        },*/
                        {
                            title: 'App badge',
                            data: true,
                            type: 'toggle',
                            icon: 'alert'
                        },
                        {
                            title: 'Notifications',
                            data: true,
                            type: 'toggle',
                            icon: 'notifications'
                        }
                    ]
                },
                {
                    title: 'Notifications',
                    options: [
                        {
                            title: 'Should notify',
                            data: 'Always',
                            type: 'radio-group',
                            group: [
                                {
                                    name: 'Always'
                                },
                                {
                                    name: 'During the day'
                                },
                                {
                                    name: 'Weekdays'
                                },
                                {
                                    name: 'Weekdays during the day'
                                }
                            ]
                        }
                    ]
                },
                {
                    title: 'Contact',
                    options: [
                        {
                            title: 'Base 16',
                            data: null,
                            type: 'button',
                            icon: 'code-working',
                            link: 'http://www.base16.co/',
                            btnTitle: 'Go'
                        }
                    ]
                }];
            if (data && (data[0].last_updated == config[0].last_updated)) {
                return this.dataProvider.app_config = data;
            }
            this.dataProvider.app_config = config;
        });
    }
}
