-- Password for this user is 'greatwall'
INSERT INTO users ("username", "email", "password", "salt", "role") VALUES
('admin', 'mwatso7@kent.edu',
'FjZDm+sndmsdEDwNtfr6NA==',
'kidcasB0te7i0jK0fmRIGHSm0mYhdLTaiGkEAiEvLp7dAEHWnuT8n/5bd2V/mqjstQ198iImm1xCmEFu+BHyOz1Mf7vm4LILcrr17y7Ws40Xyx4FOCt8jD03G+jEafpuVJnPiDmaZQXJEpEfekGOvhKGOCtBnT5uatjKEuVWuDA=',
'admin');

INSERT INTO post(title, img_url, username, tags) VALUES ('First Post','https://www.gannett-cdn.com/presto/2019/03/11/PDTN/0c313583-c11d-4ea4-8d93-dbbebaf9de06-Josh_Classroom_Tech_Elevator.png?crop=932,516,x29,y0&width=3200&height=1680&fit=bounds','user','tag1,tag2,tag3');
INSERT INTO post(title, img_url, username, tags) VALUES ('Second Post','https://course_report_production.s3.amazonaws.com/rich/rich_files/rich_files/4763/original/jamie-justin-brian-tech-elevator-veterans.jpg','user','tag1,tag2,tag3');

INSERT INTO post(title, img_url, username, tags) VALUES ('Third Post','https://course_report_production.s3.amazonaws.com/rich/rich_files/rich_files/4763/original/jamie-justin-brian-tech-elevator-veterans.jpg','user','tag1,tag2,tag3');

INSERT INTO comments(username, comment) VALUES ('user','This is a great post');
INSERT INTO comments(username, comment) VALUES ('user','This is not a great post');

INSERT INTO post_comments_reference(post_id, comment_id) VALUES (1,1);
INSERT INTO post_comments_reference(post_id, comment_id) VALUES (1,2);
