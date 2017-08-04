/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
var app = {
    // Application Constructor
    initialize: function () {
        document.addEventListener('deviceready', this.onDeviceReady.bind(this), false);
    }, // deviceready Event Handler
    //
    // Bind any cordova events here. Common events are:
    // 'pause', 'resume', etc.
    onDeviceReady: function () {
        this.receivedEvent('deviceready');
        FCMPlugin.getToken(function(token){
            alert(token);
            console.log(token);
        });
        this.pushNotification();
    },

    // Update DOM on a Received Event
    receivedEvent: function (id) {
        var parentElement = document.getElementById(id);
        //var listeningElement = parentElement.querySelector('.listening');
        //var receivedElement = parentElement.querySelector('.received');
        //listeningElement.setAttribute('style', 'display:none;');
        //receivedElement.setAttribute('style', 'display:block;');
        console.log('Received Event: ' + id);
    },
    pushNotification: function () {
        FCMPlugin.onNotification(function (data) {
            if (data.wasTapped) {
                alert(JSON.stringify(data));
            } else {
                alert(JSON.stringify(data));
            }
        });
    }
};
app.initialize();
$(function () {
    $.getJSON("JSON/User.json", function (json) {
        $('body').load('views/login.html', function () {
            login();
        });

        function login() {
            var users = json;
            var tryNumber = 0;
            var access = false;
            var emailAccess = false;
            var remember = $.cookie('remember');
            $('.wrap').on('click', function () {
                if ($('.restore').hasClass('active') && (tryNumber >= 0)) {
                    $('.restore').toggleClass('active');
                }
                if (tryNumber == -1) {
                    tryNumber++;
                }
            });
            $('form > h3 > input').on('input keypress', function (e) {
                checkUser();
                if ($('.pass').val() != "") {
                    checkPass();
                }
                if (e.which == 13) {
                    if ($(this).hasClass('user')) {
                        $('.pass').focus();
                    } else if ($(this).hasClass('pass')) {
                        login();
                    }
                }
            });
            $('.restore').on('click', function (e) {
                if (window.innerWidth <= 767) {
                    if ($(this).hasClass('active')) {
                        if (e.target == $('.restoreBtn')[0] || e.target == $('.restoreBtn').children()[0]) {
                            if (emailAccess) {
                                $('.restore').toggleClass('active');
                            } else {
                                $('.glyphicon-envelope').addClass('wrong');
                                $('.mail').addClass('wrong');
                            }
                        }
                    } else {
                        $(this).toggleClass('active');
                    }
                }
            });
            $('.wrap > .input > form > button').on('click', function () {
                if ($(this).hasClass('loading')) {
                    console.error("Already logging in...");
                } else {
                    login();
                }
            });
            $('.restore > h2 > input').on('input keypress', function (e) {
                var mail = $(this).val();
                checkMail(mail);
            });
            var login = function () {
                var user = $(".user").val();
                for (var i = 0; i < users.length; i++) {
                    if (user == users[i].username) {
                        var pass = $(".pass").val();
                        if (pass == users[i].password) {
                            checkToggle();
                            $('.logoSpinner').addClass('loading');
                            $('.login').addClass('loading');
                            setTimeout(function () {
                                $('body').load('base.html', function () {
                                    base(i);
                                });
                            }, 4000);
                            break;
                        }
                    } else if (i == users.length - 1) {
                        tryNumber++;
                        if (tryNumber == 3) {
                            $('.restore').toggleClass('active');
                            tryNumber = -1;
                        }
                    }
                }
            }
            var popup = function (div, content, btn) {
                var div = div || "body";
                $.get("views/popup.html", function (data) {
                    $(div).append(data);
                    $(".popup").draggable({
                        containment: "window"
                    });
                    $('.popup > .content > h3').html("Wrong password!");
                    $('.popup > .button > h4').html("Accept");
                    $('.popup > .button').click(function () {
                        $('.popup').remove();
                    });
                });
            }
            var checkUser = function () {
                for (var i = 0; i < users.length; i++) {
                    if ($('.user').val() == users[i].username) {
                        var check = false;
                        $('.user').addClass('correct');
                        $('.user').removeClass('incorrect');
                        return i;
                        break;
                    } else if (i == users.length - 1) {
                        $('.user').addClass('incorrect');
                        $('.user').removeClass('correct');
                    }
                }
            }
            var checkPass = function () {
                for (var i = 0; i < users.length; i++) {
                    if (checkUser() == i && $('.pass').val() == users[i].password) {
                        $('.pass').addClass('correct');
                        $('.pass').removeClass('incorrect');
                        break;
                    } else if (i == users.length - 1) {
                        $('.pass').addClass('incorrect');
                        $('.pass').removeClass('correct');
                    }
                }
            }
            var checkMail = function (mail) {
                function isValidEmailAddress(emailAddress) {
                    var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
                    return pattern.test(emailAddress);
                }
                ;
                if (isValidEmailAddress(mail)) {
                    $('.glyphicon-envelope').addClass('active');
                    $('.mail').addClass('active');
                    emailAccess = true;
                    if ($('.mail').hasClass("wrong")) {
                        $('.mail').removeClass("wrong");
                        $('.glyphicon-envelope').removeClass("wrong");
                    }
                } else {
                    $('.glyphicon-envelope').removeClass('active');
                    $('.mail').removeClass('active');
                    emailAccess = false;
                }
            }
            var checkToggle = function () {
                if ($('#cmn-toggle-1').prop('checked')) {
                    check = true;
                    $.cookie('user', $('.user').val(), {
                        expires: 7
                    });
                    $.cookie('pass', $('.pass').val(), {
                        expires: 7
                    });
                    $.cookie('remember', true, {
                        expires: 7
                    });
                } else {
                    $.cookie('user', null);
                    $.cookie('pass', null);
                    $.cookie('remember', false);
                }
            }
            if (remember == "true") {
                var user = $.cookie('user');
                var pass = $.cookie('pass');
                $('.user').val(user);
                $('.pass').val(pass);
                $('#cmn-toggle-1').prop('checked', true);
                checkUser();
                checkPass();
                for (var i = 0; i < users.length; i++) {
                    if (users[i].username == user) {
                        $('body').load('base.html', function () {
                            base(i);
                        });
                    }
                }
            }
            var logoPlace = function () {
                var wrapWidth = $('.wrap').width();
                var spinnerWidth = $('.logoSpinner').width();
                var paddingLeft = (wrapWidth - spinnerWidth) / 2;
                $('.logo').css('padding-left', paddingLeft + 'px');
            }
            var logoPlaceNotXS = function () {
                var logoWidth = $('.logo').width();
                var wrapHeight = $('.wrap').height();
                var spinnerWidth = $('.logoSpinner').width();
                var spinnerHeight = $('.logoSpinner').height();
                var paddingLeft = (logoWidth - spinnerWidth) / 2;
                var paddingTop = (wrapHeight - spinnerHeight) / 2;
                $('.logo').css('padding-left', paddingLeft + 'px');
                $('.logo').css('padding-top', paddingTop + 'px');
            }
            if ($(window).width() <= 767) {
                setTimeout(function () {
                    logoPlace();
                }, 100);
            } else {
                logoPlaceNotXS();
            }
        }

        function base(userIndex) {
            $.getJSON("JSON/Notifications.json", function (json) {
                var current, previous;
                current = "notifications.html";
                previous = "notifications.html";
                var nonActive = $('.content > h4').html();
                var notifications;
                json.sort(function (a, b) {
                    return a.date - b.date;
                });
                notifications = json;
                $.getJSON("JSON/User.json", function (userJson) {
                    var user = userJson[userIndex];

                    function getNotificationIndex(id) {
                        for (var i = 0; i < notifications.length; i++) {
                            if (notifications[i].id == id) {
                                return i;
                            }
                        }
                    }

                    function setBadgeTitle() {
                        var size = notifications.length;
                        if (size > 99) {
                            size = "99+";
                        }
                        $('.title > .logo > .badge').html(size);
                    }

                    function deleteNotification(id, reload) {
                        var index = getNotificationIndex(id);
                        notifications.splice(index, 1);
                        if (reload) {
                            loadView($('.notifications'));
                        }
                        setBadgeTitle();
                    }

                    function DetailView(id) {
                        var title, content, type, date, icon;
                        var index = getNotificationIndex(id);
                        title = notifications[index].title;
                        content = notifications[index].content;
                        type = notifications[index].type;
                        date = Number(notifications[index].date);
                        icon = notifications[index].icon;
                        notifications[index].type = type + " read";
                        $('.detail').addClass(type);
                        $('.title > h1').html(title);
                        $('.content > p').html(content);
                        $('.Date > h6').html(moment(date).format("MMMM Do YYYY, h:mm:ss a"));
                        $('.icon > h1 > span').addClass("glyphicon-" + icon);
                        $('.restore').on("click", function () {
                            $('body').click();
                        });
                        $('.modal').modal({
                            ready: function (modal, trigger) {
                                $('.modal-close').on('click', function () {
                                    deleteNotification(id, true);
                                })
                            }
                        });
                        $('.title > .content > h4').html('Detalle');
                    }

                    function NotificationsView() {
                        setBadgeTitle();

                        function appendNotification(title, content, date, type, icon, id) {
                            var notification = "<div class='col-xs-12 notification " + type + "' id='" + id + "'><div class='wrap col-xs-12'><div class='icon col-xs-2'><h2><span class='glyphicon glyphicon-" + icon + "'></span></h2></div><div class='col-xs-8 content'><div class='col-xs-12 title'><h4>" + title + "</h4> </div><div class='col-xs-12 desc'><h6>" + content + "</h6> </div></div><div class='col-xs-2 date'><h6><span data-livestamp='" + date + "'></span></h6></div></div><div class='col-xs-4 slide'><div class='col-xs-6'> <span class='glyphicon glyphicon-trash' aria-hidden='true'></span> </div><div class='col-xs-6 toggle'><span class='toggleRead glyphicon glyphicon-eye-open' aria-hidden='true'></span> </div></div></div>";
                            $('.Content > .notifications').append(notification);
                        }
                        for (var i = 0; i < notifications.length; i++) {
                            var title = notifications[i].title;
                            var content = notifications[i].content;
                            var type = notifications[i].type;
                            var id = notifications[i].id;
                            var date = Number(notifications[i].date);
                            var icon = notifications[i].icon;
                            appendNotification(title, content, date, type, icon, id);
                        }
                        $('.notification').on('swipe', function (e) {
                            e.stopPropagation;
                            $(this).addClass('slid');
                        });
                        $('.notification').on('swiperight', function (e) {
                            e.stopPropagation;
                            $(this).removeClass('slid');
                        });
                        $('.notification').on('click', function (e) {
                            e.stopPropagation;
                            if (e.target == $(this).find('.toggleRead')[0]) {
                                e.stopPropagation;
                                $(this).toggleClass('read');
                                $(this).find('.toggleRead').toggleClass('glyphicon-eye-open glyphicon-eye-close')
                            }
                            if (e.target == $(this).find('.glyphicon-trash')[0]) {
                                e.stopPropagation;
                                $(this).remove();
                                var id = $(this).attr('id');
                                deleteNotification(id, false);
                            }
                        });
                        $('.notification > .wrap').on('click', function () {
                            $($(this).parent()[0]).addClass('read');
                            var currentId = $(this).parent().attr('id');
                            $('.base > .Content').load('views/detail.html', function () {
                                DetailView(currentId);
                            });
                        });
                    }

                    function alertCreate(time, content, type, id) {
                        var alert = "<div class='col-xs-12 alert " + type + "' id='" + id + "'><div class='col-xs-3 time'><h6><span data-livestamp='" + time + "'></span></h6></div><div class='col-xs-7 content'><h5>" + content + "</h5></div><div class='col-xs-2 tag'><span></span></div></div>";
                        $('.alerts').append(alert);
                    }

                    function CalendarView() {
                        setBadgeTitle();
                        var calendar = $('#calendar').clndr({
                            weekOffset: 1
                            , targets: {
                                day: 'day'
                                , empty: 'empty'
                                , nextButton: 'clndr-next-button'
                                , todayButton: 'clndr-today-button'
                                , previousButton: 'clndr-previous-button'
                                , nextYearButton: 'clndr-next-year-button'
                                , previousYearButton: 'clndr-previous-year-button'
                            }
                            , classes: {
                                past: "past"
                                , today: "today"
                                , event: "event"
                                , selected: "selected"
                                , inactive: "inactive"
                                , lastMonth: "last-month"
                                , nextMonth: "next-month"
                                , adjacentMonth: "adjacent-month"
                            }
                            , doneRendering: function () {
                                $('.clndr-next-button').html("").addClass('glyphicon glyphicon-menu-right');
                                $('.clndr-previous-button').html("").addClass('glyphicon glyphicon-menu-left');
                                var length = Object.keys(notifications).length;
                                $('.alerts').html("");
                                for (var i = 0; i < length; i++) {
                                    var title = notifications[i].title;
                                    var content = notifications[i].content;
                                    var type = notifications[i].type;
                                    var id = notifications[i].id;
                                    var date = Number(notifications[i].date);
                                    $('.calendar-day-' + moment.unix(date).format("YYYY-MM-DD")).addClass(type);
                                    if ((date > moment().unix()) && (type != "notif")) {
                                        alertCreate(date, title, type, id);
                                    }
                                }
                                $('.day').on('click', function () {
                                    var currentDay = $(this).hasClass('today');
                                    var currentDayClass = $(this).prop('classList');
                                    for (var i = 0; i < currentDayClass.length; i++) {
                                        if (currentDayClass[i].match('^calendar-day')) {
                                            currentDayClass = currentDayClass[i];
                                            break;
                                        }
                                    }
                                    $('.base > .Content').load('views/day.html', function () {
                                        if (currentDay) {
                                            var day = moment().format("HH");
                                            $('.' + day).addClass('current');
                                        }
                                        $('.grid').addClass(currentDayClass);
                                        var date = currentDayClass.split('-').slice(2, 5);
                                        var day = date[2];
                                        var month = date[1];
                                        var year = date[0];
                                        var date = day + month + year;
                                        for (var i = 0; i < notifications.length; i++) {
                                            var currentDate = moment.unix(Number(notifications[i].date));
                                            if ((currentDate.format("DDMMYYYY") == date) && (length)) {
                                                var hour = moment(currentDate).format("HH");
                                                $('.' + hour + ' > .right').append('<div id="' + notifications[i].id + '" class="box ' + notifications[i].type + '">' + notifications[i].title + '</div>');
                                            }
                                        }
                                        for (var i = 0; i < $('.right').length; i++) {
                                            var rightLength = $($('.right')[i]).children().length;
                                            if (rightLength > 0) {
                                                function removeRightDiv(length) {
                                                    if (length > 6) {
                                                        $($('.right')[i]).children()[6].remove();
                                                        length--;
                                                        removeRightDiv(length);
                                                    } else {
                                                        return length;
                                                    }
                                                }
                                                removeRightDiv(rightLength);
                                                rightLength = $($('.right')[i]).children().length;
                                                if (rightLength == 5) {
                                                    rightLength++;
                                                }
                                                for (var j = 0; j < rightLength; j++) {
                                                    var cols = 12 / rightLength;
                                                    $($('.right')[i]).find('.box').addClass('col-xs-' + cols);
                                                }
                                            }
                                        }
                                        $('.right > .box').on('click', function () {
                                            var id = $($(this)[0]).attr('id');
                                            $('.Content').load('views/detail.html', function () {
                                                DetailView(id);
                                            })
                                        })
                                    });
                                });
                                $('.alert >').on('click tap', function () {
                                    var id = $(this).parent().attr('id');
                                    $('.base > .Content').load('views/detail.html', function () {
                                        DetailView(id);
                                    });
                                });
                            }
                            , adjacentDaysChangeMonth: true
                        });
                    }

                    function DocsView() {
                        $('.uncheck').on('click', function () {
                            $('body').click();
                        });
                        $('.modal').modal();
                        var viewer = function (url) {
                            $('.canvas-container').html();
                            $('.canvas-container').html("<canvas id='docView' class='center-block' style='width:90vw;'></canvas>");
                            PDFJS.disableWorker = true;
                            PDFJS.workerSrc = '/../js/pdf.worker.js';
                            // Asynchronous download of PDF
                            var loadingTask = PDFJS.getDocument(url);
                            loadingTask.promise.then(function (pdf) {
                                // Fetch the first page
                                var pageNumber = 1;
                                pdf.getPage(pageNumber).then(function (page) {
                                    var canvas = $('#docView')[0];
                                    var viewport = page.getViewport(canvas.width / page.getViewport(0.1).width);
                                    // Prepare canvas using PDF page dimensions
                                    var context = canvas.getContext('2d');
                                    canvas.height = viewport.height;
                                    canvas.width = viewport.width;
                                    // Render PDF page into canvas context
                                    var renderContext = {
                                        canvasContext: context
                                        , viewport: viewport
                                    };
                                    var renderTask = page.render(renderContext);
                                    renderTask.then(function () {});
                                });
                            });
                        }

                        function appendDoc(section) {
                            $('.collapsible-body > span > .row > .wrap').html("");
                            var archiveSection = false;
                            for (var i = 0; i < notifications.length; i++) {
                                var toFind;
                                if (section == "incoming.html") {
                                    toFind = (notifications[i].properties.sent == "false") && notifications[i].properties.archived == "false";
                                } else if (section == "outgoing.html") {
                                    toFind = notifications[i].properties.sent == "true";
                                    archiveSection = true;
                                } else if (section == "archived.html") {
                                    toFind = notifications[i].properties.archived == "true";
                                }
                                if (toFind) {
                                    var title = notifications[i].title;
                                    var content = notifications[i].content;
                                    var type = notifications[i].type;
                                    var id = notifications[i].id;
                                    var build = "<div class='col s12 m6 l4 " + type + "'><div class='card horizontal'><div class='card-image'></div><div class='card-stacked'><div class='card-content'><p> <b>" + title + "</b><br>" + content + " </p></div><div class='card-action'><a data-id=" + id + "><i class='material-icons'>launch</i></a><a class='archiveAction' data-id=" + id + "><i class='material-icons'>dns</i></a></div></div></div></div>";
                                    var div = $('.collapsible-body > span > .row > .wrap');
                                    div.append(build);
                                }
                            }
                            if (archiveSection) {
                                $(".archiveAction").remove();
                            }
                            $(".archiveAction").on('click', function () {
                                //$('#modal1').modal('open');
                                var tempId = $(this).attr('data-id');
                                var index = getNotificationIndex(tempId);
                                var tempBool = notifications[index].properties.archived;
                                if (tempBool == "true") {
                                    tempBool = "false";
                                } else {
                                    tempBool = "true";
                                }
                                notifications[index].properties.archived = tempBool;
                                appendDoc(section);
                                //$('.modal-action.modal-close').on('click', function () {});
                            })
                        }
                        var load = function (link) {
                            $('span > .row').load('views/' + link, function () {
                                appendDoc(link);
                                $('.card-action > a:not(.archiveAction)').on('click', function () {
                                    var dataId = $(this).attr('data-id');
                                    var url;
                                    var index = getNotificationIndex(dataId);
                                    url = notifications[index].url;
                                    $('.collapsible-header')[0].click();
                                    viewer(url);
                                });
                            });
                            var title = link.slice(0, -5).toUpperCase();
                            switch (title) {
                                case "INCOMING":
                                    title = "Entrantes";
                                    break;
                                case "OUTGOING":
                                    title = "Enviados";
                                    break;
                                case "ARCHIVED":
                                    title = "Archivados";
                                    break;
                            }
                            $($('.nav-wrapper > .brand-logo')[1]).html(title);
                        }
                        $('.float-btn').on("click", function () {
                            var link = $(this).find("a").attr("data");
                            load(link);
                        })
                        load('incoming.html');
                        $('.collapsible').collapsible();
                        var url = '../docs/doc19.pdf';
                        viewer(url);
                        $('.collapsible').collapsible('open', 1);
                    }

                    function ConfigView() {
                        $('.userDesc > .name > h1').html(user.name);
                        $('.userDesc > .description > h4').html(user.description);
                        if (user.cover.visible == "true") {
                            $('.profilePic').css('background-image', 'url("' + user.cover.url + '")');
                        }
                        if (user.logo.visible == "true") {
                            $('.profilePic > .circleContainer > img').attr('src', user.logo.url);
                        } else {
                            $('.profilePic > .circleContainer > img').attr('src', "https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg");
                        }
                    }

                    function loadView(cur) {
                        for (var i = 1; i <= 4; i++) {
                            $('.tab:nth-child(' + i + ')').removeClass('active');
                        }
                        $(cur).addClass('active');
                        previous = current;
                        current = $($(cur)[0]).attr('href');
                        if (current == undefined) {
                            current = "notifications.html";
                        }
                        $('.app > .Content').load("views/" + current, function () {
                            switch (current) {
                                case "notifications.html":
                                    NotificationsView();
                                    break;
                                case "calendar.html":
                                    CalendarView();
                                    break;
                                case "config.html":
                                    ConfigView();
                                    break;
                                case "docs.html":
                                    DocsView();
                                    break;
                            }
                        });
                        nonActive = $(cur).attr('name');
                    }
                    loadView($('.notifications'));
                    $('.hamburger').on('click', function () {
                        $(this).toggleClass('active');
                        $('.menu').toggleClass('active');
                        if ($(".menu").hasClass("active")) {
                            $(".content > h4").html("Menú");
                            $('.title > .logo > span').removeClass();
                            $('.title > .logo').html('<img src="' + user.logo + '" alt="">');
                            if (user.logo.visible == "true") {
                                $('.title > .logo').html('<img src="' + user.logo.url + '" alt="">');
                            } else {
                                $('.title > .logo').html('<img src="https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg" alt="">');
                            }
                            $('.title > .logo > img').on('click', function () {
                                loadView($('.config'));
                            });
                        } else {
                            $('.title > .logo').html('<span class="glyphicon" aria-hidden="true"></span><div class="badge active"></div>');
                            setBadgeTitle();
                            var logo = "user";
                            $('.title > .logo > .badge').removeClass('active');
                            switch (nonActive) {
                                case "Notificaciones":
                                    logo = "bell";
                                    nonActive = "Notificaciones";
                                    $('.title > .logo > .badge').addClass('active');
                                    break;
                                case "Calendario":
                                    logo = "calendar";
                                    $('.title > .logo > .badge').addClass('active');
                                    break;
                                case "Documentos":
                                    logo = "paperclip";
                                    break;
                                case "Configuracion":
                                    logo = "cog";
                                    break;
                            }
                            $(".content > h4").html(nonActive);
                            $('.title > .logo > span').removeClass();
                            $('.title > .logo > span').addClass('glyphicon glyphicon-' + logo);
                        }
                    });
                    $('.tab').on("click", function () {
                        loadView($(this)[0]);
                    });
                });
            });
        }
    });
});
