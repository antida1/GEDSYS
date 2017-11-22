webpackJsonp([14],{

/***/ 100:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DocumentForwardPage; });
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


var DocumentForwardPage = (function () {
    function DocumentForwardPage(viewCtrl, navCtrl, navParams) {
        this.viewCtrl = viewCtrl;
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.data = navParams.data;
    }
    DocumentForwardPage.prototype.close = function () {
        this.viewCtrl.dismiss();
    };
    return DocumentForwardPage;
}());
DocumentForwardPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-document-forward',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/document-forward/document-forward.html"*/'<ion-header>\n  <ion-navbar color="primary">\n    <ion-title>{{data.title}}</ion-title>\n    <ion-buttons padding-left left>\n      <button ion-button icon-only icon-left (click)="close()">\n        <ion-icon name="close"></ion-icon>\n      </button>\n    </ion-buttons>\n  </ion-navbar>\n\n</ion-header>\n\n\n<ion-content padding>\n  <ion-input placeholder="Search" [(ngModel)]="data.receptor"></ion-input>\n  <ion-textarea style="padding-left: 15px;" [placeholder]="\'Forward to \' + (data.receptor || \'...\')"></ion-textarea>\n  <ion-buttons right padding-right>\n    <button ion-button small item-end margin-top (click)="close()">Send</button>\n  </ion-buttons>\n\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/document-forward/document-forward.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["w" /* ViewController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], DocumentForwardPage);

//# sourceMappingURL=document-forward.js.map

/***/ }),

/***/ 173:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppConfigPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__shared_gedsys_api_service__ = __webpack_require__(64);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__providers_data_data__ = __webpack_require__(27);
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
        selector: 'page-app-config',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/app-config/app-config.html"*/'<ion-content>\n    <ion-list class="outer-content" *ngFor="let data of data">\n        <ion-item-divider color="primary">\n            {{data.title}}\n        </ion-item-divider>\n        <ng-container *ngFor="let option of data.options" [ngSwitch]="option.type">\n            <ion-list-header *ngSwitchCase="\'range\'" color="primary">\n                {{option.title}}\n                <ion-badge item-end color="light">{{option.data}}%</ion-badge>\n            </ion-list-header>\n            <ion-item *ngIf="option.type != \'radio-group\'">\n                <ion-icon *ngIf="option.type != \'range\'" [name]="option.icon" item-start color="primary"></ion-icon>\n                <ion-label color="primary" *ngIf="option.type != \'range\'">{{option.title}}</ion-label>\n                <ion-toggle *ngSwitchCase="\'toggle\'" [(ngModel)]="option.data"></ion-toggle>\n                <ion-input *ngSwitchCase="\'input\'" [(ngModel)]="option.data"></ion-input>\n                <ion-textarea *ngSwitchCase="\'textarea\'" [(ngModel)]="option.data"></ion-textarea>\n                <ion-range *ngSwitchCase="\'range\'" [(ngModel)]="option.data"></ion-range>\n                <a ion-button outline item-end [href]="option.link" *ngSwitchCase="\'button\'">{{option.btnTitle}}</a>\n            </ion-item>\n            <ion-list *ngSwitchCase="\'radio-group\'" radio-group [(ngModel)]="option.data">\n                <ion-list-header style="margin-top: 0;">\n                    {{option.title}}\n                </ion-list-header>\n                <ng-container *ngFor="let radio of option.group">\n                    <ion-item>\n                        <ion-label color="dark">{{radio.name}}</ion-label>\n                        <ion-radio [value]="radio.name" checked></ion-radio>\n                    </ion-item>\n                </ng-container>\n            </ion-list>\n        </ng-container>\n    </ion-list>\n    <ion-item no-lines>\n        <ion-note>\n            App last updated {{data[0].last_updated | moment}}\n        </ion-note>\n    </ion-item>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/app-config/app-config.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2__shared_gedsys_api_service__["a" /* GedsysApiService */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* Events */],
        __WEBPACK_IMPORTED_MODULE_3__providers_data_data__["a" /* DataProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], AppConfigPage);

//# sourceMappingURL=app-config.js.map

/***/ }),

/***/ 174:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return CalendarPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__notification_detail_notification_detail__ = __webpack_require__(98);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__providers_data_data__ = __webpack_require__(27);
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
        this.events = [];
        this.dataProvider.notifications.map(function (notification) {
            _this.events.push({
                start: new Date(notification.date.max),
                title: notification.title,
                color: {
                    primary: _this.checkColor(notification),
                    secondary: 'white'
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
    CalendarPage.prototype.nextMonth = function () {
        return this.date = new Date(this.date.setMonth(this.date.getMonth() + 1));
    };
    CalendarPage.prototype.previousMonth = function () {
        return this.date = new Date(this.date.setMonth(this.date.getMonth() - 1));
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
    CalendarPage.prototype.viewOnDay = function () {
        var offset = document.getElementById('divider').offsetTop;
        return this.content.scrollTo(0, offset, 1000);
    };
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
    CalendarPage.prototype.checkColor = function (notification) {
        var now = new Date().getTime();
        if ((notification.date.max - now) < 0) {
            return 'black';
        }
        else if ((notification.date.max - now) <= 86400000) {
            return 'lightcoral';
        }
        else if (((notification.date.max - now) < 259200000) && ((notification.date.max - now) > 86400000)) {
            return 'lightsalmon';
        }
        else {
            return 'lightgreen';
        }
    };
    return CalendarPage;
}());
__decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["ViewChild"])(__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["e" /* Content */]),
    __metadata("design:type", __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["e" /* Content */])
], CalendarPage.prototype, "content", void 0);
CalendarPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-calendar',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/calendar/calendar.html"*/'<ion-header [style.max-height]="hiddenHeader ? \'0vh\': \'10vh\'">\n    <ion-navbar color="primary">\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>Calendar</ion-title>\n    </ion-navbar>\n</ion-header>\n\n\n<ion-content (ionScroll)="contentScroll($event)" [class.full]="hiddenHeader">\n    <ion-item-divider text-center color="primary">\n        <button ion-button item-start outline color="light" (click)="previousMonth()"><ion-icon name="arrow-back"></ion-icon></button>\n        <h2>\n            {{date | calendarDate:(\'month\' + \'ViewTitle\'):\'en\'}}\n        </h2>\n        <button ion-button item-end outline color="light" (click)="nextMonth()"><ion-icon name="arrow-forward"></ion-icon></button>\n    </ion-item-divider>\n    <mwl-calendar-month-view\n            [activeDayIsOpen]="activeDayIsOpen"\n            [excludeDays]="[0,6]"\n            (dayClicked)="changeDay($event)"\n            [viewDate]="date"\n            (beforeViewRender)="beforeMonthViewRender($event)"\n            (eventClicked)="loadNotificationDetail($event)"\n            [events]="events">\n    </mwl-calendar-month-view>\n    <ion-item-divider (click)="viewOnDay()" id="divider" color="primary">\n        {{date | calendarDate:(\'day\' + \'ViewTitle\'):\'en\'}}\n    </ion-item-divider>\n    <mwl-calendar-day-view\n            [dayStartHour]="6"\n            [dayEndHour]="18"\n            [viewDate]="date"\n            [hourSegments]="2"\n            [eventWidth]="125"\n            (eventClicked)="loadNotificationDetail($event)"\n            [events]="events">\n    </mwl-calendar-day-view>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/calendar/calendar.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_3__providers_data_data__["a" /* DataProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], CalendarPage);

//# sourceMappingURL=calendar.js.map

/***/ }),

/***/ 175:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ConfigPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__user_config_user_config__ = __webpack_require__(176);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__app_config_app_config__ = __webpack_require__(173);
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
        selector: 'page-config',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/config/config.html"*/'<!--\n  Generated template for the ConfigPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header>\n    <ion-navbar color="primary">\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>Configuration</ion-title>\n        <ion-buttons end>\n            <button ion-button (click)="updateInfo()">Save</button>\n        </ion-buttons>\n    </ion-navbar>\n</ion-header>\n\n\n<ion-content>\n    <super-tabs (tabSelect)="listenTabs($event)" toolbarBackground="primary" toolbarColor="light" indicatorColor="light">\n        <super-tab [root]="page1" title="User" icon="person"></super-tab>\n        <super-tab [root]="page2" title="Application" icon="cog"></super-tab>\n    </super-tabs>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/config/config.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* Events */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], ConfigPage);

//# sourceMappingURL=config.js.map

/***/ }),

