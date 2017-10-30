webpackJsonp([9],{

/***/ 114:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppConfigPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(8);
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
        selector: 'page-app-config',template:/*ion-inline-start:"/home/alejandro/Documents/GEDSYS/src/pages/app-config/app-config.html"*/'<ion-content padding>\n\n</ion-content>\n'/*ion-inline-end:"/home/alejandro/Documents/GEDSYS/src/pages/app-config/app-config.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["r" /* NavParams */]])
], AppConfigPage);

//# sourceMappingURL=app-config.js.map

/***/ }),

/***/ 115:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return CalendarPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(8);
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
        selector: 'page-calendar',template:/*ion-inline-start:"/home/alejandro/Documents/GEDSYS/src/pages/calendar/calendar.html"*/'<!--\n  Generated template for the CalendarPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Calendario</ion-title>\n  </ion-navbar>\n</ion-header>\n\n\n<ion-content>\n  <ion-card>\n    <ion-card-content>\n      <ion-card-title color="primary">\n        Upcoming\n      </ion-card-title>\n      <ion-calendar [(ngModel)]="date"\n                    (onChange)="onChange($event)"\n                    [options]="optionsRange"\n                    [type]="type"\n                    [format]="\'YYYY-MM-DD\'" color="primary">\n      </ion-calendar>\n    </ion-card-content>\n  </ion-card>\n  <ion-list>\n    <ng-container *ngFor="let date_i of date">\n      <ion-item>{{date_i}}</ion-item>\n    </ng-container>\n  </ion-list>\n</ion-content>\n'/*ion-inline-end:"/home/alejandro/Documents/GEDSYS/src/pages/calendar/calendar.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["r" /* NavParams */]])
], CalendarPage);

//# sourceMappingURL=calendar.js.map

/***/ }),

/***/ 116:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ConfigPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__user_config_user_config__ = __webpack_require__(117);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__app_config_app_config__ = __webpack_require__(114);
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
        selector: 'page-config',template:/*ion-inline-start:"/home/alejandro/Documents/GEDSYS/src/pages/config/config.html"*/'<!--\n  Generated template for the ConfigPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header>\n    <ion-navbar>\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>Configuration</ion-title>\n    </ion-navbar>\n</ion-header>\n\n\n<ion-content>\n    <super-tabs color="primary">\n        <super-tab [root]="page1" title="User">test</super-tab>\n        <super-tab [root]="page2" title="Application"></super-tab>\n    </super-tabs>\n</ion-content>\n'/*ion-inline-end:"/home/alejandro/Documents/GEDSYS/src/pages/config/config.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["r" /* NavParams */]])
], ConfigPage);

//# sourceMappingURL=config.js.map

/***/ }),

/***/ 117:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserConfigPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(8);
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
        selector: 'page-user-config',template:/*ion-inline-start:"/home/alejandro/Documents/GEDSYS/src/pages/user-config/user-config.html"*/'<ion-content padding>\n\n</ion-content>\n'/*ion-inline-end:"/home/alejandro/Documents/GEDSYS/src/pages/user-config/user-config.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["r" /* NavParams */]])
], UserConfigPage);

//# sourceMappingURL=user-config.js.map

/***/ }),

/***/ 118:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DocumentsPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(8);
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
    DocumentsPage.prototype.ionViewDidLoad = function () {
    };
    return DocumentsPage;
}());
DocumentsPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-documents',template:/*ion-inline-start:"/home/alejandro/Documents/GEDSYS/src/pages/documents/documents.html"*/'<!--\n  Generated template for the DocumentsPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header>\n    <ion-navbar primary>\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>Documentos</ion-title>\n    </ion-navbar>\n</ion-header>\n\n\n<ion-content padding>\n    <ng-container *ngFor="let pdf of pdfSrc">\n        <ion-card>\n            <ion-item>\n                <ion-avatar item-start>\n                    <img src="https://instagram.feoh4-2.fna.fbcdn.net/t51.2885-15/e35/20633877_127279737891004_1725216207352627200_n.jpg">\n                </ion-avatar>\n                <h2>Marty McFly</h2>\n                <p>November 5, 1955</p>\n            </ion-item>\n            <ion-grid style="padding: 0;">\n                <ion-row>\n                    <ion-col style="padding: 0;" col-12>\n                        <pdf-viewer [original-size]="false"\n                                    [src]="pdf" [render-text]="false" style="display : block;"></pdf-viewer>\n                    </ion-col>\n                </ion-row>\n            </ion-grid>\n            <ion-card-content>\n                <p>Wait a minute. Wait a minute, Doc. Uhhh... Are you telling me that you built a time machine... out of\n                    a\n                    DeLorean?! Whoa. This is heavy.</p>\n            </ion-card-content>\n            <ion-row>\n                <ion-col>\n                    <button ion-button icon-left clear small>\n                        <ion-icon name="reply"></ion-icon>\n                        <div>12 Likes</div>\n                    </button>\n                </ion-col>\n                <ion-col>\n                    <button ion-button icon-left clear small>\n                        <ion-icon name="text"></ion-icon>\n                        <div>4 Comments</div>\n                    </button>\n                </ion-col>\n                <ion-col center text-center>\n                    <ion-note>\n                        11h ago\n                    </ion-note>\n                </ion-col>\n            </ion-row>\n        </ion-card>\n    </ng-container>\n</ion-content>\n'/*ion-inline-end:"/home/alejandro/Documents/GEDSYS/src/pages/documents/documents.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["r" /* NavParams */]])
], DocumentsPage);

