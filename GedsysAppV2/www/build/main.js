webpackJsonp([12],{

/***/ 131:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return HomePage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__tabs_tabs__ = __webpack_require__(92);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__config_config__ = __webpack_require__(90);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__shared_gedsys_api_service__ = __webpack_require__(36);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__providers_data_data__ = __webpack_require__(28);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__documents_documents__ = __webpack_require__(91);
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
    function HomePage(dataProvider, service, events, navParams, navCtrl) {
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
        this.navCtrl.setRoot(__WEBPACK_IMPORTED_MODULE_2__tabs_tabs__["a" /* TabsPage */]);
        return this.events.publish('root:change', 1);
    };
    HomePage.prototype.loadDocuments = function () {
        this.navCtrl.setRoot(__WEBPACK_IMPORTED_MODULE_6__documents_documents__["a" /* DocumentsPage */]);
        return this.events.publish('root:change', 2);
    };
    HomePage.prototype.loadSettings = function () {
        this.navCtrl.setRoot(__WEBPACK_IMPORTED_MODULE_3__config_config__["a" /* ConfigPage */]);
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
    HomePage.prototype.ionViewDidLoad = function () {
    };
    return HomePage;
}());
HomePage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-home',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/home/home.html"*/'<ion-header [style.max-height]="hiddenHeader ? \'0vh\': \'10vh\'">\n    <ion-navbar primary color="primary">\n        <button ion-button menuToggle>\n            <ion-icon name="menu" ></ion-icon>\n        </button>\n        <ion-title>Dashboard</ion-title>\n        <ion-buttons end *ngIf="!dataProvider.profile">\n            <button ion-button color="primary" menuToggle>Login</button>\n        </ion-buttons>\n    </ion-navbar>\n</ion-header>\n\n<ion-content (ionScroll)="contentScroll($event)" (ionScrollEnd)="contentScroll($event)" [class.full]="hiddenHeader">\n    <ion-slides pager *ngIf="!dataProvider.profile">\n        <ion-slide>\n            <h2>Welcome to gedsys app</h2>\n        </ion-slide>\n        <ion-slide>\n            <h2>Please\n                <ion-row></ion-row>\n                <button ion-button menuToggle small>Login</button>\n                <ion-row></ion-row>\n                or\n                <ion-row></ion-row>\n                <button ion-button menuToggle small>Register</button>\n                <ion-row></ion-row>\n                to continue\n            </h2>\n        </ion-slide>\n    </ion-slides>\n    <ion-row *ngIf="dataProvider.profile">\n        <ion-card>\n            <img [src]="dataProvider.profile.img" *ngIf="dataProvider.profile.img">\n            <ion-row *ngIf="!dataProvider.profile.img">\n                <ion-icon color="primary" col-12 name="contact"></ion-icon>\n            </ion-row>\n            <ion-fab right top>\n                <button ion-fab (click)="loadSettings()">\n                    <ion-icon name="settings"></ion-icon>\n                </button>\n            </ion-fab>\n            <ion-item>\n                <h2>{{dataProvider.profile.name}}</h2>\n                <p>{{dataProvider.profile.role}}</p>\n            </ion-item>\n            <ion-item>\n                <button ion-button icon-left clear item-start (click)="loadDocuments()">\n                    <ion-icon name="copy"></ion-icon>\n                    {{dataProvider.documents.length}} Documents\n                </button>\n                <button ion-button icon-left clear item-end (click)="loadNotifications()">\n                    <ion-icon name="notifications"></ion-icon>\n                    {{getAmount(dataProvider.notifications)}} Notifications\n                </button>\n            </ion-item>\n        </ion-card>\n        <ion-card>\n            <ion-card-content>\n                <chart style="display: block;" [options]="chartOptions" type="chart"></chart>\n            </ion-card-content>\n        </ion-card>\n        <ion-card>\n            <ion-card-content>\n                <chart style="display: block;" [options]="chartOptions1" type="chart"></chart>\n            </ion-card-content>\n        </ion-card>\n    </ion-row>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/home/home.html"*/
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_5__providers_data_data__["a" /* DataProvider */], __WEBPACK_IMPORTED_MODULE_4__shared_gedsys_api_service__["a" /* GedsysApiService */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["g" /* Events */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["r" /* NavParams */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* NavController */]])
], HomePage);

//# sourceMappingURL=home.js.map

/***/ }),

/***/ 156:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppConfigPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__shared_gedsys_api_service__ = __webpack_require__(36);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__providers_data_data__ = __webpack_require__(28);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




/**
 * Generated class for the AppConfigPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */
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
        selector: 'page-app-config',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/app-config/app-config.html"*/'<ion-content>\n    <ion-list class="outer-content" *ngFor="let data of data">\n        <ion-item-divider color="primary">\n            {{data.title}}\n        </ion-item-divider>\n        <ng-container *ngFor="let option of data.options" [ngSwitch]="option.type">\n            <ion-list-header *ngSwitchCase="\'range\'" color="primary">\n                {{option.title}}\n                <ion-badge item-end color="light">{{option.data}}%</ion-badge></ion-list-header>\n            <ion-item *ngIf="option.type != \'radio-group\'">\n                <ion-icon *ngIf="option.type != \'range\'" [name]="option.icon" item-start color="primary"></ion-icon>\n                <ion-label color="primary" *ngIf="option.type != \'range\'">{{option.title}}</ion-label>\n                <ion-toggle *ngSwitchCase="\'toggle\'" [(ngModel)]="option.data"></ion-toggle>\n                <ion-input *ngSwitchCase="\'input\'" [(ngModel)]="option.data"></ion-input>\n                <ion-textarea *ngSwitchCase="\'textarea\'" [(ngModel)]="option.data"></ion-textarea>\n                <ion-range *ngSwitchCase="\'range\'" [(ngModel)]="option.data"></ion-range>\n                <a ion-button outline item-end [href]="option.link" *ngSwitchCase="\'button\'">{{option.btnTitle}}</a>\n            </ion-item>\n            <ion-list *ngSwitchCase="\'radio-group\'" radio-group [(ngModel)]="option.data">\n                <ion-list-header style="margin-top: 0;">\n                    {{option.title}}\n                </ion-list-header>\n                <ng-container *ngFor="let radio of option.group">\n                    <ion-item>\n                        <ion-label color="dark">{{radio.name}}</ion-label>\n                        <ion-radio [value]="radio.name" checked></ion-radio>\n                    </ion-item>\n                </ng-container>\n            </ion-list>\n        </ng-container>\n    </ion-list>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/app-config/app-config.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2__shared_gedsys_api_service__["a" /* GedsysApiService */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["g" /* Events */],
        __WEBPACK_IMPORTED_MODULE_3__providers_data_data__["a" /* DataProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["r" /* NavParams */]])
], AppConfigPage);

//# sourceMappingURL=app-config.js.map

/***/ }),

