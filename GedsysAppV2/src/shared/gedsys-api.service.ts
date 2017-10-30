
import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Events} from "ionic-angular";

@Injectable()
export class GedsysApiService{
  private baseURL = "https://gedsys-8e06b.firebaseio.com/";
    notifications: any = [];
    getNotifications(){
    return new Promise(resolve => {
      this.http.get(`${this.baseURL}/notifications.json`)
        .subscribe(res => resolve(res.json()));
    })
  }
    pushNotifications(notifications){
        return new Promise(resolve => {
            this.http.put(`${this.baseURL}/notifications.json`,notifications,{})
                .subscribe(res => resolve(res.json()));
        })
    }
    public users = [
        {
            name: 'Admin',
            username : 'admin',
            password : 'admin',
            img: 'http://www.xsjjys.com/data/out/96/WHDQ-512397052.jpg',
            role: 'Developer',
            active: false,
            id : 1,
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
                },{
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
                },{
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
                },{
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
                },{
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
                },{
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
                },{
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
                },{
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
                },{
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
                },{
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
                },{
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
            username : 'base16dev',
            password : 'admin123',
            img: 'https://content-static.upwork.com/uploads/2014/10/02123010/profile-photo_friendly.jpg',
            role: 'Admin',
            active: false,
            id : 2,
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
            username : 'user2',
            password : 'user2123',
            active: false,
            id : 3,
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
            username : 'user3',
            password : 'user3123',
            role: 'Admin',
            active: false,
            id : 4,
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
    ]
    public activeUser: any;
  constructor(public events: Events, private http : Http) {
      this.events.subscribe('userActive:change',index => {
          return this.activeUser = index;
      });
      this.events.subscribe('user:updated',(user,index)=>{
          return this.users[index] = user;
      })
  }
}
