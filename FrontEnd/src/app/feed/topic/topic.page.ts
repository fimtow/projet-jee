import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NavController, LoadingController, AlertController } from '@ionic/angular';
import { tap } from 'rxjs/operators';
import { AuthService } from 'src/app/Auth/auth.service';
import { FeedService } from '../feed.service';

@Component({
  selector: 'app-topic',
  templateUrl: './topic.page.html',
  styleUrls: ['./topic.page.scss'],
})
export class TopicPage implements OnInit {
  public topicId: string;
  public topicInfos: any = { id: 0, title: '', description: '' };
  public listOfPosts: any[] = [];

  public joinbtn: string = "Join"
  public joined: boolean = false;

  public isAuthenticated: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private feedService: FeedService,
    private authService: AuthService,
    private navCtrl: NavController,
    private loadingCtrl: LoadingController,
    private alertCtrl: AlertController) { }

  ngOnInit() {
    this.authService.autoLogin().subscribe(isAuth => {
      this.isAuthenticated = isAuth;
      console.log(isAuth);
    });
    this.topicId = this.route.snapshot.paramMap.get('id');
    this.loadingCtrl.create({
      spinner: 'crescent'
    }).then(loadingElement => {
      loadingElement.present();
      this.feedService.getTopicInfo(this.topicId).pipe(tap(resData => {
        console.log(resData);
        if (resData.success) {
          resData.topic.title = this.titleCaseWord(resData.topic.title);
          this.topicInfos = resData.topic;
          if (this.isAuthenticated) {
            if (resData.joined) {
              this.joined = true;
              this.joinbtn = "joined";
            }
          }
          resData.posts.forEach(post => {
            post.title = this.titleCaseWord(post.title);
          });
          this.listOfPosts = resData.posts.sort((obj1, obj2) => {
            if (obj1.likes > obj2.likes) {
              return -1;
            }

            if (obj1.likes < obj2.likes) {
              return 1;
            }

            return 0;
          });
          loadingElement.dismiss();
        } else {
          loadingElement.dismiss();
          this.navCtrl.navigateRoot('/error/notfound');
        }
      })).subscribe();
    });
  }

  titleCaseWord(word: string) {
    if (!word) return word;
    return word[0].toUpperCase() + word.substr(1);
  }

  getDesc() {
    if (this.topicInfos['description']) {
      return this.topicInfos.description;
    }
  }

  joinTopic() {
    if (this.isAuthenticated) {
      this.joinbtn = "Joining"
      this.loadingCtrl.create({
        spinner: 'crescent'
      }).then(loadingElement => {
        loadingElement.present();
        this.feedService.joinTopic(this.topicId).pipe(tap(res=>{
          console.log(res);
          if (res.success) {
            this.joined = true;
            this.joinbtn = "joined";
            loadingElement.dismiss();
          }
          else {
            loadingElement.dismiss();
            this.alertCtrl.create({
              header: 'Something wrong',
              message: 'Please try again later ...',
              buttons: ['Ok']
            }).then(alertElement => {
              alertElement.present();
            });
          }
        }, err => {
          console.log(err);
          loadingElement.dismiss();
          this.alertCtrl.create({
            header: 'An error has occured',
            message: err.error.message ? err.error.message : 'Failed to communicate with server. Please try again later ...',
            buttons: ['Ok']
          }).then(alertElement => {
            alertElement.present();
          });
        })).subscribe();
        loadingElement.dismiss();
        this.joined = true;
        this.joinbtn = "Joined"
      });
    } else {
      this.navCtrl.navigateRoot('/login');
    }
  }
}