/***/ 176:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserConfigPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__shared_gedsys_api_service__ = __webpack_require__(64);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__providers_data_data__ = __webpack_require__(27);
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
    function UserConfigPage(/* tslint:disable */ dataProvider, /* tslint:enable */ events, service, navCtrl, navParams) {
        var _this = this;
        this.dataProvider = dataProvider;
        this.events = events;
        this.service = service;
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.user = navParams.data;
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
        selector: 'page-user-config',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/user-config/user-config.html"*/'<ion-content>\n    <ion-grid no-padding>\n        <ion-row class="user-img">\n            <ion-col col>\n                <img *ngIf="dataProvider.profile.img" style="width: 100%" [src]="dataProvider.profile.img"/>\n                <ion-icon col-12 class="not-img" *ngIf="!dataProvider.profile.img" color="primary" name="person"></ion-icon>\n                <ion-fab bottom right>\n                    <button ion-fab color="primary">\n                        <ion-icon name="camera"></ion-icon>\n                    </button>\n                </ion-fab>\n            </ion-col>\n        </ion-row>\n        <ion-row>\n            <ion-col col>\n                <ion-card-header>\n                    <ion-card-title class="profile-info">\n                        <h1>\n                            <ion-icon style="display: inline" name="brush" color="primary"></ion-icon>\n                            <ion-input style="display: inline" type="text" [(ngModel)]="dataProvider.profile.name"></ion-input>\n                        </h1>\n                        <h3>\n                            <ion-icon style="display: inline" name="brush" color="primary"></ion-icon>\n                            <ion-input style="display: inline" type="text" [(ngModel)]="dataProvider.profile.role"></ion-input>\n                        </h3>\n                        <ion-note>\n                            <p style="display: inline"> @</p>\n                            <ion-input readonly style="display: inline" type="text" [(ngModel)]="dataProvider.profile.username">\n                            </ion-input>\n                        </ion-note>\n                    </ion-card-title>\n                </ion-card-header>\n            </ion-col>\n        </ion-row>\n    </ion-grid>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/user-config/user-config.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_3__providers_data_data__["a" /* DataProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* Events */],
        __WEBPACK_IMPORTED_MODULE_2__shared_gedsys_api_service__["a" /* GedsysApiService */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], UserConfigPage);

//# sourceMappingURL=user-config.js.map

/***/ }),

/***/ 177:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DocumentDetailPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__providers_variables_variables__ = __webpack_require__(48);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__providers_data_data__ = __webpack_require__(27);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__document_reply_document_reply__ = __webpack_require__(99);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__document_forward_document_forward__ = __webpack_require__(100);
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
            cssClass: 'toast-danger',
            duration: 3000
        });
        this.docLoader.dismiss();
        return errToast.present();
    };
    DocumentDetailPage.prototype.remove = function (document) {
        var _this = this;
        var alert = this.alertCtrl.create({
            title: 'Confirm',
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
                        var toast = _this.variables.toastTemplate({
                            message: "Successfully removed " + document.title,
                            cssClass: 'toast-success',
                            position: 'bottom'
                        });
                        _this.dataProvider.documents.splice(_this.dataProvider.documents.indexOf(document), 1);
                        toast.present();
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
        var profileModal = this.modalCtrl.create(__WEBPACK_IMPORTED_MODULE_5__document_forward_document_forward__["a" /* DocumentForwardPage */], document);
        profileModal.present();
    };
    return DocumentDetailPage;
}());
DocumentDetailPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-document-detail',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/document-detail/document-detail.html"*/'<!--\n  Generated template for the DocumentDetailPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header>\n    <ion-navbar color="primary">\n        <ion-title>{{data.title}}</ion-title>\n    </ion-navbar>\n\n</ion-header>\n<ion-content>\n    <progress-bar [class.progress-hidden]="!loading" [hidden]="!progressPercent"\n                  [progress]="progressPercent"></progress-bar>\n    <ion-card>\n        <ion-fab top right>\n            <button ion-fab mini><ion-icon name="more"></ion-icon></button>\n            <ion-fab-list>\n                <button (click)="reply(data)" color="primary" ion-fab><ion-icon name="undo"></ion-icon></button>\n                <button (click)="forward(data)" color="primary" ion-fab><ion-icon name="redo"></ion-icon></button>\n                <a [href]="data.url" target="_blank" color="primary" ion-fab><ion-icon name="open"></ion-icon></a>\n                <button (click)="remove(data)" color="danger" ion-fab><ion-icon name="trash"></ion-icon></button>\n            </ion-fab-list>\n        </ion-fab>\n        <ion-item>\n            <ion-avatar item-start>\n                <img src="https://organicthemes.com/demo/profile/files/2012/12/profile_img.png">\n            </ion-avatar>\n            <h2 color="primary">Receptor</h2>\n            <p>Subject</p>\n        </ion-item>\n        <ion-card-content no-padding>\n            <pdf-viewer [hidden]=\'loading\' [page]="1"\n                        [stick-to-page]="true"\n                        [show-all]="false"\n                        [render-text]="true"\n                        (on-progress)="progress($event)"\n                        (after-load-complete)="complete($event)"\n                        (error)="err($event)"\n                        [zoom]=[1.0]\n                        [original-size]="false"\n                        [src]="data.url"></pdf-viewer>\n        </ion-card-content>\n        <ion-row padding-horizontal>\n            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab, aliquid, deleniti deserunt dicta error illo\n                ipsam libero minima molestiae, nihil praesentium recusandae sed voluptate. Aspernatur atque cum ducimus\n                magnam magni?</p>\n        </ion-row>\n        <ion-item *ngIf="pdfInfo">\n            <span item-left color="primary">{{pdfInfo.numPages}} {{pdfInfo.numPages == 1 ? \'page\':\'pages\'}}</span>\n            <a ion-button icon-left clear item-end [href]="data.url" target="_blank">\n                <ion-icon name="open"></ion-icon>\n                Open\n            </a>\n        </ion-item>\n    </ion-card>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/document-detail/document-detail.html"*/,
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

/***/ 178:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DocumentsPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__document_detail_document_detail__ = __webpack_require__(177);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__providers_data_data__ = __webpack_require__(27);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__document_reply_document_reply__ = __webpack_require__(99);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__providers_variables_variables__ = __webpack_require__(48);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__document_forward_document_forward__ = __webpack_require__(100);
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
    function DocumentsPage(/* tslint:disable */ variables, /* tslint:enable */ alertCtrl, actionSheetCtrl, modalCtrl, dataProvider, navCtrl, navParams) {
        this.variables = variables;
        this.alertCtrl = alertCtrl;
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
                    text: 'Remove',
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
        var _this = this;
        var confirm = this.alertCtrl.create({
            title: 'Confirm',
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
                        var loading = _this.variables.loadingTemplate(null);
                        loading.present().then(function () {
                            var toast = _this.variables.toastTemplate({
                                message: "Successfully removed " + document.title,
                                cssClass: 'toast-success',
                                position: 'bottom'
                            });
                            _this.dataProvider.documents.splice(_this.dataProvider.documents.indexOf(document), 1);
                            loading.dismiss().then(function () {
                                toast.present().then(function () { return; });
                            });
                        });
                    }
                }
            ]
        });
        return confirm.present();
    };
    DocumentsPage.prototype.reply = function (document) {
        var profileModal = this.modalCtrl.create(__WEBPACK_IMPORTED_MODULE_4__document_reply_document_reply__["a" /* DocumentReplyPage */], document);
        return profileModal.present();
    };
    DocumentsPage.prototype.forward = function (document) {
        var profileModal = this.modalCtrl.create(__WEBPACK_IMPORTED_MODULE_6__document_forward_document_forward__["a" /* DocumentForwardPage */], document);
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
        selector: 'page-documents',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/documents/documents.html"*/'<!--\n  Generated template for the DocumentsPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header [style.max-height]="hiddenHeader ? \'0vh\': \'10vh\'">\n    <ion-navbar primary color="primary">\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>Documents</ion-title>\n    </ion-navbar>\n</ion-header>\n\n\n<ion-content (ionScroll)="contentScroll($event)" (ionScrollEnd)="contentScroll($event)"\n             [class.full]="hiddenHeader">\n    <ion-toolbar *ngIf="dataProvider.documents.length > 0" color="primary">\n        <ion-searchbar (ionInput)="filter($event)"></ion-searchbar>\n    </ion-toolbar>\n    <ion-refresher (ionRefresh)="refresh($event)">\n        <ion-refresher-content\n                [pullingIcon]="variables.refresherTemplate().pullingIcon"\n                [pullingText]="variables.refresherTemplate({title : \'documents\'}).pullingText"\n                [refreshingSpinner]="variables.refresherTemplate().refreshingSpinner"\n                [refreshingText]="variables.refresherTemplate({title : \'documents\'}).refreshingText">\n        </ion-refresher-content>\n    </ion-refresher>\n    <ion-item-divider color="primary" *ngIf="filterStr.title">Search for: {{filterStr.title}}</ion-item-divider>\n    <ion-item no-lines *ngIf="(dataProvider.documents | filterBy: filterStr | orderBy: \'title\')?.length == 0">Any document found</ion-item>\n    <ion-row>\n        <ion-col col-12 col-sm-6 col-lg-4 col-xl-3 *ngFor="let pdf of dataProvider.documents | filterBy: filterStr | orderBy: \'title\'">\n            <ion-card>\n                <ion-item>\n                    <ion-avatar item-start>\n                        <img src="http://www.xsjjys.com/data/out/96/WHDQ-512397052.jpg">\n                    </ion-avatar>\n                    <h2>{{pdf.title}}</h2>\n                    <p>For: October 22, 2017</p>\n                </ion-item>\n                <ion-row>\n                    <ion-col>\n                        <button ion-button icon-left clear small (click)="reply(pdf)">\n                            <ion-icon name="undo"></ion-icon>\n                            <div>Reply</div>\n                        </button>\n                    </ion-col>\n                    <ion-col>\n                        <button ion-button icon-left clear small (click)="viewDoc(pdf)">\n                            <ion-icon name="eye"></ion-icon>\n                            <div>View</div>\n                        </button>\n                    </ion-col>\n                    <ion-col center text-center>\n                        <button ion-button icon-left clear small (click)="more(pdf)">\n                            <ion-icon name="more"></ion-icon>\n                        </button>\n                    </ion-col>\n                </ion-row>\n            </ion-card>\n        </ion-col>\n    </ion-row>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/documents/documents.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_5__providers_variables_variables__["a" /* VariablesProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["b" /* AlertController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* ActionSheetController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["o" /* ModalController */],
        __WEBPACK_IMPORTED_MODULE_3__providers_data_data__["a" /* DataProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], DocumentsPage);

//# sourceMappingURL=documents.js.map

/***/ }),

