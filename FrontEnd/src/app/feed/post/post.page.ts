import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { LoadingController, NavController } from '@ionic/angular';
import { tap } from 'rxjs/internal/operators/tap';
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
  public postInfos: post = { success: false, post: { user: { username: '' }, content: { url: '', text: '', joined: 0 }, topic: { title: '' } } };

  private isAuthenticated: boolean = false;
  private joinbtn: string = "Join"
  private joined: boolean = false;

  public comments: comment[] = [];

  public commentform: FormGroup;
  constructor(
    private route: ActivatedRoute,
    private feedService: FeedService,
    private navCtrl: NavController,
    private loadingCtrl: LoadingController,
    private authService: AuthService,
    public formBuilder: FormBuilder) {
      this.commentform = formBuilder.group({
        id: [''],
        text: ['']
      });
    }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    // var y: number = +this.id;
    this.loadingCtrl.create({
      spinner: 'crescent'
    }).then(loadingElement => {
      loadingElement.present();
      this.feedService.getPostInfo(this.id).pipe(tap(resData => {
        if (resData.success) {
          resData.post.title = this.titleCaseWord(resData.post.title);
          loadingElement.dismiss();
          this.postInfos = resData;
          this.comments = resData.comments;
          console.log("Comments : ",this.comments);
          console.log(this.postInfos);
        } else {
          loadingElement.dismiss();
          this.navCtrl.navigateRoot('/error/notfound');
        }
      })).subscribe();
    });
    this.authService.autoLogin().subscribe(isAuth => {this.isAuthenticated = isAuth; console.log(isAuth)});
    
  }

  titleCaseWord(word: string) {
    if (!word) return word;
    return word[0].toUpperCase() + word.substr(1);
  }

  joinEvent() {
    if (this.isAuthenticated) {
      this.joinbtn = "Joining"
      this.loadingCtrl.create({
        spinner: 'crescent'
      }).then(loadingElement => {
        loadingElement.present();
        setTimeout(() => {
          loadingElement.dismiss();
          this.joined = true;
          this.joinbtn = "Joined"
        }, 2000);
      });
      console.log("Event topic id : ", this.postInfos.post.topic.id);
    } else {
      this.navCtrl.navigateRoot('/login');
    }
  }

  comment(){
    this.feedService.subcomment(this.id,this.commentform.value.text);
    console.log(this.commentform.value);
  }
}
