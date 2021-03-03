import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NavController } from '@ionic/angular';
import { post } from './post.model';

@Injectable({
  providedIn: 'root'
})
export class FeedService {
  public apiURL: string = 'http://ec2-100-25-131-56.compute-1.amazonaws.com/application';

  constructor(
    private http: HttpClient,
    private navCtrl: NavController) { }

  getUserInfo(id: string){
    return this.http.get<any>(this.apiURL+`/user?id=${id}`, {withCredentials: true});
  }

  getPostInfo(id: string){
    return this.http.get<post>(this.apiURL+`/post?id=${id}`, {withCredentials: true});
  }
}