/***/ 157:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return CalendarPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_moment__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_moment___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_moment__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__shared_gedsys_api_service__ = __webpack_require__(36);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__notification_detail_notification_detail__ = __webpack_require__(89);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__providers_data_data__ = __webpack_require__(28);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






/**
 * Generated class for the CalendarPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */
var CalendarPage = (function () {
    function CalendarPage(dataProvider, events, service, navCtrl, navParams) {
        this.dataProvider = dataProvider;
        this.events = events;
        this.service = service;
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.hiddenHeader = false;
        this.date = [];
        this.optionsRange = {
            from: 1,
            pickMode: 'multi',
            weekStart: 1,
            color: 'primary',
            showToggleButtons: true,
        };
        this.notifications = [];
        this.updateCalendar(dataProvider.profile);
        this.currentDay = __WEBPACK_IMPORTED_MODULE_2_moment__().format('MMMM Do');
        this.currentMonth = __WEBPACK_IMPORTED_MODULE_2_moment__().format('MM');
        this.currentYear = __WEBPACK_IMPORTED_MODULE_2_moment__().format('YYYY');
        this.currentDate = __WEBPACK_IMPORTED_MODULE_2_moment__().format('DD');
    }
    CalendarPage.prototype.contentScroll = function (event) {
        event.directionY == 'down' ? this.hiddenHeader = true : this.hiddenHeader = false;
        event.scrollTop == 0 ? this.hiddenHeader = false : null;
        return true;
    };
    CalendarPage.prototype.onChange = function ($event) {
        this.currentMonth = __WEBPACK_IMPORTED_MODULE_2_moment__($event.newMonth).format('MM');
        this.currentYear = __WEBPACK_IMPORTED_MODULE_2_moment__($event.newMonth).format('YYYY');
        this.currentDate = __WEBPACK_IMPORTED_MODULE_2_moment__($event.newMonth).format('DD');
    };
    CalendarPage.prototype.loadNotificationDetail = function (notification) {
        return this.navCtrl.push(__WEBPACK_IMPORTED_MODULE_4__notification_detail_notification_detail__["a" /* NotificationDetailPage */], notification);
    };
    CalendarPage.prototype.updateCalendar = function (user) {
        var _this = this;
        this.date = [];
        this.notifications = this.dataProvider.notifications;
        this.notifications.map(function (notification) {
            var tempDate = __WEBPACK_IMPORTED_MODULE_2_moment__(__WEBPACK_IMPORTED_MODULE_2_moment__(notification.date.max).format('DD/MM/YYYY'), 'DD/MM/YYYY').unix() * (Math.pow(10, 3));
            if (_this.date.indexOf(tempDate) < 0) {
                _this.date.push(tempDate);
            }
            return notification;
        });
    };
    CalendarPage.prototype.ionViewDidLoad = function () {
    };
    return CalendarPage;
}());
CalendarPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-calendar',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/calendar/calendar.html"*/'<!--\n  Generated template for the CalendarPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header [style.max-height]="hiddenHeader ? \'0vh\': \'10vh\'">\n    <ion-navbar color="primary">\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>Calendar</ion-title>\n    </ion-navbar>\n</ion-header>\n\n\n<ion-content (ionScroll)="contentScroll($event)" [class.full]="hiddenHeader">\n    <ion-card>\n        <ion-card-content>\n            <ion-card-title color="primary">\n                Upcoming\n            </ion-card-title>\n            <ion-calendar [(ngModel)]="date"\n                          (monthChange)="onChange($event)"\n                          [options]="optionsRange"\n                          [type]="type"\n                          [format]="\'YYYY-MM\'"\n                          [readonly]="false"\n            >\n            </ion-calendar>\n        </ion-card-content>\n    </ion-card>\n    <ion-list>\n        <ion-item-divider color="primary">{{currentDay}}</ion-item-divider>\n        <ng-container *ngFor="let notification of notifications">\n            <ion-item (click)="loadNotificationDetail(notification)">{{notification.title}}</ion-item>\n        </ng-container>\n    </ion-list>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/calendar/calendar.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_5__providers_data_data__["a" /* DataProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["g" /* Events */],
        __WEBPACK_IMPORTED_MODULE_3__shared_gedsys_api_service__["a" /* GedsysApiService */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["r" /* NavParams */]])
], CalendarPage);

//# sourceMappingURL=calendar.js.map

/***/ }),

/***/ 158:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserConfigPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__shared_gedsys_api_service__ = __webpack_require__(36);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__providers_data_data__ = __webpack_require__(28);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




/**
 * Generated class for the UserConfigPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */
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
        selector: 'page-user-config',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/user-config/user-config.html"*/'<ion-content>\n    <ion-list class="outer-content" *ngFor="let data of data">\n        <ion-item-divider color="primary">\n            {{data.title}}\n        </ion-item-divider>\n        <ng-container *ngFor="let option of data.options" [ngSwitch]="option.type">\n            <ion-list-header *ngSwitchCase="\'range\'" color="primary">\n                {{option.title}}\n                <ion-badge item-end color="light">{{option.data}}%</ion-badge></ion-list-header>\n            <ion-item *ngIf="option.type != \'radio-group\'">\n                <ion-icon *ngIf="option.type != \'range\'" [name]="option.icon" item-start color="primary"></ion-icon>\n                <ion-label color="primary" *ngIf="option.type != \'range\'">{{option.title}}</ion-label>\n                <ion-toggle *ngSwitchCase="\'toggle\'" [(ngModel)]="option.data"></ion-toggle>\n                <ion-input *ngSwitchCase="\'input\'" [(ngModel)]="option.data"></ion-input>\n                <ion-textarea *ngSwitchCase="\'textarea\'" [(ngModel)]="option.data"></ion-textarea>\n                <ion-range *ngSwitchCase="\'range\'" [(ngModel)]="option.data"></ion-range>\n                <a ion-button outline item-end [href]="option.link" *ngSwitchCase="\'button\'">{{option.btnTitle}}</a>\n            </ion-item>\n            <ion-list *ngSwitchCase="\'radio-group\'" radio-group [(ngModel)]="option.data">\n                <ion-list-header style="margin-top: 0;">\n                    {{option.title}}\n                </ion-list-header>\n                <ng-container *ngFor="let radio of option.group">\n                    <ion-item>\n                        <ion-label color="dark">{{radio.name}}</ion-label>\n                        <ion-radio [value]="radio.name" checked></ion-radio>\n                    </ion-item>\n                </ng-container>\n            </ion-list>\n        </ng-container>\n    </ion-list>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/user-config/user-config.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_3__providers_data_data__["a" /* DataProvider */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["g" /* Events */], __WEBPACK_IMPORTED_MODULE_2__shared_gedsys_api_service__["a" /* GedsysApiService */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["r" /* NavParams */]])
], UserConfigPage);

//# sourceMappingURL=user-config.js.map

/***/ }),

/***/ 159:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DocumentDetailPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(10);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


/**
 * Generated class for the DocumentDetailPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */
