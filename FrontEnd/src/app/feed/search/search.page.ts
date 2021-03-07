import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FeedService } from '../feed.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.page.html',
  styleUrls: ['./search.page.scss'],
})
export class SearchPage implements OnInit {

  public found : boolean = false;
  public searchtop: string = "";
  public listOfTopics : any[] = [];
  constructor(private route: ActivatedRoute,
    private feedService: FeedService) { }

  ngOnInit() {
    this.searchtop = this.route.snapshot.paramMap.get("srch");
    this.feedService.searchTopic(this.searchtop).subscribe(data =>{
      if(data.success){
        this.found = true;
        this.listOfTopics = data.listOfTopics;
      }
    })
  }

}
