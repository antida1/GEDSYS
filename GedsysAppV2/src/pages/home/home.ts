import {Component} from '@angular/core';
import {Events, NavController, NavParams} from 'ionic-angular';
import {TabsPage} from "../tabs/tabs";
import {ConfigPage} from "../config/config";
import {GedsysApiService} from "../../shared/gedsys-api.service";
import {DataProvider} from "../../providers/data/data";
import {DocumentsPage} from "../documents/documents";

@Component({
    selector: 'page-home',
    templateUrl: 'home.html'
})
export class HomePage {
    hiddenHeader:any = false;
    contentScroll(event){
        event.directionY == 'down' ? this.hiddenHeader = true : this.hiddenHeader = false;
        event.scrollTop < 40 ? this.hiddenHeader = false : null;
        return true;
    }
    chartOptions: any;
    chartOptions1: any;
    loadNotifications(){
        this.navCtrl.setRoot(TabsPage);
        return this.events.publish('root:change',1);
    }
    loadDocuments(){
        this.navCtrl.setRoot(DocumentsPage);
        return this.events.publish('root:change',2);
    }
    loadSettings(){
        this.navCtrl.setRoot(ConfigPage);
        return this.events.publish('root:change',4);
    }
    getAmount(notifications){
        let amount = 0;
        notifications.map(notification => {
            notification.archived ? amount: amount++;
            return notification;
        });
        return amount;
    }
    constructor(private dataProvider: DataProvider, private service: GedsysApiService, public events: Events, public navParams: NavParams, public navCtrl: NavController) {
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

    ionViewDidLoad() {
    }
}
