-- Table post
create table post(
    id int not null primary key auto_increment, 
    title varchar(200) not null, 
    likes int not null, 
    dislikes int not null, 
    date datetime not null, 
    type int not null,
    topic int not null, 
    user int not null,
    foreign key (topic) references topic(id),
    foreign key (user) references user(id)
    );

-- Table topic
create table topic(
    id int not null primary key auto_increment, 
    title varchar(1000) not null, description varchar(10000), 
    iconUrl varchar(1000), 
    coverUrl varchar(1000), 
    isevent boolean not null);

-- Table User
create table user (
    id int primary key AUTO_INCREMENT, 
    username varchar(100) not null, 
    password varchar(1000) not null, 
    email varchar(200) not null);

-- Table comment
create table comment(
    id int not null primary key AUTO_INCREMENT,
    text text not null,
    likes int not null,
    dislikes int not null,
    date datetime not null,
    post int not null,
    user int not null,
    foreign key(post) references post(id),
    foreign key (user) references user(id));

-- Table event
create table event(
    id int not null primary key,
    location varchar(1000),
    date datetime not null,
    foreign key(id) references topic(id)
);

-- Table text
create table text(
    id int not null primary key AUTO_INCREMENT,
    text text not null,
    post int not null,
    foreign key(post) references post(id)
);

-- Table image
create table image(
    id int not null primary key AUTO_INCREMENT,
    url varchar(1000) not null,
    post int not null,
    foreign key(post) references post(id)
);

-- Table invitation
create table invitation(
    id int not null primary key AUTO_INCREMENT,
    joined int not null,
    description text not null,
    location varchar(50) not null,
    date date not null,
    post int not null,
    foreign key(post) references post(id)
);

-- Table member
create table member(
    userid int not null,
    topicid int not null,
    ismoderator boolean not null,
    primary key(userid, topicid),
    foreign key(userid) references user(id),
    foreign key(topicid) references topic(id)
);

-- Table post likes
create table postlike(
    userid int not null,
    postid int not null,
    islike boolean,
    primary key(userid, postid),
    foreign key(userid) references user(id),
    foreign key(postid) references post(id)
);

-- Table comment likes
create table commentlike(
    userid int not null,
    commentid int not null,
    islike boolean,
    primary key(userid, commentid),
    foreign key(userid) references user(id),
    foreign key(commentid) references comment(id)
);