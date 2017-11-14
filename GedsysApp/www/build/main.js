webpackJsonp([13],{

/***/ 171:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppConfigPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__shared_gedsys_api_service__ = __webpack_require__(40);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__providers_data_data__ = __webpack_require__(24);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var AppConfigPage = (function () {
    function AppConfigPage(service, events, dataProvider, navCtrl, navParams) {
        var _this = this;
        this.service = service;
        this.events = events;
        this.dataProvider = dataProvider;
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.data = this.dataProvider.app_config;
        this.events.subscribe('info:update', function (index) {
            index == 1 ? _this.updateInfo(index) : null;
        });
    }
    AppConfigPage.prototype.updateInfo = function (index) {
        return this.service.updateAppConfig(this.data, index);
    };
    return AppConfigPage;
}());
AppConfigPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-app-config',template:/*ion-inline-start:"/home/alejandrochvs/Documents/GEDSYS/src/pages/app-config/app-config.html"*/'<ion-content>\n    <ion-list class="outer-content" *ngFor="let data of data">\n        <ion-item-divider color="primary">\n            {{data.title}}\n        </ion-item-divider>\n        <ng-container *ngFor="let option of data.options" [ngSwitch]="option.type">\n            <ion-list-header *ngSwitchCase="\'range\'" color="primary">\n                {{option.title}}\n                <ion-badge item-end color="light">{{option.data}}%</ion-badge></ion-list-header>\n            <ion-item *ngIf="option.type != \'radio-group\'">\n                <ion-icon *ngIf="option.type != \'range\'" [name]="option.icon" item-start color="primary"></ion-icon>\n                <ion-label color="primary" *ngIf="option.type != \'range\'">{{option.title}}</ion-label>\n                <ion-toggle *ngSwitchCase="\'toggle\'" [(ngModel)]="option.data"></ion-toggle>\n                <ion-input *ngSwitchCase="\'input\'" [(ngModel)]="option.data"></ion-input>\n                <ion-textarea *ngSwitchCase="\'textarea\'" [(ngModel)]="option.data"></ion-textarea>\n                <ion-range *ngSwitchCase="\'range\'" [(ngModel)]="option.data"></ion-range>\n                <a ion-button outline item-end [href]="option.link" *ngSwitchCase="\'button\'">{{option.btnTitle}}</a>\n            </ion-item>\n            <ion-list *ngSwitchCase="\'radio-group\'" radio-group [(ngModel)]="option.data">\n                <ion-list-header style="margin-top: 0;">\n                    {{option.title}}\n                </ion-list-header>\n                <ng-container *ngFor="let radio of option.group">\n                    <ion-item>\n                        <ion-label color="dark">{{radio.name}}</ion-label>\n                        <ion-radio [value]="radio.name" checked></ion-radio>\n                    </ion-item>\n                </ng-container>\n            </ion-list>\n        </ng-container>\n    </ion-list>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/GEDSYS/src/pages/app-config/app-config.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2__shared_gedsys_api_service__["a" /* GedsysApiService */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* Events */],
        __WEBPACK_IMPORTED_MODULE_3__providers_data_data__["a" /* DataProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], AppConfigPage);

//# sourceMappingURL=app-config.js.map

/***/ }),

/***/ 172:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return CalendarPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__notification_detail_notification_detail__ = __webpack_require__(97);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__providers_data_data__ = __webpack_require__(24);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_date_fns__ = __webpack_require__(802);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_date_fns___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_4_date_fns__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var CalendarPage = (function () {
    function CalendarPage(dataProvider, navCtrl, navParams) {
        var _this = this;
        this.dataProvider = dataProvider;
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.hiddenHeader = false;
        this.activeDayIsOpen = false;
        this.selectedDay = {
            date: new Date()
        };
        this.date = new Date();
        this.events = [
            {
                end: Object(__WEBPACK_IMPORTED_MODULE_4_date_fns__["setHours"])(new Date(), 1),
                title: 'Event example 1',
                color: {
                    primary: 'lightsalmon',
                    secondary: 'black'
                },
                meta: {
                    archived: false,
                    date: { max: Date.now() },
                    title: "Notification 3",
                }
            }
        ];
        this.dataProvider.notifications.map(function (notification) {
            _this.events.push({
                start: Object(__WEBPACK_IMPORTED_MODULE_4_date_fns__["setHours"])(new Date(notification.date.max), 1),
                title: notification.title,
                color: {
                    primary: 'lightsalmon',
                    secondary: 'black'
                },
                meta: notification
            });
        });
    }
    CalendarPage.prototype.contentScroll = function (event) {
        event.directionY == 'down' ? this.hiddenHeader = true : this.hiddenHeader = false;
        event.scrollTop <= 40 ? this.hiddenHeader = false : null;
        return true;
    };
    CalendarPage.prototype.loadNotificationDetail = function (notification) {
        return this.navCtrl.push(__WEBPACK_IMPORTED_MODULE_2__notification_detail_notification_detail__["a" /* NotificationDetailPage */], notification.event.meta);
    };
    CalendarPage.prototype.changeDay = function (event) {
        this.date = new Date(event.day.date);
        if (this.selectedDay) {
            delete this.selectedDay.cssClass;
        }
        if (this.selectedDay == event.day) {
            this.selectedDay = null;
            return this.activeDayIsOpen = false;
        }
        event.day.cssClass = 'cal-day-selected';
        this.selectedDay = event.day;
        this.activeDayIsOpen = true;
        return;
    };
    /*viewOnDay() {
        let offset = document.getElementById('divider').offsetTop;
        return this.content.scrollTo(0, offset, 1000);
    }*/
    CalendarPage.prototype.beforeMonthViewRender = function (_a) {
        var _this = this;
        var body = _a.body;
        body.forEach(function (day) {
            if (_this.selectedDay &&
                day.date.getTime() === _this.selectedDay.date.getTime()) {
                day.cssClass = 'cal-day-selected';
                _this.selectedDay = day;
            }
        });
    };
    return CalendarPage;
}());
__decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["ViewChild"])(__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["e" /* Content */]),
    __metadata("design:type", typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["e" /* Content */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["e" /* Content */]) === "function" && _a || Object)
], CalendarPage.prototype, "content", void 0);
CalendarPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-calendar',template:/*ion-inline-start:"/home/alejandrochvs/Documents/GEDSYS/src/pages/calendar/calendar.html"*/'<ion-header [style.max-height]="hiddenHeader ? \'0vh\': \'10vh\'">\n    <ion-navbar color="primary">\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>Calendar</ion-title>\n    </ion-navbar>\n</ion-header>\n\n\n<ion-content (ionScroll)="contentScroll($event)" [class.full]="hiddenHeader">\n    <ion-item-divider text-center color="primary">\n        <h2>\n            {{date | calendarDate:(\'month\' + \'ViewTitle\'):\'en\'}}\n        </h2>\n    </ion-item-divider>\n    <mwl-calendar-month-view\n            [activeDayIsOpen]="activeDayIsOpen"\n            [excludeDays]="[0,6]"\n            (dayClicked)="changeDay($event)"\n            [viewDate]="date"\n            (beforeViewRender)="beforeMonthViewRender($event)"\n            (eventClicked)="loadNotificationDetail($event)"\n            [events]="events">\n    </mwl-calendar-month-view>\n    <ion-item-divider id="divider" color="primary">{{date | calendarDate:(\'day\' + \'ViewTitle\'):\'en\'}}</ion-item-divider>\n    <mwl-calendar-day-view\n            [dayStartHour]="6"\n            [dayEndHour]="18"\n            [viewDate]="date"\n            [hourSegments]="1"\n            [events]="events">\n    </mwl-calendar-day-view>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/GEDSYS/src/pages/calendar/calendar.html"*/,
    }),
    __metadata("design:paramtypes", [typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_3__providers_data_data__["a" /* DataProvider */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__providers_data_data__["a" /* DataProvider */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]) === "function" && _d || Object])
], CalendarPage);

var _a, _b, _c, _d;
//# sourceMappingURL=calendar.js.map

/***/ }),

/***/ 173:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ConfigPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__user_config_user_config__ = __webpack_require__(174);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__app_config_app_config__ = __webpack_require__(171);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var ConfigPage = (function () {
    function ConfigPage(events, navCtrl, navParams) {
        this.events = events;
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.index = 0;
        this.page1 = __WEBPACK_IMPORTED_MODULE_2__user_config_user_config__["a" /* UserConfigPage */];
        this.page2 = __WEBPACK_IMPORTED_MODULE_3__app_config_app_config__["a" /* AppConfigPage */];
        this.data = navParams.data;
    }
    ConfigPage.prototype.updateInfo = function () {
        return this.events.publish('info:update', this.index);
    };
    ConfigPage.prototype.listenTabs = function (event) {
        return this.index = event.index;
    };
    return ConfigPage;
}());
ConfigPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-config',template:/*ion-inline-start:"/home/alejandrochvs/Documents/GEDSYS/src/pages/config/config.html"*/'<!--\n  Generated template for the ConfigPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header>\n    <ion-navbar color="primary">\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>Configuration</ion-title>\n        <ion-buttons end>\n            <button ion-button (click)="updateInfo()">Save</button>\n        </ion-buttons>\n    </ion-navbar>\n</ion-header>\n\n\n<ion-content>\n    <super-tabs (tabSelect)="listenTabs($event)" toolbarBackground="primary" toolbarColor="light" indicatorColor="light">\n        <super-tab [root]="page1" [rootParams]="data" title="User" icon="person">test</super-tab>\n        <super-tab [root]="page2" [rootParams]="data" title="Application" icon="cog"></super-tab>\n    </super-tabs>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/GEDSYS/src/pages/config/config.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* Events */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], ConfigPage);

//# sourceMappingURL=config.js.map

/***/ }),

/***/ 174:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserConfigPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__shared_gedsys_api_service__ = __webpack_require__(40);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__providers_data_data__ = __webpack_require__(24);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var UserConfigPage = (function () {
    function UserConfigPage(dataProvider, events, service, navCtrl, navParams) {
        var _this = this;
        this.dataProvider = dataProvider;
        this.events = events;
        this.service = service;
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.user = navParams.data;
        this.data = this.dataProvider.user_config;
        this.events.subscribe('info:update', function (index) {
            index == 0 ? _this.updateInfo(index) : null;
        });
    }
    UserConfigPage.prototype.updateInfo = function (index) {
        return this.service.updateAppConfig(this.data, index);
    };
    return UserConfigPage;
}());
UserConfigPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-user-config',template:/*ion-inline-start:"/home/alejandrochvs/Documents/GEDSYS/src/pages/user-config/user-config.html"*/'<ion-content>\n    <ion-list class="outer-content" *ngFor="let data of data">\n        <ion-item-divider color="primary">\n            {{data.title}}\n        </ion-item-divider>\n        <ng-container *ngFor="let option of data.options" [ngSwitch]="option.type">\n            <ion-list-header *ngSwitchCase="\'range\'" color="primary">\n                {{option.title}}\n                <ion-badge item-end color="light">{{option.data}}%</ion-badge></ion-list-header>\n            <ion-item *ngIf="option.type != \'radio-group\'">\n                <ion-icon *ngIf="option.type != \'range\'" [name]="option.icon" item-start color="primary"></ion-icon>\n                <ion-label color="primary" *ngIf="option.type != \'range\'">{{option.title}}</ion-label>\n                <ion-toggle *ngSwitchCase="\'toggle\'" [(ngModel)]="option.data"></ion-toggle>\n                <ion-input *ngSwitchCase="\'input\'" [(ngModel)]="option.data"></ion-input>\n                <ion-textarea *ngSwitchCase="\'textarea\'" [(ngModel)]="option.data"></ion-textarea>\n                <ion-range *ngSwitchCase="\'range\'" [(ngModel)]="option.data"></ion-range>\n                <a ion-button outline item-end [href]="option.link" *ngSwitchCase="\'button\'">{{option.btnTitle}}</a>\n            </ion-item>\n            <ion-list *ngSwitchCase="\'radio-group\'" radio-group [(ngModel)]="option.data">\n                <ion-list-header style="margin-top: 0;">\n                    {{option.title}}\n                </ion-list-header>\n                <ng-container *ngFor="let radio of option.group">\n                    <ion-item>\n                        <ion-label color="dark">{{radio.name}}</ion-label>\n                        <ion-radio [value]="radio.name" checked></ion-radio>\n                    </ion-item>\n                </ng-container>\n            </ion-list>\n        </ng-container>\n    </ion-list>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/GEDSYS/src/pages/user-config/user-config.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_3__providers_data_data__["a" /* DataProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* Events */],
        __WEBPACK_IMPORTED_MODULE_2__shared_gedsys_api_service__["a" /* GedsysApiService */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], UserConfigPage);

