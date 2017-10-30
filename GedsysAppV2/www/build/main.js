webpackJsonp([11],{

/***/ 149:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppConfigPage; });
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


/**
 * Generated class for the AppConfigPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */
var AppConfigPage = (function () {
    function AppConfigPage(navCtrl, navParams) {
        this.navCtrl = navCtrl;
        this.navParams = navParams;
    }
    AppConfigPage.prototype.ionViewDidLoad = function () {
        console.log('ionViewDidLoad AppConfigPage');
    };
    return AppConfigPage;
}());
AppConfigPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-app-config',template:/*ion-inline-start:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/pages/app-config/app-config.html"*/'<ion-content padding>\n\n</ion-content>\n'/*ion-inline-end:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/pages/app-config/app-config.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], AppConfigPage);

//# sourceMappingURL=app-config.js.map

/***/ }),

/***/ 150:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return CalendarPage; });
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


/**
 * Generated class for the CalendarPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */
var CalendarPage = (function () {
    function CalendarPage(navCtrl, navParams) {
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.date = [];
        this.optionsRange = {
            pickMode: 'multi',
            weekStart: 1
        };
    }
    CalendarPage.prototype.onChange = function ($event) {
        console.log($event);
    };
    CalendarPage.prototype.ionViewDidLoad = function () {
    };
    return CalendarPage;
}());
CalendarPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-calendar',template:/*ion-inline-start:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/pages/calendar/calendar.html"*/'<!--\n  Generated template for the CalendarPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header>\n  <ion-navbar color="primary">\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Calendar</ion-title>\n  </ion-navbar>\n</ion-header>\n\n\n<ion-content>\n  <ion-card>\n    <ion-card-content>\n      <ion-card-title color="primary">\n        Upcoming\n      </ion-card-title>\n      <ion-calendar [(ngModel)]="date"\n                    (onChange)="onChange($event)"\n                    [options]="optionsRange"\n                    [type]="type"\n                    [format]="\'YYYY-MM-DD\'" color="primary">\n      </ion-calendar>\n    </ion-card-content>\n  </ion-card>\n  <ion-list>\n    <ng-container *ngFor="let date_i of date">\n      <ion-item>{{date_i}}</ion-item>\n    </ng-container>\n  </ion-list>\n</ion-content>\n'/*ion-inline-end:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/pages/calendar/calendar.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], CalendarPage);

//# sourceMappingURL=calendar.js.map

/***/ }),

/***/ 151:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserConfigPage; });
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


/**
 * Generated class for the UserConfigPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */
var UserConfigPage = (function () {
    function UserConfigPage(navCtrl, navParams) {
        this.navCtrl = navCtrl;
        this.navParams = navParams;
    }
    UserConfigPage.prototype.ionViewDidLoad = function () {
        console.log('ionViewDidLoad UserConfigPage');
    };
    return UserConfigPage;
}());
UserConfigPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-user-config',template:/*ion-inline-start:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/pages/user-config/user-config.html"*/'<ion-content padding>\n\n</ion-content>\n'/*ion-inline-end:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/pages/user-config/user-config.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], UserConfigPage);

//# sourceMappingURL=user-config.js.map

/***/ }),

/***/ 152:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DocumentDetailPage; });
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
    DocumentDetailPage.prototype.ionViewDidLoad = function () {
    };
    return DocumentDetailPage;
}());
DocumentDetailPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-document-detail',template:/*ion-inline-start:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/pages/document-detail/document-detail.html"*/'<!--\n  Generated template for the DocumentDetailPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header>\n\n    <ion-navbar color="primary">\n        <ion-title>{{pdfSrc}}</ion-title>\n    </ion-navbar>\n\n</ion-header>\n\n\n<ion-content>\n    <zoom-area>\n        <pdf-viewer [original-size]="false"\n                    [src]="pdfSrc" [render-text]="true" style="display : block;"></pdf-viewer>\n    </zoom-area>\n</ion-content>\n'/*ion-inline-end:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/pages/document-detail/document-detail.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], DocumentDetailPage);

//# sourceMappingURL=document-detail.js.map

/***/ }),

/***/ 153:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DocumentsPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__document_detail_document_detail__ = __webpack_require__(152);
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
    function DocumentsPage(navCtrl, navParams) {
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.pdfSrc = [
            'https://alejandrochvs.github.io/GEDSYS/docs/Doc1.pdf',
            'https://alejandrochvs.github.io/GEDSYS/docs/Doc2.pdf',
            'https://alejandrochvs.github.io/GEDSYS/docs/Doc3.pdf',
            'https://alejandrochvs.github.io/GEDSYS/docs/Doc4.pdf',
            'https://alejandrochvs.github.io/GEDSYS/docs/Doc5.pdf',
            'https://alejandrochvs.github.io/GEDSYS/docs/Doc6.pdf'
        ];
    }
    DocumentsPage.prototype.refresh = function (comp) {
        setTimeout(function () { return comp.complete(); }, 1000);
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
        selector: 'page-documents',template:/*ion-inline-start:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/pages/documents/documents.html"*/'<!--\n  Generated template for the DocumentsPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header>\n    <ion-navbar primary color="primary">\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>Documents</ion-title>\n    </ion-navbar>\n</ion-header>\n\n\n<ion-content padding>\n    <ion-refresher (ionRefresh)="refresh($event)">\n        <ion-refresher-content\n                pullingIcon="arrow-dropdown"\n                pullingText="Pull to refresh"\n                refreshingText="Refreshing...">\n        </ion-refresher-content>\n    </ion-refresher>\n    <ng-container *ngFor="let pdf of pdfSrc, let i = index">\n        <ion-card>\n            <ion-item>\n                <ion-avatar item-start>\n                    <img src="http://www.xsjjys.com/data/out/96/WHDQ-512397052.jpg">\n                </ion-avatar>\n                <h2>Admin</h2>\n                <p>For: October 22, 2017</p>\n            </ion-item>\n            <!--<ion-grid style="padding: 0;">\n                <ion-row>\n                    <ion-col style="padding: 0;" col-12>\n                        <pdf-viewer [original-size]="false"\n                                    [src]="pdf" [render-text]="false" style="display : block;"></pdf-viewer>\n                    </ion-col>\n                </ion-row>\n            </ion-grid>-->\n            <ion-card-content>\n                <p>Document {{i + 1}}</p>\n            </ion-card-content>\n            <ion-row>\n                <ion-col>\n                    <button ion-button icon-left clear small>\n                        <ion-icon name="undo"></ion-icon>\n                        <div>Reply</div>\n                    </button>\n                </ion-col>\n                <ion-col>\n                    <button ion-button icon-left clear small (click)="viewDoc(pdf)">\n                        <ion-icon name="eye"></ion-icon>\n                        <div>View</div>\n                    </button>\n                </ion-col>\n                <ion-col center text-center>\n                    <ion-note>\n                        11h ago\n                    </ion-note>\n                </ion-col>\n            </ion-row>\n        </ion-card>\n    </ng-container>\n</ion-content>\n'/*ion-inline-end:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/pages/documents/documents.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], DocumentsPage);

//# sourceMappingURL=documents.js.map

/***/ }),

