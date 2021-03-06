import { formatDate } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { NavController } from '@ionic/angular';
import { post } from './post.model';

@Injectable({
  providedIn: 'root'
})
export class FeedService {
  public apiURL: string = 'https://stonks.fimtow.com/application';

  constructor(
    private http: HttpClient,
    private navCtrl: NavController) { }

  getUserInfo(id: string){
    return this.http.get<any>(this.apiURL+`/user?id=${id}`, {withCredentials: true});
  }

  getPostInfo(id: string){
    return this.http.get<any>(this.apiURL+`/post?id=${id}`, {withCredentials: true});
  }

  getTopicInfo(id: string){
    return this.http.get<any>(this.apiURL+`/topic?id=${id}`, {withCredentials: true});
  }

  subcomment(post:string, comment: string){
    return this.http.post<any>(this.apiURL + `/comment?post=${post}&comment=${comment}`,{}, {withCredentials: true});
  }

  getHome(){
    return this.http.get<any>(this.apiURL + "/home", {withCredentials: true});
  }

  joinTopic(topic: string){
    return this.http.post<any>(this.apiURL + `/jointopic?idtopic=${topic}`,{}, {withCredentials: true});
  }

  createPost(form: FormGroup, topic: string){
    var fmval = form.value;
    console.log(fmval);
    if(fmval.type == 0){
      return this.http.post<any>(this.apiURL + `/post?title=${fmval.title}&type_content=text&text=${fmval.text}&topic=${topic}`,{}, {withCredentials: true});
    }
    if(fmval.type == 1){
      return this.http.post<any>(this.apiURL + '/post',{}, {params: {title: fmval.title, type_content:'image', url: fmval.url, topic: topic},withCredentials: true});
    }
    if(fmval.type == 2){
      fmval.date = formatDate(fmval.date, 'dd/MM/yyyy', 'en');
      return this.http.post<any>(this.apiURL + `/post?title=${fmval.title}&type_content=invitation&location=${fmval.location}&date=${fmval.date}&description=${fmval.description}&topic=${topic}`,{}, {withCredentials: true});
    }
  }
}
