import { Component, OnInit } from '@angular/core';

interface post {
  id?: number,
  title?: string,
  likes?: number,
  dislikes?: number,
  date?: string,
  type?: number,
  topic?: number,
  user?: number
}
@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.page.html',
  styleUrls: ['./user-page.page.scss'],
})
export class UserPagePage implements OnInit {
  public username: string = "lorem";
  public email: string = "lorem@ipsum.it";
  public listPosts : post[] = [
    {title:'Avantages de l internet', likes: 300, dislikes: 100},
    {title:'Avantages de l internet', likes: 300, dislikes: 100},
    {title:'Avantages de l internet', likes: 300, dislikes: 100},
    {title:'Avantages de l internet', likes: 300, dislikes: 100}
  ];
  public joinedTopics: string[] = [];
  constructor() { }

  ngOnInit() {
  }

}