var DocumentDetailPage = (function () {
    function DocumentDetailPage(navCtrl, navParams) {
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.pdfSrc = navParams.data.url;
    }
    DocumentDetailPage.prototype.pinchEvent = function (event) {
        return this.pinch = event;
    };
    DocumentDetailPage.prototype.ionViewDidLoad = function () {
    };
    return DocumentDetailPage;
}());
DocumentDetailPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-document-detail',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/document-detail/document-detail.html"*/'<!--\n  Generated template for the DocumentDetailPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header>\n\n    <ion-navbar color="primary">\n        <ion-title>{{pdfSrc}}</ion-title>\n    </ion-navbar>\n\n</ion-header>\n\n\n<ion-content>\n    {{pinch | json}}\n    <ion-card (press)="pinchEvent($event)">\n        <pdf-viewer [zoom]=[1.0] [original-size]="false" [src]="pdfSrc"></pdf-viewer>\n    </ion-card>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/document-detail/document-detail.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["r" /* NavParams */]])
], DocumentDetailPage);

//# sourceMappingURL=document-detail.js.map

/***/ }),

/***/ 160:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return LoginPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__register_register__ = __webpack_require__(161);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__providers_auth_service_auth_service__ = __webpack_require__(81);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




/**
 * Generated class for the LoginPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */
var LoginPage = (function () {
    function LoginPage(authService, toastCtrl, events, navCtrl, navParams) {
        var _this = this;
        this.authService = authService;
        this.toastCtrl = toastCtrl;
        this.events = events;
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.user = {};
        this.events.subscribe('user:max', function () {
            var toast = _this.toastCtrl.create({
                message: 'Succesfully archived!',
                duration: 1500,
                position: 'top',
                showCloseButton: true,
                dismissOnPageChange: true,
                cssClass: 'warning'
            });
            toast.present();
        });
    }
    LoginPage.prototype.login = function (user) {
        var _this = this;
        this.authService.login(user).then(function () {
            return _this.events.publish('root:change', 0);
        });
    };
    LoginPage.prototype.loadRegister = function () {
        return this.navCtrl.push(__WEBPACK_IMPORTED_MODULE_2__register_register__["a" /* RegisterPage */]);
    };
    return LoginPage;
}());
LoginPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-login',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/login/login.html"*/'<ion-header>\n  <ion-navbar color="primary">\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Login</ion-title>\n  </ion-navbar>\n</ion-header>\n<ion-content class="login-content" padding>\n  <ion-row top class="logo-row">\n    <ion-col></ion-col>\n    <ion-col width-67>\n      <img color="primary" src="https://www.svgrepo.com/show/151032/paste-text.svg"/>\n    </ion-col>\n    <ion-col></ion-col>\n  </ion-row>\n\n  <div class="login-box" [hidden]="loading">\n    <form #registerForm="ngForm">\n      <ion-row center>\n        <ion-col>\n          <ion-list inset>\n            <ion-item>\n              <ion-label floating color="primary" >Email</ion-label>\n              <ion-input type="text"  name="email" required [(ngModel)]="user.email"></ion-input>\n            </ion-item>\n            <ion-item>\n              <ion-label floating color="primary">Password</ion-label>\n              <ion-input color="primary" type="password" name="password" required [(ngModel)]="user.password"></ion-input>\n            </ion-item>\n          </ion-list>\n        </ion-col>\n      </ion-row>\n\n      <ion-row bottom>\n        <ion-col class="signup-col">\n          <button ion-button class="submit-btn" outline block type="submit" (click)="login(user)">Login</button>\n          <button ion-button class="register-btn" block clear (click)="loadRegister()">Create New Account</button>\n        </ion-col>\n      </ion-row>\n\n    </form>\n  </div>\n</ion-content>'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/login/login.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_3__providers_auth_service_auth_service__["a" /* AuthServiceProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["u" /* ToastController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["g" /* Events */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["r" /* NavParams */]])
], LoginPage);

//# sourceMappingURL=login.js.map

/***/ }),

/***/ 161:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return RegisterPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__providers_auth_service_auth_service__ = __webpack_require__(81);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__home_home__ = __webpack_require__(131);
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
    function RegisterPage(authService, navCtrl, navParams) {
        this.authService = authService;
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.user = {};
        this.profile = {};
    }
    RegisterPage.prototype.register = function (user, profile) {
        var _this = this;
        this.authService.register(user, profile).then(function () {
            _this.navCtrl.setRoot(__WEBPACK_IMPORTED_MODULE_3__home_home__["a" /* HomePage */]);
        });
    };
    return RegisterPage;
}());
RegisterPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-register',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/register/register.html"*/'<ion-header>\n  <ion-navbar color="primary">\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Register</ion-title>\n  </ion-navbar>\n</ion-header>\n<ion-content class="login-content" padding>\n  <ion-row top class="logo-row">\n    <ion-col></ion-col>\n    <ion-col width-67>\n      <img src="https://www.svgrepo.com/show/151032/paste-text.svg"/>\n    </ion-col>\n    <ion-col></ion-col>\n  </ion-row>\n  <div class="login-box">\n    <form #registerForm="ngForm">\n      <ion-row center>\n        <ion-col>\n          <ion-list inset>\n            <ion-item>\n              <ion-label floating color="primary">Username</ion-label>\n              <ion-input type="text"  name="username" required [(ngModel)]="profile.username"></ion-input>\n            </ion-item>\n            <ion-item>\n              <ion-label floating color="primary">Name</ion-label>\n              <ion-input type="text"  name="name" required [(ngModel)]="profile.name"></ion-input>\n            </ion-item>\n            <ion-item>\n              <ion-label floating color="primary">Last name</ion-label>\n              <ion-input type="text"  name="last_name" required [(ngModel)]="profile.last_name"></ion-input>\n            </ion-item>\n            <ion-item>\n              <ion-label floating color="primary">Role</ion-label>\n              <ion-input type="text"  name="role" required [(ngModel)]="profile.role"></ion-input>\n            </ion-item>\n            <ion-item>\n              <ion-label floating color="primary">Image</ion-label>\n              <ion-input type="text"  name="img" required [(ngModel)]="profile.img"></ion-input>\n            </ion-item>\n            <ion-item>\n              <ion-label floating color="primary">Email</ion-label>\n              <ion-input type="text"  name="email" required [(ngModel)]="user.email"></ion-input>\n            </ion-item>\n            <ion-item>\n              <ion-label floating color="primary">Password</ion-label>\n              <ion-input color="primary" type="password" name="password" required [(ngModel)]="user.password"></ion-input>\n            </ion-item>\n          </ion-list>\n        </ion-col>\n      </ion-row>\n\n      <ion-row bottom>\n        <ion-col class="signup-col">\n          <button ion-button class="submit-btn" outline block type="submit" (click)="register(user,profile)">Register</button>\n        </ion-col>\n      </ion-row>\n\n    </form>\n  </div>\n</ion-content>'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/register/register.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2__providers_auth_service_auth_service__["a" /* AuthServiceProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["r" /* NavParams */]])
], RegisterPage);