//# sourceMappingURL=user-config.js.map

/***/ }),

/***/ 175:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DocumentDetailPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__providers_variables_variables__ = __webpack_require__(42);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__providers_data_data__ = __webpack_require__(24);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__document_reply_document_reply__ = __webpack_require__(98);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var DocumentDetailPage = (function () {
    function DocumentDetailPage(alertCtrl, modalCtrl, dataProvider, variables, navCtrl, navParams) {
        this.alertCtrl = alertCtrl;
        this.modalCtrl = modalCtrl;
        this.dataProvider = dataProvider;
        this.variables = variables;
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.loading = true;
        this.data = navParams.data;
        this.docLoader = this.variables.loadingTemplate({
            content: "Loading document..."
        });
        this.docLoader.present();
    }
    DocumentDetailPage.prototype.complete = function (event) {
        this.loading = false;
        this.pdfInfo = event.pdfInfo;
        return this.docLoader.dismiss();
    };
    DocumentDetailPage.prototype.progress = function (event) {
        return this.progressPercent = Math.ceil((event.loaded / event.total) * 100);
    };
    DocumentDetailPage.prototype.err = function (err) {
        var errToast = this.variables.toastTemplate({
            title: err,
            cssClass: 'toast-err',
            duration: 3000
        });
        this.docLoader.dismiss();
        errToast.present();
        return console.log(err);
    };
    DocumentDetailPage.prototype.remove = function (document) {
        var _this = this;
        var alert = this.alertCtrl.create({
            title: 'Confirm remove',
            message: 'Do you want to remove this document?',
            buttons: [
                {
                    text: 'Cancel',
                    role: 'cancel',
                    handler: function () {
                    }
                },
                {
                    text: 'Remove',
                    handler: function () {
                        _this.dataProvider.documents.splice(_this.dataProvider.documents.indexOf(document), 1);
                        _this.navCtrl.pop();
                    }
                }
            ]
        });
        alert.present();
    };
    DocumentDetailPage.prototype.reply = function (document) {
        var profileModal = this.modalCtrl.create(__WEBPACK_IMPORTED_MODULE_4__document_reply_document_reply__["a" /* DocumentReplyPage */], document);
        profileModal.present();
    };
    DocumentDetailPage.prototype.forward = function (document) {
        var profileModal = this.modalCtrl.create(__WEBPACK_IMPORTED_MODULE_4__document_reply_document_reply__["a" /* DocumentReplyPage */], document);
        profileModal.present();
    };
    return DocumentDetailPage;
}());
DocumentDetailPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-document-detail',template:/*ion-inline-start:"/home/alejandrochvs/Documents/GEDSYS/src/pages/document-detail/document-detail.html"*/'<!--\n  Generated template for the DocumentDetailPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header>\n    <ion-navbar color="primary">\n        <ion-title>{{data.title}}</ion-title>\n    </ion-navbar>\n\n</ion-header>\n<ion-content>\n    <progress-bar [class.progress-hidden]="!loading" [hidden]="!progressPercent"\n                  [progress]="progressPercent"></progress-bar>\n    <ion-card>\n        <ion-fab top right>\n            <button ion-fab mini><ion-icon name="more"></ion-icon></button>\n            <ion-fab-list>\n                <button (click)="reply(data)" color="primary" ion-fab><ion-icon name="undo"></ion-icon></button>\n                <button (click)="forward(data)" color="primary" ion-fab><ion-icon name="redo"></ion-icon></button>\n                <a [href]="data.url" target="_blank" color="primary" ion-fab><ion-icon name="open"></ion-icon></a>\n                <button (click)="remove(data)" color="danger" ion-fab><ion-icon name="trash"></ion-icon></button>\n            </ion-fab-list>\n        </ion-fab>\n        <ion-item>\n            <ion-avatar item-start>\n                <img src="https://organicthemes.com/demo/profile/files/2012/12/profile_img.png">\n            </ion-avatar>\n            <h2 color="primary">Receptor</h2>\n            <p>Subject</p>\n        </ion-item>\n        <ion-card-content no-padding>\n            <pdf-viewer [hidden]=\'loading\' [page]="1"\n                        [stick-to-page]="true"\n                        [show-all]="false"\n                        [render-text]="true"\n                        (on-progress)="progress($event)"\n                        (after-load-complete)="complete($event)"\n                        (error)="err($event)"\n                        [zoom]=[1.0]\n                        [original-size]="false"\n                        [src]="data.url"></pdf-viewer>\n        </ion-card-content>\n        <ion-row padding-horizontal>\n            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab, aliquid, deleniti deserunt dicta error illo\n                ipsam libero minima molestiae, nihil praesentium recusandae sed voluptate. Aspernatur atque cum ducimus\n                magnam magni?</p>\n        </ion-row>\n        <ion-item *ngIf="pdfInfo">\n            <span item-left color="primary">{{pdfInfo.numPages}} {{pdfInfo.numPages == 1 ? \'page\':\'pages\'}}</span>\n            <a ion-button icon-left clear item-end [href]="data.url" target="_blank">\n                <ion-icon name="open"></ion-icon>\n                Open\n            </a>\n        </ion-item>\n    </ion-card>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/GEDSYS/src/pages/document-detail/document-detail.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["b" /* AlertController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["o" /* ModalController */],
        __WEBPACK_IMPORTED_MODULE_3__providers_data_data__["a" /* DataProvider */],
        __WEBPACK_IMPORTED_MODULE_2__providers_variables_variables__["a" /* VariablesProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], DocumentDetailPage);

//# sourceMappingURL=document-detail.js.map

/***/ }),

/***/ 176:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DocumentsPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__document_detail_document_detail__ = __webpack_require__(175);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__providers_data_data__ = __webpack_require__(24);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__document_reply_document_reply__ = __webpack_require__(98);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__providers_variables_variables__ = __webpack_require__(42);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var DocumentsPage = (function () {
    function DocumentsPage(variables, actionSheetCtrl, modalCtrl, dataProvider, navCtrl, navParams) {
        this.variables = variables;
        this.actionSheetCtrl = actionSheetCtrl;
        this.modalCtrl = modalCtrl;
        this.dataProvider = dataProvider;
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.hiddenHeader = false;
        this.filterStr = { title: '' };
    }
    DocumentsPage.prototype.contentScroll = function (event) {
        event.directionY == 'down' ? this.hiddenHeader = true : this.hiddenHeader = false;
        event.scrollTop < 40 ? this.hiddenHeader = false : null;
        return true;
    };
    DocumentsPage.prototype.filter = function (event) {
        return this.filterStr.title = event.target.value;
    };
    DocumentsPage.prototype.refresh = function (comp) {
        setTimeout(function () { return comp.complete(); }, 1000);
    };
    DocumentsPage.prototype.more = function (document) {
        var _this = this;
        var actionSheet = this.actionSheetCtrl.create({
            title: document.title + '\'s options',
            buttons: [
                {
                    text: 'Delete',
                    role: 'destructive',
                    handler: function () {
                        _this.remove(document);
                    }
                },
                {
                    text: 'Reply',
                    handler: function () {
                        _this.reply(document);
                    }
                },
                {
                    text: 'Forward',
                    handler: function () {
                        _this.forward(document);
                    }
                },
                {
                    text: 'View',
                    handler: function () {
                        _this.viewDoc(document);
                    }
                },
                {
                    text: 'Cancel',
                    role: 'cancel',
                    handler: function () {
                    }
                }
            ]
        });
        return actionSheet.present();
    };
    DocumentsPage.prototype.remove = function (document) {
        return this.dataProvider.documents.splice(this.dataProvider.documents.indexOf(document), 1);
    };
    DocumentsPage.prototype.reply = function (document) {
        var profileModal = this.modalCtrl.create(__WEBPACK_IMPORTED_MODULE_4__document_reply_document_reply__["a" /* DocumentReplyPage */], document);
        return profileModal.present();
    };
    DocumentsPage.prototype.forward = function (document) {
        var profileModal = this.modalCtrl.create(__WEBPACK_IMPORTED_MODULE_4__document_reply_document_reply__["a" /* DocumentReplyPage */], document);
        return profileModal.present();
    };
    DocumentsPage.prototype.viewDoc = function (document) {
        return this.navCtrl.push(__WEBPACK_IMPORTED_MODULE_2__document_detail_document_detail__["a" /* DocumentDetailPage */], document);
    };
    DocumentsPage.prototype.ionViewDidLoad = function () {
    };
    return DocumentsPage;
}());
DocumentsPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-documents',template:/*ion-inline-start:"/home/alejandrochvs/Documents/GEDSYS/src/pages/documents/documents.html"*/'<!--\n  Generated template for the DocumentsPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header [style.max-height]="hiddenHeader ? \'0vh\': \'10vh\'">\n    <ion-navbar primary color="primary">\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>Documents</ion-title>\n    </ion-navbar>\n</ion-header>\n\n\n<ion-content (ionScroll)="contentScroll($event)" (ionScrollEnd)="contentScroll($event)"\n             [class.full]="hiddenHeader">\n    <ion-toolbar *ngIf="dataProvider.documents.length > 0" color="primary">\n        <ion-searchbar (ionInput)="filter($event)"></ion-searchbar>\n    </ion-toolbar>\n    <ion-refresher (ionRefresh)="refresh($event)">\n        <ion-refresher-content\n                [pullingIcon]="variables.refresherTemplate().pullingIcon"\n                [pullingText]="variables.refresherTemplate({title : \'documents\'}).pullingText"\n                [refreshingSpinner]="variables.refresherTemplate().refreshingSpinner"\n                [refreshingText]="variables.refresherTemplate({title : \'documents\'}).refreshingText">\n        </ion-refresher-content>\n    </ion-refresher>\n    <ion-item-divider color="primary" *ngIf="filterStr.title">Search for: {{filterStr.title}}</ion-item-divider>\n    <ion-item no-lines *ngIf="(dataProvider.documents | filterBy: filterStr | orderBy: \'title\')?.length == 0">Any document found</ion-item>\n    <ion-row>\n        <ion-col col-12 col-sm-6 col-lg-4 col-xl-3 *ngFor="let pdf of dataProvider.documents | filterBy: filterStr | orderBy: \'title\'">\n            <ion-card>\n                <ion-item>\n                    <ion-avatar item-start>\n                        <img src="http://www.xsjjys.com/data/out/96/WHDQ-512397052.jpg">\n                    </ion-avatar>\n                    <h2>{{pdf.title}}</h2>\n                    <p>For: October 22, 2017</p>\n                </ion-item>\n                <ion-row>\n                    <ion-col>\n                        <button ion-button icon-left clear small (click)="reply(pdf)">\n                            <ion-icon name="undo"></ion-icon>\n                            <div>Reply</div>\n                        </button>\n                    </ion-col>\n                    <ion-col>\n                        <button ion-button icon-left clear small (click)="viewDoc(pdf)">\n                            <ion-icon name="eye"></ion-icon>\n                            <div>View</div>\n                        </button>\n                    </ion-col>\n                    <ion-col center text-center>\n                        <button ion-button icon-left clear small (click)="more(pdf)">\n                            <ion-icon name="more"></ion-icon>\n                        </button>\n                    </ion-col>\n                </ion-row>\n            </ion-card>\n        </ion-col>\n    </ion-row>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/GEDSYS/src/pages/documents/documents.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_5__providers_variables_variables__["a" /* VariablesProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* ActionSheetController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["o" /* ModalController */],
        __WEBPACK_IMPORTED_MODULE_3__providers_data_data__["a" /* DataProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], DocumentsPage);

//# sourceMappingURL=documents.js.map

/***/ }),

/***/ 177:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return LoginPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__register_register__ = __webpack_require__(178);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__providers_auth_service_auth_service__ = __webpack_require__(90);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__providers_variables_variables__ = __webpack_require__(42);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__providers_data_data__ = __webpack_require__(24);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__angular_forms__ = __webpack_require__(22);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};







