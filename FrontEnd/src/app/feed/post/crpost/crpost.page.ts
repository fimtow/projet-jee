import { Component, OnInit } from '@angular/core';
import { Form, FormBuilder, FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-crpost',
  templateUrl: './crpost.page.html',
  styleUrls: ['./crpost.page.scss'],
})
export class CrpostPage implements OnInit {
  public ptype : string = 'text';

  public tform: FormGroup;
  public iform: FormGroup;
  public title: string = '';
  constructor(public formBuilder: FormBuilder) { 
    this.tform = formBuilder.group({
      title: [''],
      text: [''],
      type: 0
    });
    this.iform = formBuilder.group({
      title: [''],
      test: [''],
      type: 1
    });
   }

  ngOnInit() {
    
  }

  changeType(type: string){
    this.ptype = type;
    console.log(this.ptype);
  }

  disableSegment(){
    if(this.tform.dirty && ((this.tform.value.text != "") || (this.iform.value.test != ""))){
      return true;
    }
    return false;
  }

  submit(form: FormGroup){
    console.log(form.value);
  }
}