//# sourceMappingURL=documents.js.map

/***/ }),

/***/ 119:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return LoginPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(8);
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
    function LoginPage(navCtrl, navParams) {
        this.navCtrl = navCtrl;
        this.navParams = navParams;
    }
    LoginPage.prototype.ionViewDidLoad = function () {
        console.log('ionViewDidLoad LoginPage');
    };
    return LoginPage;
}());
LoginPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-login',template:/*ion-inline-start:"/home/alejandro/Documents/GEDSYS/src/pages/login/login.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Login</ion-title>\n  </ion-navbar>\n</ion-header>\n<ion-content class="login-content" padding>\n  <ion-row top class="logo-row">\n    <ion-col></ion-col>\n    <ion-col width-67>\n      <img src="http://placehold.it/300x200"/>\n    </ion-col>\n    <ion-col></ion-col>\n  </ion-row>\n  <div class="login-box">\n    <form #registerForm="ngForm">\n      <ion-row center>\n        <ion-col>\n          <ion-list inset>\n            <ion-item>\n              <ion-label floating color="primary">Email</ion-label>\n              <ion-input type="text"  name="email" required></ion-input>\n            </ion-item>\n            <ion-item>\n              <ion-label floating color="primary">Password</ion-label>\n              <ion-input color="primary" type="password" name="password" required></ion-input>\n            </ion-item>\n          </ion-list>\n        </ion-col>\n      </ion-row>\n\n      <ion-row bottom>\n        <ion-col class="signup-col">\n          <button ion-button class="submit-btn" outline block type="submit" disabled>Login</button>\n          <button ion-button class="register-btn" block clear>Create New Account</button>\n        </ion-col>\n      </ion-row>\n\n    </form>\n  </div>\n</ion-content>'/*ion-inline-end:"/home/alejandro/Documents/GEDSYS/src/pages/login/login.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["r" /* NavParams */]])
], LoginPage);

//# sourceMappingURL=login.js.map

/***/ }),

