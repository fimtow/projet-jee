!function(){function t(t,n){if(!(t instanceof n))throw new TypeError("Cannot call a class as a function")}function n(t,n){for(var e=0;e<n.length;e++){var o=n[e];o.enumerable=o.enumerable||!1,o.configurable=!0,"value"in o&&(o.writable=!0),Object.defineProperty(t,o.key,o)}}function e(t,e,o){return e&&n(t.prototype,e),o&&n(t,o),t}(window.webpackJsonp=window.webpackJsonp||[]).push([[64],{PA3a:function(n,o,i){"use strict";i.r(o),i.d(o,"CrpostPageModule",function(){return y});var r,c=i("ofXK"),b=i("3Pt+"),a=i("TEn/"),l=i("tyNb"),s=i("vkgz"),u=i("fXoL"),p=i("P7CE"),d=i("mrSG"),f=i("tk/3"),g=((r=function(){function n(e){t(this,n),this.http=e,this.images=[],this.url="https://api.imgur.com/3/image",this.clientId="9a8b2ba9a503b48"}return e(n,[{key:"uploadImage",value:function(t,n){return Object(d.a)(this,void 0,void 0,regeneratorRuntime.mark(function n(){var e,o;return regeneratorRuntime.wrap(function(n){for(;;)switch(n.prev=n.next){case 0:return(e=new FormData).append("image",t,t.name),o=new f.c({authorization:"Client-ID "+this.clientId}),n.next=5,this.http.post(this.url,e,{headers:o}).toPromise();case 5:return n.abrupt("return",n.sent);case 6:case"end":return n.stop()}},n,this)}))}}]),n}()).\u0275fac=function(t){return new(t||r)(u.Sb(f.a))},r.\u0275prov=u.Fb({token:r,factory:r.\u0275fac,providedIn:"root"}),r);function m(t,n){if(1&t){var e=u.Pb();u.Mb(0),u.Ob(1,"form",16),u.Ob(2,"ion-row"),u.Ob(3,"ion-col",0),u.Ob(4,"ion-input",17),u.Wb("ngModelChange",function(t){return u.hc(e),u.ac().title=t}),u.Nb(),u.Nb(),u.Nb(),u.Ob(5,"ion-row"),u.Ob(6,"ion-col",0),u.Kb(7,"ion-textarea",18),u.Nb(),u.Nb(),u.Ob(8,"ion-row"),u.Ob(9,"ion-col",19),u.Ob(10,"ion-button",20),u.Wb("click",function(){u.hc(e);var t=u.ac();return t.submit(t.tform)}),u.lc(11," Post "),u.Nb(),u.Nb(),u.Nb(),u.Nb(),u.Lb()}if(2&t){var o=u.ac();u.zb(1),u.dc("formGroup",o.tform),u.zb(3),u.dc("ngModel",o.title)}}function h(t,n){if(1&t){var e=u.Pb();u.Mb(0),u.Ob(1,"form",16),u.Ob(2,"ion-row"),u.Ob(3,"ion-col",0),u.Ob(4,"ion-input",17),u.Wb("ngModelChange",function(t){return u.hc(e),u.ac().title=t}),u.Nb(),u.Nb(),u.Nb(),u.Ob(5,"ion-row"),u.Ob(6,"ion-col",0),u.Ob(7,"input",21,22),u.Wb("change",function(){u.hc(e);var t=u.gc(8);return u.ac().imageInputChange(t)}),u.Nb(),u.Nb(),u.Nb(),u.Ob(9,"ion-row"),u.Ob(10,"ion-col",19),u.Ob(11,"ion-button",20),u.Wb("click",function(){u.hc(e);var t=u.ac();return t.submitImg(t.iform)}),u.lc(12," Post "),u.Nb(),u.Nb(),u.Nb(),u.Nb(),u.Lb()}if(2&t){var o=u.ac();u.zb(1),u.dc("formGroup",o.iform),u.zb(3),u.dc("ngModel",o.title)}}function O(t,n){if(1&t){var e=u.Pb();u.Mb(0),u.Ob(1,"form",16),u.Ob(2,"ion-row"),u.Ob(3,"ion-col",0),u.Ob(4,"ion-input",17),u.Wb("ngModelChange",function(t){return u.hc(e),u.ac().title=t}),u.Nb(),u.Nb(),u.Nb(),u.Ob(5,"ion-row"),u.Ob(6,"ion-col",0),u.Kb(7,"ion-textarea",23),u.Nb(),u.Nb(),u.Ob(8,"ion-row"),u.Ob(9,"ion-col",0),u.Kb(10,"ion-input",24),u.Nb(),u.Nb(),u.Ob(11,"ion-row"),u.Ob(12,"ion-col",0),u.Ob(13,"ion-item"),u.Ob(14,"ion-label"),u.lc(15,"Date"),u.Nb(),u.Kb(16,"ion-datetime",25),u.Nb(),u.Nb(),u.Nb(),u.Ob(17,"ion-row"),u.Ob(18,"ion-col",19),u.Ob(19,"ion-button",20),u.Wb("click",function(){u.hc(e);var t=u.ac();return t.submit(t.invform)}),u.lc(20," Post "),u.Nb(),u.Nb(),u.Nb(),u.Nb(),u.Lb()}if(2&t){var o=u.ac();u.zb(1),u.dc("formGroup",o.invform),u.zb(3),u.dc("ngModel",o.title)}}var N,v,x,w=[{path:"",component:(N=function(){function n(e,o,i,r){t(this,n),this.formBuilder=e,this.route=o,this.feedService=i,this.imageService=r,this.ptype="text",this.title="",this.topicId="",this.tform=e.group({title:[""],text:[""],type:0}),this.iform=e.group({title:[""],url:[""],type:1}),this.invform=e.group({title:[""],description:[""],location:[""],date:Date,type:2})}return e(n,[{key:"ngOnInit",value:function(){this.topicId=this.route.snapshot.paramMap.get("id")}},{key:"changeType",value:function(t){this.ptype=t,console.log(this.ptype)}},{key:"disableSegment",value:function(){return!(!(this.tform.dirty||this.iform.dirty||this.invform.dirty)||""==this.tform.value.text&&""==this.iform.value.url&&""==this.invform.value.description)}},{key:"addImage",value:function(){this.imageService.uploadImage(this.imageFile,{title:this.title}).then(function(t){return console.log(t),!!t.success}),this.title=""}},{key:"imageInputChange",value:function(t){this.imageFile=t.files[0]}},{key:"submit",value:function(t){this.feedService.createPost(t,this.topicId).subscribe(function(t){console.log(t)}),console.log(t.value)}},{key:"submitImg",value:function(t){var n=this;this.imageService.uploadImage(this.imageFile,{title:this.title}).then(function(e){console.log(e),e.success?(t.value.url=e.data.link,n.feedService.createPost(t,n.topicId).pipe(Object(s.a)(function(t){console.log("feedservice.createpost : ",t)})).subscribe()):console.log("there's an error somewhere")})}}]),n}(),N.\u0275fac=function(t){return new(t||N)(u.Jb(b.b),u.Jb(l.a),u.Jb(p.a),u.Jb(g))},N.\u0275cmp=u.Db({type:N,selectors:[["app-crpost"]],decls:50,vars:4,consts:[["size","12"],["size","7","offset","1",2,"--ion-background-color","#fff"],["fixed",""],["fixed","",1,"ion-padding"],[1,"crpost"],[2,"background-color","#aaa9a9"],["value","text",3,"disabled","ionChange"],["segment",""],["value","text"],["value","image"],["value","invit"],[4,"ngIf"],["size","3"],[1,"widget"],[1,"regles","w-center"],[1,"ion-no-padding","ion-no-margin"],[3,"formGroup"],["formControlName","title","placeholder","Title",1,"ion-padding",3,"ngModel","ngModelChange"],["formControlName","text","placeholder","Put your text here (optional)."],["offset","10","size","2"],["expand","block","fill","clear",1,"postbtn",3,"click"],["type","file","accept","image/*",3,"change"],["imageInput",""],["formControlName","description","placeholder","Description"],["formControlName","location","type","text","placeholder","Location"],["formControlName","date","displayFormat","MMM DD YYYY","placeholder","Select Date"]],template:function(t,n){if(1&t){var e=u.Pb();u.Ob(0,"ion-header"),u.Kb(1,"ion-toolbar"),u.Nb(),u.Ob(2,"ion-content"),u.Ob(3,"ion-grid"),u.Ob(4,"ion-row"),u.Kb(5,"ion-col",0),u.Nb(),u.Ob(6,"ion-row"),u.Ob(7,"ion-col",1),u.Ob(8,"ion-grid",2),u.Ob(9,"ion-row"),u.Ob(10,"ion-col"),u.Ob(11,"ion-card"),u.Ob(12,"ion-grid",3),u.Ob(13,"ion-row"),u.Ob(14,"ion-col",0),u.Ob(15,"p",4),u.lc(16,"Create a new post"),u.Nb(),u.Kb(17,"hr",5),u.Nb(),u.Nb(),u.Ob(18,"ion-row"),u.Ob(19,"ion-col",0),u.Ob(20,"ion-segment",6,7),u.Wb("ionChange",function(){u.hc(e);var t=u.gc(21);return n.ptype=t.value}),u.Ob(22,"ion-segment-button",8),u.Ob(23,"ion-label"),u.lc(24,"Text"),u.Nb(),u.Nb(),u.Ob(25,"ion-segment-button",9),u.Ob(26,"ion-label"),u.lc(27,"Image"),u.Nb(),u.Nb(),u.Ob(28,"ion-segment-button",10),u.Ob(29,"ion-label"),u.lc(30,"Invitation"),u.Nb(),u.Nb(),u.Nb(),u.Nb(),u.Nb(),u.kc(31,m,12,2,"ng-container",11),u.kc(32,h,13,2,"ng-container",11),u.kc(33,O,21,2,"ng-container",11),u.Nb(),u.Nb(),u.Nb(),u.Nb(),u.Nb(),u.Nb(),u.Ob(34,"ion-col",12),u.Ob(35,"div",13),u.Ob(36,"h4",14),u.lc(37,"Posting rules"),u.Nb(),u.Kb(38,"hr"),u.Ob(39,"ion-list",15),u.Ob(40,"ion-item"),u.lc(41," 1- Respect the general order. "),u.Nb(),u.Ob(42,"ion-item"),u.lc(43," 2- Act as a human being. "),u.Nb(),u.Ob(44,"ion-item"),u.lc(45," 3- Avoid RACISM. "),u.Nb(),u.Ob(46,"ion-item"),u.lc(47," 4- Avoid fanaticism. "),u.Nb(),u.Ob(48,"ion-item"),u.lc(49," 5- Take care ! "),u.Nb(),u.Nb(),u.Nb(),u.Nb(),u.Nb(),u.Nb(),u.Nb()}2&t&&(u.zb(20),u.dc("disabled",n.disableSegment()),u.zb(11),u.dc("ngIf","text"==n.ptype),u.zb(1),u.dc("ngIf","image"==n.ptype),u.zb(1),u.dc("ngIf","invit"==n.ptype))},directives:[a.p,a.C,a.m,a.o,a.x,a.l,a.h,a.y,a.J,a.z,a.u,c.i,a.v,a.t,b.o,b.j,b.d,a.s,a.K,b.i,b.c,a.A,a.f,a.n],styles:["h4.regles[_ngcontent-%COMP%]{background-color:#dd4e35;margin:0;padding-top:10px;padding-bottom:10px;color:#fff}#container[_ngcontent-%COMP%]{text-align:center;position:absolute;left:0;right:0;top:50%;transform:translateY(-50%)}#container[_ngcontent-%COMP%]   strong[_ngcontent-%COMP%]{font-size:20px;line-height:26px}#container[_ngcontent-%COMP%]   p[_ngcontent-%COMP%]{font-size:16px;line-height:22px;color:#8c8c8c;margin:0}#container[_ngcontent-%COMP%]   a[_ngcontent-%COMP%]{text-decoration:none}ion-content[_ngcontent-%COMP%]{--ion-background-color:#f2f2f2}ion-card[_ngcontent-%COMP%]{box-shadow:none;border:1px solid #bbb;margin:0 0 0 10px;border-radius:0;text-decoration:none}ion-title[_ngcontent-%COMP%]{font-size:17px}.widget[_ngcontent-%COMP%]{float:left;width:100%;background-color:#fff;border-left:1px solid #e4e4e4;border-right:1px solid #e4e4e4;border-bottom:1px solid #e4e4e4;margin-bottom:20px;-ms-box-shadow:0 2px #e4e4e4;-o-box-shadow:0 2px #e4e4e4;box-shadow:0 2px #e4e4e4;--ion-background-color:#fff}.w-center[_ngcontent-%COMP%]{text-align:center}p.crpost[_ngcontent-%COMP%]{padding:0 0 5px 20px;font-size:medium}ion-select[_ngcontent-%COMP%]::part(icon){display:none!important}ion-select[_ngcontent-%COMP%]{max-width:100%}.popover-content[_ngcontent-%COMP%]{left:0!important;width:100%}ion-button.postbtn[_ngcontent-%COMP%]{border:2px solid #3880ff;font-weight:700;border-radius:5px}ion-textarea[_ngcontent-%COMP%]{height:150px}ion-input[_ngcontent-%COMP%], ion-textarea[_ngcontent-%COMP%]{font-size:medium;border-radius:5px;border:1px solid #bebebe;padding-left:5px}ion-input.imgup[_ngcontent-%COMP%]{border:1px dashed #bebebe}"]}),N)}],C=((x=function n(){t(this,n)}).\u0275mod=u.Hb({type:x}),x.\u0275inj=u.Gb({factory:function(t){return new(t||x)},imports:[[l.j.forChild(w)],l.j]}),x),y=((v=function n(){t(this,n)}).\u0275mod=u.Hb({type:v}),v.\u0275inj=u.Gb({factory:function(t){return new(t||v)},imports:[[c.b,b.e,a.D,C,b.m]]}),v)}}])}();