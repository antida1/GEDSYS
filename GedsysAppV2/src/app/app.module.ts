import {BrowserModule} from '@angular/platform-browser';
import {ErrorHandler, NgModule} from '@angular/core';
import {IonicApp, IonicErrorHandler, IonicModule} from 'ionic-angular';

import {MyApp} from './app.component';
import {LoginPage} from "../pages/login/login";
import {HomePage} from '../pages/home/home';
import {TabsPage} from "../pages/tabs/tabs";
import {NotificationsPage} from '../pages/notifications/notifications';
import {NotificationDetailPage} from "../pages/notification-detail/notification-detail";
import {CalendarPage} from "../pages/calendar/calendar";
import {DocumentsPage} from "../pages/documents/documents";
import {DocumentDetailPage} from "../pages/document-detail/document-detail";
import {ConfigPage} from "../pages/config/config";
import {UserConfigPage} from "../pages/user-config/user-config";
import {AppConfigPage} from "../pages/app-config/app-config";

import {StatusBar} from '@ionic-native/status-bar';
import {SplashScreen} from '@ionic-native/splash-screen';
import {HttpModule} from "@angular/http";
import {CalendarModule} from "ion2-calendar";
import {SuperTabsModule} from "ionic2-super-tabs";
import {PdfViewerComponent} from 'ng2-pdf-viewer';
import {ChartModule} from "angular2-highcharts";

import {Ng2FilterPipeModule} from 'ng2-filter-pipe';
import {MomentPipe} from "../pipes/moment/moment";
import {OrderByPipe} from "../pipes/order-by/order-by";

import * as highCharts from 'highcharts';
import {IonicStorageModule} from "@ionic/storage";
import {RegisterPage} from "../pages/register/register";
import {AuthServiceProvider} from '../providers/auth-service/auth-service';
import {AngularFireModule} from "angularfire2";
import {FIREBASE_CONFIG} from "../shared/app.firebase.config";
import {AngularFireAuthModule} from "angularfire2/auth";
import {AngularFireDatabaseModule} from "angularfire2/database";
import {GedsysApiService} from "../shared/gedsys-api.service";
import {DataProvider} from '../providers/data/data';

@NgModule({
    declarations: [
        MyApp,
        HomePage,
        NotificationsPage,
        CalendarPage,
        ConfigPage,
        DocumentsPage,
        TabsPage,
        LoginPage,
        UserConfigPage,
        AppConfigPage,
        PdfViewerComponent,
        DocumentDetailPage,
        NotificationDetailPage,
        MomentPipe,
        OrderByPipe,
        RegisterPage
    ],
    imports: [
        BrowserModule,
        IonicModule.forRoot(MyApp),
        HttpModule,
        CalendarModule,
        SuperTabsModule.forRoot(),
        Ng2FilterPipeModule,
        ChartModule.forRoot(highCharts),
        IonicStorageModule.forRoot(),
        AngularFireModule.initializeApp(FIREBASE_CONFIG),
        AngularFireAuthModule,
        AngularFireDatabaseModule
    ],
    bootstrap: [IonicApp],
    entryComponents: [
        MyApp,
        HomePage,
        NotificationsPage,
        CalendarPage,
        ConfigPage,
        DocumentsPage,
        TabsPage,
        LoginPage,
        UserConfigPage,
        AppConfigPage,
        DocumentDetailPage,
        NotificationDetailPage,
        RegisterPage
    ],
    providers: [
        StatusBar,
        SplashScreen,
        {provide: ErrorHandler, useClass: IonicErrorHandler},
        AuthServiceProvider,
        GedsysApiService,
        DataProvider
    ]
})
export class AppModule {
}
