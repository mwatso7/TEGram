BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id serial PRIMARY KEY,
  username varchar(255) NOT NULL UNIQUE,     -- Username
  password varchar(32) NOT NULL,      -- Password
  salt varchar(256) NOT NULL,          -- Password Salt
  role varchar(255) NOT NULL default('user')
);

drop table if exists post cascade;
drop table if exists post_comments_reference cascade;
drop table if exists comments cascade;


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
username varchar(255)   not null,
date_time timestamp      not null DEFAULT NOW(),
comment varchar(500)     not null,

constraint pk_comments_comment_id primary key (comment_id)
);

create table post_comments_reference (

post_id int not null,
comment_id int not null,

constraint fk_post_comments_reference_post_id foreign key(post_id) references post(post_id),
constraint fk_post_comments_reference_comment_id foreign key(comment_id) references comments(comment_id)
);


COMMIT TRANSACTION;