/***/ 179:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return LoginPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__providers_auth_service_auth_service__ = __webpack_require__(90);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__providers_variables_variables__ = __webpack_require__(48);
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





var LoginPage = (function () {
    /*    loadRegister() {
            return this.navCtrl.push(RegisterPage);
        }*/
    function LoginPage(formBuilder, variables, authService, events, navCtrl, navParams) {
        this.formBuilder = formBuilder;
        this.variables = variables;
        this.authService = authService;
        this.events = events;
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.user = {};
        this.formGroup = this.formBuilder.group({
            email: ['', __WEBPACK_IMPORTED_MODULE_4__angular_forms__["f" /* Validators */].required],
            password: ['', __WEBPACK_IMPORTED_MODULE_4__angular_forms__["f" /* Validators */].required]
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
                    cssClass: 'toast-danger',
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
    return LoginPage;
}());
LoginPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-login',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/login/login.html"*/'<ion-header>\n    <ion-navbar color="primary">\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>Login</ion-title>\n    </ion-navbar>\n</ion-header>\n<ion-content class="login-content" padding>\n    <ion-row top class="logo-row">\n        <ion-col col-10 offset-1 col-sm-4>\n            <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" id="Capa_1" x="0px" y="0px" viewBox="0 0 612 612" style="enable-background:new 0 0 612 612;" xml:space="preserve">\n<g>\n	<g id="_x36__30_">\n		<g>\n			<path d="M331.685,425.378c-7.478,7.479-7.478,19.584,0,27.043c7.479,7.478,19.584,7.478,27.043,0l131.943-131.962     c3.979-3.979,5.681-9.276,5.412-14.479c0.269-5.221-1.434-10.499-5.412-14.477L358.728,159.56     c-7.459-7.478-19.584-7.478-27.043,0c-7.478,7.478-7.478,19.584,0,27.042l100.272,100.272H19.125C8.568,286.875,0,295.443,0,306     c0,10.557,8.568,19.125,19.125,19.125h412.832L331.685,425.378z M535.5,38.25H153c-42.247,0-76.5,34.253-76.5,76.5v76.5h38.25     v-76.5c0-21.114,17.117-38.25,38.25-38.25h382.5c21.133,0,38.25,17.136,38.25,38.25v382.5c0,21.114-17.117,38.25-38.25,38.25H153     c-21.133,0-38.25-17.117-38.25-38.25v-76.5H76.5v76.5c0,42.247,34.253,76.5,76.5,76.5h382.5c42.247,0,76.5-34.253,76.5-76.5     v-382.5C612,72.503,577.747,38.25,535.5,38.25z"/>\n		</g>\n	</g>\n</g>\n<g>\n</g>\n<g>\n</g>\n<g>\n</g>\n<g>\n</g>\n<g>\n</g>\n<g>\n</g>\n<g>\n</g>\n<g>\n</g>\n<g>\n</g>\n<g>\n</g>\n<g>\n</g>\n<g>\n</g>\n<g>\n</g>\n<g>\n</g>\n<g>\n</g>\n</svg>\n        </ion-col>\n        <ion-col col-12 col-sm-6 offset-sm-1 col-md-4 offset-md-2 col-lg-3 offset-lg-2 col-xl-2 offset-xl-3>\n            <div class="login-box" [hidden]="loading">\n                <form [formGroup]="formGroup">\n                    <ion-row center>\n                        <ion-col>\n                            <ion-list no-lines inset>\n                                <ion-item>\n                                    <ion-label floating color="primary">Email</ion-label>\n                                    <ion-input formControlName="email" clearInput type="text"\n                                               [(ngModel)]="user.email"></ion-input>\n                                </ion-item>\n                                <ion-note margin-left item-end padding-left color="danger"\n                                          *ngIf="email.hasError(\'required\') && email.touched">Email required\n                                </ion-note>\n                                <ion-note margin-left item-end padding-left color="danger"\n                                          *ngIf="email.touched && !email.hasError(\'required\') && email.invalid">Wrong\n                                    format\n                                </ion-note>\n                                <ion-item>\n                                    <ion-label floating color="primary">Password</ion-label>\n                                    <ion-input formControlName="password" clearInput type="password"\n                                               [(ngModel)]="user.password"></ion-input>\n                                </ion-item>\n                                <ion-note margin-left item-end padding-left color="danger"\n                                          *ngIf="password.hasError(\'required\') && password.touched">Password required\n                                </ion-note>\n                                <ion-note margin-left item-end padding-left color="danger"\n                                          *ngIf="password.touched && !password.hasError(\'required\') && password.invalid">\n                                    Wrong format\n                                </ion-note>\n                            </ion-list>\n                        </ion-col>\n                    </ion-row>\n                    <ion-row bottom>\n                        <ion-col class="signup-col">\n                            <button [disabled]="formGroup.invalid" ion-button class="submit-btn" outline block\n                                    type="submit" (click)="login(user)">Login\n                            </button>\n                            <!--<button ion-button class="register-btn" block clear (click)="loadRegister()">Create New\n                                Account\n                            </button>-->\n                        </ion-col>\n                    </ion-row>\n                </form>\n            </div>\n        </ion-col>\n    </ion-row>\n</ion-content>'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/login/login.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_4__angular_forms__["a" /* FormBuilder */],
        __WEBPACK_IMPORTED_MODULE_3__providers_variables_variables__["a" /* VariablesProvider */],
        __WEBPACK_IMPORTED_MODULE_2__providers_auth_service_auth_service__["a" /* AuthServiceProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* Events */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], LoginPage);

//# sourceMappingURL=login.js.map

/***/ }),

/***/ 180:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return NotificationsPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__notification_detail_notification_detail__ = __webpack_require__(98);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__providers_data_data__ = __webpack_require__(27);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__providers_variables_variables__ = __webpack_require__(48);
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
            title: 'Confirm',
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
                                message: "Successfully removed " + notification.title,
                                cssClass: 'toast-success',
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
        var loading = this.variables.loadingTemplate({
            content: this.index ? "Unarchiving..." : "Archiving..."
        });
        loading.present();
        var toast = this.variables.toastTemplate({
            message: this.index ? "Successfully unarchived " + notification.title : "Successfully archived " + notification.title,
            closeButtonText: 'Ok',
            cssClass: 'toast-success',
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
        selector: 'page-notifications',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/notifications/notifications.html"*/'<!--\n  Generated template for the NotificationsPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header [style.max-height]="hiddenHeader ? \'0vh\': \'10vh\'">\n    <ion-navbar color="primary">\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>{{index ? \'Archived\' : \'Notifications\'}}</ion-title>\n    </ion-navbar>\n</ion-header>\n<ion-content (ionScroll)="contentScroll($event)" (ionScrollEnd)="contentScroll($event)" [class.full]="hiddenHeader">\n    <ion-toolbar *ngIf="dataProvider.notifications.length > 0" color="primary">\n        <ion-searchbar [(ngModel)]="filterStr.title"></ion-searchbar>\n    </ion-toolbar>\n    <ion-refresher (ionRefresh)="refresh($event)">\n        <ion-refresher-content\n                [pullingIcon]="variables.refresherTemplate().pullingIcon"\n                [pullingText]="variables.refresherTemplate({title : \'notifications\'}).pullingText"\n                [refreshingSpinner]="variables.refresherTemplate().refreshingSpinner"\n                [refreshingText]="variables.refresherTemplate({title : \'notifications\'}).refreshingText">\n        </ion-refresher-content>\n    </ion-refresher>\n    <ion-list no-lines no-margin no-border>\n        <ion-item-divider color="primary" *ngIf="filterStr.title">Search for: {{filterStr.title}}</ion-item-divider>\n        <ion-item *ngIf="(dataProvider.notifications |filterBy: filterBool | filterBy: filterStr | orderBy: \'date.max\')?.length == 0">\n            {{index ? "No archived notifications" : "No pending notifications"}}\n        </ion-item>\n        <ng-container\n                *ngFor="let notification of dataProvider.notifications |filterBy: filterBool | filterBy: filterStr | orderBy: \'date.max\'">\n            <ion-item-sliding>\n                <ion-item (click)="loadNotificationDetail(notification)">\n                    {{notification.title}}\n                    <ion-badge [color]="checkColor(notification)" item-end>{{notification.date.max | moment}}\n                    </ion-badge>\n                </ion-item>\n                <ion-item-options side="right" (ionSwipe)="archive(notification)">\n                    <button color="danger" ion-button (click)="remove(notification)">Delete</button>\n                    <button ion-button expandable (click)="archive(notification)">Archive</button>\n                </ion-item-options>\n            </ion-item-sliding>\n        </ng-container>\n    </ion-list>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/notifications/notifications.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["b" /* AlertController */],
        __WEBPACK_IMPORTED_MODULE_3__providers_data_data__["a" /* DataProvider */],
        __WEBPACK_IMPORTED_MODULE_4__providers_variables_variables__["a" /* VariablesProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], NotificationsPage);

//# sourceMappingURL=notifications.js.map

/***/ }),

/***/ 181:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return TabsPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__notifications_notifications__ = __webpack_require__(180);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__providers_data_data__ = __webpack_require__(27);
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
    function TabsPage(/* tslint:disable */ dataProvider, /* tslint:enable */ events, navCtrl, navParams) {
        this.dataProvider = dataProvider;
        this.events = events;
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
        selector: 'page-tabs',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/tabs/tabs.html"*/'<!--\n  Generated template for the TabsPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n\n<ion-tabs color="primary">\n  <ion-tab [rootParams]="data" [root]="notificationsView" tabIcon="alert" tabTitle="Notifications"\n           [tabBadge]="getAmount(dataProvider.notifications)" tabBadgeStyle="danger"></ion-tab>\n  <ion-tab [rootParams]="data" [root]="notificationsView" tabIcon="archive" tabTitle="Archived"\n           [tabBadge]="getArchived(dataProvider.notifications)" tabBadgeStyle="light"></ion-tab>\n</ion-tabs>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/tabs/tabs.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_3__providers_data_data__["a" /* DataProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* Events */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], TabsPage);

//# sourceMappingURL=tabs.js.map

/***/ }),

/***/ 188:
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
webpackEmptyAsyncContext.id = 188;

/***/ }),

