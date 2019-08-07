SELECT * FROM post ORDER BY post_id DESC;

SELECT * FROM post WHERE post_id = 2;

SELECT * FROM post, post_user_reference WHERE post.post_id = post_user_reference.post_id AND post_user_reference.id = 1;

SELECT NEXTVAL('post_post_id_seq');