/***/ 154:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return LoginPage; });
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


/**
 * Generated class for the LoginPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */
var LoginPage = (function () {
    function LoginPage(toastCtrl, events, navCtrl, navParams) {
        var _this = this;
        this.toastCtrl = toastCtrl;
        this.events = events;
        this.navCtrl = navCtrl;
        this.navParams = navParams;
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
    LoginPage.prototype.login = function () {
        return this.events.publish('user:login');
    };
    LoginPage.prototype.ionViewDidLoad = function () {
    };
    return LoginPage;
}());
LoginPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-login',template:/*ion-inline-start:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/pages/login/login.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Login</ion-title>\n  </ion-navbar>\n</ion-header>\n<ion-content class="login-content" padding>\n  <ion-row top class="logo-row">\n    <ion-col></ion-col>\n    <ion-col width-67>\n      <img src="http://placehold.it/300x200"/>\n    </ion-col>\n    <ion-col></ion-col>\n  </ion-row>\n  <div class="login-box">\n    <form #registerForm="ngForm">\n      <ion-row center>\n        <ion-col>\n          <ion-list inset>\n            <ion-item>\n              <ion-label floating color="primary">Email</ion-label>\n              <ion-input type="text"  name="email" required></ion-input>\n            </ion-item>\n            <ion-item>\n              <ion-label floating color="primary">Password</ion-label>\n              <ion-input color="primary" type="password" name="password" required></ion-input>\n            </ion-item>\n          </ion-list>\n        </ion-col>\n      </ion-row>\n\n      <ion-row bottom>\n        <ion-col class="signup-col">\n          <button ion-button class="submit-btn" outline block type="submit" (click)="login()">Login</button>\n          <button ion-button class="register-btn" block clear>Create New Account</button>\n        </ion-col>\n      </ion-row>\n\n    </form>\n  </div>\n</ion-content>'/*ion-inline-end:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/pages/login/login.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["v" /* ToastController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["g" /* Events */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], LoginPage);

//# sourceMappingURL=login.js.map

/***/ }),

/***/ 155:
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
    NotificationDetailPage.prototype.ionViewDidLoad = function () {
    };
    return NotificationDetailPage;
}());
NotificationDetailPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-notification-detail',template:/*ion-inline-start:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/pages/notification-detail/notification-detail.html"*/'<!--\n  Generated template for the NotificationDetailPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header>\n\n  <ion-navbar color="primary">\n    <ion-title>{{notification.title}}</ion-title>\n  </ion-navbar>\n\n</ion-header>\n\n\n<ion-content padding>\n\n</ion-content>\n'/*ion-inline-end:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/pages/notification-detail/notification-detail.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], NotificationDetailPage);

//# sourceMappingURL=notification-detail.js.map

/***/ }),