/***/ 230:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"../pages/app-config/app-config.module": [
		764,
		13
	],
	"../pages/calendar/calendar.module": [
		765,
		12
	],
	"../pages/config/config.module": [
		766,
		11
	],
	"../pages/document-detail/document-detail.module": [
		767,
		10
	],
	"../pages/document-forward/document-forward.module": [
		768,
		9
	],
	"../pages/document-reply/document-reply.module": [
		769,
		8
	],
	"../pages/documents/documents.module": [
		770,
		7
	],
	"../pages/login/login.module": [
		771,
		6
	],
	"../pages/notification-detail/notification-detail.module": [
		772,
		5
	],
	"../pages/notifications/notifications.module": [
		773,
		4
	],
	"../pages/register/register.module": [
		774,
		3
	],
	"../pages/tabs/tabs.module": [
		775,
		2
	],
	"../pages/user-config/user-config.module": [
		776,
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
webpackAsyncContext.id = 230;
module.exports = webpackAsyncContext;

/***/ }),

/***/ 27:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DataProvider; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_map__ = __webpack_require__(40);
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

/***/ 353:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return HomePage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__providers_data_data__ = __webpack_require__(27);
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
    function HomePage(/* tslint:disable */ dataProvider, /* tslint:enable */ events, navParams, navCtrl) {
        this.dataProvider = dataProvider;
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
        selector: 'page-home',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/home/home.html"*/'<ion-header [style.max-height]="hiddenHeader ? \'0vh\': \'10vh\'">\n    <ion-navbar primary color="primary">\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>Dashboard</ion-title>\n        <ion-buttons end *ngIf="!dataProvider.profile">\n            <button ion-button color="primary" menuToggle>Login</button>\n        </ion-buttons>\n    </ion-navbar>\n</ion-header>\n\n<ion-content (ionScroll)="contentScroll($event)" (ionScrollEnd)="contentScroll($event)" [class.full]="hiddenHeader">\n    <ion-row *ngIf="dataProvider.profile">\n        <ion-col col-12 col-sm-6 col-lg-4>\n            <ion-card>\n                <img [src]="dataProvider.profile.img" *ngIf="dataProvider.profile.img">\n                <ion-row *ngIf="!dataProvider.profile.img">\n                    <ion-icon color="primary" col-12 name="contact"></ion-icon>\n                </ion-row>\n                <ion-fab right top>\n                    <button ion-fab (click)="loadSettings()">\n                        <ion-icon name="settings"></ion-icon>\n                    </button>\n                </ion-fab>\n                <ion-item>\n                    <h2>{{dataProvider.profile.name}}</h2>\n                    <p>{{dataProvider.profile.role}}</p>\n                </ion-item>\n                <ion-item>\n                    <button ion-button icon-left clear item-start (click)="loadDocuments()">\n                        <ion-icon name="copy"></ion-icon>\n                        {{dataProvider.documents.length}} Documents\n                    </button>\n                    <button ion-button icon-left clear item-end (click)="loadNotifications()">\n                        <ion-icon name="notifications"></ion-icon>\n                        {{getAmount(dataProvider.notifications)}} Notifications\n                    </button>\n                </ion-item>\n            </ion-card>\n        </ion-col>\n        <ion-col col-12 col-sm-6 col-lg-8>\n            <ion-card>\n                <ion-card-content>\n                    <chart style="display: block;" [options]="chartOptions" type="chart"></chart>\n                </ion-card-content>\n            </ion-card>\n        </ion-col>\n        <ion-col col-12>\n            <ion-card>\n                <ion-card-content>\n                    <chart style="display: block;" [options]="chartOptions1" type="chart"></chart>\n                </ion-card-content>\n            </ion-card>\n        </ion-col>\n    </ion-row>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/home/home.html"*/
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2__providers_data_data__["a" /* DataProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* Events */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */]])
], HomePage);

//# sourceMappingURL=home.js.map

/***/ }),

/***/ 48:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return VariablesProvider; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_map__ = __webpack_require__(40);
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

