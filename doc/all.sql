#用户表
drop table if exists `user`;
create table `user`
(
    `id` bigint not null comment 'id',
    `username` varchar(100) not null comment '用户名',
    `name` varchar(100) not null comment '昵称',
    `password` varchar(100) not null comment '密码',
    `avatar` varchar(100) not null default 'default.png',
        primary key (`id`),
    unique key `username_unique` (`username`)
) engine = innodb
  default charset = utf8mb4
    comment ='用户表';
insert into `user` (user.id, user.username, user.name, user.password, user.avatar)
values (1, '114', 'carr', '87878','1.png');

#用户信息表
create table user_info
(
    id bigint not null,
    self_intro varchar(2000) null,
    age int null,
    gender varchar(20) null,
    circles mediumtext null,
    managed_circles mediumtext null,
    tags mediumtext null,
    status bool null,
    constraint user_info_pk
        primary key (id)
) engine = innodb
  default charset = utf8mb4
    comment '用户信息表';

#兴趣圈表
create table circle
(
    id bigint not null,
    circle_name varchar(100) not null comment '兴趣圈名字',
    intro mediumtext null comment '介绍',
    manager_id bigint not null comment '管理者id',
    created_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    tags mediumtext null comment '兴趣圈标签',
    constraint circle_pk
        primary key (id),
    constraint circle_user_id_fk
        foreign key (manager_id) references user (id)
) engine = innodb
  default charset = utf8mb4
    comment '兴趣圈表';

# 用户-兴趣圈表
drop table if exists `user_circle` ;
create table user_circle
(
    id bigint not null comment 'id',
    circle_id bigint null,
    user_id bigint null,
    manager_id bigint null,
    constraint user_circle_pk
        primary key (id),
    constraint user_circle_circle_id_fk
        foreign key (circle_id) references circle (id),
    constraint user_circle_user_id_fk
        foreign key (user_id) references user (id)
) engine = innodb
  default charset = utf8mb4
    comment '用户-兴趣圈表';

# 关注表
create table follow
(
    id bigint not null,
    like_id bigint not null,
    beliked_id bigint not null,
    `follow _time` datetime default CURRENT_TIMESTAMP not null,
    constraint follow_pk
        primary key (id),
    constraint follow_user_id_fk
        foreign key (like_id) references user (id),
    constraint follow_user_id_fk_2
        foreign key (beliked_id) references user (id)
)
    comment '关注表';


#帖子表
drop table if exists `blog`;
create table blog
(
    id bigint not null comment 'id',
    author_id bigint not null comment '作者id',
    publish_time datetime default CURRENT_TIMESTAMP not null,
    content mediumtext not null,
    vote_num int default 0 null,
    constraint blog_pk
        primary key (id),
    constraint blog_user_fk
        foreign key (author_id) references user (id)
)engine = innodb
 default charset = utf8mb4
    comment '帖子表';

alter table blog add circle_id bigint null;

alter table blog
    add oppose_num int default 0 null;
alter table blog
    add comment_num int default 0 null;

insert into `blog` (id, author_id, content)
values (1, 1, 'carr');
insert into `blog` (id, author_id, content)
values (2, 1, '这是我发布的第一篇blog!');


# 更新评论数量
update `blog`,(select COUNT(1) num, blog_id from `comment` t1, `blog` t2 where t1.blog_id = t2.id group by blog_id) t3
set comment_num = t3.num
where `blog`.id = t3.blog_id;


# 评论表
create table comment
(
    id bigint not null comment '评论id',
    blog_id bigint not null comment 'blog id',
    commentator_id bigint not null comment '评论人id',
    content varchar(1000) not null comment '评论内容',
    comment_time datetime default CURRENT_TIMESTAMP not null comment '评论时间',
    constraint comment_pk
        primary key (id),
    constraint comment_blog_id_fk
        foreign key (blog_id) references blog (id),
    constraint comment_user_id_fk
        foreign key (commentator_id) references user (id)
)engine = innodb
 default charset = utf8mb4
    comment '评论表';


# 点赞/点踩表
drop table if exists `likes`;
create table `likes`
(
    id bigint not null comment 'id',
    commentator_id bigint not null comment '点赞人id',
    blog_id bigint not null comment '被赞贴id',
    is_like boolean null comment '是点赞（还是点踩）',
    comment_time datetime default CURRENT_TIMESTAMP null comment '时间',
    constraint like_pk
        primary key (id),
    constraint like_blog_id_fk
        foreign key (blog_id) references blog (id),
    constraint like_user_id_fk
        foreign key (commentator_id) references user (id)
)
    comment '点赞/反对表';

# 消息表