var LoginPage = (function () {
    function LoginPage(formBuilder, dataProvider, variables, authService, events, navCtrl, navParams) {
        this.formBuilder = formBuilder;
        this.dataProvider = dataProvider;
        this.variables = variables;
        this.authService = authService;
        this.events = events;
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.user = {};
        this.formGroup = formBuilder.group({
            email: ['', __WEBPACK_IMPORTED_MODULE_6__angular_forms__["f" /* Validators */].required],
            password: ['', __WEBPACK_IMPORTED_MODULE_6__angular_forms__["f" /* Validators */].required]
        });
        this.email = this.formGroup.controls['email'];
        this.password = this.formGroup.controls['password'];
    }
    LoginPage.prototype.login = function (user) {
        var _this = this;
        var loading = this.variables.loadingTemplate({ content: 'Logging in, please wait...' });
        loading.present();
        this.authService.login(user).then(function (err) {
            loading.dismiss();
            if (err) {
                var toast = _this.variables.toastTemplate({
                    message: err,
                    cssClass: 'error-toast',
                    duration: 5000,
                    showCloseButton: true
                });
                toast.present();
            }
            else {
                return;
            }
        });
    };
    LoginPage.prototype.loadRegister = function () {
        return this.navCtrl.push(__WEBPACK_IMPORTED_MODULE_2__register_register__["a" /* RegisterPage */]);
    };
    return LoginPage;
}());
LoginPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-login',template:/*ion-inline-start:"/home/alejandrochvs/Documents/GEDSYS/src/pages/login/login.html"*/'<ion-header>\n  <ion-navbar color="primary">\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Login</ion-title>\n  </ion-navbar>\n</ion-header>\n<ion-content class="login-content" padding>\n  <ion-row top class="logo-row">\n    <ion-col col-10 offset-1 col-sm-4>\n      <img color="primary" src="https://www.svgrepo.com/show/151032/paste-text.svg"/>\n    </ion-col>\n    <ion-col col-12 col-sm-6 offset-sm-1 col-md-4 offset-md-2 col-lg-3 offset-lg-2 col-xl-2 offset-xl-3>\n      <div class="login-box" [hidden]="loading">\n        <form [formGroup]="formGroup">\n          <ion-row center>\n            <ion-col>\n              <ion-list no-lines inset>\n                <ion-item>\n                  <ion-label floating color="primary" >Email</ion-label>\n                  <ion-input formControlName="email" clearInput type="text" [(ngModel)]="user.email"></ion-input>\n                </ion-item>\n                <ion-note margin-left item-end padding-left color="danger" *ngIf="email.hasError(\'required\') && email.touched">Email required</ion-note>\n                <ion-note margin-left item-end padding-left color="danger" *ngIf="email.touched && !email.hasError(\'required\') && email.invalid">Wrong format</ion-note>\n                <ion-item>\n                  <ion-label floating color="primary">Password</ion-label>\n                  <ion-input formControlName="password" clearInput type="password" [(ngModel)]="user.password"></ion-input>\n                </ion-item>\n                <ion-note margin-left item-end padding-left color="danger" *ngIf="password.hasError(\'required\') && password.touched">Password required</ion-note>\n                <ion-note margin-left item-end padding-left color="danger" *ngIf="password.touched && !password.hasError(\'required\') && password.invalid">Wrong format</ion-note>\n              </ion-list>\n            </ion-col>\n          </ion-row>\n          <ion-row bottom>\n            <ion-col class="signup-col">\n              <button [disabled]="formGroup.invalid" ion-button class="submit-btn" outline block type="submit" (click)="login(user)">Login</button>\n              <button ion-button class="register-btn" block clear (click)="loadRegister()">Create New Account</button>\n            </ion-col>\n          </ion-row>\n        </form>\n      </div>\n    </ion-col>\n  </ion-row>\n</ion-content>'/*ion-inline-end:"/home/alejandrochvs/Documents/GEDSYS/src/pages/login/login.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_6__angular_forms__["a" /* FormBuilder */],
        __WEBPACK_IMPORTED_MODULE_5__providers_data_data__["a" /* DataProvider */],
        __WEBPACK_IMPORTED_MODULE_4__providers_variables_variables__["a" /* VariablesProvider */],
        __WEBPACK_IMPORTED_MODULE_3__providers_auth_service_auth_service__["a" /* AuthServiceProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* Events */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], LoginPage);

//# sourceMappingURL=login.js.map

/***/ }),

/***/ 178:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return RegisterPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__providers_auth_service_auth_service__ = __webpack_require__(90);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__providers_variables_variables__ = __webpack_require__(42);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__angular_forms__ = __webpack_require__(22);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var RegisterPage = (function () {
    function RegisterPage(formBuilder, variables, authService, navCtrl, navParams) {
        this.formBuilder = formBuilder;
        this.variables = variables;
        this.authService = authService;
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.user = {};
        this.profile = {};
        this.formGroup = formBuilder.group({
            username: ['', __WEBPACK_IMPORTED_MODULE_4__angular_forms__["f" /* Validators */].required],
            name: ['', __WEBPACK_IMPORTED_MODULE_4__angular_forms__["f" /* Validators */].required],
            last_name: ['', __WEBPACK_IMPORTED_MODULE_4__angular_forms__["f" /* Validators */].required],
            role: ['', __WEBPACK_IMPORTED_MODULE_4__angular_forms__["f" /* Validators */].required],
            img: [''],
            email: ['', __WEBPACK_IMPORTED_MODULE_4__angular_forms__["f" /* Validators */].required],
            password: ['', __WEBPACK_IMPORTED_MODULE_4__angular_forms__["f" /* Validators */].required]
        });
        this.username = this.formGroup.controls['username'];
        this.name = this.formGroup.controls['name'];
        this.last_name = this.formGroup.controls['last_name'];
        this.role = this.formGroup.controls['role'];
        this.img = this.formGroup.controls['img'];
        this.email = this.formGroup.controls['email'];
        this.password = this.formGroup.controls['password'];
    }
    RegisterPage.prototype.register = function (user, profile) {
        var _this = this;
        !profile.img ? profile.img = null : profile.img;
        var loading = this.variables.loadingTemplate({
            content: 'Registering, please wait...'
        });
        loading.present();
        this.authService.register(user, profile).then(function (err) {
            loading.dismiss();
            if (err) {
                var errToast = _this.variables.toastTemplate({
                    message: err,
                    cssClass: 'error-toast',
                    duration: 5000,
                    showCloseButton: true
                });
                console.log(err);
                return errToast.present();
            }
            else {
                return _this.authService.auth(null);
            }
        });
    };
    return RegisterPage;
}());
RegisterPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-register',template:/*ion-inline-start:"/home/alejandrochvs/Documents/GEDSYS/src/pages/register/register.html"*/'<ion-header>\n    <ion-navbar color="primary">\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>Register</ion-title>\n    </ion-navbar>\n</ion-header>\n<ion-content class="login-content" padding>\n    <ion-row top class="logo-row">\n        <ion-col col-10 offset-1 col-sm-4>\n            <img src="https://www.svgrepo.com/show/151032/paste-text.svg"/>\n        </ion-col>\n        <ion-col col-12 col-sm-6 offset-sm-1 col-md-4 offset-md-2 col-lg-3 offset-lg-2 col-xl-2 offset-xl-3>\n            <div class="login-box">\n                <form [formGroup]="formGroup">\n                    <ion-row center>\n                        <ion-col>\n                            <ion-list no-lines inset>\n                                <ion-item>\n                                    <ion-label floating color="primary">Username</ion-label>\n                                    <ion-input formControlName="username" type="text" [(ngModel)]="profile.username"></ion-input>\n                                </ion-item>\n                                <ion-note margin-left item-end padding-left color="danger" *ngIf="username.hasError(\'required\') && username.touched">Username required</ion-note>\n                                <ion-note margin-left item-end padding-left color="danger" *ngIf="username.touched && !username.hasError(\'required\') && username.invalid">Wrong format</ion-note>\n                                <ion-item>\n                                    <ion-label floating color="primary">Name</ion-label>\n                                    <ion-input formControlName="name" type="text" [(ngModel)]="profile.name"></ion-input>\n                                </ion-item>\n                                <ion-note margin-left item-end padding-left color="danger" *ngIf="name.hasError(\'required\') && name.touched">Name required</ion-note>\n                                <ion-note margin-left item-end padding-left color="danger" *ngIf="name.touched && !name.hasError(\'required\') && name.invalid">Wrong format</ion-note>\n                                <ion-item>\n                                    <ion-label floating color="primary">Last name</ion-label>\n                                    <ion-input formControlName="last_name" type="text" [(ngModel)]="profile.last_name"></ion-input>\n                                </ion-item>\n                                <ion-note margin-left item-end padding-left color="danger" *ngIf="last_name.hasError(\'required\') && last_name.touched">Last name required</ion-note>\n                                <ion-note margin-left item-end padding-left color="danger" *ngIf="last_name.touched && !last_name.hasError(\'required\') && last_name.invalid">Wrong format</ion-note>\n                                <ion-item>\n                                    <ion-label floating color="primary">Role</ion-label>\n                                    <ion-input formControlName="role" type="text" [(ngModel)]="profile.role"></ion-input>\n                                </ion-item>\n                                <ion-note margin-left item-end padding-left color="danger" *ngIf="role.hasError(\'required\') && role.touched">Role required</ion-note>\n                                <ion-note margin-left item-end padding-left color="danger" *ngIf="role.touched && !role.hasError(\'required\') && role.invalid">Wrong format</ion-note>\n                                <ion-item>\n                                    <ion-label floating color="primary">Image</ion-label>\n                                    <ion-input formControlName="img" type="text" [(ngModel)]="profile.img"></ion-input>\n                                </ion-item>\n                                <ion-note margin-left item-end padding-left color="danger" *ngIf="img.hasError(\'required\') && img.touched">Image required</ion-note>\n                                <ion-note margin-left item-end padding-left color="danger" *ngIf="img.touched && !img.hasError(\'required\') && img.invalid">Wrong format</ion-note>\n                                <ion-item>\n                                    <ion-label floating color="primary">Email</ion-label>\n                                    <ion-input formControlName="email" type="text" [(ngModel)]="user.email"></ion-input>\n                                </ion-item>\n                                <ion-note margin-left item-end padding-left color="danger" *ngIf="email.hasError(\'required\') && email.touched">Email required</ion-note>\n                                <ion-note margin-left item-end padding-left color="danger" *ngIf="email.touched && !email.hasError(\'required\') && email.invalid">Wrong format</ion-note>\n                                <ion-item>\n                                    <ion-label floating color="primary">Password</ion-label>\n                                    <ion-input formControlName="password" color="primary" type="password" [(ngModel)]="user.password"></ion-input>\n                                </ion-item>\n                                <ion-note margin-left item-end padding-left color="danger" *ngIf="password.hasError(\'required\') && password.touched">Password required</ion-note>\n                                <ion-note margin-left item-end padding-left color="danger" *ngIf="password.touched && !password.hasError(\'required\') && password.invalid">Wrong format</ion-note>\n\n                            </ion-list>\n                        </ion-col>\n                    </ion-row>\n\n                    <ion-row bottom>\n                        <ion-col class="signup-col">\n                            <button [disabled]="formGroup.invalid" ion-button class="submit-btn" outline block type="submit"\n                                    (click)="register(user,profile)">Register\n                            </button>\n                        </ion-col>\n                    </ion-row>\n\n                </form>\n            </div>\n        </ion-col>\n    </ion-row>\n\n</ion-content>'/*ion-inline-end:"/home/alejandrochvs/Documents/GEDSYS/src/pages/register/register.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_4__angular_forms__["a" /* FormBuilder */],
        __WEBPACK_IMPORTED_MODULE_3__providers_variables_variables__["a" /* VariablesProvider */],
        __WEBPACK_IMPORTED_MODULE_2__providers_auth_service_auth_service__["a" /* AuthServiceProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], RegisterPage);

//# sourceMappingURL=register.js.map

/***/ }),

