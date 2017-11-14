import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';

@Injectable()
export class DataProvider {
    public profile: any = {};
    public notifications: any = [];
    public documents: any = [];
    public app_config: any;
    public user_config: any;
  constructor() {

  }

}