//# sourceMappingURL=register.js.map

/***/ }),

/***/ 162:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return NotificationsPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__notification_detail_notification_detail__ = __webpack_require__(89);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__providers_data_data__ = __webpack_require__(28);
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
    function NotificationsPage(dataProvider, loadingCtrl, toastCtrl, navCtrl, navParams) {
        this.dataProvider = dataProvider;
        this.loadingCtrl = loadingCtrl;
        this.toastCtrl = toastCtrl;
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
        var loading = this.loadingCtrl.create({
            content: 'Deleting, please wait....'
        });
        loading.present();
        var toast = this.toastCtrl.create({
            message: 'Succesfully deleted!',
            duration: 1500,
            position: 'top',
            showCloseButton: true,
            dismissOnPageChange: true,
            cssClass: 'toast-success'
        });
        this.dataProvider.notifications.splice(this.dataProvider.notifications.indexOf(notification), 1);
        loading.dismiss();
        toast.present();
    };
    NotificationsPage.prototype.archive = function (notification) {
        var loading = this.loadingCtrl.create({
            content: 'Archiving, please wait....'
        });
        loading.present();
        var toast = this.toastCtrl.create({
            message: this.index ? 'Succesfully unarchived!' : 'Succesfully archived',
            duration: 1500,
            position: 'top',
            showCloseButton: true,
            closeButtonText: 'OK',
            dismissOnPageChange: true,
            cssClass: 'success'
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
        selector: 'page-notifications',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/notifications/notifications.html"*/'<!--\n  Generated template for the NotificationsPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header [style.max-height]="hiddenHeader ? \'0vh\': \'10vh\'">\n    <ion-navbar color="primary">\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>{{index ? \'Archived\' : \'Notifications\'}}</ion-title>\n    </ion-navbar>\n</ion-header>\n<ion-content (ionScroll)="contentScroll($event)" (ionScrollEnd)="contentScroll($event)" [class.full]="hiddenHeader">\n    <ion-toolbar *ngIf="dataProvider.notifications.length > 0" color="primary">\n        <ion-searchbar [(ngModel)]="filterStr.title"></ion-searchbar>\n    </ion-toolbar>\n    <ion-refresher (ionRefresh)="refresh($event)">\n        <ion-refresher-content\n                pullingIcon="arrow-dropdown"\n                pullingText="Pull to refresh"\n                refreshingText="Refreshing...">\n        </ion-refresher-content>\n    </ion-refresher>\n    <ion-list no-lines no-border>\n        <ion-item-divider color="primary" *ngIf="filterStr.title">Search for: {{filterStr.title}}</ion-item-divider>\n        <ion-item *ngIf="(dataProvider.notifications |filterBy: filterBool | filterBy: filterStr | orderBy: \'date.max\')?.length == 0">\n            {{index ? "No archived notifications" : "No pending notifications"}}\n        </ion-item>\n        <ng-container\n                *ngFor="let notification of dataProvider.notifications |filterBy: filterBool | filterBy: filterStr | orderBy: \'date.max\'">\n            <ion-item-sliding>\n                <ion-item (click)="loadNotificationDetail(notification)">\n                    {{notification.title}}\n                    <ion-badge [color]="checkColor(notification)" item-end>{{notification.date.max | moment}}\n                    </ion-badge>\n                </ion-item>\n                <ion-item-options side="right" (ionSwipe)="archive(notification)">\n                    <button color="danger" ion-button (click)="remove(notification)">Delete</button>\n                    <button ion-button expandable (click)="archive(notification)">Archive</button>\n                </ion-item-options>\n            </ion-item-sliding>\n        </ng-container>\n    </ion-list>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/notifications/notifications.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_3__providers_data_data__["a" /* DataProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["m" /* LoadingController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["u" /* ToastController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["r" /* NavParams */]])
], NotificationsPage);

//# sourceMappingURL=notifications.js.map

/***/ }),

/***/ 172:
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
webpackEmptyAsyncContext.id = 172;

/***/ }),

/***/ 215:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"../pages/app-config/app-config.module": [
		665,
		11
	],
	"../pages/calendar/calendar.module": [
		666,
		10
	],
	"../pages/config/config.module": [
		667,
		9
	],
	"../pages/document-detail/document-detail.module": [
		668,
		8
	],
	"../pages/documents/documents.module": [
		669,
		7
	],
	"../pages/login/login.module": [
		670,
		6
	],
	"../pages/notification-detail/notification-detail.module": [
		671,
		5
	],
	"../pages/notifications/notifications.module": [
		672,
		4
	],
	"../pages/register/register.module": [
		673,
		3
	],
	"../pages/tabs/tabs.module": [
		674,
		2
	],
	"../pages/user-config/user-config.module": [
		675,
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
webpackAsyncContext.id = 215;
module.exports = webpackAsyncContext;

/***/ }),

/***/ 28:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DataProvider; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_map__ = __webpack_require__(66);
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

/***/ 36:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GedsysApiService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(118);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_ionic_angular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__ionic_storage__ = __webpack_require__(216);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_angularfire2_database__ = __webpack_require__(217);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__providers_data_data__ = __webpack_require__(28);
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
    GedsysApiService.prototype.putProfile = function (user, profile) {
        var _this = this;
        return new Promise(function (resolve) {
            _this.http.put(_this.baseURL + "/notifications/" + user + ".json", profile, {})
                .subscribe(function (res) { return resolve(res.json()); });
        });
    };
    GedsysApiService.prototype.postProfile = function (uid, profile) {
        var _this = this;
        this.fireDatabase.object('profile/' + uid)
            .set(profile)
            .then(function () {
            profile.active = false;
            profile.notifications = [];
            profile.documents = [];
            _this.events.publish('user:login', profile);
        });
    };
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
    GedsysApiService.prototype.putNotifications = function (user, notifications) {
        var _this = this;
        return new Promise(function (resolve) {
            _this.http.put(_this.baseURL + "/notifications/" + user + ".json", notifications, {})
                .subscribe(function (res) { return resolve(res.json()); });
        });
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
        __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["u" /* ToastController */],
        __WEBPACK_IMPORTED_MODULE_3__ionic_storage__["b" /* Storage */],
        __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["g" /* Events */], __WEBPACK_IMPORTED_MODULE_1__angular_http__["a" /* Http */]])
], GedsysApiService);

//# sourceMappingURL=gedsys-api.service.js.map

/***/ }),

/***/ 459:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser_dynamic__ = __webpack_require__(460);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__app_module__ = __webpack_require__(475);


Object(__WEBPACK_IMPORTED_MODULE_0__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_1__app_module__["a" /* AppModule */]);
//# sourceMappingURL=main.js.map

/***/ }),