/***/ 179:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return NotificationsPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__notification_detail_notification_detail__ = __webpack_require__(97);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__providers_data_data__ = __webpack_require__(24);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__providers_variables_variables__ = __webpack_require__(42);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var NotificationsPage = (function () {
    function NotificationsPage(alertCtrl, dataProvider, variables, navCtrl, navParams) {
        this.alertCtrl = alertCtrl;
        this.dataProvider = dataProvider;
        this.variables = variables;
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.filterStr = { title: '' };
        this.filterBool = { archived: false };
        this.hiddenHeader = false;
        this.index = this.navCtrl.index;
        this.index ? this.filterBool.archived = true : this.filterBool.archived = false;
    }
    NotificationsPage.prototype.loadNotificationDetail = function (notification) {
        this.navCtrl.push(__WEBPACK_IMPORTED_MODULE_2__notification_detail_notification_detail__["a" /* NotificationDetailPage */], notification);
    };
    NotificationsPage.prototype.checkColor = function (notification) {
        var now = new Date().getTime();
        if (notification.date.max - now < 0) {
            return 'dark';
        }
        else if (notification.date.max - now < 86400000) {
            return 'danger';
        }
    };
    NotificationsPage.prototype.remove = function (notification) {
        var _this = this;
        var alert = this.alertCtrl.create({
            title: 'Confirm remove',
            message: 'Do you want to remove this notification?',
            buttons: [
                {
                    text: 'Cancel',
                    role: 'cancel',
                    handler: function () {
                    }
                },
                {
                    text: 'Remove',
                    handler: function () {
                        var loading = _this.variables.loadingTemplate(null);
                        loading.present().then(function () {
                            var toast = _this.variables.toastTemplate({
                                message: 'Succesfully removed',
                                cssClass: 'danger-toast',
                                position: 'bottom'
                            });
                            _this.dataProvider.notifications.splice(_this.dataProvider.notifications.indexOf(notification), 1);
                            loading.dismiss().then(function () {
                                toast.present().then(function () { return; });
                            });
                        });
                    }
                }
            ]
        });
        alert.present();
    };
    NotificationsPage.prototype.archive = function (notification) {
        var loading = this.variables.loadingTemplate(null);
        loading.present();
        var toast = this.variables.toastTemplate({
            message: this.index ? 'Succesfully unarchived!' : 'Succesfully archived',
            closeButtonText: 'Ok',
            cssClass: 'success-toast',
            position: 'bottom'
        });
        this.index ? notification.archived = false : notification.archived = true;
        loading.dismiss();
        toast.present();
    };
    NotificationsPage.prototype.refresh = function (comp) {
        setTimeout(function () { return comp.complete(); }, 1000);
    };
    NotificationsPage.prototype.contentScroll = function (event) {
        event.directionY == 'down' ? this.hiddenHeader = true : this.hiddenHeader = false;
        event.scrollTop < 40 ? this.hiddenHeader = false : null;
        return true;
    };
    NotificationsPage.prototype.getAmount = function (notifications, archived) {
        var amount = 0;
        if (notifications.length == 0 || notifications.length == undefined) {
            return 0;
        }
        else {
            if (archived) {
                notifications.map(function (notification) {
                    notification.archived ? amount++ : amount;
                    return notification;
                });
            }
            else {
                notifications.map(function (notification) {
                    notification.archived ? amount : amount++;
                    return notification;
                });
            }
            return amount;
        }
    };
    return NotificationsPage;
}());
NotificationsPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-notifications',template:/*ion-inline-start:"/home/alejandrochvs/Documents/GEDSYS/src/pages/notifications/notifications.html"*/'<!--\n  Generated template for the NotificationsPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header [style.max-height]="hiddenHeader ? \'0vh\': \'10vh\'">\n    <ion-navbar color="primary">\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>{{index ? \'Archived\' : \'Notifications\'}}</ion-title>\n    </ion-navbar>\n</ion-header>\n<ion-content (ionScroll)="contentScroll($event)" (ionScrollEnd)="contentScroll($event)" [class.full]="hiddenHeader">\n    <ion-toolbar *ngIf="dataProvider.notifications.length > 0" color="primary">\n        <ion-searchbar [(ngModel)]="filterStr.title"></ion-searchbar>\n    </ion-toolbar>\n    <ion-refresher (ionRefresh)="refresh($event)">\n        <ion-refresher-content\n                [pullingIcon]="variables.refresherTemplate().pullingIcon"\n                [pullingText]="variables.refresherTemplate({title : \'notifications\'}).pullingText"\n                [refreshingSpinner]="variables.refresherTemplate().refreshingSpinner"\n                [refreshingText]="variables.refresherTemplate({title : \'notifications\'}).refreshingText">\n        </ion-refresher-content>\n    </ion-refresher>\n    <ion-list no-lines no-border>\n        <ion-item-divider color="primary" *ngIf="filterStr.title">Search for: {{filterStr.title}}</ion-item-divider>\n        <ion-item *ngIf="(dataProvider.notifications |filterBy: filterBool | filterBy: filterStr | orderBy: \'date.max\')?.length == 0">\n            {{index ? "No archived notifications" : "No pending notifications"}}\n        </ion-item>\n        <ng-container\n                *ngFor="let notification of dataProvider.notifications |filterBy: filterBool | filterBy: filterStr | orderBy: \'date.max\'">\n            <ion-item-sliding>\n                <ion-item (click)="loadNotificationDetail(notification)">\n                    {{notification.title}}\n                    <ion-badge [color]="checkColor(notification)" item-end>{{notification.date.max | moment}}\n                    </ion-badge>\n                </ion-item>\n                <ion-item-options side="right" (ionSwipe)="archive(notification)">\n                    <button color="danger" ion-button (click)="remove(notification)">Delete</button>\n                    <button ion-button expandable (click)="archive(notification)">Archive</button>\n                </ion-item-options>\n            </ion-item-sliding>\n        </ng-container>\n    </ion-list>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/GEDSYS/src/pages/notifications/notifications.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["b" /* AlertController */],
        __WEBPACK_IMPORTED_MODULE_3__providers_data_data__["a" /* DataProvider */],
        __WEBPACK_IMPORTED_MODULE_4__providers_variables_variables__["a" /* VariablesProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], NotificationsPage);

//# sourceMappingURL=notifications.js.map

/***/ }),

/***/ 180:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return TabsPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__notifications_notifications__ = __webpack_require__(179);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__shared_gedsys_api_service__ = __webpack_require__(40);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__providers_data_data__ = __webpack_require__(24);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var TabsPage = (function () {
    function TabsPage(dataProvider, events, service, navCtrl, navParams) {
        this.dataProvider = dataProvider;
        this.events = events;
        this.service = service;
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.notificationsView = __WEBPACK_IMPORTED_MODULE_2__notifications_notifications__["a" /* NotificationsPage */];
    }
    TabsPage.prototype.getAmount = function (notifications) {
        var amount = 0;
        notifications.map(function (notification) {
            notification.archived ? amount : amount++;
            return notification;
        });
        return amount;
    };
    TabsPage.prototype.getArchived = function (notifications) {
        var amount = 0;
        notifications.map(function (notification) {
            notification.archived ? amount++ : amount;
            return notification;
        });
        return amount;
    };
    TabsPage.prototype.ionViewDidEnter = function () {
    };
    return TabsPage;
}());
TabsPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-tabs',template:/*ion-inline-start:"/home/alejandrochvs/Documents/GEDSYS/src/pages/tabs/tabs.html"*/'<!--\n  Generated template for the TabsPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n\n<ion-tabs color="primary">\n  <ion-tab [rootParams]="data" [root]="notificationsView" tabIcon="alert" tabTitle="Notifications"\n           [tabBadge]="getAmount(dataProvider.notifications)" tabBadgeStyle="danger"></ion-tab>\n  <ion-tab [rootParams]="data" [root]="notificationsView" tabIcon="archive" tabTitle="Archived"\n           [tabBadge]="getArchived(dataProvider.notifications)" tabBadgeStyle="light"></ion-tab>\n</ion-tabs>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/GEDSYS/src/pages/tabs/tabs.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_4__providers_data_data__["a" /* DataProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* Events */],
        __WEBPACK_IMPORTED_MODULE_3__shared_gedsys_api_service__["a" /* GedsysApiService */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], TabsPage);

//# sourceMappingURL=tabs.js.map

/***/ }),

/***/ 187:
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncatched exception popping up in devtools
	return Promise.resolve().then(function() {
		throw new Error("Cannot find module '" + req + "'.");
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = 187;

/***/ }),

/***/ 229:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"../pages/app-config/app-config.module": [
		761,
		12
	],
	"../pages/calendar/calendar.module": [
		762,
		11
	],
	"../pages/config/config.module": [
		763,
		10
	],
	"../pages/document-detail/document-detail.module": [
		764,
		9
	],
	"../pages/document-reply/document-reply.module": [
		765,
		8
	],
	"../pages/documents/documents.module": [
		766,
		7
	],
	"../pages/login/login.module": [
		767,
		6
	],
	"../pages/notification-detail/notification-detail.module": [
		768,
		5
	],
	"../pages/notifications/notifications.module": [
		769,
		4
	],
	"../pages/register/register.module": [
		770,
		3
	],
	"../pages/tabs/tabs.module": [
		771,
		2
	],
	"../pages/user-config/user-config.module": [
		772,
		1
	]
};
function webpackAsyncContext(req) {
	var ids = map[req];
	if(!ids)
		return Promise.reject(new Error("Cannot find module '" + req + "'."));
	return __webpack_require__.e(ids[1]).then(function() {
		return __webpack_require__(ids[0]);
	});
};
webpackAsyncContext.keys = function webpackAsyncContextKeys() {
	return Object.keys(map);
};
webpackAsyncContext.id = 229;
module.exports = webpackAsyncContext;

/***/ }),

/***/ 24:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DataProvider; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_map__ = __webpack_require__(41);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_map___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_map__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var DataProvider = (function () {
    function DataProvider() {
        this.profile = {};
        this.notifications = [];
        this.documents = [];
    }
    return DataProvider;
}());
DataProvider = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [])
], DataProvider);

//# sourceMappingURL=data.js.map

/***/ }),