/***/ 120:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return NotificationsPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(8);
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
    NotificationsPage.prototype.archive = function (notification) {
        this.index ? notification.archived = false : notification.archived = true;
        var loading = this.loadingCtrl.create({
            content: 'Archiving, please wait....'
        });
        loading.present();
        var toast = this.toastCtrl.create({
            message: 'Succesfully archived!',
            duration: 1500,
            position: 'top',
            showCloseButton: true,
            dismissOnPageChange: true,
            cssClass: 'success'
        });
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
    return NotificationsPage;
}());
NotificationsPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-notifications',template:/*ion-inline-start:"/home/alejandro/Documents/GEDSYS/src/pages/notifications/notifications.html"*/'<!--\n  Generated template for the NotificationsPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n<ion-header>\n    <ion-navbar>\n        <button ion-button menuToggle>\n            <ion-icon name="menu"></ion-icon>\n        </button>\n        <ion-title>{{index ? \'Archived\' : \'Notifications\'}}</ion-title>\n    </ion-navbar>\n</ion-header>\n<ion-content>\n    <ion-toolbar *ngIf="ammount > 0" color="primary">\n        <ion-searchbar (ionInput)="filter($event)"></ion-searchbar>\n    </ion-toolbar>\n    <ion-refresher (ionRefresh)="refresh($event)">\n        <ion-refresher-content\n                pullingIcon="arrow-dropdown"\n                pullingText="Pull to refresh"\n                refreshingText="Refreshing...">\n        </ion-refresher-content>\n    </ion-refresher>\n    <ion-list no-lines no-border>\n        <ion-item-divider color="primary" *ngIf="filterStr.title">Search for: {{filterStr.title}}</ion-item-divider>\n        <ion-item *ngIf="ammount == 0">{{index ? "No archived notifications" : "No pending notifications"}}</ion-item>\n        <ng-container *ngFor="let notification of user.notifications | filterBy: filterStr">\n            <ion-item-sliding *ngIf="index == 0 && !notification.archived">\n                <ion-item>\n                    {{notification.title}}\n                </ion-item>\n                <ion-item-options side="right" (ionSwipe)="archive(notification)">\n                    <button ion-button expandable (click)="archive(notification)">Archive</button>\n                </ion-item-options>\n            </ion-item-sliding>\n        </ng-container>\n        <ng-container *ngFor="let notification of user.notifications | filterBy: filterStr">\n            <ion-item-sliding *ngIf="index == 1 && notification.archived">\n                <ion-item>\n                    {{notification.title}}\n                </ion-item>\n                <ion-item-options side="right" (ionSwipe)="archive(notification)">\n                    <button ion-button expandable (click)="archive(notification)">Unarchive</button>\n                </ion-item-options>\n            </ion-item-sliding>\n        </ng-container>\n    </ion-list>\n</ion-content>\n'/*ion-inline-end:"/home/alejandro/Documents/GEDSYS/src/pages/notifications/notifications.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["g" /* Events */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["m" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["u" /* ToastController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["r" /* NavParams */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* ActionSheetController */]])
], NotificationsPage);

//# sourceMappingURL=notifications.js.map

/***/ }),

/***/ 121:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return TabsPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__notifications_notifications__ = __webpack_require__(120);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__shared_gedsys_api_service__ = __webpack_require__(178);
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
        selector: 'page-tabs',template:/*ion-inline-start:"/home/alejandro/Documents/GEDSYS/src/pages/tabs/tabs.html"*/'<!--\n  Generated template for the TabsPage page.\n\n  See http://ionicframework.com/docs/components/#navigation for more info on\n  Ionic pages and navigation.\n-->\n\n<ion-tabs>\n  <ion-tab [rootParams]="data" [root]="unarchived" tabIcon="alert" tabTitle="Notifications"\n           [tabBadge]="notificationsCount" tabBadgeStyle="danger"></ion-tab>\n  <ion-tab [rootParams]="data" [root]="unarchived" tabIcon="archive" tabTitle="Archived"\n           [tabBadge]="archivedCount"></ion-tab>\n</ion-tabs>\n'/*ion-inline-end:"/home/alejandro/Documents/GEDSYS/src/pages/tabs/tabs.html"*/,
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["g" /* Events */], __WEBPACK_IMPORTED_MODULE_3__shared_gedsys_api_service__["a" /* GedsysApiService */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["r" /* NavParams */]])
], TabsPage);

//# sourceMappingURL=tabs.js.map

/***/ }),

/***/ 130:
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
webpackEmptyAsyncContext.id = 130;

/***/ }),

/***/ 171:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"../pages/app-config/app-config.module": [
		480,
		8
	],
	"../pages/calendar/calendar.module": [
		481,
		7
	],
	"../pages/config/config.module": [
		482,
		6
	],
	"../pages/documents/documents.module": [
		483,
		5
	],
	"../pages/login/login.module": [
		484,
		4
	],
	"../pages/notifications/notifications.module": [
		485,
		3
	],
	"../pages/tabs/tabs.module": [
		486,
		2
	],
	"../pages/user-config/user-config.module": [
		487,
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
webpackAsyncContext.id = 171;
module.exports = webpackAsyncContext;

/***/ }),

