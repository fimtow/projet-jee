import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AlertController, NavController } from '@ionic/angular';
import { tap } from 'rxjs/operators';
import { AuthService } from 'src/app/Auth/auth.service';
import { FeedService } from '../../feed.service';
import { ImageService } from '../../image.service';

interface topic {
  id?: number,
  title?: string
}

@Component({
  selector: 'app-crpost',
  templateUrl: './crpost.page.html',
  styleUrls: ['./crpost.page.scss'],
})
export class CrpostPage implements OnInit {
  //Post type
  public ptype: string = 'text';

  //Post title
  public title: string = '';

  //Divers forms
  public tform: FormGroup;
  public iform: FormGroup;
  public invform: FormGroup;

  //ID of topic
  public topicId: string = '';

  //Uploading image
  private imageFile: File;

  constructor(public formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private feedService: FeedService,
    private imageService: ImageService,
    private navCtrl: NavController,
    private alertCtrl: AlertController) {
    this.tform = formBuilder.group({
      title: [''],
      text: [''],
      type: 0
    });
    this.iform = formBuilder.group({
      title: [''],
      url: [''],
      type: 1
    });
    this.invform = formBuilder.group({
      title: [''],
      description: [''],
      location: [''],
      date: Date,
      type: 2
    });
  }

  ngOnInit() {
    this.topicId = this.route.snapshot.paramMap.get('id');
  }

  changeType(type: string) {
    this.ptype = type;
    console.log(this.ptype);
  }

  disableSegment() {
    if ((this.tform.dirty || this.iform.dirty || this.invform.dirty) && ((this.tform.value.text != "") || (this.iform.value.url != "") || (this.invform.value.description != ""))) {
      return true;
    }
    return false;
  }

  addImage() {
    let infoObject = {
      title: this.title
    }
    this.imageService.uploadImage(this.imageFile, infoObject).then(resData=>{
      console.log(resData);
      if(resData.success) {
        return true;
      }
      else {
        return false;
      }
    });
    this.title = "";
  }

  imageInputChange(imageInput: any) {
    this.imageFile = imageInput.files[0];
  }

  submit(form: FormGroup) {
    this.feedService.createPost(form, this.topicId).subscribe(data => {
      console.log(data);
      if(data.success){
        this.alertCtrl.create({
          header: 'Success',
          message: 'Post Created Successfully',
          buttons: ['Ok']
        }).then(alertElement => {
          alertElement.present();
        }).then(()=>{
          this.navCtrl.back();
        }); 
      }
    });
    console.log(form.value)
  }

  submitImg(form: FormGroup){
    let infoObject = {
      title: this.title
    }
    this.imageService.uploadImage(this.imageFile, infoObject).then(resData=>{
      console.log(resData);
      if(resData.success) {
        form.value.url = resData.data.link;
        this.feedService.createPost(form, this.topicId).pipe(tap(crdata => {
          console.log("feedservice.createpost : ",crdata);
          if(crdata.success){
            this.alertCtrl.create({
              header: 'Success',
              message: 'Post Created Successfully',
              buttons: ['Ok']
            }).then(alertElement => {
              alertElement.present();
            }).then(()=>{
              this.navCtrl.back();
            });
            
          }
        })).subscribe();
      }
      else {
        console.log("there's an error somewhere");
      }
    });
  }
}