/***/ 352:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return HomePage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__shared_gedsys_api_service__ = __webpack_require__(40);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__providers_data_data__ = __webpack_require__(24);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__providers_variables_variables__ = __webpack_require__(42);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var HomePage = (function () {
    function HomePage(variables, dataProvider, service, events, navParams, navCtrl) {
        this.variables = variables;
        this.dataProvider = dataProvider;
        this.service = service;
        this.events = events;
        this.navParams = navParams;
        this.navCtrl = navCtrl;
        this.hiddenHeader = false;
        this.chartOptions = {
            chart: {
                type: 'areaspline'
            },
            title: {
                text: 'Documents in process'
            },
            legend: {
                layout: 'horizontal',
                align: 'right',
                verticalAlign: 'bottom',
                itemWidth: 150,
                x: 0,
                y: 0,
                floating: false,
                backgroundColor: '#FFFFFF'
            },
            xAxis: {
                categories: [
                    'Monday',
                    'Tuesday',
                    'Wednesday',
                    'Thursday',
                    'Friday',
                    'Saturday',
                    'Sunday',
                ],
                plotBands: [{
                        from: 4.5,
                        to: 7,
                        color: 'rgba(68, 170, 213, .1)'
                    }]
            },
            yAxis: {
                title: {
                    text: 'Response time'
                }
            },
            tooltip: {
                shared: true,
                valueSuffix: ' units'
            },
            credits: {
                enabled: false
            },
            plotOptions: {
                areaspline: {
                    fillOpacity: 0.5
                }
            },
            series: [{
                    name: 'Proceedings',
                    data: [30, 40, 30, 50, 40, 100, 120]
                }, {
                    name: 'Guardianships',
                    data: [10, 30, 40, 30, 30, 50, 40]
                }, {
                    name: 'Contracts',
                    data: [60, 80, 70, 90, 110, 120, 100]
                }, {
                    name: 'Bills',
                    data: [80, 60, 80, 60, 40, 20, 10]
                }]
        };
        this.chartOptions1 = {
            chart: {
                type: 'bar'
            },
            title: {
                text: 'Documents by type'
            },
            xAxis: {
                categories: ['Type']
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Total documents'
                }
            },
            legend: {
                layout: 'horizontal',
                align: 'right',
                verticalAlign: 'bottom',
                itemWidth: 150,
                x: 0,
                y: 0,
                floating: false,
                backgroundColor: '#FFFFFF'
            },
            plotOptions: {
                series: {
                    stacking: 'normal'
                }
            },
            series: [{
                    name: 'Proceedings',
                    data: [5]
                }, {
                    name: 'Guardianships',
                    data: [2]
                }, {
                    name: 'Contracts',
                    data: [3]
                }, {
                    name: 'Bills',
                    data: [6]
                }]
        };
    }
    HomePage.prototype.contentScroll = function (event) {
        event.directionY == 'down' ? this.hiddenHeader = true : this.hiddenHeader = false;
        event.scrollTop < 40 ? this.hiddenHeader = false : null;
        return true;
    };
    HomePage.prototype.loadNotifications = function () {
        return this.events.publish('root:change', 1);
    };
    HomePage.prototype.loadDocuments = function () {
        return this.events.publish('root:change', 2);
    };
    HomePage.prototype.loadSettings = function () {
        return this.events.publish('root:change', 4);
    };
    HomePage.prototype.getAmount = function (notifications) {
        var amount = 0;
        notifications.map(function (notification) {
            notification.archived ? amount : amount++;
            return notification;
        });
        return amount;
    };
    return HomePage;
}());
HomePage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-home',template:/*ion-inline-start:"/home/alejandrochvs/Documents/GEDSYS/src/pages/home/home.html"*/'<ion-header [style.max-height]="hiddenHeader ? \'0vh\': \'10vh\'">\n    <ion-navbar primary color="primary">\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>Dashboard</ion-title>\n        <ion-buttons end *ngIf="!dataProvider.profile">\n            <button ion-button color="primary" menuToggle>Login</button>\n        </ion-buttons>\n    </ion-navbar>\n</ion-header>\n\n<ion-content (ionScroll)="contentScroll($event)" (ionScrollEnd)="contentScroll($event)" [class.full]="hiddenHeader">\n    <ion-row *ngIf="dataProvider.profile">\n        <ion-col col-12 col-sm-6 col-lg-4>\n            <ion-card>\n                <img [src]="dataProvider.profile.img" *ngIf="dataProvider.profile.img">\n                <ion-row *ngIf="!dataProvider.profile.img">\n                    <ion-icon color="primary" col-12 name="contact"></ion-icon>\n                </ion-row>\n                <ion-fab right top>\n                    <button ion-fab (click)="loadSettings()">\n                        <ion-icon name="settings"></ion-icon>\n                    </button>\n                </ion-fab>\n                <ion-item>\n                    <h2>{{dataProvider.profile.name}}</h2>\n                    <p>{{dataProvider.profile.role}}</p>\n                </ion-item>\n                <ion-item>\n                    <button ion-button icon-left clear item-start (click)="loadDocuments()">\n                        <ion-icon name="copy"></ion-icon>\n                        {{dataProvider.documents.length}} Documents\n                    </button>\n                    <button ion-button icon-left clear item-end (click)="loadNotifications()">\n                        <ion-icon name="notifications"></ion-icon>\n                        {{getAmount(dataProvider.notifications)}} Notifications\n                    </button>\n                </ion-item>\n            </ion-card>\n        </ion-col>\n        <ion-col col-12 col-sm-6 col-lg-8>\n            <ion-card>\n                <ion-card-content>\n                    <chart style="display: block;" [options]="chartOptions" type="chart"></chart>\n                </ion-card-content>\n            </ion-card>\n        </ion-col>\n        <ion-col col-12>\n            <ion-card>\n                <ion-card-content>\n                    <chart style="display: block;" [options]="chartOptions1" type="chart"></chart>\n                </ion-card-content>\n            </ion-card>\n        </ion-col>\n    </ion-row>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/GEDSYS/src/pages/home/home.html"*/
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_4__providers_variables_variables__["a" /* VariablesProvider */],
        __WEBPACK_IMPORTED_MODULE_3__providers_data_data__["a" /* DataProvider */],
        __WEBPACK_IMPORTED_MODULE_2__shared_gedsys_api_service__["a" /* GedsysApiService */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* Events */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */]])
], HomePage);

//# sourceMappingURL=home.js.map

/***/ }),

/***/ 40:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GedsysApiService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(126);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__ionic_storage__ = __webpack_require__(230);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_angularfire2_database__ = __webpack_require__(231);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__providers_data_data__ = __webpack_require__(24);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var GedsysApiService = (function () {
    function GedsysApiService(dataProvider, fireDatabase, toastCtrl, storage, events, http) {
        var _this = this;
        this.dataProvider = dataProvider;
        this.fireDatabase = fireDatabase;
        this.toastCtrl = toastCtrl;
        this.storage = storage;
        this.events = events;
        this.http = http;
        this.baseURL = "https://gedsys-8e06b.firebaseio.com/";
        this.storage.get('app_config').then(function (data) {
            if (data) {
                return _this.dataProvider.app_config = data;
            }
            _this.dataProvider.app_config = [
                {
                    title: 'category 1',
                    options: [
                        {
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
                            data: null,
                            type: 'range',
                            icon: 'cog'
                        },
                        {
                            title: 'option4',
                            data: null,
                            type: 'toggle',
                            icon: 'cog'
                        }
                    ]
                },
                {
                    title: 'category 2',
                    options: [
                        {
                            title: 'option6',
                            data: null,
                            type: 'radio-group',
                            group: [
                                {
                                    name: '1'
                                },
                                {
                                    name: '2'
                                },
                                {
                                    name: '3'
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
                }
            ];
        });
        this.storage.get('user_config').then(function (data) {
            if (data) {
                return _this.dataProvider.user_config = data;
            }
            _this.dataProvider.user_config = [
                {
                    title: 'category 2',
                    options: [
                        {
                            title: 'option6',
                            data: null,
                            type: 'radio-group',
                            group: [
                                {
                                    name: '1'
                                },
                                {
                                    name: '2'
                                },
                                {
                                    name: '3'
                                }
                            ]
                        }
                    ]
                },
                {
                    title: 'category 1',
                    options: [
                        {
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
                            data: null,
                            type: 'range',
                            icon: 'cog'
                        },
                        {
                            title: 'option4',
                            data: null,
                            type: 'toggle',
                            icon: 'cog'
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
                }
            ];
        });
    }
    GedsysApiService.prototype.getProfile = function (user) {
        var _this = this;
        return new Promise(function (resolve) {
            _this.http.get(_this.baseURL + "/profile/" + user + ".json")
                .subscribe(function (res) { return resolve(res.json()); });
        }).then(function (profile) { return _this.dataProvider.profile = profile; });
    };
    GedsysApiService.prototype.postProfile = function (uid, profile) {
        var _this = this;
        this.fireDatabase.object("profile/" + uid)
            .set(profile)
            .then(function (err) {
            if (err) {
                console.log(err);
            }
            var documents = [
                { title: 'Test document', url: 'http://google.com/' }
            ];
            _this.patchDocuments(uid, documents).then(function () {
                var notifications = [
                    { title: 'Test notification', archived: false, date: { max: Date.now() } }
                ];
                _this.patchNotifications(uid, notifications).then(function () {
                    return;
                });
            });
        });
    };
    ;
    GedsysApiService.prototype.getNotifications = function (user) {
        var _this = this;
        return new Promise(function (resolve) {
            _this.http.get(_this.baseURL + "/notifications/" + user + ".json")
                .subscribe(function (res) { return resolve(res.json()); });
        }).then(function (notifications) { return _this.dataProvider.notifications = notifications || []; });
    };
    GedsysApiService.prototype.getDocuments = function (user) {
        var _this = this;
        return new Promise(function (resolve) {
            _this.http.get(_this.baseURL + "/documents/" + user + ".json")
                .subscribe(function (res) { return resolve(res.json()); });
        }).then(function (documents) { return _this.dataProvider.documents = documents || []; });
    };
    GedsysApiService.prototype.patchNotifications = function (user, data) {
        var _this = this;
        var patch = (_a = {}, _a[user] = data, _a);
        return new Promise(function (resolve) {
            _this.http.patch(_this.baseURL + "/notifications.json", patch, {})
                .subscribe(function (res) { return resolve(res.json()); });
        });
        var _a;
    };
    GedsysApiService.prototype.patchDocuments = function (user, data) {
        var _this = this;
        var patch = (_a = {}, _a[user] = data, _a);
        return new Promise(function (resolve) {
            _this.http.patch(_this.baseURL + "/documents.json", patch, {})
                .subscribe(function (res) { return resolve(res.json()); });
        });
        var _a;
    };
    GedsysApiService.prototype.updateAppConfig = function (data, index) {
        var toast;
        if (index == 0) {
            this.storage.set('user_config', data);
            toast = this.toastCtrl.create({
                message: 'User info updated succesfully',
                duration: 3000
            });
        }
        else {
            this.storage.set('app_config', data);
            toast = this.toastCtrl.create({
                message: 'App configuration updated succesfully',
                duration: 3000
            });
        }
        return toast.present();
    };
    return GedsysApiService;
}());
GedsysApiService = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_5__providers_data_data__["a" /* DataProvider */],
        __WEBPACK_IMPORTED_MODULE_4_angularfire2_database__["a" /* AngularFireDatabase */],
        __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["v" /* ToastController */],
        __WEBPACK_IMPORTED_MODULE_3__ionic_storage__["b" /* Storage */],
        __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["h" /* Events */],
        __WEBPACK_IMPORTED_MODULE_1__angular_http__["a" /* Http */]])
], GedsysApiService);

//# sourceMappingURL=gedsys-api.service.js.map

/***/ }),

/***/ 42:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return VariablesProvider; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_map__ = __webpack_require__(41);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_map___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_map__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_ionic_angular__ = __webpack_require__(11);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var VariablesProvider = (function () {
    function VariablesProvider(toastCtrl, loadingCtrl) {
        this.toastCtrl = toastCtrl;
        this.loadingCtrl = loadingCtrl;
        this.spinner = 'bubbles';
    }
    VariablesProvider.prototype.refresherTemplate = function (options) {
        !options ? options = {} : options;
        return {
            pullingIcon: 'arrow-dropdown',
            pullingText: options.title ? "Pull to refresh " + options.title + "..." : "Pull to refresh...",
            refreshingSpinner: this.spinner,
            refreshingText: options.title ? "Refreshing " + options.title + "..." : "Refreshing..."
        };
    };
    VariablesProvider.prototype.loadingTemplate = function (options) {
        !options ? options = {} : options;
        return this.loadingCtrl.create({
            spinner: this.spinner,
            content: options.content || 'Loading Please Wait...',
            duration: options.duration || null
        });
    };
    VariablesProvider.prototype.toastTemplate = function (options) {
        !options ? options = {} : options;
        return this.toastCtrl.create({
            message: options.message || 'Done',
            duration: options.duration || 1500,
            position: options.position || 'top',
            showCloseButton: options.showCloseButton || true,
            closeButtonText: options.closeButtonText || 'Close',
            dismissOnPageChange: options.dismissOnPageChange || false,
            cssClass: options.cssClass || null
        });
    };
    return VariablesProvider;
}());
VariablesProvider = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2_ionic_angular__["v" /* ToastController */],
        __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["n" /* LoadingController */]])
], VariablesProvider);

//# sourceMappingURL=variables.js.map

/***/ }),

/***/ 492:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser_dynamic__ = __webpack_require__(493);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__app_module__ = __webpack_require__(508);


Object(__WEBPACK_IMPORTED_MODULE_0__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_1__app_module__["a" /* AppModule */]);
//# sourceMappingURL=main.js.map

/***/ }),

