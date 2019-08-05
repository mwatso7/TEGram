begin transaction;

drop table if exists post;
drop table if exists post_user_reference;


create table post (

post_id   serial         not null,
title     varchar(255)   not null,
date_time date           not null,
img_url   varchar(255)   not null,
thumbnail varchar(255)   not null,
tags      varchar(255),

constraint pk_post_post_id primary key(post_id) 

);

create table post_user_reference (

post_id int  not null,
id      int  not null,

constraint fk_post_user_reference_post_id foreign key(post_id) references post(post_id),
constraint fk_post_user_reference_id foreign key(id) references app_user(id)
);

commit;