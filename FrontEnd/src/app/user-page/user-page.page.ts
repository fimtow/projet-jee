import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AlertController, LoadingController } from '@ionic/angular';
import { AuthService } from '../Auth/auth.service';
import { FeedService } from '../feed/feed.service';
import { post } from '../feed/post.model'


@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.page.html',
  styleUrls: ['./user-page.page.scss'],
})
export class UserPagePage implements OnInit {
  public userId: string = '';
  public username: string = "lorem";
  public email: string = "lorem@ipsum.it";
  public listPosts : post[] = [];
  public joinedTopics: string[] = [];
  constructor(
    private route: ActivatedRoute,
    private authService: AuthService, 
    private feedService: FeedService, 
    private loadingCtrl: LoadingController, 
    private alertCtrl: AlertController) { }

  ngOnInit() {
    this.userId = this.route.snapshot.paramMap.get('id');
    this.authService.autoLogin().subscribe();
    this.loadingCtrl.create({
      message: 'Astaghfiru llah ..',
      spinner: 'circular'
    }).then(loadingElement => {
      loadingElement.present();
    this.feedService.getUserInfo(this.userId).subscribe(resData => {
      this.username = resData.user.username;
      this.email = resData.user.email;
      loadingElement.dismiss();
      // resData.listOfPosts.forEach(elem => {
      //   var newPost:post = {
      //     post:{
      //     id: elem.post.id,
      //     title: elem.post.title,
      //     likes: elem.post.likes,
      //     dislikes: elem.post.dislikes,
      //     date: elem.post.date,
      //     type: elem.post.type,
      //     topic: elem.post.topic.id}
      //   };
      //   this.listPosts.push(newPost);
      // });
      console.log(resData);
    }, err => {
      console.log(err);
      loadingElement.dismiss();
      this.alertCtrl.create({
        header: 'An error has occured',
        message: err.error.message ? err.error.message : 'Failed to communicate with server. Please try again later...',
        buttons: ['Ok']
      }).then(alertElement => {
        alertElement.present();
      });
    });
  });
  }
}

