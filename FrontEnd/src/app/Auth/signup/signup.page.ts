import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AlertController, NavController } from '@ionic/angular';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.page.html',
  styleUrls: ['./signup.page.scss'],
})
export class SignupPage implements OnInit {
  public registerForm: FormGroup;

  constructor(public formBuilder: FormBuilder, 
    private authService: AuthService,
    private navCtrl: NavController,
    private alertCtrl: AlertController) {
    this.registerForm = formBuilder.group({
      username: ['', Validators.required],
      email: ['', Validators.compose([Validators.email, Validators.required])],
      password: ['', Validators.compose([Validators.required, Validators.minLength(4)])]
    });
   }

  ngOnInit() {
  }

  signup(form: FormGroup){
    if(form.valid){
      this.authService.signup(form).subscribe(data => {
        console.log(data);
        if(data.success){
          this.alertCtrl.create({
            header: 'Success',
            buttons: ['Get Started']
          }).then(alertElement => {
            alertElement.present();
          }).then(()=>{
            this.navCtrl.navigateRoot('/login');
          }); 
        } else {
          var msg = "";
          for (let key in data.errors) {
            msg += `${key} : ${data.errors[key]}\n\n`
          }
          this.alertCtrl.create({
            header: 'Failed',
            message: msg,
            buttons: ['Ok']
          }).then(alertElement => {
            alertElement.present();
          }); 
        }
      })
    }
  }

  titleCaseWord(word: string) {
    if (!word) return word;
    return word[0].toUpperCase() + word.substr(1);
  }
}
