import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { ConfigPage } from './config';
import {SuperTabsModule} from "ionic2-super-tabs";

@NgModule({
  declarations: [
    ConfigPage,
  ],
  imports: [
    IonicPageModule.forChild(ConfigPage),
      SuperTabsModule
  ],
})
export class ConfigPageModule {}