/***/ 156:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return NotificationsPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__notification_detail_notification_detail__ = __webpack_require__(155);
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
    function NotificationsPage(events, loadingCtrl, toastCtrl, navCtrl, navParams, actionSheetCtrl) {
        var _this = this;
        this.events = events;
        this.loadingCtrl = loadingCtrl;
        this.toastCtrl = toastCtrl;
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.actionSheetCtrl = actionSheetCtrl;
        this.user = [];
        this.curPage = 0;
        this.pagesPer = 15;
        this.filterStr = { title: '' };
        this.ammount = 0;
        this.user = this.navParams.data.user;
        this.index = this.navCtrl.index;
        this.userInd = this.navParams.data.index;
        this.user.notifications.map(function (notification) {
            if (_this.index) {
                notification.archived ? _this.ammount++ : _this.ammount;
            }
            else {
                notification.archived ? _this.ammount : _this.ammount++;
            }
            return notification;
        });
        this.events.subscribe('user:updated', function (user, index) {
            _this.user = user;
            _this.userInd = index;
            _this.ammount = 0;
            _this.user.notifications.map(function (notification) {
                if (_this.index) {
                    notification.archived ? _this.ammount++ : _this.ammount;
                }
                else {
                    notification.archived ? _this.ammount : _this.ammount++;
                }
                return notification;
            });
        });
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
        this.user.notifications.splice(this.user.notifications.indexOf(notification), 1);
        this.events.publish('user:updated', this.user, this.userInd);
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
        this.events.publish('user:updated', this.user, this.userInd);
        loading.dismiss();
        toast.present();
    };
    NotificationsPage.prototype.filter = function (event) {
        this.filterStr.title = event.target.value;
    };
    NotificationsPage.prototype.refresh = function (comp) {
        setTimeout(function () { return comp.complete(); }, 1000);
    };
    NotificationsPage.prototype.ionViewDidEnter = function () {
    };
    NotificationsPage.prototype.ionViewDidUpdate = function () {
    };
    return NotificationsPage;
}());
NotificationsPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-notifications',template:/*ion-inline-start:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/pages/notifications/notifications.html"*/'<!--\n  Generated template for the NotificationsPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header>\n    <ion-navbar color="primary">\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>{{index ? \'Archived\' : \'Notifications\'}}</ion-title>\n    </ion-navbar>\n</ion-header>\n<ion-content>\n    <ion-toolbar *ngIf="ammount > 0" color="primary">\n        <ion-searchbar (ionInput)="filter($event)"></ion-searchbar>\n    </ion-toolbar>\n    <ion-refresher (ionRefresh)="refresh($event)">\n        <ion-refresher-content\n                pullingIcon="arrow-dropdown"\n                pullingText="Pull to refresh"\n                refreshingText="Refreshing...">\n        </ion-refresher-content>\n    </ion-refresher>\n    <ion-list no-lines no-border>\n        <ion-item-divider color="primary" *ngIf="filterStr.title">Search for: {{filterStr.title}}</ion-item-divider>\n        <ion-item *ngIf="ammount == 0">{{index ? "No archived notifications" : "No pending notifications"}}</ion-item>\n        <ng-container *ngFor="let notification of user.notifications | filterBy: filterStr | orderBy: \'date.max\'; let i = index">\n            <ion-item-sliding *ngIf="index == 0 && !notification.archived">\n                <ion-item (click)="loadNotificationDetail(notification)">\n                    {{notification.title}} <ion-badge [color]="checkColor(notification)" item-end>{{notification.date.max | moment}}</ion-badge>\n                </ion-item>\n                <ion-item-options side="right" (ionSwipe)="archive(notification)">\n                    <button color="danger" ion-button (click)="remove(notification)">Delete</button>\n                    <button ion-button expandable (click)="archive(notification)">Archive</button>\n                </ion-item-options>\n            </ion-item-sliding>\n        </ng-container>\n        <ng-container *ngFor="let notification of user.notifications | filterBy: filterStr | orderBy: \'date.max\'">\n            <ion-item-sliding *ngIf="index == 1 && notification.archived">\n                <ion-item (click)="loadNotificationDetail(notification)">\n                    {{notification.title}} <ion-badge [color]="checkColor(notification)" item-end>{{notification.date.max | moment}}</ion-badge>\n                </ion-item>\n                <ion-item-options side="right" (ionSwipe)="archive(notification)">\n                    <button color="danger" ion-button (click)="remove(notification)">Delete</button>\n                    <button ion-button expandable (click)="archive(notification)">Unarchive</button>\n                </ion-item-options>\n            </ion-item-sliding>\n        </ng-container>\n    </ion-list>\n</ion-content>\n'/*ion-inline-end:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/pages/notifications/notifications.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["g" /* Events */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["n" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["v" /* ToastController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* ActionSheetController */]])
], NotificationsPage);

//# sourceMappingURL=notifications.js.map

/***/ }),

/***/ 167:
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
webpackEmptyAsyncContext.id = 167;

/***/ }),

/***/ 210:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"../pages/app-config/app-config.module": [
		800,
		10
	],
	"../pages/calendar/calendar.module": [
		801,
		9
	],
	"../pages/config/config.module": [
		802,
		8
	],
	"../pages/document-detail/document-detail.module": [
		803,
		7
	],
	"../pages/documents/documents.module": [
		804,
		6
	],
	"../pages/login/login.module": [
		805,
		5
	],
	"../pages/notification-detail/notification-detail.module": [
		806,
		4
	],
	"../pages/notifications/notifications.module": [
		807,
		3
	],
	"../pages/tabs/tabs.module": [
		808,
		2
	],
	"../pages/user-config/user-config.module": [
		809,
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
webpackAsyncContext.id = 210;
module.exports = webpackAsyncContext;

/***/ }),

/***/ 218:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GedsysApiService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(115);
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



var GedsysApiService = (function () {
    function GedsysApiService(events, http) {
        var _this = this;
        this.events = events;
        this.http = http;
        this.baseURL = "https://gedsys-8e06b.firebaseio.com/";
        this.notifications = [];
        this.users = [
            {
                name: 'Admin',
                username: 'admin',
                password: 'admin',
                img: 'http://www.xsjjys.com/data/out/96/WHDQ-512397052.jpg',
                role: 'Developer',
                active: false,
                id: 1,
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
            },
            {
                name: 'Base 16',
                username: 'base16dev',
                password: 'admin123',
                img: 'https://content-static.upwork.com/uploads/2014/10/02123010/profile-photo_friendly.jpg',
                role: 'Admin',
                active: false,
                id: 2,
                notifications: [
                    {
                        title: 'Notificacion 1 - user 2',
                        archived: false,
                        date: {
                            created: 1508441301333,
                            max: 1508475600000
                        }
                    },
                    {
                        title: 'Notificacion 2 - user 2',
                        archived: false,
                        date: {
                            created: 1508441301333,
                            max: 1508562000000
                        }
                    },
                    {
                        title: 'Notificacion 3 - user 2',
                        archived: false,
                        date: {
                            created: 1508441301333,
                            max: 1508475600000
                        }
                    },
                    {
                        title: 'Notificacion 4 - user 2',
                        archived: false,
                        date: {
                            created: 1508441301333,
                            max: 1508441301333
                        }
                    },
                    {
                        title: 'Notificacion 5 - user 2',
                        archived: false,
                        date: {
                            created: 1508441301333,
                            max: 1508441301333
                        }
                    }
                ]
            },
            {
                name: 'User 2',
                role: 'Admin',
                username: 'user2',
                password: 'user2123',
                active: false,
                id: 3,
                notifications: [
                    {
                        title: 'Notificacion 1',
                        archived: false,
                        date: {
                            created: 1505001600,
                            max: 1508803200
                        }
                    },
                    {
                        title: 'Notificacion 2',
                        archived: false,
                        date: {
                            created: 1505021600,
                            max: 1508802200
                        }
                    },
                    {
                        title: 'Notificacion 3',
                        archived: false,
                        date: {
                            created: 1505101600,
                            max: 1509803200
                        }
                    },
                    {
                        title: 'Notificacion 4',
                        archived: false,
                        date: {
                            created: 1505002300,
                            max: 1508805200
                        }
                    }, {
                        title: 'Notificacion 5',
                        archived: false,
                        date: {
                            created: 1501001600,
                            max: 1508303200
                        }
                    }
                ]
            },
            {
                name: 'User 3',
                username: 'user3',
                password: 'user3123',
                role: 'Admin',
                active: false,
                id: 4,
                notifications: [
                    {
                        title: 'Notificacion 1',
                        archived: true,
                        date: {
                            created: 1505001600,
                            max: 1508803200
                        }
                    },
                    {
                        title: 'Notificacion 2',
                        archived: true,
                        date: {
                            created: 1505021600,
                            max: 1508802200
                        }
                    },
                    {
                        title: 'Notificacion 3',
                        archived: true,
                        date: {
                            created: 1505101600,
                            max: 1509803200
                        }
                    },
                    {
                        title: 'Notificacion 4',
                        archived: true,
                        date: {
                            created: 1505002300,
                            max: 1508805200
                        }
                    }, {
                        title: 'Notificacion 5',
                        archived: true,
                        date: {
                            created: 1501001600,
                            max: 1508303200
                        }
                    }
                ]
            }
        ];
        this.events.subscribe('userActive:change', function (index) {
            return _this.activeUser = index;
        });
        this.events.subscribe('user:updated', function (user, index) {
            return _this.users[index] = user;
        });
    }
    GedsysApiService.prototype.getNotifications = function () {
        var _this = this;
        return new Promise(function (resolve) {
            _this.http.get(_this.baseURL + "/notifications.json")
                .subscribe(function (res) { return resolve(res.json()); });
        });
    };
    GedsysApiService.prototype.pushNotifications = function (notifications) {
        var _this = this;
        return new Promise(function (resolve) {
            _this.http.put(_this.baseURL + "/notifications.json", notifications, {})
                .subscribe(function (res) { return resolve(res.json()); });
        });
    };
    return GedsysApiService;
}());
GedsysApiService = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2_ionic_angular__["g" /* Events */], __WEBPACK_IMPORTED_MODULE_1__angular_http__["a" /* Http */]])
], GedsysApiService);