/***/ 475:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__ = __webpack_require__(39);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_ionic_angular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__app_component__ = __webpack_require__(607);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__pages_login_login__ = __webpack_require__(160);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__pages_home_home__ = __webpack_require__(131);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__pages_tabs_tabs__ = __webpack_require__(92);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__pages_notifications_notifications__ = __webpack_require__(162);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__pages_notification_detail_notification_detail__ = __webpack_require__(89);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__pages_calendar_calendar__ = __webpack_require__(157);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__pages_documents_documents__ = __webpack_require__(91);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__pages_document_detail_document_detail__ = __webpack_require__(159);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__pages_config_config__ = __webpack_require__(90);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__pages_user_config_user_config__ = __webpack_require__(158);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14__pages_app_config_app_config__ = __webpack_require__(156);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15__ionic_native_status_bar__ = __webpack_require__(431);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_16__ionic_native_splash_screen__ = __webpack_require__(434);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_17__angular_http__ = __webpack_require__(118);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_18_ion2_calendar__ = __webpack_require__(614);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_19_ionic2_super_tabs__ = __webpack_require__(458);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_20_ng2_pdf_viewer__ = __webpack_require__(619);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_20_ng2_pdf_viewer___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_20_ng2_pdf_viewer__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_21_angular2_highcharts__ = __webpack_require__(651);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_21_angular2_highcharts___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_21_angular2_highcharts__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_22_ng2_filter_pipe__ = __webpack_require__(658);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_22_ng2_filter_pipe___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_22_ng2_filter_pipe__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_23__pipes_moment_moment__ = __webpack_require__(660);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_24__pipes_order_by_order_by__ = __webpack_require__(661);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_25_highcharts__ = __webpack_require__(663);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_25_highcharts___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_25_highcharts__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_26__ionic_storage__ = __webpack_require__(216);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_27__pages_register_register__ = __webpack_require__(161);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_28__providers_auth_service_auth_service__ = __webpack_require__(81);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_29_angularfire2__ = __webpack_require__(47);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_30__shared_app_firebase_config__ = __webpack_require__(664);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_31_angularfire2_auth__ = __webpack_require__(389);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_32_angularfire2_database__ = __webpack_require__(217);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_33__shared_gedsys_api_service__ = __webpack_require__(36);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_34__providers_data_data__ = __webpack_require__(28);
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
            __WEBPACK_IMPORTED_MODULE_27__pages_register_register__["a" /* RegisterPage */]
        ],
        imports: [
            __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["a" /* BrowserModule */],
            __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["k" /* IonicModule */].forRoot(__WEBPACK_IMPORTED_MODULE_3__app_component__["a" /* MyApp */], {}, {
                links: [
                    { loadChildren: '../pages/app-config/app-config.module#AppConfigPageModule', name: 'AppConfigPage', segment: 'app-config', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/calendar/calendar.module#CalendarPageModule', name: 'CalendarPage', segment: 'calendar', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/config/config.module#ConfigPageModule', name: 'ConfigPage', segment: 'config', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/document-detail/document-detail.module#DocumentDetailPageModule', name: 'DocumentDetailPage', segment: 'document-detail', priority: 'low', defaultHistory: [] },
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
            __WEBPACK_IMPORTED_MODULE_18_ion2_calendar__["a" /* CalendarModule */],
            __WEBPACK_IMPORTED_MODULE_19_ionic2_super_tabs__["a" /* SuperTabsModule */].forRoot(),
            __WEBPACK_IMPORTED_MODULE_22_ng2_filter_pipe__["Ng2FilterPipeModule"],
            __WEBPACK_IMPORTED_MODULE_21_angular2_highcharts__["ChartModule"].forRoot(__WEBPACK_IMPORTED_MODULE_25_highcharts__),
            __WEBPACK_IMPORTED_MODULE_26__ionic_storage__["a" /* IonicStorageModule */].forRoot(),
            __WEBPACK_IMPORTED_MODULE_29_angularfire2__["a" /* AngularFireModule */].initializeApp(__WEBPACK_IMPORTED_MODULE_30__shared_app_firebase_config__["a" /* FIREBASE_CONFIG */]),
            __WEBPACK_IMPORTED_MODULE_31_angularfire2_auth__["b" /* AngularFireAuthModule */],
            __WEBPACK_IMPORTED_MODULE_32_angularfire2_database__["b" /* AngularFireDatabaseModule */]
        ],
        bootstrap: [__WEBPACK_IMPORTED_MODULE_2_ionic_angular__["i" /* IonicApp */]],
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
            __WEBPACK_IMPORTED_MODULE_27__pages_register_register__["a" /* RegisterPage */]
        ],
        providers: [
            __WEBPACK_IMPORTED_MODULE_15__ionic_native_status_bar__["a" /* StatusBar */],
            __WEBPACK_IMPORTED_MODULE_16__ionic_native_splash_screen__["a" /* SplashScreen */],
            { provide: __WEBPACK_IMPORTED_MODULE_1__angular_core__["ErrorHandler"], useClass: __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["j" /* IonicErrorHandler */] },
            __WEBPACK_IMPORTED_MODULE_28__providers_auth_service_auth_service__["a" /* AuthServiceProvider */],
            __WEBPACK_IMPORTED_MODULE_33__shared_gedsys_api_service__["a" /* GedsysApiService */],
            __WEBPACK_IMPORTED_MODULE_34__providers_data_data__["a" /* DataProvider */]
        ]
    })
], AppModule);

//# sourceMappingURL=app.module.js.map

/***/ }),

