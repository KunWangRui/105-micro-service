create table blog(id int PRIMARY KEY NOT NULL auto_increment, title VARCHAR(20) NOT NULL , content VARCHAR(100) NOT NULL , author VARCHAR(20) NOT NULL);
INSERT INTO blog (title,author,content) VALUES ('java','this is java','zhneyu');
INSERT INTO blog (title,author,content) VALUES ('python','this is python','wangkun');
INSERT INTO blog (title,author,content) VALUES ('golang','this is go','zzy');
INSERT INTO blog (title,author,content) VALUES ('ang','this is go','zzy');
INSERT INTO blog (title,author,content) VALUES ('cpp','this is cpp','kunkun');
INSERT INTO blog (title,author,content) VALUES ('python','this is python','wangkun');

CREATE TABLE user(id int(10) PRIMARY KEY NOT NULL auto_increment, loginId VARCHAR(10) NOT NULL, name VARCHAR(10) NOT NULL, passwd VARCHAR(20) NOT NULL);
INSERT INTO user (loginId,name,passwd) VALUES('wk@git.com','wk','123');
INSERT INTO user (loginId,name,passwd) VALUES('admin','admin','admin');