//# sourceMappingURL=gedsys-api.service.js.map

/***/ }),

/***/ 263:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return HomePage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__tabs_tabs__ = __webpack_require__(87);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__config_config__ = __webpack_require__(86);
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
    function HomePage(events, navParams, navCtrl) {
        var _this = this;
        this.events = events;
        this.navParams = navParams;
        this.navCtrl = navCtrl;
        this.user = {};
        this.user = this.navParams.data;
        this.events.subscribe('user:updated', function (user) {
            _this.user = user;
        });
        this.events.subscribe('user:logOff', function () {
            _this.user = {};
        });
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
    HomePage.prototype.loadNotifications = function () {
        this.navCtrl.setRoot(__WEBPACK_IMPORTED_MODULE_2__tabs_tabs__["a" /* TabsPage */], this.user);
        this.events.publish('root:change', 1);
    };
    HomePage.prototype.loadSettings = function () {
        this.navCtrl.setRoot(__WEBPACK_IMPORTED_MODULE_3__config_config__["a" /* ConfigPage */], this.user);
        this.events.publish('root:change', 4);
    };
    HomePage.prototype.ionViewDidLoad = function () {
    };
    return HomePage;
}());
HomePage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-home',template:/*ion-inline-start:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/pages/home/home.html"*/'<ion-header>\n    <ion-navbar primary color="primary">\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>Home</ion-title>\n        <ion-buttons end *ngIf="!user.name">\n            <button ion-button color="primary" menuToggle>Login</button>\n        </ion-buttons>\n    </ion-navbar>\n</ion-header>\n\n<ion-content class="card-background-page">\n    <ion-slides pager *ngIf="!user.name">\n        <ion-slide>\n            <h2>Welcome to gedsys app</h2>\n        </ion-slide>\n        <ion-slide>\n            <h2>Please\n                <ion-row></ion-row>\n                <button ion-button menuToggle small>Login</button>\n                <ion-row></ion-row>\n                or\n                <ion-row></ion-row>\n                <button ion-button menuToggle small>Register</button>\n                <ion-row></ion-row>\n                to continue\n            </h2>\n        </ion-slide>\n    </ion-slides>\n    <ion-row *ngIf="user.name">\n        <ion-card>\n            <img [src]="user.img" *ngIf="user.img">\n            <ion-row *ngIf="!user.img">\n                <ion-icon color="primary" col-12 name="contact"></ion-icon>\n            </ion-row>\n            <ion-fab right top>\n                <button ion-fab (click)="loadSettings()">\n                    <ion-icon name="settings"></ion-icon>\n                </button>\n            </ion-fab>\n            <ion-item>\n                <h2>{{user.name}}</h2>\n                <p>{{user.role}}</p>\n            </ion-item>\n            <ion-item>\n                <span item-left>6 documents</span>\n                <button ion-button icon-left clear item-end (click)="loadNotifications()">\n                    <ion-icon name="notifications"></ion-icon>\n                    Notifications\n                </button>\n            </ion-item>\n        </ion-card>\n        <ion-card>\n            <ion-card-content>\n                <chart style="display: block;" [options]="chartOptions" type="chart"></chart>\n            </ion-card-content>\n        </ion-card>\n        <ion-card>\n            <ion-card-content>\n                <chart style="display: block;" [options]="chartOptions1" type="chart"></chart>\n            </ion-card-content>\n        </ion-card>\n    </ion-row>\n</ion-content>\n'/*ion-inline-end:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/pages/home/home.html"*/
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["g" /* Events */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */]])
], HomePage);

//# sourceMappingURL=home.js.map

/***/ }),

/***/ 428:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser_dynamic__ = __webpack_require__(429);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__app_module__ = __webpack_require__(433);


Object(__WEBPACK_IMPORTED_MODULE_0__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_1__app_module__["a" /* AppModule */]);
//# sourceMappingURL=main.js.map

/***/ }),