/***/ 580:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"./af": 265,
	"./af.js": 265,
	"./ar": 266,
	"./ar-dz": 267,
	"./ar-dz.js": 267,
	"./ar-kw": 268,
	"./ar-kw.js": 268,
	"./ar-ly": 269,
	"./ar-ly.js": 269,
	"./ar-ma": 270,
	"./ar-ma.js": 270,
	"./ar-sa": 271,
	"./ar-sa.js": 271,
	"./ar-tn": 272,
	"./ar-tn.js": 272,
	"./ar.js": 266,
	"./az": 273,
	"./az.js": 273,
	"./be": 274,
	"./be.js": 274,
	"./bg": 275,
	"./bg.js": 275,
	"./bm": 276,
	"./bm.js": 276,
	"./bn": 277,
	"./bn.js": 277,
	"./bo": 278,
	"./bo.js": 278,
	"./br": 279,
	"./br.js": 279,
	"./bs": 280,
	"./bs.js": 280,
	"./ca": 281,
	"./ca.js": 281,
	"./cs": 282,
	"./cs.js": 282,
	"./cv": 283,
	"./cv.js": 283,
	"./cy": 284,
	"./cy.js": 284,
	"./da": 285,
	"./da.js": 285,
	"./de": 286,
	"./de-at": 287,
	"./de-at.js": 287,
	"./de-ch": 288,
	"./de-ch.js": 288,
	"./de.js": 286,
	"./dv": 289,
	"./dv.js": 289,
	"./el": 290,
	"./el.js": 290,
	"./en-au": 291,
	"./en-au.js": 291,
	"./en-ca": 292,
	"./en-ca.js": 292,
	"./en-gb": 293,
	"./en-gb.js": 293,
	"./en-ie": 294,
	"./en-ie.js": 294,
	"./en-nz": 295,
	"./en-nz.js": 295,
	"./eo": 296,
	"./eo.js": 296,
	"./es": 297,
	"./es-do": 298,
	"./es-do.js": 298,
	"./es-us": 299,
	"./es-us.js": 299,
	"./es.js": 297,
	"./et": 300,
	"./et.js": 300,
	"./eu": 301,
	"./eu.js": 301,
	"./fa": 302,
	"./fa.js": 302,
	"./fi": 303,
	"./fi.js": 303,
	"./fo": 304,
	"./fo.js": 304,
	"./fr": 305,
	"./fr-ca": 306,
	"./fr-ca.js": 306,
	"./fr-ch": 307,
	"./fr-ch.js": 307,
	"./fr.js": 305,
	"./fy": 308,
	"./fy.js": 308,
	"./gd": 309,
	"./gd.js": 309,
	"./gl": 310,
	"./gl.js": 310,
	"./gom-latn": 311,
	"./gom-latn.js": 311,
	"./gu": 312,
	"./gu.js": 312,
	"./he": 313,
	"./he.js": 313,
	"./hi": 314,
	"./hi.js": 314,
	"./hr": 315,
	"./hr.js": 315,
	"./hu": 316,
	"./hu.js": 316,
	"./hy-am": 317,
	"./hy-am.js": 317,
	"./id": 318,
	"./id.js": 318,
	"./is": 319,
	"./is.js": 319,
	"./it": 320,
	"./it.js": 320,
	"./ja": 321,
	"./ja.js": 321,
	"./jv": 322,
	"./jv.js": 322,
	"./ka": 323,
	"./ka.js": 323,
	"./kk": 324,
	"./kk.js": 324,
	"./km": 325,
	"./km.js": 325,
	"./kn": 326,
	"./kn.js": 326,
	"./ko": 327,
	"./ko.js": 327,
	"./ky": 328,
	"./ky.js": 328,
	"./lb": 329,
	"./lb.js": 329,
	"./lo": 330,
	"./lo.js": 330,
	"./lt": 331,
	"./lt.js": 331,
	"./lv": 332,
	"./lv.js": 332,
	"./me": 333,
	"./me.js": 333,
	"./mi": 334,
	"./mi.js": 334,
	"./mk": 335,
	"./mk.js": 335,
	"./ml": 336,
	"./ml.js": 336,
	"./mr": 337,
	"./mr.js": 337,
	"./ms": 338,
	"./ms-my": 339,
	"./ms-my.js": 339,
	"./ms.js": 338,
	"./my": 340,
	"./my.js": 340,
	"./nb": 341,
	"./nb.js": 341,
	"./ne": 342,
	"./ne.js": 342,
	"./nl": 343,
	"./nl-be": 344,
	"./nl-be.js": 344,
	"./nl.js": 343,
	"./nn": 345,
	"./nn.js": 345,
	"./pa-in": 346,
	"./pa-in.js": 346,
	"./pl": 347,
	"./pl.js": 347,
	"./pt": 348,
	"./pt-br": 349,
	"./pt-br.js": 349,
	"./pt.js": 348,
	"./ro": 350,
	"./ro.js": 350,
	"./ru": 351,
	"./ru.js": 351,
	"./sd": 352,
	"./sd.js": 352,
	"./se": 353,
	"./se.js": 353,
	"./si": 354,
	"./si.js": 354,
	"./sk": 355,
	"./sk.js": 355,
	"./sl": 356,
	"./sl.js": 356,
	"./sq": 357,
	"./sq.js": 357,
	"./sr": 358,
	"./sr-cyrl": 359,
	"./sr-cyrl.js": 359,
	"./sr.js": 358,
	"./ss": 360,
	"./ss.js": 360,
	"./sv": 361,
	"./sv.js": 361,
	"./sw": 362,
	"./sw.js": 362,
	"./ta": 363,
	"./ta.js": 363,
	"./te": 364,
	"./te.js": 364,
	"./tet": 365,
	"./tet.js": 365,
	"./th": 366,
	"./th.js": 366,
	"./tl-ph": 367,
	"./tl-ph.js": 367,
	"./tlh": 368,
	"./tlh.js": 368,
	"./tr": 369,
	"./tr.js": 369,
	"./tzl": 370,
	"./tzl.js": 370,
	"./tzm": 371,
	"./tzm-latn": 372,
	"./tzm-latn.js": 372,
	"./tzm.js": 371,
	"./uk": 373,
	"./uk.js": 373,
	"./ur": 374,
	"./ur.js": 374,
	"./uz": 375,
	"./uz-latn": 376,
	"./uz-latn.js": 376,
	"./uz.js": 375,
	"./vi": 377,
	"./vi.js": 377,
	"./x-pseudo": 378,
	"./x-pseudo.js": 378,
	"./yo": 379,
	"./yo.js": 379,
	"./zh-cn": 380,
	"./zh-cn.js": 380,
	"./zh-hk": 381,
	"./zh-hk.js": 381,
	"./zh-tw": 382,
	"./zh-tw.js": 382
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
webpackContext.id = 580;

/***/ }),