/***/ 494:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return RegisterPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__providers_auth_service_auth_service__ = __webpack_require__(90);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__providers_variables_variables__ = __webpack_require__(48);
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
        this.formGroup = this.formBuilder.group({
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
                    cssClass: 'toast-danger',
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
        selector: 'page-register',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/register/register.html"*/'<ion-header>\n    <ion-navbar color="primary">\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>Register</ion-title>\n    </ion-navbar>\n</ion-header>\n<ion-content class="login-content" padding>\n    <ion-row top class="logo-row">\n        <ion-col col-10 offset-1 col-sm-4>\n            <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 1000 1000" enable-background="new 0 0 1000 1000" xml:space="preserve">\n<metadata> Svg Vector Icons : http://www.onlinewebfonts.com/icon </metadata>\n<g><path d="M838.1,10H161.9C111.4,10,70,51.3,70,101.9v796.3c0,50.5,41.3,91.9,91.9,91.9h676.1c50.5,0,91.9-41.3,91.9-91.9V101.9C930,51.3,888.6,10,838.1,10z M842.5,905.7H157.6V94.3h684.9V905.7z M254.1,560.5h488.6v61.2H254.1V560.5L254.1,560.5z M254.1,683h488.6v61.3H254.1V683L254.1,683z M407.3,361c50.7,0,91.9-41.1,91.9-91.9c0-50.8-41.2-91.9-91.9-91.9c-50.8,0-91.9,41.1-91.9,91.9S356.5,361,407.3,361z M560.4,438c0-33.7-41.4-61.2-91.9-61.2H346c-50.6,0-91.9,27.6-91.9,61.2v61.3h306.3L560.4,438L560.4,438z"/></g>\n</svg>\n        </ion-col>\n        <ion-col col-12 col-sm-6 offset-sm-1 col-md-4 offset-md-2 col-lg-3 offset-lg-2 col-xl-2 offset-xl-3>\n            <div class="login-box">\n                <form [formGroup]="formGroup">\n                    <ion-row center>\n                        <ion-col>\n                            <ion-list no-lines inset>\n                                <ion-item>\n                                    <ion-label floating color="primary">Username</ion-label>\n                                    <ion-input formControlName="username" type="text"\n                                               [(ngModel)]="profile.username"></ion-input>\n                                </ion-item>\n                                <ion-note margin-left item-end padding-left color="danger"\n                                          *ngIf="username.hasError(\'required\') && username.touched">Username required\n                                </ion-note>\n                                <ion-note margin-left item-end padding-left color="danger"\n                                          *ngIf="username.touched && !username.hasError(\'required\') && username.invalid">\n                                    Wrong format\n                                </ion-note>\n                                <ion-item>\n                                    <ion-label floating color="primary">Name</ion-label>\n                                    <ion-input formControlName="name" type="text"\n                                               [(ngModel)]="profile.name"></ion-input>\n                                </ion-item>\n                                <ion-note margin-left item-end padding-left color="danger"\n                                          *ngIf="name.hasError(\'required\') && name.touched">Name required\n                                </ion-note>\n                                <ion-note margin-left item-end padding-left color="danger"\n                                          *ngIf="name.touched && !name.hasError(\'required\') && name.invalid">Wrong\n                                    format\n                                </ion-note>\n                                <ion-item>\n                                    <ion-label floating color="primary">Last name</ion-label>\n                                    <ion-input formControlName="last_name" type="text"\n                                               [(ngModel)]="profile.last_name"></ion-input>\n                                </ion-item>\n                                <ion-note margin-left item-end padding-left color="danger"\n                                          *ngIf="last_name.hasError(\'required\') && last_name.touched">Last name required\n                                </ion-note>\n                                <ion-note margin-left item-end padding-left color="danger"\n                                          *ngIf="last_name.touched && !last_name.hasError(\'required\') && last_name.invalid">\n                                    Wrong format\n                                </ion-note>\n                                <ion-item>\n                                    <ion-label floating color="primary">Role</ion-label>\n                                    <ion-input formControlName="role" type="text"\n                                               [(ngModel)]="profile.role"></ion-input>\n                                </ion-item>\n                                <ion-note margin-left item-end padding-left color="danger"\n                                          *ngIf="role.hasError(\'required\') && role.touched">Role required\n                                </ion-note>\n                                <ion-note margin-left item-end padding-left color="danger"\n                                          *ngIf="role.touched && !role.hasError(\'required\') && role.invalid">Wrong\n                                    format\n                                </ion-note>\n                                <ion-item>\n                                    <ion-label floating color="primary">Image</ion-label>\n                                    <ion-input formControlName="img" type="text" [(ngModel)]="profile.img"></ion-input>\n                                </ion-item>\n                                <ion-note margin-left item-end padding-left color="danger"\n                                          *ngIf="img.hasError(\'required\') && img.touched">Image required\n                                </ion-note>\n                                <ion-note margin-left item-end padding-left color="danger"\n                                          *ngIf="img.touched && !img.hasError(\'required\') && img.invalid">Wrong format\n                                </ion-note>\n                                <ion-item>\n                                    <ion-label floating color="primary">Email</ion-label>\n                                    <ion-input formControlName="email" type="text" [(ngModel)]="user.email"></ion-input>\n                                </ion-item>\n                                <ion-note margin-left item-end padding-left color="danger"\n                                          *ngIf="email.hasError(\'required\') && email.touched">Email required\n                                </ion-note>\n                                <ion-note margin-left item-end padding-left color="danger"\n                                          *ngIf="email.touched && !email.hasError(\'required\') && email.invalid">Wrong\n                                    format\n                                </ion-note>\n                                <ion-item>\n                                    <ion-label floating color="primary">Password</ion-label>\n                                    <ion-input formControlName="password" color="primary" type="password"\n                                               [(ngModel)]="user.password"></ion-input>\n                                </ion-item>\n                                <ion-note margin-left item-end padding-left color="danger"\n                                          *ngIf="password.hasError(\'required\') && password.touched">Password required\n                                </ion-note>\n                                <ion-note margin-left item-end padding-left color="danger"\n                                          *ngIf="password.touched && !password.hasError(\'required\') && password.invalid">\n                                    Wrong format\n                                </ion-note>\n\n                            </ion-list>\n                        </ion-col>\n                    </ion-row>\n\n                    <ion-row bottom>\n                        <ion-col class="signup-col">\n                            <button [disabled]="formGroup.invalid" ion-button class="submit-btn" outline block\n                                    type="submit"\n                                    (click)="register(user,profile)">Register\n                            </button>\n                        </ion-col>\n                    </ion-row>\n\n                </form>\n            </div>\n        </ion-col>\n    </ion-row>\n\n</ion-content>'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/register/register.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_4__angular_forms__["a" /* FormBuilder */],
        __WEBPACK_IMPORTED_MODULE_3__providers_variables_variables__["a" /* VariablesProvider */],
        __WEBPACK_IMPORTED_MODULE_2__providers_auth_service_auth_service__["a" /* AuthServiceProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], RegisterPage);

//# sourceMappingURL=register.js.map

/***/ }),

/***/ 495:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser_dynamic__ = __webpack_require__(496);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__app_module__ = __webpack_require__(511);


Object(__WEBPACK_IMPORTED_MODULE_0__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_1__app_module__["a" /* AppModule */]);
//# sourceMappingURL=main.js.map

/***/ }),