/***/ 433:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__ = __webpack_require__(39);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__app_component__ = __webpack_require__(476);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__pages_login_login__ = __webpack_require__(154);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__pages_home_home__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__pages_tabs_tabs__ = __webpack_require__(87);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__pages_notifications_notifications__ = __webpack_require__(156);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__pages_notification_detail_notification_detail__ = __webpack_require__(155);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__pages_calendar_calendar__ = __webpack_require__(150);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__pages_documents_documents__ = __webpack_require__(153);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__pages_document_detail_document_detail__ = __webpack_require__(152);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__pages_config_config__ = __webpack_require__(86);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__pages_user_config_user_config__ = __webpack_require__(151);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14__pages_app_config_app_config__ = __webpack_require__(149);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15__ionic_native_status_bar__ = __webpack_require__(258);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_16__ionic_native_splash_screen__ = __webpack_require__(262);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_17__angular_http__ = __webpack_require__(115);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_18_ion2_calendar__ = __webpack_require__(482);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_19_ionic2_super_tabs__ = __webpack_require__(427);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_20_ng2_pdf_viewer__ = __webpack_require__(488);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_20_ng2_pdf_viewer___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_20_ng2_pdf_viewer__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_21_angular2_highcharts__ = __webpack_require__(521);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_21_angular2_highcharts___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_21_angular2_highcharts__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_22_ng2_filter_pipe__ = __webpack_require__(528);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_22_ng2_filter_pipe___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_22_ng2_filter_pipe__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_23__pipes_moment_moment__ = __webpack_require__(530);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_24__pipes_order_by_order_by__ = __webpack_require__(531);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_25_highcharts__ = __webpack_require__(533);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_25_highcharts___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_25_highcharts__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_26_ionic2_zoom_area__ = __webpack_require__(534);
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
            __WEBPACK_IMPORTED_MODULE_24__pipes_order_by_order_by__["a" /* OrderByPipe */]
        ],
        imports: [
            __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["a" /* BrowserModule */],
            __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["l" /* IonicModule */].forRoot(__WEBPACK_IMPORTED_MODULE_3__app_component__["a" /* MyApp */], {}, {
                links: [
                    { loadChildren: '../pages/app-config/app-config.module#AppConfigPageModule', name: 'AppConfigPage', segment: 'app-config', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/calendar/calendar.module#CalendarPageModule', name: 'CalendarPage', segment: 'calendar', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/config/config.module#ConfigPageModule', name: 'ConfigPage', segment: 'config', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/document-detail/document-detail.module#DocumentDetailPageModule', name: 'DocumentDetailPage', segment: 'document-detail', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/documents/documents.module#DocumentsPageModule', name: 'DocumentsPage', segment: 'documents', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/login/login.module#LoginPageModule', name: 'LoginPage', segment: 'login', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/notification-detail/notification-detail.module#NotificationDetailPageModule', name: 'NotificationDetailPage', segment: 'notification-detail', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/notifications/notifications.module#NotificationsPageModule', name: 'NotificationsPage', segment: 'notifications', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/tabs/tabs.module#TabsPageModule', name: 'TabsPage', segment: 'tabs', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/user-config/user-config.module#UserConfigPageModule', name: 'UserConfigPage', segment: 'user-config', priority: 'low', defaultHistory: [] }
                ]
            }),
            __WEBPACK_IMPORTED_MODULE_17__angular_http__["b" /* HttpModule */],
            __WEBPACK_IMPORTED_MODULE_18_ion2_calendar__["a" /* CalendarModule */],
            __WEBPACK_IMPORTED_MODULE_19_ionic2_super_tabs__["a" /* SuperTabsModule */].forRoot(),
            __WEBPACK_IMPORTED_MODULE_22_ng2_filter_pipe__["Ng2FilterPipeModule"],
            __WEBPACK_IMPORTED_MODULE_21_angular2_highcharts__["ChartModule"].forRoot(__WEBPACK_IMPORTED_MODULE_25_highcharts__),
            __WEBPACK_IMPORTED_MODULE_26_ionic2_zoom_area__["a" /* ZoomAreaModule */].forRoot()
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
            __WEBPACK_IMPORTED_MODULE_8__pages_notification_detail_notification_detail__["a" /* NotificationDetailPage */]
        ],
        providers: [
            __WEBPACK_IMPORTED_MODULE_15__ionic_native_status_bar__["a" /* StatusBar */],
            __WEBPACK_IMPORTED_MODULE_16__ionic_native_splash_screen__["a" /* SplashScreen */],
            { provide: __WEBPACK_IMPORTED_MODULE_1__angular_core__["ErrorHandler"], useClass: __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["k" /* IonicErrorHandler */] }
        ]
    })
], AppModule);

//# sourceMappingURL=app.module.js.map

/***/ }),