/***/ 607:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MyApp; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__ionic_native_status_bar__ = __webpack_require__(431);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__ionic_native_splash_screen__ = __webpack_require__(434);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__angular_http__ = __webpack_require__(118);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__pages_home_home__ = __webpack_require__(131);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__pages_calendar_calendar__ = __webpack_require__(157);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__pages_config_config__ = __webpack_require__(90);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__pages_documents_documents__ = __webpack_require__(91);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__shared_shared__ = __webpack_require__(613);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__pages_tabs_tabs__ = __webpack_require__(92);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__pages_login_login__ = __webpack_require__(160);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__providers_auth_service_auth_service__ = __webpack_require__(81);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__providers_data_data__ = __webpack_require__(28);
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
    function MyApp(authService, loadingCtrl, events, actionSheetCtrl, platform, statusBar, splashScreen, dataProvider) {
        var _this = this;
        this.authService = authService;
        this.loadingCtrl = loadingCtrl;
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
            return _this.activePage = i;
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
        var loading = this.loadingCtrl.create({
            content: 'Please wait...'
        });
        loading.present();
        var tempThis = this;
        this.authService.auth(function (err, profileData) {
            if (err) {
                console.error(err);
            }
            else {
                tempThis.nav.setRoot(__WEBPACK_IMPORTED_MODULE_5__pages_home_home__["a" /* HomePage */]);
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
    MyApp.prototype.openPage = function (page, data, index) {
        this.nav.setRoot(page.component, data);
        this.activePage = index;
    };
    MyApp.prototype.checkActivePage = function (index) {
        return index == this.activePage;
    };
    return MyApp;
}());
__decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["ViewChild"])(__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["o" /* Nav */]),
    __metadata("design:type", __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["o" /* Nav */])
], MyApp.prototype, "nav", void 0);
MyApp = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/app/app.html"*/'<ion-menu [content]="content">\n    <ion-header>\n        <ion-navbar [color]="navStatus ? \'primary\' : \'\'">\n            <ion-buttons end>\n                <button ion-button icon-only (click)="toggleNav()">\n                    <ion-icon [name]="navStatus ? \'arrow-down\':\'arrow-up\'"></ion-icon>\n                </button>\n            </ion-buttons>\n            <span style="padding : 0 15px;" [style.color]="navStatus ? \'white\' : \'inherit\'" *ngIf="dataProvider.profile.name">\n                {{navStatus ? dataProvider.profile.name: \'Users\'}}\n            </span>\n            <ion-title *ngIf="!dataProvider.profile.name">\n                Login\n            </ion-title>\n            <ion-buttons start *ngIf="navStatus && dataProvider.profile.name">\n                <button menuClose ion-button round item-start *ngIf="dataProvider.profile.img"\n                        (click)="openPage(pages[4])">\n                    <img width="50px" style="border-radius: 50px;padding : 3px;"\n                         [src]="dataProvider.profile.img">\n                </button>\n                <button menuClose *ngIf="!dataProvider.profile.img" ion-button icon-only\n                        (click)="openPage(pages[4])">\n                    <ion-icon name="contact"></ion-icon>\n                </button>\n            </ion-buttons>\n        </ion-navbar>\n    </ion-header>\n    <ion-content>\n        <ion-toolbar [class]="navStatus ? \'\' : \'active\'">\n            <ion-list no-lines no-border>\n                <ion-card *ngIf="dataProvider.profile.name" (click)="toggle(dataProvider.profile,$event)">\n                    <ion-item color="primary">\n                        <ion-avatar item-start *ngIf="dataProvider.profile.img">\n                            <img [src]="dataProvider.profile.img">\n                        </ion-avatar>\n                        <ion-icon *ngIf="!dataProvider.profile.img" color="light" name="contact"\n                                  item-start></ion-icon>\n                        <h2 [style.color]="dataProvider.profile.active ? \'white\' : \'inherit\'">{{dataProvider.profile.name}}</h2>\n                        <p>{{dataProvider.profile.role}}\n                            <ion-badge color="danger" item-end>{{getAmount(dataProvider.notifications)}}</ion-badge>\n                        </p>\n                        <button ion-button icon-left clear item-end>\n                            <ion-icon name="more" color="secondary"></ion-icon>\n                        </button>\n                    </ion-item>\n                </ion-card>\n                <ion-card>\n                    <button menuClose ion-item (click)="openPage(loginPage)">\n                        <ion-icon name="person-add" item-start color="primary"></ion-icon>\n                        {{dataProvider.profile.name ? \'Login with another account...\' : \'Login...\'}}\n                    </button>\n                </ion-card>\n            </ion-list>\n        </ion-toolbar>\n        <ion-list no-border no-lines *ngIf="dataProvider.profile.name">\n            <button *ngFor="let p of pages, let i = index" detail-none [class.active]="checkActivePage(i)" menuClose\n                    ion-item\n                    (click)="openPage(p,dataProvider.profile,i)">\n                <ion-label>\n                    {{p.title}}\n                </ion-label>\n                <ion-icon color="primary" item-start [name]="p.icon">\n                    <ion-badge *ngIf="i == 1" class="counter" color="danger" item-end>{{getAmount(dataProvider.notifications)}}</ion-badge>\n                    <ion-badge *ngIf="i == 2" class="counter" color="primary" item-end>{{dataProvider.documents.length}}</ion-badge>\n                </ion-icon>\n            </button>\n        </ion-list>\n    </ion-content>\n</ion-menu>\n\n<!-- Disable swipe-to-go-back because it\'s poor UX to combine STGB with side menus -->\n<ion-nav no-lines [root]="rootPage" #content\n         swipeBackEnabled="false"></ion-nav>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/app/app.html"*/,
        providers: [
            __WEBPACK_IMPORTED_MODULE_9__shared_shared__["a" /* GedsysApiService */],
            __WEBPACK_IMPORTED_MODULE_4__angular_http__["b" /* HttpModule */]
        ]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_12__providers_auth_service_auth_service__["a" /* AuthServiceProvider */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["m" /* LoadingController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["g" /* Events */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* ActionSheetController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* Platform */],
        __WEBPACK_IMPORTED_MODULE_2__ionic_native_status_bar__["a" /* StatusBar */],
        __WEBPACK_IMPORTED_MODULE_3__ionic_native_splash_screen__["a" /* SplashScreen */],
        __WEBPACK_IMPORTED_MODULE_13__providers_data_data__["a" /* DataProvider */]])
], MyApp);

//# sourceMappingURL=app.component.js.map

/***/ }),

/***/ 613:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__gedsys_api_service__ = __webpack_require__(36);
/* harmony namespace reexport (by used) */ __webpack_require__.d(__webpack_exports__, "a", function() { return __WEBPACK_IMPORTED_MODULE_0__gedsys_api_service__["a"]; });

//# sourceMappingURL=shared.js.map

/***/ }),

/***/ 626:
/***/ (function(module, exports) {

/* (ignored) */

/***/ }),

/***/ 640:
/***/ (function(module, exports) {

/* (ignored) */

/***/ }),

/***/ 641:
/***/ (function(module, exports) {

/* (ignored) */

/***/ }),

/***/ 642:
/***/ (function(module, exports) {

/* (ignored) */

/***/ }),

/***/ 660:
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

/***/ 661:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return OrderByPipe; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_lodash__ = __webpack_require__(662);
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

/***/ 664:
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

/***/ 81:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AuthServiceProvider; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_map__ = __webpack_require__(66);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_map___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_map__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_angularfire2_auth__ = __webpack_require__(389);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__shared_gedsys_api_service__ = __webpack_require__(36);
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
                _this.dbService.getProfile(data.uid).then(function (profile) {
                    _this.dbService.getNotifications(data.uid).then(function () {
                        _this.dbService.getDocuments(data.uid).then(function () {
                            return callback(null, null);
                        });
                    });
                });
            }
            else {
                return callback('No data', null);
            }
        });
    };
    AuthServiceProvider.prototype.login = function (user) {
        return __awaiter(this, void 0, void 0, function () {
            var auth, e_1;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        _a.trys.push([0, 2, , 3]);
                        return [4 /*yield*/, this.fireAuth.auth.signInWithEmailAndPassword(user.email, user.password)];
                    case 1:
                        auth = _a.sent();
                        this.auth(function (err) {
                            if (err) {
                                return console.error(err);
                            }
                        });
                        return [3 /*break*/, 3];
                    case 2:
                        e_1 = _a.sent();
                        console.error(e_1);
                        return [3 /*break*/, 3];
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
                        this.dbService.postProfile(result.uid, profile);
                        return [3 /*break*/, 3];
                    case 2:
                        e_3 = _a.sent();
                        console.error(e_3);
                        return [3 /*break*/, 3];
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

/***/ 89:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return NotificationDetailPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(10);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


/**
 * Generated class for the NotificationDetailPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */
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
    NotificationDetailPage.prototype.ionViewDidLoad = function () {
    };
    return NotificationDetailPage;
}());
NotificationDetailPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-notification-detail',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/notification-detail/notification-detail.html"*/'<!--\n  Generated template for the NotificationDetailPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header>\n\n  <ion-navbar color="primary">\n    <ion-title>{{notification.title}}</ion-title>\n  </ion-navbar>\n\n</ion-header>\n\n\n<ion-content>\n  <ion-list>\n    <ion-item><ion-badge [color]="checkColor(notification)">{{notification.date.max | moment}}</ion-badge><ion-chip item-start [color]="notification.archived ? \'dark\':\'primary\'"><ion-icon name="notifications"></ion-icon></ion-chip></ion-item>\n  </ion-list>\n\n\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/notification-detail/notification-detail.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["r" /* NavParams */]])
], NotificationDetailPage);

