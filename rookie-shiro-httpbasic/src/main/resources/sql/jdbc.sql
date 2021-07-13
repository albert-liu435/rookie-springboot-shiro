drop table if EXISTS users;

create table users (
                       id int auto_increment,
                       username varchar(20),
                       password varchar(20),
                       password_salt varchar(20),
                       constraint pk_users primary key(id)
) charset=utf8 ENGINE=InnoDB;

insert into users(username,password)values('zhangsan','123');

drop table if EXISTS user_roles;
create table user_roles(
                           id bigint auto_increment,
                           username varchar(100),
                           role_name varchar(100),
                           constraint pk_user_roles primary key(id)
) charset=utf8 ENGINE=InnoDB;

INSERT INTO user_roles VALUES(1,'zhangsan','admin');


drop table if EXISTS roles_permissions;

create table roles_permissions(
                                  id bigint auto_increment,
                                  role_name varchar(100),
                                  permission varchar(100),
                                  constraint pk_roles_permissions primary key(id)
) charset=utf8 ENGINE=InnoDB;


INSERT INTO roles_permissions VALUES(1,'admin','update');