/***/ 476:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MyApp; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__ionic_native_status_bar__ = __webpack_require__(258);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__ionic_native_splash_screen__ = __webpack_require__(262);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__angular_http__ = __webpack_require__(115);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__pages_home_home__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__pages_calendar_calendar__ = __webpack_require__(150);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__pages_config_config__ = __webpack_require__(86);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__pages_documents_documents__ = __webpack_require__(153);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__shared_shared__ = __webpack_require__(481);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__pages_tabs_tabs__ = __webpack_require__(87);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__pages_login_login__ = __webpack_require__(154);
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
    function MyApp(events, actionSheetCtrl, platform, statusBar, splashScreen, service) {
        var _this = this;
        this.events = events;
        this.actionSheetCtrl = actionSheetCtrl;
        this.platform = platform;
        this.statusBar = statusBar;
        this.splashScreen = splashScreen;
        this.service = service;
        this.rootPage = __WEBPACK_IMPORTED_MODULE_5__pages_home_home__["a" /* HomePage */];
        this.notifications = [];
        this.navStatus = true;
        this.loginPage = { title: 'Login', component: __WEBPACK_IMPORTED_MODULE_11__pages_login_login__["a" /* LoginPage */] };
        this.initializeApp();
        this.pages = [
            { title: 'Home', component: __WEBPACK_IMPORTED_MODULE_5__pages_home_home__["a" /* HomePage */] },
            { title: 'Notifications', component: __WEBPACK_IMPORTED_MODULE_10__pages_tabs_tabs__["a" /* TabsPage */] },
            { title: 'Documents', component: __WEBPACK_IMPORTED_MODULE_8__pages_documents_documents__["a" /* DocumentsPage */] },
            { title: 'Calendar', component: __WEBPACK_IMPORTED_MODULE_6__pages_calendar_calendar__["a" /* CalendarPage */] },
            { title: 'Configuration', component: __WEBPACK_IMPORTED_MODULE_7__pages_config_config__["a" /* ConfigPage */] }
        ];
        if (this.userActive == undefined) {
            this.navStatus = false;
        }
        this.users = this.service.users;
        this.users.map(function (user, i) {
            _this.notifications[i] = 0;
            user.notifications.map(function (notification) {
                notification.archived ? _this.notifications : _this.notifications[i]++;
                return notification;
            });
            return user;
        });
        this.events.subscribe('user:updated', function (user, index) {
            _this.users[index] = user;
            _this.notifications[index] = 0;
            user.notifications.map(function (notification) {
                notification.archived ? _this.notifications : _this.notifications[index]++;
                return notification;
            });
        });
        this.events.subscribe('user:login', function () {
            if (_this.users.length < 5) {
                var tempUsr = {
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
                console.log(_this.userActive);
                if (!_this.userActive) {
                    tempUsr.active = true;
                    _this.userActive = 0;
                }
                _this.users.push(tempUsr);
                _this.events.publish('user:updated', tempUsr, _this.users.indexOf(tempUsr));
            }
            else {
                _this.events.publish('user:max');
            }
        });
        this.events.subscribe('root:change', function (page) {
            _this.activePage = page;
        });
    }
    MyApp.prototype.toggleNav = function () {
        return this.navStatus = !this.navStatus;
    };
    MyApp.prototype.toggle = function (user, index, event) {
        var _this = this;
        if (event && event.target.className == 'button-inner') {
            var actionSheet = this.actionSheetCtrl.create({
                title: 'User options',
                buttons: [
                    {
                        text: 'Log off ' + user.name,
                        role: 'destructive',
                        handler: function () {
                            if (_this.userActive == index) {
                                _this.userActive = undefined;
                                _this.events.publish('user:logOff');
                            }
                            else if (_this.userActive > index) {
                                _this.userActive--;
                            }
                            _this.users.splice(index, 1);
                            _this.notifications.splice(index, 1);
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
        this.users.map(function (userMap) {
            userMap.active = false;
            return userMap;
        });
        user.active = !user.active;
        this.navStatus = !this.navStatus;
        this.notifications[index] = 0;
        this.users[this.userActive].notifications.map(function (notification) {
            notification.archived ? _this.notifications[index] : _this.notifications[index]++;
            return notification;
        });
    };
    MyApp.prototype.initializeApp = function () {
        var _this = this;
        this.platform.ready().then(function () {
            // Okay, so the platform is ready and our plugins are available.
            // Here you can do any higher level native things you might need.
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
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["ViewChild"])(__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* Nav */]),
    __metadata("design:type", __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* Nav */])
], MyApp.prototype, "nav", void 0);
MyApp = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({template:/*ion-inline-start:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/app/app.html"*/'<ion-menu [content]="content">\n    <ion-header>\n        <ion-navbar [color]="navStatus ? \'primary\' : \'\'">\n            <ion-buttons end *ngIf="userActive >= 0">\n                <button ion-button icon-only (click)="toggleNav()">\n                    <ion-icon [name]="navStatus ? \'arrow-down\':\'arrow-up\'"></ion-icon>\n                </button>\n            </ion-buttons>\n            <span style="padding : 0 15px;" [style.color]="navStatus ? \'white\' : \'inherit\'" *ngIf="userActive >= 0">\n                {{navStatus ? users[userActive].name: \'Users\'}}\n            </span>\n            <ion-title *ngIf="userActive == undefined">\n                Login\n            </ion-title>\n            <ion-buttons start *ngIf="navStatus">\n                <button menuClose ion-button round item-start *ngIf="users[userActive].img"\n                        (click)="openPage(pages[4],users[userActive])">\n                    <img width="50px" style="border-radius: 50px;padding : 3px;"\n                         [src]="users[userActive].img">\n                </button>\n                <button menuClose *ngIf="!users[userActive].img" ion-button icon-only\n                        (click)="openPage(pages[4],users[userActive])">\n                    <ion-icon name="contact"></ion-icon>\n                </button>\n            </ion-buttons>\n        </ion-navbar>\n    </ion-header>\n    <ion-content>\n        <ion-toolbar [class]="navStatus ? \'\' : \'active\'">\n            <ion-list no-lines no-border>\n                <ng-container *ngFor="let user of users; let i = index">\n                    <ion-card (click)="toggle(user,i,$event)">\n                        <ion-item [color]="user.active ? \'primary\' : \'\'">\n                            <ion-avatar item-start *ngIf="user.img">\n                                <img [src]="user.img">\n                            </ion-avatar>\n                            <ion-icon *ngIf="!user.img" [color]="user.active ? \'light\' : \'primary\'" name="contact"\n                                      [isActive]="user.active == true ? \'primary\' : \'\'"\n                                      item-start></ion-icon>\n                            <h2 [style.color]="user.active ? \'white\' : \'inherit\'">{{user.name}}</h2>\n                            <p>{{user.role}}\n                                <ion-badge color="danger" item-end>{{notifications[i]}}</ion-badge>\n                            </p>\n                            <button ion-button icon-left clear item-end>\n                                <ion-icon name="more" [color]="user.active ? \'secondary\' : \'primary\'"></ion-icon>\n                            </button>\n                        </ion-item>\n                    </ion-card>\n                </ng-container>\n                <ion-card *ngIf="users.length < 5">\n                    <button menuClose ion-item (click)="openPage(loginPage)">\n                        <ion-icon name="person-add" item-start color="primary"></ion-icon>\n                        {{users.length > 0 ? \'Login with another account...\' : \'Login...\'}}\n                    </button>\n                </ion-card>\n            </ion-list>\n        </ion-toolbar>\n        <ion-list no-border no-lines="" *ngIf="userActive >= 0">\n            <button [class.active]="checkActivePage(i)" menuClose ion-item *ngFor="let p of pages, let i = index" (click)="openPage(p,users[userActive],i)">\n                {{p.title}}\n            </button>\n        </ion-list>\n    </ion-content>\n\n</ion-menu>\n\n<!-- Disable swipe-to-go-back because it\'s poor UX to combine STGB with side menus -->\n<ion-nav no-lines [root]="rootPage" #content\n         swipeBackEnabled="false"></ion-nav>\n'/*ion-inline-end:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/app/app.html"*/,
        providers: [
            __WEBPACK_IMPORTED_MODULE_9__shared_shared__["a" /* GedsysApiService */],
            __WEBPACK_IMPORTED_MODULE_4__angular_http__["b" /* HttpModule */]
        ]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["g" /* Events */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* ActionSheetController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["t" /* Platform */], __WEBPACK_IMPORTED_MODULE_2__ionic_native_status_bar__["a" /* StatusBar */], __WEBPACK_IMPORTED_MODULE_3__ionic_native_splash_screen__["a" /* SplashScreen */], __WEBPACK_IMPORTED_MODULE_9__shared_shared__["a" /* GedsysApiService */]])
], MyApp);

//# sourceMappingURL=app.component.js.map

/***/ }),

/***/ 481:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__gedsys_api_service__ = __webpack_require__(218);
/* harmony namespace reexport (by used) */ __webpack_require__.d(__webpack_exports__, "a", function() { return __WEBPACK_IMPORTED_MODULE_0__gedsys_api_service__["a"]; });

//# sourceMappingURL=shared.js.map

/***/ }),

