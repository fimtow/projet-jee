import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/Auth/auth.service';
import { FeedService } from '../../feed.service';

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
  public ptype : string = 'text';

  public title: string = '';

  public tform: FormGroup;
  public iform: FormGroup;
  public invform: FormGroup;

  public topicId: string = '';

  public topics: topic[] = [];

  constructor(public formBuilder: FormBuilder, private route: ActivatedRoute, private authService: AuthService) { 
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
    console.log("topic id : ", this.topicId);
    this.authService.getUserId().subscribe(id => {
      console.log("id from crpost :",id);
    });
  }

  changeType(type: string){
    this.ptype = type;
    console.log(this.ptype);
  }

  disableSegment(){
    if((this.tform.dirty || this.iform.dirty || this.invform.dirty) && ((this.tform.value.text != "") || (this.iform.value.url != ""))){
      return true;
    }
    return false;
  }

  submit(form: FormGroup){
    this.invform.value.date = formatDate(this.invform.value.date, 'dd/MM/yyyy', 'en');
    console.log(form.value);
  }
}
