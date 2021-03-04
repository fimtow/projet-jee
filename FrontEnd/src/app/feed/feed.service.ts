import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NavController } from '@ionic/angular';
import { post } from './post.model';

@Injectable({
  providedIn: 'root'
})
export class FeedService {
  public apiURL: string = 'http://stonks.fimtow.com/application';

  constructor(
    private http: HttpClient,
    private navCtrl: NavController) { }

  getUserInfo(id: string){
    return this.http.get<any>(this.apiURL+`/user?id=${id}`, {withCredentials: true});
  }

  getPostInfo(id: string){
    return this.http.get<post>(this.apiURL+`/post?id=${id}`, {withCredentials: true});
  }

  subcomment(post:string, comment: string){
    this.http.post(this.apiURL + `/comment?post=${post}&comment=${comment}`,{}, {withCredentials: true}).subscribe(data=>console.log(data));
  }

  getHome(){
    return this.http.get<any>(this.apiURL + "/home", {withCredentials: true});
  }
}