/***/ 511:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__ = __webpack_require__(32);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__ionic_native_badge__ = __webpack_require__(349);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__app_component__ = __webpack_require__(711);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__pages_login_login__ = __webpack_require__(179);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__pages_home_home__ = __webpack_require__(353);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__pages_tabs_tabs__ = __webpack_require__(181);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__pages_notifications_notifications__ = __webpack_require__(180);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__pages_notification_detail_notification_detail__ = __webpack_require__(98);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__pages_calendar_calendar__ = __webpack_require__(174);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__pages_documents_documents__ = __webpack_require__(178);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__pages_document_detail_document_detail__ = __webpack_require__(177);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__pages_config_config__ = __webpack_require__(175);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14__pages_user_config_user_config__ = __webpack_require__(176);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15__pages_app_config_app_config__ = __webpack_require__(173);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_16__ionic_native_status_bar__ = __webpack_require__(351);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_17__ionic_native_splash_screen__ = __webpack_require__(352);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_18__angular_http__ = __webpack_require__(128);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_19_angular_calendar__ = __webpack_require__(492);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_20_ionic2_super_tabs__ = __webpack_require__(493);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_21_ng2_pdf_viewer__ = __webpack_require__(713);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_21_ng2_pdf_viewer___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_21_ng2_pdf_viewer__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_22_angular2_highcharts__ = __webpack_require__(745);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_22_angular2_highcharts___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_22_angular2_highcharts__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_23_ng2_filter_pipe__ = __webpack_require__(752);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_23_ng2_filter_pipe___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_23_ng2_filter_pipe__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_24__pipes_moment_moment__ = __webpack_require__(754);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_25__pipes_order_by_order_by__ = __webpack_require__(756);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_26_highcharts__ = __webpack_require__(758);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_26_highcharts___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_26_highcharts__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_27__ionic_storage__ = __webpack_require__(231);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_28__pages_register_register__ = __webpack_require__(494);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_29__providers_auth_service_auth_service__ = __webpack_require__(90);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_30_angularfire2__ = __webpack_require__(52);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_31__shared_app_firebase_config__ = __webpack_require__(759);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_32_angularfire2_auth__ = __webpack_require__(307);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_33_angularfire2_database__ = __webpack_require__(232);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_34__shared_gedsys_api_service__ = __webpack_require__(64);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_35__providers_data_data__ = __webpack_require__(27);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_36__pages_document_reply_document_reply__ = __webpack_require__(99);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_37__providers_variables_variables__ = __webpack_require__(48);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_38__components_components_module__ = __webpack_require__(760);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_39__angular_platform_browser_animations__ = __webpack_require__(762);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_40__pages_document_forward_document_forward__ = __webpack_require__(100);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_41__ionic_native_local_notifications__ = __webpack_require__(354);
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
            __WEBPACK_IMPORTED_MODULE_4__app_component__["a" /* MyApp */],
            __WEBPACK_IMPORTED_MODULE_6__pages_home_home__["a" /* HomePage */],
            __WEBPACK_IMPORTED_MODULE_14__pages_user_config_user_config__["a" /* UserConfigPage */],
            __WEBPACK_IMPORTED_MODULE_15__pages_app_config_app_config__["a" /* AppConfigPage */],
            __WEBPACK_IMPORTED_MODULE_21_ng2_pdf_viewer__["PdfViewerComponent"],
            __WEBPACK_IMPORTED_MODULE_12__pages_document_detail_document_detail__["a" /* DocumentDetailPage */],
            __WEBPACK_IMPORTED_MODULE_24__pipes_moment_moment__["a" /* MomentPipe */],
            __WEBPACK_IMPORTED_MODULE_25__pipes_order_by_order_by__["a" /* OrderByPipe */],
            __WEBPACK_IMPORTED_MODULE_28__pages_register_register__["a" /* RegisterPage */],
            __WEBPACK_IMPORTED_MODULE_36__pages_document_reply_document_reply__["a" /* DocumentReplyPage */],
            __WEBPACK_IMPORTED_MODULE_40__pages_document_forward_document_forward__["a" /* DocumentForwardPage */],
            __WEBPACK_IMPORTED_MODULE_8__pages_notifications_notifications__["a" /* NotificationsPage */],
            __WEBPACK_IMPORTED_MODULE_9__pages_notification_detail_notification_detail__["a" /* NotificationDetailPage */],
            __WEBPACK_IMPORTED_MODULE_11__pages_documents_documents__["a" /* DocumentsPage */],
            __WEBPACK_IMPORTED_MODULE_5__pages_login_login__["a" /* LoginPage */],
            __WEBPACK_IMPORTED_MODULE_7__pages_tabs_tabs__["a" /* TabsPage */],
            __WEBPACK_IMPORTED_MODULE_10__pages_calendar_calendar__["a" /* CalendarPage */],
            __WEBPACK_IMPORTED_MODULE_13__pages_config_config__["a" /* ConfigPage */]
        ],
        imports: [
            __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["a" /* BrowserModule */],
            __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["l" /* IonicModule */].forRoot(__WEBPACK_IMPORTED_MODULE_4__app_component__["a" /* MyApp */], {
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
                    { loadChildren: '../pages/document-forward/document-forward.module#DocumentForwardPageModule', name: 'DocumentForwardPage', segment: 'document-forward', priority: 'low', defaultHistory: [] },
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
            __WEBPACK_IMPORTED_MODULE_18__angular_http__["b" /* HttpModule */],
            __WEBPACK_IMPORTED_MODULE_19_angular_calendar__["a" /* CalendarModule */].forRoot(),
            __WEBPACK_IMPORTED_MODULE_20_ionic2_super_tabs__["a" /* SuperTabsModule */].forRoot(),
            __WEBPACK_IMPORTED_MODULE_23_ng2_filter_pipe__["Ng2FilterPipeModule"],
            __WEBPACK_IMPORTED_MODULE_22_angular2_highcharts__["ChartModule"].forRoot(__WEBPACK_IMPORTED_MODULE_26_highcharts__),
            __WEBPACK_IMPORTED_MODULE_27__ionic_storage__["a" /* IonicStorageModule */].forRoot(),
            __WEBPACK_IMPORTED_MODULE_30_angularfire2__["a" /* AngularFireModule */].initializeApp(__WEBPACK_IMPORTED_MODULE_31__shared_app_firebase_config__["a" /* FIREBASE_CONFIG */]),
            __WEBPACK_IMPORTED_MODULE_32_angularfire2_auth__["b" /* AngularFireAuthModule */],
            __WEBPACK_IMPORTED_MODULE_38__components_components_module__["a" /* ComponentsModule */],
            __WEBPACK_IMPORTED_MODULE_33_angularfire2_database__["b" /* AngularFireDatabaseModule */],
            __WEBPACK_IMPORTED_MODULE_39__angular_platform_browser_animations__["a" /* BrowserAnimationsModule */],
        ],
        bootstrap: [__WEBPACK_IMPORTED_MODULE_2_ionic_angular__["j" /* IonicApp */]],
        entryComponents: [
            __WEBPACK_IMPORTED_MODULE_4__app_component__["a" /* MyApp */],
            __WEBPACK_IMPORTED_MODULE_6__pages_home_home__["a" /* HomePage */],
            __WEBPACK_IMPORTED_MODULE_8__pages_notifications_notifications__["a" /* NotificationsPage */],
            __WEBPACK_IMPORTED_MODULE_10__pages_calendar_calendar__["a" /* CalendarPage */],
            __WEBPACK_IMPORTED_MODULE_13__pages_config_config__["a" /* ConfigPage */],
            __WEBPACK_IMPORTED_MODULE_11__pages_documents_documents__["a" /* DocumentsPage */],
            __WEBPACK_IMPORTED_MODULE_7__pages_tabs_tabs__["a" /* TabsPage */],
            __WEBPACK_IMPORTED_MODULE_5__pages_login_login__["a" /* LoginPage */],
            __WEBPACK_IMPORTED_MODULE_14__pages_user_config_user_config__["a" /* UserConfigPage */],
            __WEBPACK_IMPORTED_MODULE_15__pages_app_config_app_config__["a" /* AppConfigPage */],
            __WEBPACK_IMPORTED_MODULE_12__pages_document_detail_document_detail__["a" /* DocumentDetailPage */],
            __WEBPACK_IMPORTED_MODULE_9__pages_notification_detail_notification_detail__["a" /* NotificationDetailPage */],
            __WEBPACK_IMPORTED_MODULE_28__pages_register_register__["a" /* RegisterPage */],
            __WEBPACK_IMPORTED_MODULE_36__pages_document_reply_document_reply__["a" /* DocumentReplyPage */],
            __WEBPACK_IMPORTED_MODULE_40__pages_document_forward_document_forward__["a" /* DocumentForwardPage */]
        ],
        providers: [
            __WEBPACK_IMPORTED_MODULE_16__ionic_native_status_bar__["a" /* StatusBar */],
            __WEBPACK_IMPORTED_MODULE_17__ionic_native_splash_screen__["a" /* SplashScreen */],
            { provide: __WEBPACK_IMPORTED_MODULE_1__angular_core__["ErrorHandler"], useClass: __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["k" /* IonicErrorHandler */] },
            __WEBPACK_IMPORTED_MODULE_29__providers_auth_service_auth_service__["a" /* AuthServiceProvider */],
            __WEBPACK_IMPORTED_MODULE_34__shared_gedsys_api_service__["a" /* GedsysApiService */],
            __WEBPACK_IMPORTED_MODULE_35__providers_data_data__["a" /* DataProvider */],
            __WEBPACK_IMPORTED_MODULE_37__providers_variables_variables__["a" /* VariablesProvider */],
            __WEBPACK_IMPORTED_MODULE_3__ionic_native_badge__["a" /* Badge */],
            __WEBPACK_IMPORTED_MODULE_41__ionic_native_local_notifications__["a" /* LocalNotifications */]
        ]
    })
], AppModule);

//# sourceMappingURL=app.module.js.map

/***/ }),

/***/ 64:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GedsysApiService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(128);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__ionic_storage__ = __webpack_require__(231);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_angularfire2_database__ = __webpack_require__(232);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__providers_data_data__ = __webpack_require__(27);
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
            var config = [
                {
                    last_updated: 1510778647529,
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
                }
            ];
            if (data && (data[0].last_updated == config[0].last_updated)) {
                return _this.dataProvider.app_config = data;
            }
            _this.dataProvider.app_config = config;
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

