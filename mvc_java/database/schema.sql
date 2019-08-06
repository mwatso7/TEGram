-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

-- CREATE statements go here
DROP TABLE IF EXISTS app_user cascade;

CREATE TABLE app_user (
  id SERIAL PRIMARY KEY,
  user_name varchar(32) NOT NULL UNIQUE,
  password varchar(32) NOT NULL,
  role varchar(32),
  salt varchar(255) NOT NULL
);

drop table if exists post cascade;
drop table if exists post_user_reference cascade;
drop table if exists post_comments_reference cascade;
drop table if exists comments cascade;


create table post (

post_id   serial         not null,
title     varchar(255)   not null,
date_time timestamp      not null DEFAULT NOW(),
img_url   varchar(255)   not null,
thumbnail varchar(255)   not null,
tags      varchar(255),

constraint pk_post_post_id primary key(post_id) 

);

create table comments (

comment_id serial       not null,
user_name varchar(255)  not null,
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

create table post_user_reference (

post_id int  not null,
id      int  not null,

constraint fk_post_user_reference_post_id foreign key(post_id) references post(post_id),
constraint fk_post_user_reference_id foreign key(id) references app_user(id)
);

COMMIT;