/***/ 178:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GedsysApiService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(89);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_ionic_angular__ = __webpack_require__(8);
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
                name: 'Alejandro Chaves',
                username: 'alejandrochvs',
                password: 'alejandrochvs123',
                img: 'https://instagram.feoh4-1.fna.fbcdn.net/t51.2885-19/s320x320/16228980_370071743368546_5430692052201373696_a.jpg',
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
                    }, {
                        title: 'Notificacion 5 - user 1',
                        archived: true,
                        date: {
                            created: 1501001600,
                            max: 1508303200
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
                            created: 1505001600,
                            max: 1508803200
                        }
                    },
                    {
                        title: 'Notificacion 2 - user 2',
                        archived: true,
                        date: {
                            created: 1505021600,
                            max: 1508802200
                        }
                    },
                    {
                        title: 'Notificacion 3 - user 2',
                        archived: true,
                        date: {
                            created: 1505101600,
                            max: 1509803200
                        }
                    },
                    {
                        title: 'Notificacion 4 - user 2',
                        archived: false,
                        date: {
                            created: 1505002300,
                            max: 1508805200
                        }
                    },
                    {
                        title: 'Notificacion 5 - user 2',
                        archived: true,
                        date: {
                            created: 1501001600,
                            max: 1508303200
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

/***/ 222:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return HomePage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(8);
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
    function HomePage(navCtrl) {
        this.navCtrl = navCtrl;
        this.tap = 0;
        this.swipe = 0;
        this.swipeLeft = 0;
        this.swipeRight = 0;
    }
    HomePage.prototype.swipeEvent = function (event) {
        console.log(event);
        if (event.deltaX < 0) {
            this.swipeLeft++;
        }
        else {
            this.swipeRight++;
        }
        this.swipe++;
    };
    HomePage.prototype.tapEvent = function (event) {
        this.tap++;
        console.log(event);
    };
    HomePage.prototype.notify = function () {
    };
    return HomePage;
}());
HomePage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'page-home',template:/*ion-inline-start:"/home/alejandro/Documents/GEDSYS/src/pages/home/home.html"*/'<ion-header>\n  <ion-navbar primary>\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Home</ion-title>\n  </ion-navbar>\n</ion-header>\n\n<ion-content padding>\n  <ion-card (tap)="tapEvent($event)" (swipe)="swipeEvent($event)">\n    <ion-item>\n      Tapped: {{tap}} times\n    </ion-item>\n    <ion-item>\n      Swiped: {{swipe}} times\n    </ion-item>\n    <ion-item>\n      Left: {{swipeLeft}}\n    </ion-item>\n    <ion-item>\n      Right: {{swipeRight}}\n    </ion-item>\n  </ion-card>\n  <button ion-button block (click)="notify()">Notify!</button>\n</ion-content>\n'/*ion-inline-end:"/home/alejandro/Documents/GEDSYS/src/pages/home/home.html"*/
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* NavController */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["p" /* NavController */]) === "function" && _a || Object])
], HomePage);

var _a;
//# sourceMappingURL=home.js.map

/***/ }),

/***/ 361:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser_dynamic__ = __webpack_require__(362);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__app_module__ = __webpack_require__(379);


Object(__WEBPACK_IMPORTED_MODULE_0__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_1__app_module__["a" /* AppModule */]);
//# sourceMappingURL=main.js.map

/***/ }),