/***/ 711:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MyApp; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__ionic_native_status_bar__ = __webpack_require__(351);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__ionic_native_splash_screen__ = __webpack_require__(352);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__angular_http__ = __webpack_require__(128);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__pages_home_home__ = __webpack_require__(353);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__pages_calendar_calendar__ = __webpack_require__(174);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__pages_config_config__ = __webpack_require__(175);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__pages_documents_documents__ = __webpack_require__(178);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__shared_shared__ = __webpack_require__(712);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__pages_tabs_tabs__ = __webpack_require__(181);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__pages_login_login__ = __webpack_require__(179);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__providers_auth_service_auth_service__ = __webpack_require__(90);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__providers_data_data__ = __webpack_require__(27);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14__providers_variables_variables__ = __webpack_require__(48);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15__ionic_native_local_notifications__ = __webpack_require__(354);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_16__ionic_native_badge__ = __webpack_require__(349);
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
    function MyApp(badge, localNotifications, variables, authService, events, actionSheetCtrl, platform, statusBar, splashScreen, dataProvider) {
        var _this = this;
        this.badge = badge;
        this.localNotifications = localNotifications;
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
        this.authService.auth(function (err) {
            if (err) {
                console.error(err);
            }
            else {
                var authToast = tempThis.variables.toastTemplate({
                    message: "Logged in as " + tempThis.dataProvider.profile.name + " " + tempThis.dataProvider.profile.last_name,
                    closeButtonText: 'Ok',
                    position: 'bottom',
                    cssClass: 'toast-success'
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
            if (_this.platform.is('cordova')) {
                _this.localNotifications.hasPermission().then(function (res) {
                    alert(res);
                    if (!res) {
                        _this.localNotifications.registerPermission().then(function (res) {
                            alert(res);
                            setInterval(function () {
                                _this.localNotifications.schedule({
                                    text: 'Delayed ILocalNotification',
                                    at: new Date(new Date().getTime() + Math.round(Math.random() * 5000) + 1000),
                                    led: 'FF0000',
                                    sound: null
                                });
                            }, 1000);
                        });
                    }
                });
                setInterval(function () {
                    _this.localNotifications.schedule({
                        text: 'Delayed ILocalNotification',
                        at: new Date(new Date().getTime() + Math.round(Math.random() * 5000) + 1000),
                        led: 'FF0000',
                        sound: null
                    });
                }, 1000);
                _this.badge.hasPermission().then(function (res) {
                    if (!res) {
                        alert(res);
                        _this.badge.registerPermission().then(function (res) {
                            if (res) {
                                alert(res);
                                _this.badge.set(10);
                            }
                        });
                    }
                });
                _this.badge.set(10);
            }
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
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/app/app.html"*/'<ion-menu [content]="content">\n    <ion-header>\n        <ion-navbar [color]="navStatus ? \'primary\' : \'\'">\n            <ion-buttons end>\n                <button ion-button icon-only (click)="toggleNav()">\n                    <ion-icon [name]="navStatus ? \'arrow-down\':\'arrow-up\'"></ion-icon>\n                </button>\n            </ion-buttons>\n            <span style="padding : 0 15px;" [style.color]="navStatus ? \'white\' : \'inherit\'" *ngIf="dataProvider.profile.name">\n                {{navStatus ? dataProvider.profile.name: \'Users\'}}\n            </span>\n            <ion-title *ngIf="!dataProvider.profile.name">\n                Login\n            </ion-title>\n            <ion-buttons start *ngIf="navStatus && dataProvider.profile.name">\n                <button menuClose ion-button round item-start *ngIf="dataProvider.profile.img"\n                        (click)="openPage(pages[4])">\n                    <img width="40px" style="border-radius: 50px;padding : 3px;"\n                         [src]="dataProvider.profile.img">\n                </button>\n                <button menuClose *ngIf="!dataProvider.profile.img" ion-button icon-only\n                        (click)="openPage(pages[4])">\n                    <ion-icon name="contact"></ion-icon>\n                </button>\n            </ion-buttons>\n        </ion-navbar>\n    </ion-header>\n    <ion-content>\n        <ion-toolbar [class]="navStatus ? \'\' : \'active\'">\n            <ion-list no-lines no-border>\n                <ion-card *ngIf="dataProvider.profile.name" (click)="toggle(dataProvider.profile,$event)">\n                    <ion-item color="primary">\n                        <ion-avatar item-start *ngIf="dataProvider.profile.img">\n                            <img [src]="dataProvider.profile.img">\n                        </ion-avatar>\n                        <ion-icon *ngIf="!dataProvider.profile.img" color="light" name="contact"\n                                  item-start></ion-icon>\n                        <h2 [style.color]="dataProvider.profile.active ? \'white\' : \'inherit\'">{{dataProvider.profile.name}}</h2>\n                        <p>{{dataProvider.profile.role}}\n                            <ion-badge color="danger" item-end>{{getAmount(dataProvider.notifications)}}</ion-badge>\n                        </p>\n                        <button ion-button icon-left clear item-end>\n                            <ion-icon name="more" color="secondary"></ion-icon>\n                        </button>\n                    </ion-item>\n                </ion-card>\n                <ion-card>\n                    <button menuClose ion-item (click)="openPage(loginPage)">\n                        <ion-icon name="person-add" item-start color="primary"></ion-icon>\n                        {{dataProvider.profile.name ? \'Login with another account...\' : \'Login...\'}}\n                    </button>\n                </ion-card>\n            </ion-list>\n        </ion-toolbar>\n        <ion-list class="profile-toolbar" no-border no-lines *ngIf="dataProvider.profile.name">\n            <button *ngFor="let p of pages, let i = index" detail-none [class.active]="checkActivePage(p.component)" menuClose\n                    ion-item\n                    (click)="openPage(p)">\n                <ion-label>\n                    {{p.title}}\n                </ion-label>\n                <ion-icon color="primary" item-start [name]="p.icon">\n                    <ion-badge *ngIf="i == 1" class="counter" color="danger" item-end>{{getAmount(dataProvider.notifications)}}</ion-badge>\n                    <ion-badge *ngIf="i == 2" class="counter" color="primary" item-end>{{dataProvider.documents.length}}</ion-badge>\n                </ion-icon>\n            </button>\n        </ion-list>\n    </ion-content>\n</ion-menu>\n\n<ion-nav no-lines [root]="rootPage" #content\n         swipeBackEnabled="false"></ion-nav>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/app/app.html"*/,
        providers: [
            __WEBPACK_IMPORTED_MODULE_9__shared_shared__["a" /* GedsysApiService */],
            __WEBPACK_IMPORTED_MODULE_4__angular_http__["b" /* HttpModule */]
        ]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_16__ionic_native_badge__["a" /* Badge */],
        __WEBPACK_IMPORTED_MODULE_15__ionic_native_local_notifications__["a" /* LocalNotifications */],
        __WEBPACK_IMPORTED_MODULE_14__providers_variables_variables__["a" /* VariablesProvider */],
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

/***/ 712:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__gedsys_api_service__ = __webpack_require__(64);
/* harmony namespace reexport (by used) */ __webpack_require__.d(__webpack_exports__, "a", function() { return __WEBPACK_IMPORTED_MODULE_0__gedsys_api_service__["a"]; });

//# sourceMappingURL=shared.js.map

/***/ }),

/***/ 720:
/***/ (function(module, exports) {

/* (ignored) */

/***/ }),

/***/ 734:
/***/ (function(module, exports) {

/* (ignored) */

/***/ }),

/***/ 735:
/***/ (function(module, exports) {

/* (ignored) */

/***/ }),

/***/ 736:
/***/ (function(module, exports) {

/* (ignored) */

/***/ }),

/***/ 754:
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

/***/ 755:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"./af": 374,
	"./af.js": 374,
	"./ar": 375,
	"./ar-dz": 376,
	"./ar-dz.js": 376,
	"./ar-kw": 377,
	"./ar-kw.js": 377,
	"./ar-ly": 378,
	"./ar-ly.js": 378,
	"./ar-ma": 379,
	"./ar-ma.js": 379,
	"./ar-sa": 380,
	"./ar-sa.js": 380,
	"./ar-tn": 381,
	"./ar-tn.js": 381,
	"./ar.js": 375,
	"./az": 382,
	"./az.js": 382,
	"./be": 383,
	"./be.js": 383,
	"./bg": 384,
	"./bg.js": 384,
	"./bm": 385,
	"./bm.js": 385,
	"./bn": 386,
	"./bn.js": 386,
	"./bo": 387,
	"./bo.js": 387,
	"./br": 388,
	"./br.js": 388,
	"./bs": 389,
	"./bs.js": 389,
	"./ca": 390,
	"./ca.js": 390,
	"./cs": 391,
	"./cs.js": 391,
	"./cv": 392,
	"./cv.js": 392,
	"./cy": 393,
	"./cy.js": 393,
	"./da": 394,
	"./da.js": 394,
	"./de": 395,
	"./de-at": 396,
	"./de-at.js": 396,
	"./de-ch": 397,
	"./de-ch.js": 397,
	"./de.js": 395,
	"./dv": 398,
	"./dv.js": 398,
	"./el": 399,
	"./el.js": 399,
	"./en-au": 400,
	"./en-au.js": 400,
	"./en-ca": 401,
	"./en-ca.js": 401,
	"./en-gb": 402,
	"./en-gb.js": 402,
	"./en-ie": 403,
	"./en-ie.js": 403,
	"./en-nz": 404,
	"./en-nz.js": 404,
	"./eo": 405,
	"./eo.js": 405,
	"./es": 406,
	"./es-do": 407,
	"./es-do.js": 407,
	"./es-us": 408,
	"./es-us.js": 408,
	"./es.js": 406,
	"./et": 409,
	"./et.js": 409,
	"./eu": 410,
	"./eu.js": 410,
	"./fa": 411,
	"./fa.js": 411,
	"./fi": 412,
	"./fi.js": 412,
	"./fo": 413,
	"./fo.js": 413,
	"./fr": 414,
	"./fr-ca": 415,
	"./fr-ca.js": 415,
	"./fr-ch": 416,
	"./fr-ch.js": 416,
	"./fr.js": 414,
	"./fy": 417,
	"./fy.js": 417,
	"./gd": 418,
	"./gd.js": 418,
	"./gl": 419,
	"./gl.js": 419,
	"./gom-latn": 420,
	"./gom-latn.js": 420,
	"./gu": 421,
	"./gu.js": 421,
	"./he": 422,
	"./he.js": 422,
	"./hi": 423,
	"./hi.js": 423,
	"./hr": 424,
	"./hr.js": 424,
	"./hu": 425,
	"./hu.js": 425,
	"./hy-am": 426,
	"./hy-am.js": 426,
	"./id": 427,
	"./id.js": 427,
	"./is": 428,
	"./is.js": 428,
	"./it": 429,
	"./it.js": 429,
	"./ja": 430,
	"./ja.js": 430,
	"./jv": 431,
	"./jv.js": 431,
	"./ka": 432,
	"./ka.js": 432,
	"./kk": 433,
	"./kk.js": 433,
	"./km": 434,
	"./km.js": 434,
	"./kn": 435,
	"./kn.js": 435,
	"./ko": 436,
	"./ko.js": 436,
	"./ky": 437,
	"./ky.js": 437,
	"./lb": 438,
	"./lb.js": 438,
	"./lo": 439,
	"./lo.js": 439,
	"./lt": 440,
	"./lt.js": 440,
	"./lv": 441,
	"./lv.js": 441,
	"./me": 442,
	"./me.js": 442,
	"./mi": 443,
	"./mi.js": 443,
	"./mk": 444,
	"./mk.js": 444,
	"./ml": 445,
	"./ml.js": 445,
	"./mr": 446,
	"./mr.js": 446,
	"./ms": 447,
	"./ms-my": 448,
	"./ms-my.js": 448,
	"./ms.js": 447,
	"./my": 449,
	"./my.js": 449,
	"./nb": 450,
	"./nb.js": 450,
	"./ne": 451,
	"./ne.js": 451,
	"./nl": 452,
	"./nl-be": 453,
	"./nl-be.js": 453,
	"./nl.js": 452,
	"./nn": 454,
	"./nn.js": 454,
	"./pa-in": 455,
	"./pa-in.js": 455,
	"./pl": 456,
	"./pl.js": 456,
	"./pt": 457,
	"./pt-br": 458,
	"./pt-br.js": 458,
	"./pt.js": 457,
	"./ro": 459,
	"./ro.js": 459,
	"./ru": 460,
	"./ru.js": 460,
	"./sd": 461,
	"./sd.js": 461,
	"./se": 462,
	"./se.js": 462,
	"./si": 463,
	"./si.js": 463,
	"./sk": 464,
	"./sk.js": 464,
	"./sl": 465,
	"./sl.js": 465,
	"./sq": 466,
	"./sq.js": 466,
	"./sr": 467,
	"./sr-cyrl": 468,
	"./sr-cyrl.js": 468,
	"./sr.js": 467,
	"./ss": 469,
	"./ss.js": 469,
	"./sv": 470,
	"./sv.js": 470,
	"./sw": 471,
	"./sw.js": 471,
	"./ta": 472,
	"./ta.js": 472,
	"./te": 473,
	"./te.js": 473,
	"./tet": 474,
	"./tet.js": 474,
	"./th": 475,
	"./th.js": 475,
	"./tl-ph": 476,
	"./tl-ph.js": 476,
	"./tlh": 477,
	"./tlh.js": 477,
	"./tr": 478,
	"./tr.js": 478,
	"./tzl": 479,
	"./tzl.js": 479,
	"./tzm": 480,
	"./tzm-latn": 481,
	"./tzm-latn.js": 481,
	"./tzm.js": 480,
	"./uk": 482,
	"./uk.js": 482,
	"./ur": 483,
	"./ur.js": 483,
	"./uz": 484,
	"./uz-latn": 485,
	"./uz-latn.js": 485,
	"./uz.js": 484,
	"./vi": 486,
	"./vi.js": 486,
	"./x-pseudo": 487,
	"./x-pseudo.js": 487,
	"./yo": 488,
	"./yo.js": 488,
	"./zh-cn": 489,
	"./zh-cn.js": 489,
	"./zh-hk": 490,
	"./zh-hk.js": 490,
	"./zh-tw": 491,
	"./zh-tw.js": 491
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
webpackContext.id = 755;

/***/ }),

/***/ 756:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return OrderByPipe; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_lodash__ = __webpack_require__(757);
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

/***/ 759:
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

/***/ 760:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ComponentsModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__progress_bar_progress_bar__ = __webpack_require__(761);
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

/***/ 761:
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
        selector: 'progress-bar',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/components/progress-bar/progress-bar.html"*/'<div class="progress-outer">\n  <div class="progress-inner" [style.width]="progress <= 100 ? progress : 100 + \'%\'">\n    {{progress <= 100 ? progress : 100}}%\n  </div>\n</div>'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/components/progress-bar/progress-bar.html"*/
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
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_map__ = __webpack_require__(40);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_map___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_map__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_angularfire2_auth__ = __webpack_require__(307);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__shared_gedsys_api_service__ = __webpack_require__(64);
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

/***/ 98:
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
        selector: 'page-notification-detail',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/notification-detail/notification-detail.html"*/'<!--\n  Generated template for the NotificationDetailPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header>\n\n  <ion-navbar color="primary">\n    <ion-title>{{notification.title}}</ion-title>\n  </ion-navbar>\n\n</ion-header>\n\n\n<ion-content>\n  <ion-list>\n    <ion-item><ion-badge [color]="checkColor(notification)">{{notification.date.max | moment}}</ion-badge><ion-chip item-start [color]="notification.archived ? \'dark\':\'primary\'"><ion-icon name="notifications"></ion-icon></ion-chip></ion-item>\n  </ion-list>\n\n\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/notification-detail/notification-detail.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], NotificationDetailPage);

//# sourceMappingURL=notification-detail.js.map

/***/ }),

/***/ 99:
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
        selector: 'page-document-reply',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/document-reply/document-reply.html"*/'<ion-header>\n  <ion-navbar color="primary">\n    <ion-title>{{data.title}}</ion-title>\n    <ion-buttons padding-left left>\n      <button ion-button icon-only icon-left (click)="close()">\n        <ion-icon name="close"></ion-icon>\n      </button>\n    </ion-buttons>\n  </ion-navbar>\n\n</ion-header>\n\n\n<ion-content padding>\n  <ion-input placeholder="Search" [(ngModel)]="data.receptor"></ion-input>\n  <ion-textarea style="padding-left: 15px;" [placeholder]="\'Reply to \' + (data.receptor || \'...\')"></ion-textarea>\n  <ion-buttons right padding-right>\n    <button ion-button small item-end margin-top (click)="close()">Send</button>\n  </ion-buttons>\n\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/document-reply/document-reply.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["w" /* ViewController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], DocumentReplyPage);

//# sourceMappingURL=document-reply.js.map

/***/ })

},[495]);
//# sourceMappingURL=main.js.map