/***/ 508:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__ = __webpack_require__(32);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__app_component__ = __webpack_require__(703);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__pages_login_login__ = __webpack_require__(177);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__pages_home_home__ = __webpack_require__(352);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__pages_tabs_tabs__ = __webpack_require__(180);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__pages_notifications_notifications__ = __webpack_require__(179);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__pages_notification_detail_notification_detail__ = __webpack_require__(97);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__pages_calendar_calendar__ = __webpack_require__(172);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__pages_documents_documents__ = __webpack_require__(176);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__pages_document_detail_document_detail__ = __webpack_require__(175);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__pages_config_config__ = __webpack_require__(173);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__pages_user_config_user_config__ = __webpack_require__(174);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14__pages_app_config_app_config__ = __webpack_require__(171);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15__ionic_native_status_bar__ = __webpack_require__(348);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_16__ionic_native_splash_screen__ = __webpack_require__(351);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_17__angular_http__ = __webpack_require__(126);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_18_angular_calendar__ = __webpack_require__(490);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_19_ionic2_super_tabs__ = __webpack_require__(491);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_20_ng2_pdf_viewer__ = __webpack_require__(710);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_20_ng2_pdf_viewer___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_20_ng2_pdf_viewer__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_21_angular2_highcharts__ = __webpack_require__(742);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_21_angular2_highcharts___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_21_angular2_highcharts__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_22_ng2_filter_pipe__ = __webpack_require__(749);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_22_ng2_filter_pipe___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_22_ng2_filter_pipe__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_23__pipes_moment_moment__ = __webpack_require__(751);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_24__pipes_order_by_order_by__ = __webpack_require__(753);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_25_highcharts__ = __webpack_require__(755);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_25_highcharts___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_25_highcharts__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_26__ionic_storage__ = __webpack_require__(230);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_27__pages_register_register__ = __webpack_require__(178);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_28__providers_auth_service_auth_service__ = __webpack_require__(90);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_29_angularfire2__ = __webpack_require__(53);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_30__shared_app_firebase_config__ = __webpack_require__(756);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_31_angularfire2_auth__ = __webpack_require__(306);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_32_angularfire2_database__ = __webpack_require__(231);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_33__shared_gedsys_api_service__ = __webpack_require__(40);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_34__providers_data_data__ = __webpack_require__(24);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_35__pages_document_reply_document_reply__ = __webpack_require__(98);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_36__providers_variables_variables__ = __webpack_require__(42);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_37__components_components_module__ = __webpack_require__(757);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_38__angular_platform_browser_animations__ = __webpack_require__(759);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};







































var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_1__angular_core__["NgModule"])({
        declarations: [
            __WEBPACK_IMPORTED_MODULE_3__app_component__["a" /* MyApp */],
            __WEBPACK_IMPORTED_MODULE_5__pages_home_home__["a" /* HomePage */],
            __WEBPACK_IMPORTED_MODULE_7__pages_notifications_notifications__["a" /* NotificationsPage */],
            __WEBPACK_IMPORTED_MODULE_9__pages_calendar_calendar__["a" /* CalendarPage */],
            __WEBPACK_IMPORTED_MODULE_12__pages_config_config__["a" /* ConfigPage */],
            __WEBPACK_IMPORTED_MODULE_10__pages_documents_documents__["a" /* DocumentsPage */],
            __WEBPACK_IMPORTED_MODULE_6__pages_tabs_tabs__["a" /* TabsPage */],
            __WEBPACK_IMPORTED_MODULE_4__pages_login_login__["a" /* LoginPage */],
            __WEBPACK_IMPORTED_MODULE_13__pages_user_config_user_config__["a" /* UserConfigPage */],
            __WEBPACK_IMPORTED_MODULE_14__pages_app_config_app_config__["a" /* AppConfigPage */],
            __WEBPACK_IMPORTED_MODULE_20_ng2_pdf_viewer__["PdfViewerComponent"],
            __WEBPACK_IMPORTED_MODULE_11__pages_document_detail_document_detail__["a" /* DocumentDetailPage */],
            __WEBPACK_IMPORTED_MODULE_8__pages_notification_detail_notification_detail__["a" /* NotificationDetailPage */],
            __WEBPACK_IMPORTED_MODULE_23__pipes_moment_moment__["a" /* MomentPipe */],
            __WEBPACK_IMPORTED_MODULE_24__pipes_order_by_order_by__["a" /* OrderByPipe */],
            __WEBPACK_IMPORTED_MODULE_27__pages_register_register__["a" /* RegisterPage */],
            __WEBPACK_IMPORTED_MODULE_35__pages_document_reply_document_reply__["a" /* DocumentReplyPage */]
        ],
        imports: [
            __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["a" /* BrowserModule */],
            __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["l" /* IonicModule */].forRoot(__WEBPACK_IMPORTED_MODULE_3__app_component__["a" /* MyApp */], {
                platforms: {
                    ios: {
                        statusbarPadding: true
                    }
                }
            }, {
                links: [
                    { loadChildren: '../pages/app-config/app-config.module#AppConfigPageModule', name: 'AppConfigPage', segment: 'app-config', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/calendar/calendar.module#CalendarPageModule', name: 'CalendarPage', segment: 'calendar', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/config/config.module#ConfigPageModule', name: 'ConfigPage', segment: 'config', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/document-detail/document-detail.module#DocumentDetailPageModule', name: 'DocumentDetailPage', segment: 'document-detail', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/document-reply/document-reply.module#DocumentReplyPageModule', name: 'DocumentReplyPage', segment: 'document-reply', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/documents/documents.module#DocumentsPageModule', name: 'DocumentsPage', segment: 'documents', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/login/login.module#LoginPageModule', name: 'LoginPage', segment: 'login', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/notification-detail/notification-detail.module#NotificationDetailPageModule', name: 'NotificationDetailPage', segment: 'notification-detail', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/notifications/notifications.module#NotificationsPageModule', name: 'NotificationsPage', segment: 'notifications', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/register/register.module#RegisterPageModule', name: 'RegisterPage', segment: 'register', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/tabs/tabs.module#TabsPageModule', name: 'TabsPage', segment: 'tabs', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/user-config/user-config.module#UserConfigPageModule', name: 'UserConfigPage', segment: 'user-config', priority: 'low', defaultHistory: [] }
                ]
            }),
            __WEBPACK_IMPORTED_MODULE_17__angular_http__["b" /* HttpModule */],
            __WEBPACK_IMPORTED_MODULE_18_angular_calendar__["a" /* CalendarModule */].forRoot(),
            __WEBPACK_IMPORTED_MODULE_19_ionic2_super_tabs__["a" /* SuperTabsModule */].forRoot(),
            __WEBPACK_IMPORTED_MODULE_22_ng2_filter_pipe__["Ng2FilterPipeModule"],
            __WEBPACK_IMPORTED_MODULE_21_angular2_highcharts__["ChartModule"].forRoot(__WEBPACK_IMPORTED_MODULE_25_highcharts__),
            __WEBPACK_IMPORTED_MODULE_26__ionic_storage__["a" /* IonicStorageModule */].forRoot(),
            __WEBPACK_IMPORTED_MODULE_29_angularfire2__["a" /* AngularFireModule */].initializeApp(__WEBPACK_IMPORTED_MODULE_30__shared_app_firebase_config__["a" /* FIREBASE_CONFIG */]),
            __WEBPACK_IMPORTED_MODULE_31_angularfire2_auth__["b" /* AngularFireAuthModule */],
            __WEBPACK_IMPORTED_MODULE_37__components_components_module__["a" /* ComponentsModule */],
            __WEBPACK_IMPORTED_MODULE_32_angularfire2_database__["b" /* AngularFireDatabaseModule */],
            __WEBPACK_IMPORTED_MODULE_38__angular_platform_browser_animations__["a" /* BrowserAnimationsModule */]
        ],
        bootstrap: [__WEBPACK_IMPORTED_MODULE_2_ionic_angular__["j" /* IonicApp */]],
        entryComponents: [
            __WEBPACK_IMPORTED_MODULE_3__app_component__["a" /* MyApp */],
            __WEBPACK_IMPORTED_MODULE_5__pages_home_home__["a" /* HomePage */],
            __WEBPACK_IMPORTED_MODULE_7__pages_notifications_notifications__["a" /* NotificationsPage */],
            __WEBPACK_IMPORTED_MODULE_9__pages_calendar_calendar__["a" /* CalendarPage */],
            __WEBPACK_IMPORTED_MODULE_12__pages_config_config__["a" /* ConfigPage */],
            __WEBPACK_IMPORTED_MODULE_10__pages_documents_documents__["a" /* DocumentsPage */],
            __WEBPACK_IMPORTED_MODULE_6__pages_tabs_tabs__["a" /* TabsPage */],
            __WEBPACK_IMPORTED_MODULE_4__pages_login_login__["a" /* LoginPage */],
            __WEBPACK_IMPORTED_MODULE_13__pages_user_config_user_config__["a" /* UserConfigPage */],
            __WEBPACK_IMPORTED_MODULE_14__pages_app_config_app_config__["a" /* AppConfigPage */],
            __WEBPACK_IMPORTED_MODULE_11__pages_document_detail_document_detail__["a" /* DocumentDetailPage */],
            __WEBPACK_IMPORTED_MODULE_8__pages_notification_detail_notification_detail__["a" /* NotificationDetailPage */],
            __WEBPACK_IMPORTED_MODULE_27__pages_register_register__["a" /* RegisterPage */],
            __WEBPACK_IMPORTED_MODULE_35__pages_document_reply_document_reply__["a" /* DocumentReplyPage */]
        ],
        providers: [
            __WEBPACK_IMPORTED_MODULE_15__ionic_native_status_bar__["a" /* StatusBar */],
            __WEBPACK_IMPORTED_MODULE_16__ionic_native_splash_screen__["a" /* SplashScreen */],
            { provide: __WEBPACK_IMPORTED_MODULE_1__angular_core__["ErrorHandler"], useClass: __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["k" /* IonicErrorHandler */] },
            __WEBPACK_IMPORTED_MODULE_28__providers_auth_service_auth_service__["a" /* AuthServiceProvider */],
            __WEBPACK_IMPORTED_MODULE_33__shared_gedsys_api_service__["a" /* GedsysApiService */],
            __WEBPACK_IMPORTED_MODULE_34__providers_data_data__["a" /* DataProvider */],
            __WEBPACK_IMPORTED_MODULE_36__providers_variables_variables__["a" /* VariablesProvider */]
        ]
    })
], AppModule);

//# sourceMappingURL=app.module.js.map

/***/ }),

/***/ 703:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MyApp; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__ionic_native_status_bar__ = __webpack_require__(348);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__ionic_native_splash_screen__ = __webpack_require__(351);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__angular_http__ = __webpack_require__(126);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__pages_home_home__ = __webpack_require__(352);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__pages_calendar_calendar__ = __webpack_require__(172);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__pages_config_config__ = __webpack_require__(173);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__pages_documents_documents__ = __webpack_require__(176);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__shared_shared__ = __webpack_require__(709);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__pages_tabs_tabs__ = __webpack_require__(180);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__pages_login_login__ = __webpack_require__(177);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__providers_auth_service_auth_service__ = __webpack_require__(90);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__providers_data_data__ = __webpack_require__(24);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14__providers_variables_variables__ = __webpack_require__(42);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};