/***/ 484:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"./af": 264,
	"./af.js": 264,
	"./ar": 265,
	"./ar-dz": 266,
	"./ar-dz.js": 266,
	"./ar-kw": 267,
	"./ar-kw.js": 267,
	"./ar-ly": 268,
	"./ar-ly.js": 268,
	"./ar-ma": 269,
	"./ar-ma.js": 269,
	"./ar-sa": 270,
	"./ar-sa.js": 270,
	"./ar-tn": 271,
	"./ar-tn.js": 271,
	"./ar.js": 265,
	"./az": 272,
	"./az.js": 272,
	"./be": 273,
	"./be.js": 273,
	"./bg": 274,
	"./bg.js": 274,
	"./bm": 275,
	"./bm.js": 275,
	"./bn": 276,
	"./bn.js": 276,
	"./bo": 277,
	"./bo.js": 277,
	"./br": 278,
	"./br.js": 278,
	"./bs": 279,
	"./bs.js": 279,
	"./ca": 280,
	"./ca.js": 280,
	"./cs": 281,
	"./cs.js": 281,
	"./cv": 282,
	"./cv.js": 282,
	"./cy": 283,
	"./cy.js": 283,
	"./da": 284,
	"./da.js": 284,
	"./de": 285,
	"./de-at": 286,
	"./de-at.js": 286,
	"./de-ch": 287,
	"./de-ch.js": 287,
	"./de.js": 285,
	"./dv": 288,
	"./dv.js": 288,
	"./el": 289,
	"./el.js": 289,
	"./en-au": 290,
	"./en-au.js": 290,
	"./en-ca": 291,
	"./en-ca.js": 291,
	"./en-gb": 292,
	"./en-gb.js": 292,
	"./en-ie": 293,
	"./en-ie.js": 293,
	"./en-nz": 294,
	"./en-nz.js": 294,
	"./eo": 295,
	"./eo.js": 295,
	"./es": 296,
	"./es-do": 297,
	"./es-do.js": 297,
	"./es-us": 298,
	"./es-us.js": 298,
	"./es.js": 296,
	"./et": 299,
	"./et.js": 299,
	"./eu": 300,
	"./eu.js": 300,
	"./fa": 301,
	"./fa.js": 301,
	"./fi": 302,
	"./fi.js": 302,
	"./fo": 303,
	"./fo.js": 303,
	"./fr": 304,
	"./fr-ca": 305,
	"./fr-ca.js": 305,
	"./fr-ch": 306,
	"./fr-ch.js": 306,
	"./fr.js": 304,
	"./fy": 307,
	"./fy.js": 307,
	"./gd": 308,
	"./gd.js": 308,
	"./gl": 309,
	"./gl.js": 309,
	"./gom-latn": 310,
	"./gom-latn.js": 310,
	"./gu": 311,
	"./gu.js": 311,
	"./he": 312,
	"./he.js": 312,
	"./hi": 313,
	"./hi.js": 313,
	"./hr": 314,
	"./hr.js": 314,
	"./hu": 315,
	"./hu.js": 315,
	"./hy-am": 316,
	"./hy-am.js": 316,
	"./id": 317,
	"./id.js": 317,
	"./is": 318,
	"./is.js": 318,
	"./it": 319,
	"./it.js": 319,
	"./ja": 320,
	"./ja.js": 320,
	"./jv": 321,
	"./jv.js": 321,
	"./ka": 322,
	"./ka.js": 322,
	"./kk": 323,
	"./kk.js": 323,
	"./km": 324,
	"./km.js": 324,
	"./kn": 325,
	"./kn.js": 325,
	"./ko": 326,
	"./ko.js": 326,
	"./ky": 327,
	"./ky.js": 327,
	"./lb": 328,
	"./lb.js": 328,
	"./lo": 329,
	"./lo.js": 329,
	"./lt": 330,
	"./lt.js": 330,
	"./lv": 331,
	"./lv.js": 331,
	"./me": 332,
	"./me.js": 332,
	"./mi": 333,
	"./mi.js": 333,
	"./mk": 334,
	"./mk.js": 334,
	"./ml": 335,
	"./ml.js": 335,
	"./mr": 336,
	"./mr.js": 336,
	"./ms": 337,
	"./ms-my": 338,
	"./ms-my.js": 338,
	"./ms.js": 337,
	"./my": 339,
	"./my.js": 339,
	"./nb": 340,
	"./nb.js": 340,
	"./ne": 341,
	"./ne.js": 341,
	"./nl": 342,
	"./nl-be": 343,
	"./nl-be.js": 343,
	"./nl.js": 342,
	"./nn": 344,
	"./nn.js": 344,
	"./pa-in": 345,
	"./pa-in.js": 345,
	"./pl": 346,
	"./pl.js": 346,
	"./pt": 347,
	"./pt-br": 348,
	"./pt-br.js": 348,
	"./pt.js": 347,
	"./ro": 349,
	"./ro.js": 349,
	"./ru": 350,
	"./ru.js": 350,
	"./sd": 351,
	"./sd.js": 351,
	"./se": 352,
	"./se.js": 352,
	"./si": 353,
	"./si.js": 353,
	"./sk": 354,
	"./sk.js": 354,
	"./sl": 355,
	"./sl.js": 355,
	"./sq": 356,
	"./sq.js": 356,
	"./sr": 357,
	"./sr-cyrl": 358,
	"./sr-cyrl.js": 358,
	"./sr.js": 357,
	"./ss": 359,
	"./ss.js": 359,
	"./sv": 360,
	"./sv.js": 360,
	"./sw": 361,
	"./sw.js": 361,
	"./ta": 362,
	"./ta.js": 362,
	"./te": 363,
	"./te.js": 363,
	"./tet": 364,
	"./tet.js": 364,
	"./th": 365,
	"./th.js": 365,
	"./tl-ph": 366,
	"./tl-ph.js": 366,
	"./tlh": 367,
	"./tlh.js": 367,
	"./tr": 368,
	"./tr.js": 368,
	"./tzl": 369,
	"./tzl.js": 369,
	"./tzm": 370,
	"./tzm-latn": 371,
	"./tzm-latn.js": 371,
	"./tzm.js": 370,
	"./uk": 372,
	"./uk.js": 372,
	"./ur": 373,
	"./ur.js": 373,
	"./uz": 374,
	"./uz-latn": 375,
	"./uz-latn.js": 375,
	"./uz.js": 374,
	"./vi": 376,
	"./vi.js": 376,
	"./x-pseudo": 377,
	"./x-pseudo.js": 377,
	"./yo": 378,
	"./yo.js": 378,
	"./zh-cn": 379,
	"./zh-cn.js": 379,
	"./zh-hk": 380,
	"./zh-hk.js": 380,
	"./zh-tw": 381,
	"./zh-tw.js": 381
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
webpackContext.id = 484;

