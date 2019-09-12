BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id serial PRIMARY KEY,
  username varchar(255) NOT NULL UNIQUE,     -- Username
  email varchar(255) NOT NULL UNIQUE,
  password varchar(32) NOT NULL,      -- Password
  salt varchar(256) NOT NULL,          -- Password Salt
  role varchar(255) NOT NULL default('user'),
  isDisabled boolean default('false')
);

drop table if exists post cascade;
drop table if exists favorites cascade;
drop table if exists comments cascade;
drop table if exists likes cascade;


create table post (

post_id   serial         not null,
title     varchar(255)   not null,
date_time timestamp      not null DEFAULT NOW(),
img_url   varchar(255)   not null,
username  varchar(255)   not null,
tags      varchar(255),

constraint pk_post_post_id primary key(post_id) 

);

create table comments (

comment_id serial       not null,
post_id int             not null,
username varchar(255)   not null,
date_time timestamp      not null DEFAULT NOW(),
comment varchar(500)     not null,

constraint pk_comments_comment_id primary key (comment_id),
constraint fk_comments_post_id foreign key(post_id) references post(post_id)
);

create table likes (

like_id serial          not null,
post_id int             not null,
username varchar (255) not null,

constraint pk_likes_like_id primary key (like_id),
constraint fk_likes_post_id foreign key(post_id) references post(post_id)

);

create table favorites (
favorite_id serial      not null,
post_id int             not null,
username varchar (255)  not null,

constraint pk_favorites_favorite_id primary key (favorite_id),
constraint fk_favorites_post_id foreign key(post_id) references post(post_id)

);


COMMIT TRANSACTION;
