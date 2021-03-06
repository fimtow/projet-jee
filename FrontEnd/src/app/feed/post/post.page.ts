import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AlertController, AngularDelegate, LoadingController, NavController } from '@ionic/angular';
import { tap } from 'rxjs/operators';
import { AuthService } from 'src/app/Auth/auth.service';
import { FeedService } from '../feed.service';
import { post } from '../post.model';

interface comment {
  id?: number,
  date?: string,
  text?: string,
  likes?: number,
  dislikes?: number,
  user?: {
    id?: number,
    username?: string
  }
}

@Component({
  selector: 'app-post',
  templateUrl: './post.page.html',
  styleUrls: ['./post.page.scss'],
})
export class PostPage implements OnInit {
  public id: string = '';
  public postInfos: any = { success: false, post: { user: { username: "" }, content: { url: "", text: "", joined: 0 }, topic: { title: "" } } };

  private isAuthenticated: boolean = false;
  public joinbtn: string = "Join"
  public joined: boolean = false;

  public comments: comment[] = [];

  public commentform: FormGroup;
  constructor(
    private route: ActivatedRoute,
    private feedService: FeedService,
    private navCtrl: NavController,
    private loadingCtrl: LoadingController,
    private authService: AuthService,
    public formBuilder: FormBuilder,
    private alertCtrl: AlertController) {
    this.commentform = formBuilder.group({
      text: ['']
    });
  }

  ngOnInit() {
    this.authService.autoLogin().subscribe(isAuth => { this.isAuthenticated = isAuth; console.log(isAuth) });
    this.id = this.route.snapshot.paramMap.get('id');
    // var y: number = +this.id;
    this.loadingCtrl.create({
      spinner: 'crescent'
    }).then(loadingElement => {
      loadingElement.present();
      this.feedService.getPostInfo(this.id).pipe(tap(resData => {
        if (resData.success) {
          resData.post.title = this.titleCaseWord(resData.post.title);
          this.postInfos = resData;
          this.comments = resData.comments;
          console.log("Comments : ", this.comments);
          console.log(this.postInfos);
          if(resData.post.type == 2){
            this.feedService.getTopicInfo(resData.post.topic.id).subscribe(data =>{
              if (data.joined) {
                this.joined = true;
                this.joinbtn = "joined";
              }
            })
          }
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

  joinTopic() {
    if (this.isAuthenticated) {
      this.joinbtn = "Joining"
      this.loadingCtrl.create({
        spinner: 'crescent'
      }).then(loadingElement => {
        loadingElement.present();
        this.feedService.joinTopic(this.postInfos.post.topic.id).pipe(tap(res=>{
          console.log(res);
          if (res.success) {
            this.joined = true;
            this.joinbtn = "joined";
            this.postInfos.post.content.joined += 1;
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

  comment() {
    this.loadingCtrl.create({
      spinner: 'crescent'
    }).then(loadingElement => {
      loadingElement.present();
      this.feedService.subcomment(this.id, this.commentform.value.text).subscribe(data => {
        console.log(data);
        if (data.success) {
          loadingElement.dismiss();
          this.commentform.reset();
          this.feedService.getPostInfo(this.id).pipe(tap(resData => {
            if (resData.success) {
              this.comments = resData.comments;
              console.log("Comments : ", this.comments);
            } else {
              this.navCtrl.navigateRoot('/error/notfound');
            }
          })).subscribe();
        }
        else {
          loadingElement.dismiss();
          this.alertCtrl.create({
            header: 'Something wrong',
            message: 'Please retry again !',
            buttons: ['Ok']
          }).then(alertElement => {
            alertElement.present();
          });
          console.log("Commenting failed");
        }
      });
    });
  }

  getImageURL() {
    var item = 'url';
    if (this.postInfos.post['content']) {
      if(this.postInfos.post.content[item]){
        return this.postInfos.post.content.url;
      }
    }
  }
}
