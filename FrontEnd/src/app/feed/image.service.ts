import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

interface ImageInfo{
  title:string;
  link:string;
}

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  private images:object[] = [];
  private url: string = 'https://api.imgur.com/3/image';
  private clientId: string = '9a8b2ba9a503b48';
  imageLink:any;
  public imgInfos: any;
 
  constructor(private http:HttpClient) { }
  
  async uploadImage(imageFile:File, infoObject:{}){
    let formData = new FormData();
    formData.append('image', imageFile, imageFile.name);
 
    let header = new HttpHeaders({
      "authorization": 'Client-ID '+this.clientId
    });
   
    // const imageData = await this.http.post(this.url, formData, {headers:header}).toPromise();
    // this.imgInfos = imageData;
    // this.imageLink = imageData['data'].link;

    return await this.http.post<any>(this.url, formData, {headers:header}).toPromise();

    // let newImageObject:ImageInfo = {
    //   title:infoObject["title"],
    //   link:this.imageLink
    // };
 
  }
}