//# sourceMappingURL=notification-detail.js.map

/***/ }),

/***/ 90:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ConfigPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__user_config_user_config__ = __webpack_require__(158);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__app_config_app_config__ = __webpack_require__(156);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




/**
 * Generated class for the ConfigPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */
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
        selector: 'page-config',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/config/config.html"*/'<!--\n  Generated template for the ConfigPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header>\n    <ion-navbar color="primary">\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>Configuration</ion-title>\n        <ion-buttons end>\n            <button ion-button (click)="updateInfo()">Save</button>\n        </ion-buttons>\n    </ion-navbar>\n</ion-header>\n\n\n<ion-content>\n    <super-tabs (tabSelect)="listenTabs($event)" toolbarBackground="primary" toolbarColor="light" indicatorColor="light">\n        <super-tab [root]="page1" [rootParams]="data" title="User" icon="person">test</super-tab>\n        <super-tab [root]="page2" [rootParams]="data" title="Application" icon="cog"></super-tab>\n    </super-tabs>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/config/config.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["g" /* Events */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["r" /* NavParams */]])
], ConfigPage);

//# sourceMappingURL=config.js.map

/***/ }),

/***/ 91:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DocumentsPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__document_detail_document_detail__ = __webpack_require__(159);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__providers_data_data__ = __webpack_require__(28);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




/**
 * Generated class for the DocumentsPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */
var DocumentsPage = (function () {
    function DocumentsPage(dataProvider, navCtrl, navParams) {
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
    DocumentsPage.prototype.more = function () {
    };
    DocumentsPage.prototype.viewDoc = function (url) {
        this.navCtrl.push(__WEBPACK_IMPORTED_MODULE_2__document_detail_document_detail__["a" /* DocumentDetailPage */], { url: url });
    };
    DocumentsPage.prototype.ionViewDidLoad = function () {
    };
    return DocumentsPage;
}());
DocumentsPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-documents',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/documents/documents.html"*/'<!--\n  Generated template for the DocumentsPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header [style.max-height]="hiddenHeader ? \'0vh\': \'10vh\'">\n    <ion-navbar primary color="primary">\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>Documents</ion-title>\n    </ion-navbar>\n</ion-header>\n\n\n<ion-content (ionScroll)="contentScroll($event)" (ionScrollEnd)="contentScroll($event)"\n             [class.full]="hiddenHeader">\n    <ion-toolbar *ngIf="dataProvider.documents.length > 0" color="primary">\n        <ion-searchbar (ionInput)="filter($event)"></ion-searchbar>\n    </ion-toolbar>\n    <ion-refresher (ionRefresh)="refresh($event)">\n        <ion-refresher-content\n                pullingIcon="arrow-dropdown"\n                pullingText="Pull to refresh"\n                refreshingText="Refreshing...">\n        </ion-refresher-content>\n    </ion-refresher>\n    <ion-item-divider color="primary" *ngIf="filterStr.title">Search for: {{filterStr.title}}</ion-item-divider>\n    <ion-item no-lines *ngIf="(dataProvider.documents | filterBy: filterStr | orderBy: \'title\')?.length == 0">Any document found</ion-item>\n    <ng-container *ngFor="let pdf of dataProvider.documents | filterBy: filterStr | orderBy: \'title\'">\n        <ion-card>\n            <ion-item>\n                <ion-avatar item-start>\n                    <img src="http://www.xsjjys.com/data/out/96/WHDQ-512397052.jpg">\n                </ion-avatar>\n                <h2>Admin</h2>\n                <p>For: October 22, 2017</p>\n            </ion-item>\n            <ion-card-content>\n                <p>{{pdf.title}}</p>\n            </ion-card-content>\n            <ion-row>\n                <ion-col>\n                    <button ion-button icon-left clear small>\n                        <ion-icon name="undo"></ion-icon>\n                        <div>Reply</div>\n                    </button>\n                </ion-col>\n                <ion-col>\n                    <button ion-button icon-left clear small (click)="viewDoc(pdf.url)">\n                        <ion-icon name="eye"></ion-icon>\n                        <div>View</div>\n                    </button>\n                </ion-col>\n                <ion-col center text-center>\n                    <button ion-button icon-left clear small (click)="more()">\n                        <ion-icon name="more"></ion-icon>\n                    </button>\n                </ion-col>\n            </ion-row>\n        </ion-card>\n    </ng-container>\n</ion-content>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/documents/documents.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_3__providers_data_data__["a" /* DataProvider */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["r" /* NavParams */]])
], DocumentsPage);

//# sourceMappingURL=documents.js.map

/***/ }),

/***/ 92:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return TabsPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__notifications_notifications__ = __webpack_require__(162);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__shared_gedsys_api_service__ = __webpack_require__(36);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__providers_data_data__ = __webpack_require__(28);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





/**
 * Generated class for the TabsPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */
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
        selector: 'page-tabs',template:/*ion-inline-start:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/tabs/tabs.html"*/'<!--\n  Generated template for the TabsPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n\n<ion-tabs color="primary">\n  <ion-tab [rootParams]="data" [root]="notificationsView" tabIcon="alert" tabTitle="Notifications"\n           [tabBadge]="getAmount(dataProvider.notifications)" tabBadgeStyle="danger"></ion-tab>\n  <ion-tab [rootParams]="data" [root]="notificationsView" tabIcon="archive" tabTitle="Archived"\n           [tabBadge]="getArchived(dataProvider.notifications)" tabBadgeStyle="light"></ion-tab>\n</ion-tabs>\n'/*ion-inline-end:"/home/alejandrochvs/Documents/gedsys/GedsysAppV2/src/pages/tabs/tabs.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_4__providers_data_data__["a" /* DataProvider */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["g" /* Events */], __WEBPACK_IMPORTED_MODULE_3__shared_gedsys_api_service__["a" /* GedsysApiService */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["r" /* NavParams */]])
], TabsPage);

//# sourceMappingURL=tabs.js.map

/***/ })

},[459]);
//# sourceMappingURL=main.js.map