var MyApp = (function () {
    function MyApp(variables, authService, events, actionSheetCtrl, platform, statusBar, splashScreen, dataProvider) {
        var _this = this;
        this.variables = variables;
        this.authService = authService;
        this.events = events;
        this.actionSheetCtrl = actionSheetCtrl;
        this.platform = platform;
        this.statusBar = statusBar;
        this.splashScreen = splashScreen;
        this.dataProvider = dataProvider;
        this.rootPage = __WEBPACK_IMPORTED_MODULE_11__pages_login_login__["a" /* LoginPage */];
        this.navStatus = false;
        this.loginPage = { title: 'Login', component: __WEBPACK_IMPORTED_MODULE_11__pages_login_login__["a" /* LoginPage */] };
        this.loading = true;
        this.checkAuth();
        this.initializeApp();
        this.pages = [
            { title: 'Dashboard', component: __WEBPACK_IMPORTED_MODULE_5__pages_home_home__["a" /* HomePage */], icon: 'clipboard' },
            { title: 'Notifications', component: __WEBPACK_IMPORTED_MODULE_10__pages_tabs_tabs__["a" /* TabsPage */], icon: 'notifications' },
            { title: 'Documents', component: __WEBPACK_IMPORTED_MODULE_8__pages_documents_documents__["a" /* DocumentsPage */], icon: 'copy' },
            { title: 'Calendar', component: __WEBPACK_IMPORTED_MODULE_6__pages_calendar_calendar__["a" /* CalendarPage */], icon: 'calendar' },
            { title: 'Configuration', component: __WEBPACK_IMPORTED_MODULE_7__pages_config_config__["a" /* ConfigPage */], icon: 'construct' }
        ];
        this.events.subscribe('root:change', function (i) {
            _this.nav.setRoot(_this.pages[i].component);
            return _this.activePage = _this.pages[i].component;
        });
    }
    MyApp.prototype.toggleNav = function () {
        return this.navStatus = !this.navStatus;
    };
    MyApp.prototype.toggle = function (user, event) {
        var _this = this;
        if (event && event.target.className == 'button-inner') {
            var actionSheet = this.actionSheetCtrl.create({
                title: 'User options',
                buttons: [
                    {
                        text: 'Log off ' + user.name,
                        role: 'destructive',
                        handler: function () {
                            _this.authService.logout();
                            _this.dataProvider.profile = {};
                            _this.dataProvider.notifications = [];
                            _this.dataProvider.documents = [];
                            _this.nav.setRoot(__WEBPACK_IMPORTED_MODULE_11__pages_login_login__["a" /* LoginPage */]);
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
    };
    MyApp.prototype.checkAuth = function () {
        var loading = this.variables.loadingTemplate(null);
        loading.present();
        var tempThis = this;
        this.authService.auth(function (err, profileData) {
            if (err) {
                console.error(err);
            }
            else {
                var authToast = tempThis.variables.toastTemplate({
                    message: "Logged in as " + tempThis.dataProvider.profile.name + " " + tempThis.dataProvider.profile.last_name,
                    closeButtonText: 'Ok',
                    position: 'bottom'
                });
                authToast.present();
                tempThis.events.publish('root:change', 0);
            }
            tempThis.loading = false;
            return loading.dismiss();
        });
    };
    MyApp.prototype.getAmount = function (notifications) {
        var amount = 0;
        notifications.map(function (notification) {
            notification.archived ? amount : amount++;
            return notification;
        });
        return amount;
    };
    MyApp.prototype.initializeApp = function () {
        var _this = this;
        this.platform.ready().then(function () {
            _this.statusBar.styleDefault();
            _this.splashScreen.hide();
        });
    };
    MyApp.prototype.openPage = function (page) {
        this.nav.setRoot(page.component);
        return this.activePage = page.component;
    };
    MyApp.prototype.checkActivePage = function (index) {
        return index == this.activePage;
    };
    return MyApp;
}());
__decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["ViewChild"])(__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* Nav */]),
    __metadata("design:type", __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* Nav */])
], MyApp.prototype, "nav", void 0);
MyApp = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({template:/*ion-inline-start:"/home/alejandrochvs/Documents/GEDSYS/src/app/app.html"*/'<ion-menu [content]="content">\n    <ion-header>\n        <ion-navbar [color]="navStatus ? \'primary\' : \'\'">\n            <ion-buttons end>\n                <button ion-button icon-only (click)="toggleNav()">\n                    <ion-icon [name]="navStatus ? \'arrow-down\':\'arrow-up\'"></ion-icon>\n                </button>\n            </ion-buttons>\n            <span style="padding : 0 15px;" [style.color]="navStatus ? \'white\' : \'inherit\'" *ngIf="dataProvider.profile.name">\n                {{navStatus ? dataProvider.profile.name: \'Users\'}}\n            </span>\n            <ion-title *ngIf="!dataProvider.profile.name">\n                Login\n            </ion-title>\n            <ion-buttons start *ngIf="navStatus && dataProvider.profile.name">\n                <button menuClose ion-button round item-start *ngIf="dataProvider.profile.img"\n                        (click)="openPage(pages[4])">\n                    <img width="40px" style="border-radius: 50px;padding : 3px;"\n                         [src]="dataProvider.profile.img">\n                </button>\n                <button menuClose *ngIf="!dataProvider.profile.img" ion-button icon-only\n                        (click)="openPage(pages[4])">\n                    <ion-icon name="contact"></ion-icon>\n                </button>\n            </ion-buttons>\n        </ion-navbar>\n    </ion-header>\n    <ion-content>\n        <ion-toolbar [class]="navStatus ? \'\' : \'active\'">\n            <ion-list no-lines no-border>\n                <ion-card *ngIf="dataProvider.profile.name" (click)="toggle(dataProvider.profile,$event)">\n                    <ion-item color="primary">\n                        <ion-avatar item-start *ngIf="dataProvider.profile.img">\n                            <img [src]="dataProvider.profile.img">\n                        </ion-avatar>\n                        <ion-icon *ngIf="!dataProvider.profile.img" color="light" name="contact"\n                                  item-start></ion-icon>\n                        <h2 [style.color]="dataProvider.profile.active ? \'white\' : \'inherit\'">{{dataProvider.profile.name}}</h2>\n                        <p>{{dataProvider.profile.role}}\n                            <ion-badge color="danger" item-end>{{getAmount(dataProvider.notifications)}}</ion-badge>\n                        </p>\n                        <button ion-button icon-left clear item-end>\n                            <ion-icon name="more" color="secondary"></ion-icon>\n                        </button>\n                    </ion-item>\n                </ion-card>\n                <ion-card>\n                    <button menuClose ion-item (click)="openPage(loginPage)">\n                        <ion-icon name="person-add" item-start color="primary"></ion-icon>\n                        {{dataProvider.profile.name ? \'Login with another account...\' : \'Login...\'}}\n                    </button>\n                </ion-card>\n            </ion-list>\n        </ion-toolbar>\n        <ion-list class="profile-toolbar" no-border no-lines *ngIf="dataProvider.profile.name">\n            <button *ngFor="let p of pages, let i = index" detail-none [class.active]="checkActivePage(p.component)" menuClose\n                    ion-item\n                    (click)="openPage(p)">\n                <ion-label>\n                    {{p.title}}\n                </ion-label>\n                <ion-icon color="primary" item-start [name]="p.icon">\n                    <ion-badge *ngIf="i == 1" class="counter" color="danger" item-end>{{getAmount(dataProvider.notifications)}}</ion-badge>\n                    <ion-badge *ngIf="i == 2" class="counter" color="primary" item-end>{{dataProvider.documents.length}}</ion-badge>\n                </ion-icon>\n            </button>\n        </ion-list>\n    </ion-content>\n</ion-menu>\n\n<!-- Disable swipe-to-go-back because it\'s poor UX to combine STGB with side menus -->\n<ion-nav no-lines [root]="rootPage" #content\n         swipeBackEnabled="false"></ion-nav>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/GEDSYS/src/app/app.html"*/,
        providers: [
            __WEBPACK_IMPORTED_MODULE_9__shared_shared__["a" /* GedsysApiService */],
            __WEBPACK_IMPORTED_MODULE_4__angular_http__["b" /* HttpModule */]
        ]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_14__providers_variables_variables__["a" /* VariablesProvider */],
        __WEBPACK_IMPORTED_MODULE_12__providers_auth_service_auth_service__["a" /* AuthServiceProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* Events */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* ActionSheetController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["t" /* Platform */],
        __WEBPACK_IMPORTED_MODULE_2__ionic_native_status_bar__["a" /* StatusBar */],
        __WEBPACK_IMPORTED_MODULE_3__ionic_native_splash_screen__["a" /* SplashScreen */],
        __WEBPACK_IMPORTED_MODULE_13__providers_data_data__["a" /* DataProvider */]])
], MyApp);

//# sourceMappingURL=app.component.js.map

/***/ }),

/***/ 709:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__gedsys_api_service__ = __webpack_require__(40);
/* harmony namespace reexport (by used) */ __webpack_require__.d(__webpack_exports__, "a", function() { return __WEBPACK_IMPORTED_MODULE_0__gedsys_api_service__["a"]; });

//# sourceMappingURL=shared.js.map

/***/ }),

/***/ 717:
/***/ (function(module, exports) {

/* (ignored) */

/***/ }),

/***/ 731:
/***/ (function(module, exports) {

/* (ignored) */

/***/ }),

/***/ 732:
/***/ (function(module, exports) {

/* (ignored) */

/***/ }),

/***/ 733:
/***/ (function(module, exports) {

/* (ignored) */

/***/ }),

/***/ 751:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MomentPipe; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_moment__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_moment___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_moment__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};


/**
 * Generated class for the MomentPipe pipe.
 *
 * See https://angular.io/api/core/Pipe for more info on Angular Pipes.
 */
var MomentPipe = (function () {
    function MomentPipe() {
    }
    /**
     * Takes a value and makes it lowercase.
     */
    MomentPipe.prototype.transform = function (value) {
        var args = [];
        for (var _i = 1; _i < arguments.length; _i++) {
            args[_i - 1] = arguments[_i];
        }
        return __WEBPACK_IMPORTED_MODULE_1_moment___default()(value).fromNow();
    };
    return MomentPipe;
}());
MomentPipe = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Pipe"])({
        name: 'moment',
    })
], MomentPipe);

//# sourceMappingURL=moment.js.map

/***/ }),

/***/ 752:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"./af": 372,
	"./af.js": 372,
	"./ar": 373,
	"./ar-dz": 374,
	"./ar-dz.js": 374,
	"./ar-kw": 375,
	"./ar-kw.js": 375,
	"./ar-ly": 376,
	"./ar-ly.js": 376,
	"./ar-ma": 377,
	"./ar-ma.js": 377,
	"./ar-sa": 378,
	"./ar-sa.js": 378,
	"./ar-tn": 379,
	"./ar-tn.js": 379,
	"./ar.js": 373,
	"./az": 380,
	"./az.js": 380,
	"./be": 381,
	"./be.js": 381,
	"./bg": 382,
	"./bg.js": 382,
	"./bm": 383,
	"./bm.js": 383,
	"./bn": 384,
	"./bn.js": 384,
	"./bo": 385,
	"./bo.js": 385,
	"./br": 386,
	"./br.js": 386,
	"./bs": 387,
	"./bs.js": 387,
	"./ca": 388,
	"./ca.js": 388,
	"./cs": 389,
	"./cs.js": 389,
	"./cv": 390,
	"./cv.js": 390,
	"./cy": 391,
	"./cy.js": 391,
	"./da": 392,
	"./da.js": 392,
	"./de": 393,
	"./de-at": 394,
	"./de-at.js": 394,
	"./de-ch": 395,
	"./de-ch.js": 395,
	"./de.js": 393,
	"./dv": 396,
	"./dv.js": 396,
	"./el": 397,
	"./el.js": 397,
	"./en-au": 398,
	"./en-au.js": 398,
	"./en-ca": 399,
	"./en-ca.js": 399,
	"./en-gb": 400,
	"./en-gb.js": 400,
	"./en-ie": 401,
	"./en-ie.js": 401,
	"./en-nz": 402,
	"./en-nz.js": 402,
	"./eo": 403,
	"./eo.js": 403,
	"./es": 404,
	"./es-do": 405,
	"./es-do.js": 405,
	"./es-us": 406,
	"./es-us.js": 406,
	"./es.js": 404,
	"./et": 407,
	"./et.js": 407,
	"./eu": 408,
	"./eu.js": 408,
	"./fa": 409,
	"./fa.js": 409,
	"./fi": 410,
	"./fi.js": 410,
	"./fo": 411,
	"./fo.js": 411,
	"./fr": 412,
	"./fr-ca": 413,
	"./fr-ca.js": 413,
	"./fr-ch": 414,
	"./fr-ch.js": 414,
	"./fr.js": 412,
	"./fy": 415,
	"./fy.js": 415,
	"./gd": 416,
	"./gd.js": 416,
	"./gl": 417,
	"./gl.js": 417,
	"./gom-latn": 418,
	"./gom-latn.js": 418,
	"./gu": 419,
	"./gu.js": 419,
	"./he": 420,
	"./he.js": 420,
	"./hi": 421,
	"./hi.js": 421,
	"./hr": 422,
	"./hr.js": 422,
	"./hu": 423,
	"./hu.js": 423,
	"./hy-am": 424,
	"./hy-am.js": 424,
	"./id": 425,
	"./id.js": 425,
	"./is": 426,
	"./is.js": 426,
	"./it": 427,
	"./it.js": 427,
	"./ja": 428,
	"./ja.js": 428,
	"./jv": 429,
	"./jv.js": 429,
	"./ka": 430,
	"./ka.js": 430,
	"./kk": 431,
	"./kk.js": 431,
	"./km": 432,
	"./km.js": 432,
	"./kn": 433,
	"./kn.js": 433,
	"./ko": 434,
	"./ko.js": 434,
	"./ky": 435,
	"./ky.js": 435,
	"./lb": 436,
	"./lb.js": 436,
	"./lo": 437,
	"./lo.js": 437,
	"./lt": 438,
	"./lt.js": 438,
	"./lv": 439,
	"./lv.js": 439,
	"./me": 440,
	"./me.js": 440,
	"./mi": 441,
	"./mi.js": 441,
	"./mk": 442,
	"./mk.js": 442,
	"./ml": 443,
	"./ml.js": 443,
	"./mr": 444,
	"./mr.js": 444,
	"./ms": 445,
	"./ms-my": 446,
	"./ms-my.js": 446,
	"./ms.js": 445,
	"./my": 447,
	"./my.js": 447,
	"./nb": 448,
	"./nb.js": 448,
	"./ne": 449,
	"./ne.js": 449,
	"./nl": 450,
	"./nl-be": 451,
	"./nl-be.js": 451,
	"./nl.js": 450,
	"./nn": 452,
	"./nn.js": 452,
	"./pa-in": 453,
	"./pa-in.js": 453,
	"./pl": 454,
	"./pl.js": 454,
	"./pt": 455,
	"./pt-br": 456,
	"./pt-br.js": 456,
	"./pt.js": 455,
	"./ro": 457,
	"./ro.js": 457,
	"./ru": 458,
	"./ru.js": 458,
	"./sd": 459,
	"./sd.js": 459,
	"./se": 460,
	"./se.js": 460,
	"./si": 461,
	"./si.js": 461,
	"./sk": 462,
	"./sk.js": 462,
	"./sl": 463,
	"./sl.js": 463,
	"./sq": 464,
	"./sq.js": 464,
	"./sr": 465,
	"./sr-cyrl": 466,
	"./sr-cyrl.js": 466,
	"./sr.js": 465,
	"./ss": 467,
	"./ss.js": 467,
	"./sv": 468,
	"./sv.js": 468,
	"./sw": 469,
	"./sw.js": 469,
	"./ta": 470,
	"./ta.js": 470,
	"./te": 471,
	"./te.js": 471,
	"./tet": 472,
	"./tet.js": 472,
	"./th": 473,
	"./th.js": 473,
	"./tl-ph": 474,
	"./tl-ph.js": 474,
	"./tlh": 475,
	"./tlh.js": 475,
	"./tr": 476,
	"./tr.js": 476,
	"./tzl": 477,
	"./tzl.js": 477,
	"./tzm": 478,
	"./tzm-latn": 479,
	"./tzm-latn.js": 479,
	"./tzm.js": 478,
	"./uk": 480,
	"./uk.js": 480,
	"./ur": 481,
	"./ur.js": 481,
	"./uz": 482,
	"./uz-latn": 483,
	"./uz-latn.js": 483,
	"./uz.js": 482,
	"./vi": 484,
	"./vi.js": 484,
	"./x-pseudo": 485,
	"./x-pseudo.js": 485,
	"./yo": 486,
	"./yo.js": 486,
	"./zh-cn": 487,
	"./zh-cn.js": 487,
	"./zh-hk": 488,
	"./zh-hk.js": 488,
	"./zh-tw": 489,
	"./zh-tw.js": 489
};
function webpackContext(req) {
	return __webpack_require__(webpackContextResolve(req));
};
function webpackContextResolve(req) {
	var id = map[req];
	if(!(id + 1)) // check for number or string
		throw new Error("Cannot find module '" + req + "'.");
	return id;
};
webpackContext.keys = function webpackContextKeys() {
	return Object.keys(map);
};
webpackContext.resolve = webpackContextResolve;
module.exports = webpackContext;
webpackContext.id = 752;

