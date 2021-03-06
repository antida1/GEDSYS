import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import 'rxjs/add/operator/map';
import {User} from "../../models/user";
import {AngularFireAuth} from "angularfire2/auth";
import {Profile} from "../../models/profile";
import {GedsysApiService} from "../../shared/gedsys-api.service";

@Injectable()
export class AuthServiceProvider {

    constructor(private dbService: GedsysApiService,
                private fireAuth: AngularFireAuth) {}

    public auth(callback) {
        this.fireAuth.authState.subscribe(data => {
            if (data && data.email && data.uid) {
                this.dbService.getProfile(data.uid).then(() => {
                    this.dbService.getNotifications(data.uid).then(() => {
                        this.dbService.getDocuments(data.uid).then( () => {
                            return callback(null, null);
                        });
                    });
                })
            } else {
                return callback('User not found', null);
            }
        });
    }

    public async login(user: User) {
        try {
            await this.fireAuth.auth.signInWithEmailAndPassword(user.email, user.password);
            this.auth((err)=>{
                if (err){
                    return err;
                }
            });
        } catch (e) {
            return e;
        }
    }

    public async logout() {
        try {
            return await this.fireAuth.auth.signOut();
        } catch (e) {
            console.error(e);
        }
    }

    public async register(user: User, profile: Profile) {
        try {
            const result = await this.fireAuth.auth.createUserWithEmailAndPassword(user.email, user.password);
            return this.dbService.postProfile(result.uid, profile);
        } catch (e) {
            return e;
        }
    }

}
