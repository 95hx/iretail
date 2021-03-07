create schema stack collate utf8mb4_unicode_ci;

create table operation
(
	id int auto_increment
		primary key,
	operation_code varchar(255) not null,
	operation_desc varchar(255) not null,
	constraint operation_operation_code_uindex
		unique (operation_code)
);

create table org
(
	id int auto_increment
		primary key,
	org_name varchar(255) not null,
	level int not null,
	parent_id int not null
);

create table role
(
	id int auto_increment
		primary key,
	role_name varchar(255) not null,
	constraint role_role_name_uindex
		unique (role_name)
);

create table role_operation
(
	id int auto_increment
		primary key,
	operation_id int not null,
	role_id int not null,
	constraint role_operation_operation_id_role_id_uindex
		unique (operation_id, role_id)
);

create table store
(
	id int auto_increment
		primary key,
	store_name varchar(255) not null,
	org_id int not null
);

create table user
(
	id int auto_increment
		primary key,
	username varchar(255) not null,
	password varchar(255) not null,
	nickname varchar(255) not null,
	constraint user_username_uindex
		unique (username)
);

create table user_org
(
	id int auto_increment
		primary key,
	org_id int not null,
	user_id int not null,
	constraint user_org_org_id_user_id_uindex
		unique (org_id, user_id)
);

create table user_role
(
	id int auto_increment
		primary key,
	role_id int not null,
	user_id int not null,
	constraint user_role_role_id_user_id_uindex
		unique (role_id, user_id)
);

