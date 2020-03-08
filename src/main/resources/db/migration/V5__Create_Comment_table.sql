create table comment
(
	id bigint auto_increment,
	p_id bigint not null,
	type int not null,
	commentator int,
	gmt_create bigint not null,
	gmt_modified bigint not null,
	content varchar,
	like_count bigint default 0,
	constraint comment_pk
		primary key (id)
);