/***/ 379:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__ = __webpack_require__(31);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_ionic_angular__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__app_component__ = __webpack_require__(431);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__pages_home_home__ = __webpack_require__(222);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__pages_notifications_notifications__ = __webpack_require__(120);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__pages_calendar_calendar__ = __webpack_require__(115);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__pages_config_config__ = __webpack_require__(116);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__pages_documents_documents__ = __webpack_require__(118);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__ionic_native_status_bar__ = __webpack_require__(218);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__ionic_native_splash_screen__ = __webpack_require__(221);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__angular_http__ = __webpack_require__(89);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__pages_tabs_tabs__ = __webpack_require__(121);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13_ion2_calendar__ = __webpack_require__(438);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14_ionic2_super_tabs__ = __webpack_require__(360);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15_ng2_filter_pipe__ = __webpack_require__(444);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15_ng2_filter_pipe___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_15_ng2_filter_pipe__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_16__pages_login_login__ = __webpack_require__(119);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_17__pages_user_config_user_config__ = __webpack_require__(117);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_18__pages_app_config_app_config__ = __webpack_require__(114);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_19_ng2_pdf_viewer__ = __webpack_require__(446);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_19_ng2_pdf_viewer___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_19_ng2_pdf_viewer__);
throw new Error("Cannot find module \"@ionic-native/local-notifications\"");
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
            __WEBPACK_IMPORTED_MODULE_4__pages_home_home__["a" /* HomePage */],
            __WEBPACK_IMPORTED_MODULE_5__pages_notifications_notifications__["a" /* NotificationsPage */],
            __WEBPACK_IMPORTED_MODULE_6__pages_calendar_calendar__["a" /* CalendarPage */],
            __WEBPACK_IMPORTED_MODULE_7__pages_config_config__["a" /* ConfigPage */],
            __WEBPACK_IMPORTED_MODULE_8__pages_documents_documents__["a" /* DocumentsPage */],
            __WEBPACK_IMPORTED_MODULE_12__pages_tabs_tabs__["a" /* TabsPage */],
            __WEBPACK_IMPORTED_MODULE_16__pages_login_login__["a" /* LoginPage */],
            __WEBPACK_IMPORTED_MODULE_17__pages_user_config_user_config__["a" /* UserConfigPage */],
            __WEBPACK_IMPORTED_MODULE_18__pages_app_config_app_config__["a" /* AppConfigPage */],
            __WEBPACK_IMPORTED_MODULE_19_ng2_pdf_viewer__["PdfViewerComponent"]
        ],
        imports: [
            __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["a" /* BrowserModule */],
            __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["k" /* IonicModule */].forRoot(__WEBPACK_IMPORTED_MODULE_3__app_component__["a" /* MyApp */], {}, {
                links: [
                    { loadChildren: '../pages/app-config/app-config.module#AppConfigPageModule', name: 'AppConfigPage', segment: 'app-config', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/calendar/calendar.module#CalendarPageModule', name: 'CalendarPage', segment: 'calendar', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/config/config.module#ConfigPageModule', name: 'ConfigPage', segment: 'config', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/documents/documents.module#DocumentsPageModule', name: 'DocumentsPage', segment: 'documents', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/login/login.module#LoginPageModule', name: 'LoginPage', segment: 'login', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/notifications/notifications.module#NotificationsPageModule', name: 'NotificationsPage', segment: 'notifications', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/tabs/tabs.module#TabsPageModule', name: 'TabsPage', segment: 'tabs', priority: 'low', defaultHistory: [] },
                    { loadChildren: '../pages/user-config/user-config.module#UserConfigPageModule', name: 'UserConfigPage', segment: 'user-config', priority: 'low', defaultHistory: [] }
                ]
            }),
            __WEBPACK_IMPORTED_MODULE_11__angular_http__["b" /* HttpModule */],
            __WEBPACK_IMPORTED_MODULE_13_ion2_calendar__["a" /* CalendarModule */],
            __WEBPACK_IMPORTED_MODULE_14_ionic2_super_tabs__["a" /* SuperTabsModule */].forRoot(),
            __WEBPACK_IMPORTED_MODULE_15_ng2_filter_pipe__["Ng2FilterPipeModule"]
        ],
        bootstrap: [__WEBPACK_IMPORTED_MODULE_2_ionic_angular__["i" /* IonicApp */]],
        entryComponents: [
            __WEBPACK_IMPORTED_MODULE_3__app_component__["a" /* MyApp */],
            __WEBPACK_IMPORTED_MODULE_4__pages_home_home__["a" /* HomePage */],
            __WEBPACK_IMPORTED_MODULE_5__pages_notifications_notifications__["a" /* NotificationsPage */],
            __WEBPACK_IMPORTED_MODULE_6__pages_calendar_calendar__["a" /* CalendarPage */],
            __WEBPACK_IMPORTED_MODULE_7__pages_config_config__["a" /* ConfigPage */],
            __WEBPACK_IMPORTED_MODULE_8__pages_documents_documents__["a" /* DocumentsPage */],
            __WEBPACK_IMPORTED_MODULE_12__pages_tabs_tabs__["a" /* TabsPage */],
            __WEBPACK_IMPORTED_MODULE_16__pages_login_login__["a" /* LoginPage */],
            __WEBPACK_IMPORTED_MODULE_17__pages_user_config_user_config__["a" /* UserConfigPage */],
            __WEBPACK_IMPORTED_MODULE_18__pages_app_config_app_config__["a" /* AppConfigPage */]
        ],
        providers: [
            __WEBPACK_IMPORTED_MODULE_9__ionic_native_status_bar__["a" /* StatusBar */],
            __WEBPACK_IMPORTED_MODULE_10__ionic_native_splash_screen__["a" /* SplashScreen */],
            { provide: __WEBPACK_IMPORTED_MODULE_1__angular_core__["ErrorHandler"], useClass: __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["j" /* IonicErrorHandler */] },
            __WEBPACK_IMPORTED_MODULE_20__ionic_native_local_notifications__["LocalNotifications"]
        ]
    })
], AppModule);

//# sourceMappingURL=app.module.js.map

/***/ }),