/***/ }),

/***/ 495:
/***/ (function(module, exports) {

/* (ignored) */

/***/ }),

/***/ 510:
/***/ (function(module, exports) {

/* (ignored) */

/***/ }),

/***/ 511:
/***/ (function(module, exports) {

/* (ignored) */

/***/ }),

/***/ 512:
/***/ (function(module, exports) {

/* (ignored) */

/***/ }),

/***/ 530:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MomentPipe; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_moment__ = __webpack_require__(2);
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

/***/ 531:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return OrderByPipe; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_lodash__ = __webpack_require__(532);
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

/***/ 86:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ConfigPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__user_config_user_config__ = __webpack_require__(151);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__app_config_app_config__ = __webpack_require__(149);
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
    function ConfigPage(navCtrl, navParams) {
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.page1 = __WEBPACK_IMPORTED_MODULE_2__user_config_user_config__["a" /* UserConfigPage */];
        this.page2 = __WEBPACK_IMPORTED_MODULE_3__app_config_app_config__["a" /* AppConfigPage */];
    }
    ConfigPage.prototype.ionViewDidLoad = function () {
        console.log('ionViewDidLoad ConfigPage');
    };
    return ConfigPage;
}());
ConfigPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-config',template:/*ion-inline-start:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/pages/config/config.html"*/'<!--\n  Generated template for the ConfigPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header>\n    <ion-navbar color="primary">\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>Configuration</ion-title>\n    </ion-navbar>\n</ion-header>\n\n\n<ion-content>\n    <super-tabs toolbarBackground="primary" toolbarColor="light" indicatorColor="light">\n        <super-tab [root]="page1" title="User" icon="person">test</super-tab>\n        <super-tab [root]="page2" title="Application" icon="cog"></super-tab>\n    </super-tabs>\n</ion-content>\n'/*ion-inline-end:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/pages/config/config.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], ConfigPage);

//# sourceMappingURL=config.js.map

/***/ }),

/***/ 87:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return TabsPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__notifications_notifications__ = __webpack_require__(156);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__shared_gedsys_api_service__ = __webpack_require__(218);
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
    function TabsPage(events, service, navCtrl, navParams) {
        var _this = this;
        this.events = events;
        this.service = service;
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        this.unarchived = __WEBPACK_IMPORTED_MODULE_2__notifications_notifications__["a" /* NotificationsPage */];
        this.notificationsCount = 0;
        this.archivedCount = 0;
        this.data = {
            user: '',
            index: 0
        };
        this.navParams.data.notifications.map(function (notification) {
            notification.archived ? _this.archivedCount++ : _this.notificationsCount++;
            return notification;
        });
        this.data.index = this.service.activeUser;
        this.data.user = this.service.users[this.data.index];
        this.events.subscribe('user:updated', function (user, index) {
            _this.data.user = user;
            _this.data.index = index;
            _this.notificationsCount = 0;
            _this.archivedCount = 0;
            _this.data.user.notifications.map(function (notification) {
                notification.archived ? _this.archivedCount++ : _this.notificationsCount++;
            });
        });
    }
    TabsPage.prototype.ionViewDidEnter = function () {
    };
    return TabsPage;
}());
TabsPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-tabs',template:/*ion-inline-start:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/pages/tabs/tabs.html"*/'<!--\n  Generated template for the TabsPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n\n<ion-tabs color="primary">\n  <ion-tab [rootParams]="data" [root]="unarchived" tabIcon="alert" tabTitle="Notifications"\n           [tabBadge]="notificationsCount" tabBadgeStyle="danger"></ion-tab>\n  <ion-tab [rootParams]="data" [root]="unarchived" tabIcon="archive" tabTitle="Archived"\n           [tabBadge]="archivedCount" tabBadgeStyle="light"></ion-tab>\n</ion-tabs>\n'/*ion-inline-end:"/home/alejandro/Documents/gedsys/GedsysAppV2/src/pages/tabs/tabs.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["g" /* Events */], __WEBPACK_IMPORTED_MODULE_3__shared_gedsys_api_service__["a" /* GedsysApiService */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["q" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* NavParams */]])
], TabsPage);

//# sourceMappingURL=tabs.js.map

/***/ })

},[428]);
//# sourceMappingURL=main.js.map