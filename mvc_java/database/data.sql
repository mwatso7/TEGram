-- *****************************************************************************
-- This script contains INSERT statements for populating tables with seed data
-- *****************************************************************************

BEGIN;

INSERT INTO app_user(user_name, password, role, salt) VALUES ('max','password','user','fakesaltvalue');
INSERT INTO app_user(user_name, password, role, salt) VALUES ('steve','password1','user','fakesaltvalue2');

INSERT INTO post(title, img_url, thumbnail, tags) VALUES ('First Post','https://www.gannett-cdn.com/presto/2019/03/11/PDTN/0c313583-c11d-4ea4-8d93-dbbebaf9de06-Josh_Classroom_Tech_Elevator.png?crop=932,516,x29,y0&width=3200&height=1680&fit=bounds','thumbnail/url/hereitis','tag1,tag2,tag3');
INSERT INTO post(title, img_url, thumbnail, tags) VALUES ('Second Post','https://course_report_production.s3.amazonaws.com/rich/rich_files/rich_files/4763/original/jamie-justin-brian-tech-elevator-veterans.jpg','thumbnail2/url/hereitis','tag1,tag2,tag3');

INSERT INTO post(title, img_url, thumbnail, tags) VALUES ('Third Post','https://course_report_production.s3.amazonaws.com/rich/rich_files/rich_files/4763/original/jamie-justin-brian-tech-elevator-veterans.jpg','thumbnail3/url/hereitis','tag1,tag2,tag3');

INSERT INTO comments(user_name, comment) VALUES ('max','This is a great post');
INSERT INTO comments(user_name, comment) VALUES ('max','This is not a great post');

INSERT INTO post_comments_reference(post_id, comment_id) VALUES (1,1);
INSERT INTO post_comments_reference(post_id, comment_id) VALUES (1,2);

INSERT INTO post_user_reference(post_id, id) VALUES (1,1);
INSERT INTO post_user_reference(post_id, id) VALUES (2,1);
INSERT INTO post_user_reference(post_id, id) VALUES (3,2);

COMMIT;