/***/ 431:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MyApp; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__ionic_native_status_bar__ = __webpack_require__(218);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__ionic_native_splash_screen__ = __webpack_require__(221);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__angular_http__ = __webpack_require__(89);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__pages_home_home__ = __webpack_require__(222);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__pages_calendar_calendar__ = __webpack_require__(115);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__pages_config_config__ = __webpack_require__(116);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__pages_documents_documents__ = __webpack_require__(118);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__shared_shared__ = __webpack_require__(437);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__pages_tabs_tabs__ = __webpack_require__(121);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__pages_login_login__ = __webpack_require__(119);
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
            { title: 'Notificaciones', component: __WEBPACK_IMPORTED_MODULE_10__pages_tabs_tabs__["a" /* TabsPage */] },
            { title: 'Documentos', component: __WEBPACK_IMPORTED_MODULE_8__pages_documents_documents__["a" /* DocumentsPage */] },
            { title: 'Calendario', component: __WEBPACK_IMPORTED_MODULE_6__pages_calendar_calendar__["a" /* CalendarPage */] },
            { title: 'Configuarcion', component: __WEBPACK_IMPORTED_MODULE_7__pages_config_config__["a" /* ConfigPage */] }
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
    MyApp.prototype.openPage = function (page, data) {
        this.nav.setRoot(page.component, data);
    };
    return MyApp;
}());
__decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["ViewChild"])(__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["o" /* Nav */]),
    __metadata("design:type", __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["o" /* Nav */])
], MyApp.prototype, "nav", void 0);
MyApp = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({template:/*ion-inline-start:"/home/alejandro/Documents/GEDSYS/src/app/app.html"*/'<ion-menu [content]="content">\n    <ion-header>\n        <ion-navbar [color]="navStatus ? \'primary\' : \'\'">\n            <ion-buttons end *ngIf="userActive >= 0">\n                <button ion-button icon-only (click)="toggleNav()">\n                    <ion-icon [name]="navStatus ? \'arrow-down\':\'arrow-up\'"></ion-icon>\n                </button>\n            </ion-buttons>\n            <span style="padding : 0 15px;" [style.color]="navStatus ? \'white\' : \'inherit\'" *ngIf="userActive >= 0">\n                {{navStatus ? users[userActive].name: \'Users\'}}\n            </span>\n            <ion-title *ngIf="userActive == undefined">\n                Login\n            </ion-title>\n            <ion-buttons start *ngIf="navStatus" >\n                <button menuClose ion-button round item-start *ngIf="users[userActive].img"\n                        (click)="openPage(pages[4],users[userActive])">\n                    <img width="50px" style="border-radius: 50px;padding : 3px;"\n                         [src]="users[userActive].img">\n                </button>\n                <button menuClose *ngIf="!users[userActive].img" ion-button icon-only\n                        (click)="openPage(pages[4],users[userActive])">\n                    <ion-icon name="contact"></ion-icon>\n                </button>\n            </ion-buttons>\n        </ion-navbar>\n    </ion-header>\n    <ion-content>\n        <ion-toolbar [class]="navStatus ? \'\' : \'active\'">\n            <ion-list no-lines no-border>\n                <ng-container *ngFor="let user of users, let i = index">\n                    <ion-card (click)="toggle(user,i,$event)">\n                        <ion-item [color]="user.active ? \'primary\' : \'\'">\n                            <ion-avatar item-start *ngIf="user.img">\n                                <img [src]="user.img">\n                            </ion-avatar>\n                            <ion-icon *ngIf="!user.img" [color]="user.active ? \'light\' : \'primary\'" name="contact"\n                                      [isActive]="user.active == true ? \'primary\' : \'\'"\n                                      item-start></ion-icon>\n                            <h2 [style.color]="user.active ? \'white\' : \'inherit\'">{{user.name}}</h2>\n                            <p>{{user.role}}\n                                <ion-badge color="danger" item-end>{{notifications[i]}}</ion-badge>\n                            </p>\n                            <button ion-button icon-left clear item-end>\n                                <ion-icon name="more" [color]="user.active ? \'secondary\' : \'primary\'"></ion-icon>\n                            </button>\n                        </ion-item>\n                    </ion-card>\n                </ng-container>\n                <ion-card *ngIf="users.length < 5">\n                    <button menuClose ion-item (click)="openPage(loginPage)">\n                        <ion-icon name="person-add" item-start color="primary"></ion-icon>\n                        {{users.length > 0 ? \'Login with another account...\' : \'Login...\'}}\n                    </button>\n                </ion-card>\n            </ion-list>\n        </ion-toolbar>\n        <ion-list *ngIf="userActive >= 0">\n            <button menuClose ion-item *ngFor="let p of pages" (click)="openPage(p,users[userActive])">\n                {{p.title}}\n            </button>\n        </ion-list>\n    </ion-content>\n\n</ion-menu>\n\n<!-- Disable swipe-to-go-back because it\'s poor UX to combine STGB with side menus -->\n<ion-nav no-lines [root]="rootPage" #content\n         swipeBackEnabled="false"></ion-nav>\n'/*ion-inline-end:"/home/alejandro/Documents/GEDSYS/src/app/app.html"*/,
        providers: [
            __WEBPACK_IMPORTED_MODULE_9__shared_shared__["a" /* GedsysApiService */],
            __WEBPACK_IMPORTED_MODULE_4__angular_http__["b" /* HttpModule */]
        ]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["g" /* Events */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* ActionSheetController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["s" /* Platform */], __WEBPACK_IMPORTED_MODULE_2__ionic_native_status_bar__["a" /* StatusBar */], __WEBPACK_IMPORTED_MODULE_3__ionic_native_splash_screen__["a" /* SplashScreen */], __WEBPACK_IMPORTED_MODULE_9__shared_shared__["a" /* GedsysApiService */]])
], MyApp);