/***/ }),

/***/ 753:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return OrderByPipe; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_lodash__ = __webpack_require__(754);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_lodash___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_lodash__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};


/**
 * Generated class for the OrderByPipe pipe.
 *
 * See https://angular.io/api/core/Pipe for more info on Angular Pipes.
 */
var OrderByPipe = (function () {
    function OrderByPipe() {
    }
    /**
     * Takes a value and makes it lowercase.
     */
    OrderByPipe.prototype.transform = function (array, args) {
        return __WEBPACK_IMPORTED_MODULE_1_lodash__["sortBy"](array, args);
    };
    return OrderByPipe;
}());
OrderByPipe = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Pipe"])({
        name: 'orderBy',
    })
], OrderByPipe);

//# sourceMappingURL=order-by.js.map

/***/ }),

/***/ 756:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return FIREBASE_CONFIG; });
var FIREBASE_CONFIG = {
    apiKey: "AIzaSyBlfHGlLBiujfjS7hS1gsplRTj_Vw-RJlU",
    authDomain: "gedsys-8e06b.firebaseapp.com",
    databaseURL: "https://gedsys-8e06b.firebaseio.com",
    projectId: "gedsys-8e06b",
    storageBucket: "gedsys-8e06b.appspot.com",
    messagingSenderId: "924324551571"
};
//# sourceMappingURL=app.firebase.config.js.map

/***/ }),

/***/ 757:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ComponentsModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__progress_bar_progress_bar__ = __webpack_require__(758);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};


var ComponentsModule = (function () {
    function ComponentsModule() {
    }
    return ComponentsModule;
}());
ComponentsModule = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["NgModule"])({
        declarations: [__WEBPACK_IMPORTED_MODULE_1__progress_bar_progress_bar__["a" /* ProgressBarComponent */]],
        imports: [],
        exports: [__WEBPACK_IMPORTED_MODULE_1__progress_bar_progress_bar__["a" /* ProgressBarComponent */]]
    })
], ComponentsModule);

//# sourceMappingURL=components.module.js.map

/***/ }),

/***/ 758:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ProgressBarComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var ProgressBarComponent = (function () {
    function ProgressBarComponent() {
    }
    return ProgressBarComponent;
}());
__decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])('progress'),
    __metadata("design:type", Object)
], ProgressBarComponent.prototype, "progress", void 0);
ProgressBarComponent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'progress-bar',template:/*ion-inline-start:"/home/alejandrochvs/Documents/GEDSYS/src/components/progress-bar/progress-bar.html"*/'<div class="progress-outer">\n  <div class="progress-inner" [style.width]="progress <= 100 ? progress : 100 + \'%\'">\n    {{progress <= 100 ? progress : 100}}%\n  </div>\n</div>'/*ion-inline-end:"/home/alejandrochvs/Documents/GEDSYS/src/components/progress-bar/progress-bar.html"*/
    }),
    __metadata("design:paramtypes", [])
], ProgressBarComponent);

//# sourceMappingURL=progress-bar.js.map

/***/ }),

/***/ 90:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AuthServiceProvider; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_map__ = __webpack_require__(41);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_map___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_map__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_angularfire2_auth__ = __webpack_require__(306);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__shared_gedsys_api_service__ = __webpack_require__(40);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : new P(function (resolve) { resolve(result.value); }).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (_) try {
            if (f = 1, y && (t = y[op[0] & 2 ? "return" : op[0] ? "throw" : "next"]) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [0, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};




var AuthServiceProvider = (function () {
    function AuthServiceProvider(dbService, fireAuth) {
        this.dbService = dbService;
        this.fireAuth = fireAuth;
    }
    AuthServiceProvider.prototype.auth = function (callback) {
        var _this = this;
        this.fireAuth.authState.subscribe(function (data) {
            if (data && data.email && data.uid) {
                _this.dbService.getProfile(data.uid).then(function () {
                    _this.dbService.getNotifications(data.uid).then(function () {
                        _this.dbService.getDocuments(data.uid).then(function () {
                            return callback(null, null);
                        });
                    });
                });
            }
            else {
                return callback('User not found', null);
            }
        });
    };
    AuthServiceProvider.prototype.login = function (user) {
        return __awaiter(this, void 0, void 0, function () {
            var e_1;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        _a.trys.push([0, 2, , 3]);
                        return [4 /*yield*/, this.fireAuth.auth.signInWithEmailAndPassword(user.email, user.password)];
                    case 1:
                        _a.sent();
                        this.auth(function (err) {
                            if (err) {
                                return err;
                            }
                        });
                        return [3 /*break*/, 3];
                    case 2:
                        e_1 = _a.sent();
                        return [2 /*return*/, e_1];
                    case 3: return [2 /*return*/];
                }
            });
        });
    };
    AuthServiceProvider.prototype.logout = function () {
        return __awaiter(this, void 0, void 0, function () {
            var e_2;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        _a.trys.push([0, 2, , 3]);
                        return [4 /*yield*/, this.fireAuth.auth.signOut()];
                    case 1: return [2 /*return*/, _a.sent()];
                    case 2:
                        e_2 = _a.sent();
                        console.error(e_2);
                        return [3 /*break*/, 3];
                    case 3: return [2 /*return*/];
                }
            });
        });
    };
    AuthServiceProvider.prototype.register = function (user, profile) {
        return __awaiter(this, void 0, void 0, function () {
            var result, e_3;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        _a.trys.push([0, 2, , 3]);
                        return [4 /*yield*/, this.fireAuth.auth.createUserWithEmailAndPassword(user.email, user.password)];
                    case 1:
                        result = _a.sent();
                        return [2 /*return*/, this.dbService.postProfile(result.uid, profile)];
                    case 2:
                        e_3 = _a.sent();
                        return [2 /*return*/, e_3];
                    case 3: return [2 /*return*/];
                }
            });
        });
    };
    return AuthServiceProvider;
}());
AuthServiceProvider = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_3__shared_gedsys_api_service__["a" /* GedsysApiService */],
        __WEBPACK_IMPORTED_MODULE_2_angularfire2_auth__["a" /* AngularFireAuth */]])
], AuthServiceProvider);

//# sourceMappingURL=auth-service.js.map

/***/ }),

/***/ 97:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return NotificationDetailPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var NotificationDetailPage = (function () {
    function NotificationDetailPage(navCtrl, navParams) {
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.notification = this.navParams.data;
    }
    NotificationDetailPage.prototype.checkColor = function (notification) {
        var now = new Date().getTime();
        if (notification.date.max - now < 0) {
            return 'dark';
        }
        else if (notification.date.max - now < 86400000) {
            return 'danger';
        }
    };
    return NotificationDetailPage;
}());
NotificationDetailPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-notification-detail',template:/*ion-inline-start:"/home/alejandrochvs/Documents/GEDSYS/src/pages/notification-detail/notification-detail.html"*/'<!--\n  Generated template for the NotificationDetailPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header>\n\n  <ion-navbar color="primary">\n    <ion-title>{{notification.title}}</ion-title>\n  </ion-navbar>\n\n</ion-header>\n\n\n<ion-content>\n  <ion-list>\n    <ion-item><ion-badge [color]="checkColor(notification)">{{notification.date.max | moment}}</ion-badge><ion-chip item-start [color]="notification.archived ? \'dark\':\'primary\'"><ion-icon name="notifications"></ion-icon></ion-chip></ion-item>\n  </ion-list>\n\n\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/GEDSYS/src/pages/notification-detail/notification-detail.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], NotificationDetailPage);

//# sourceMappingURL=notification-detail.js.map

/***/ }),

/***/ 98:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DocumentReplyPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var DocumentReplyPage = (function () {
    function DocumentReplyPage(viewCtrl, navCtrl, navParams) {
        this.viewCtrl = viewCtrl;
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.data = navParams.data;
    }
    DocumentReplyPage.prototype.close = function () {
        this.viewCtrl.dismiss();
    };
    return DocumentReplyPage;
}());
DocumentReplyPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-document-reply',template:/*ion-inline-start:"/home/alejandrochvs/Documents/GEDSYS/src/pages/document-reply/document-reply.html"*/'<ion-header>\n  <ion-navbar color="primary">\n    <ion-title>{{data.title}}</ion-title>\n    <ion-buttons padding-left left>\n      <button ion-button icon-only icon-left (click)="close()">\n        <ion-icon name="close"></ion-icon>\n      </button>\n    </ion-buttons>\n  </ion-navbar>\n\n</ion-header>\n\n\n<ion-content padding>\n  <ion-input placeholder="Search" [(ngModel)]="data.receptor"></ion-input>\n  <ion-textarea style="padding-left: 15px;" [placeholder]="\'Reply to \' + (data.receptor || \'...\')"></ion-textarea>\n  <ion-buttons right padding-right>\n    <button ion-button small item-end margin-top (click)="close()">Send</button>\n  </ion-buttons>\n\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/GEDSYS/src/pages/document-reply/document-reply.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["w" /* ViewController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], DocumentReplyPage);

//# sourceMappingURL=document-reply.js.map

/***/ })

},[492]);
//# sourceMappingURL=main.js.map