import {Injectable} from '@angular/core';
import 'rxjs/add/operator/map';
import {LoadingController, ToastController} from "ionic-angular";

@Injectable()
export class VariablesProvider {
    public spinner = 'bubbles';

    public refresherTemplate(options) {
        !options ? options = {} : options;
        return {
            pullingIcon : 'arrow-dropdown',
            pullingText : options.title ? `Pull to refresh ${options.title}...` : `Pull to refresh...`,
            refreshingSpinner: this.spinner,
            refreshingText: options.title ? `Refreshing ${options.title}...` : `Refreshing...`
        }
    }

    public loadingTemplate(options) {
        !options ? options = {} : options;
        return this.loadingCtrl.create({
            spinner: this.spinner,
            content: options.content || 'Loading Please Wait...',
            duration: options.duration || null
        });
    }

    public toastTemplate(options) {
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
    }

    constructor(private toastCtrl: ToastController,
                private loadingCtrl: LoadingController) {
    }

}