//# sourceMappingURL=app.component.js.map

/***/ }),

/***/ 437:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__gedsys_api_service__ = __webpack_require__(178);
/* harmony namespace reexport (by used) */ __webpack_require__.d(__webpack_exports__, "a", function() { return __WEBPACK_IMPORTED_MODULE_0__gedsys_api_service__["a"]; });

//# sourceMappingURL=shared.js.map

/***/ }),

/***/ 440:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"./af": 224,
	"./af.js": 224,
	"./ar": 225,
	"./ar-dz": 226,
	"./ar-dz.js": 226,
	"./ar-kw": 227,
	"./ar-kw.js": 227,
	"./ar-ly": 228,
	"./ar-ly.js": 228,
	"./ar-ma": 229,
	"./ar-ma.js": 229,
	"./ar-sa": 230,
	"./ar-sa.js": 230,
	"./ar-tn": 231,
	"./ar-tn.js": 231,
	"./ar.js": 225,
	"./az": 232,
	"./az.js": 232,
	"./be": 233,
	"./be.js": 233,
	"./bg": 234,
	"./bg.js": 234,
	"./bm": 235,
	"./bm.js": 235,
	"./bn": 236,
	"./bn.js": 236,
	"./bo": 237,
	"./bo.js": 237,
	"./br": 238,
	"./br.js": 238,
	"./bs": 239,
	"./bs.js": 239,
	"./ca": 240,
	"./ca.js": 240,
	"./cs": 241,
	"./cs.js": 241,
	"./cv": 242,
	"./cv.js": 242,
	"./cy": 243,
	"./cy.js": 243,
	"./da": 244,
	"./da.js": 244,
	"./de": 245,
	"./de-at": 246,
	"./de-at.js": 246,
	"./de-ch": 247,
	"./de-ch.js": 247,
	"./de.js": 245,
	"./dv": 248,
	"./dv.js": 248,
	"./el": 249,
	"./el.js": 249,
	"./en-au": 250,
	"./en-au.js": 250,
	"./en-ca": 251,
	"./en-ca.js": 251,
	"./en-gb": 252,
	"./en-gb.js": 252,
	"./en-ie": 253,
	"./en-ie.js": 253,
	"./en-nz": 254,
	"./en-nz.js": 254,
	"./eo": 255,
	"./eo.js": 255,
	"./es": 256,
	"./es-do": 257,
	"./es-do.js": 257,
	"./es-us": 258,
	"./es-us.js": 258,
	"./es.js": 256,
	"./et": 259,
	"./et.js": 259,
	"./eu": 260,
	"./eu.js": 260,
	"./fa": 261,
	"./fa.js": 261,
	"./fi": 262,
	"./fi.js": 262,
	"./fo": 263,
	"./fo.js": 263,
	"./fr": 264,
	"./fr-ca": 265,
	"./fr-ca.js": 265,
	"./fr-ch": 266,
	"./fr-ch.js": 266,
	"./fr.js": 264,
	"./fy": 267,
	"./fy.js": 267,
	"./gd": 268,
	"./gd.js": 268,
	"./gl": 269,
	"./gl.js": 269,
	"./gom-latn": 270,
	"./gom-latn.js": 270,
	"./gu": 271,
	"./gu.js": 271,
	"./he": 272,
	"./he.js": 272,
	"./hi": 273,
	"./hi.js": 273,
	"./hr": 274,
	"./hr.js": 274,
	"./hu": 275,
	"./hu.js": 275,
	"./hy-am": 276,
	"./hy-am.js": 276,
	"./id": 277,
	"./id.js": 277,
	"./is": 278,
	"./is.js": 278,
	"./it": 279,
	"./it.js": 279,
	"./ja": 280,
	"./ja.js": 280,
	"./jv": 281,
	"./jv.js": 281,
	"./ka": 282,
	"./ka.js": 282,
	"./kk": 283,
	"./kk.js": 283,
	"./km": 284,
	"./km.js": 284,
	"./kn": 285,
	"./kn.js": 285,
	"./ko": 286,
	"./ko.js": 286,
	"./ky": 287,
	"./ky.js": 287,
	"./lb": 288,
	"./lb.js": 288,
	"./lo": 289,
	"./lo.js": 289,
	"./lt": 290,
	"./lt.js": 290,
	"./lv": 291,
	"./lv.js": 291,
	"./me": 292,
	"./me.js": 292,
	"./mi": 293,
	"./mi.js": 293,
	"./mk": 294,
	"./mk.js": 294,
	"./ml": 295,
	"./ml.js": 295,
	"./mr": 296,
	"./mr.js": 296,
	"./ms": 297,
	"./ms-my": 298,
	"./ms-my.js": 298,
	"./ms.js": 297,
	"./my": 299,
	"./my.js": 299,
	"./nb": 300,
	"./nb.js": 300,
	"./ne": 301,
	"./ne.js": 301,
	"./nl": 302,
	"./nl-be": 303,
	"./nl-be.js": 303,
	"./nl.js": 302,
	"./nn": 304,
	"./nn.js": 304,
	"./pa-in": 305,
	"./pa-in.js": 305,
	"./pl": 306,
	"./pl.js": 306,
	"./pt": 307,
	"./pt-br": 308,
	"./pt-br.js": 308,
	"./pt.js": 307,
	"./ro": 309,
	"./ro.js": 309,
	"./ru": 310,
	"./ru.js": 310,
	"./sd": 311,
	"./sd.js": 311,
	"./se": 312,
	"./se.js": 312,
	"./si": 313,
	"./si.js": 313,
	"./sk": 314,
	"./sk.js": 314,
	"./sl": 315,
	"./sl.js": 315,
	"./sq": 316,
	"./sq.js": 316,
	"./sr": 317,
	"./sr-cyrl": 318,
	"./sr-cyrl.js": 318,
	"./sr.js": 317,
	"./ss": 319,
	"./ss.js": 319,
	"./sv": 320,
	"./sv.js": 320,
	"./sw": 321,
	"./sw.js": 321,
	"./ta": 322,
	"./ta.js": 322,
	"./te": 323,
	"./te.js": 323,
	"./tet": 324,
	"./tet.js": 324,
	"./th": 325,
	"./th.js": 325,
	"./tl-ph": 326,
	"./tl-ph.js": 326,
	"./tlh": 327,
	"./tlh.js": 327,
	"./tr": 328,
	"./tr.js": 328,
	"./tzl": 329,
	"./tzl.js": 329,
	"./tzm": 330,
	"./tzm-latn": 331,
	"./tzm-latn.js": 331,
	"./tzm.js": 330,
	"./uk": 332,
	"./uk.js": 332,
	"./ur": 333,
	"./ur.js": 333,
	"./uz": 334,
	"./uz-latn": 335,
	"./uz-latn.js": 335,
	"./uz.js": 334,
	"./vi": 336,
	"./vi.js": 336,
	"./x-pseudo": 337,
	"./x-pseudo.js": 337,
	"./yo": 338,
	"./yo.js": 338,
	"./zh-cn": 339,
	"./zh-cn.js": 339,
	"./zh-hk": 340,
	"./zh-hk.js": 340,
	"./zh-tw": 341,
	"./zh-tw.js": 341
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
webpackContext.id = 440;

/***/ }),

/***/ 453:
/***/ (function(module, exports) {

/* (ignored) */

/***/ }),

/***/ 469:
/***/ (function(module, exports) {

/* (ignored) */

/***/ }),

/***/ 470:
/***/ (function(module, exports) {

/* (ignored) */

/***/ }),

/***/ 471:
/***/ (function(module, exports) {

/* (ignored) */

/***/ })

},[361]);
//# sourceMappingURL=main.js.map