select `id`, `name`, `password` from `test`;

drop table if exists `test`;
create table `test`
(
    `id`       bigint not null comment 'id',
    `name`     varchar(50) comment '名称',
    `password` varchar(50) comment '密码',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4
    comment ='测试';
insert into `test` (id, name, password)
VALUES (1, '测试', '123456');

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

alter table blog
    add oppose_num int default 0 null;

insert into `blog` (id, author_id, content)
values (1, 1, 'carr');
insert into `blog` (id, author_id, content)
values (2, 1, '这是我发布的第一篇blog!');

insert into `blog` (id, author_id, content)
values (3, 1, '这是我发布的第一篇blog2!');
insert into `blog` (id, author_id, content)
values (4, 1, '这是我发布的第一篇blog3!');
insert into `blog` (id, author_id, content)
values (5, 1, '这是我发布的第一篇blog4!');
insert into `blog` (id, author_id, content)
values (6, 1, '这是我发布的第一篇blog5!');
insert into `blog` (id, author_id, content)
values (7, 1, '这是我发布的第一篇blog6!');

insert into `user` values ();

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

# 兴趣圈表




#电子书表
drop table if exists `ebook`;
create table `ebook`
(
    `id`           bigint not null comment 'id',
    `name`         varchar(50) comment '名称',
    `category1_id` bigint comment '分类1',
    `category2_id` bigint comment '分类2',
    `description`  varchar(200) comment '描述',
    `cover`        varchar(200) comment '封面',
    `doc_count`    int comment '文档数',
    `view_count`   int comment '阅读数',
    `vote_count`   int comment '点赞数',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='电子书';

insert into `ebook` (id, name, description)
values (1, 'Spring Boot入门教程', '零基础入门Java开发,企业级应用开发最佳首选框架');
insert into `ebook` (id, name, description)
values (2, 'Vue入门教程', '零基础入门Vue开发，企业级应用开发最佳首选框架');
insert into `ebook` (id, name, description)
values (3, 'Python入门教程', '零基础入门Python开发，企业级应用开发最佳首选框架');
insert into `ebook` (id, name, description)
values (4, 'Mysq1入门教程', '零基础入门Mysql开发，企业级应用开发2最佳首选框架');
insert into `ebook` (id, name, description)
values (5, 'Oracle入门教程', '零基础入门Oracle开发，企业级应用开发最佳首选框架');

# 分类
drop table if exists `category`;
create table `category`
(
    `id`     bigint      not null comment 'id',
    `parent` bigint      not null default 0 comment '父id',
    `name`   varchar(50) not null comment '名称',
    `sort`   int comment '顺序',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='分类';

insert into `category` (id, parent, name, sort)
values (100, 000, '前端开发', 100);
insert into `category` (id, parent, name, sort)
values (101, 100, 'Vue', 101);
insert into `category` (id, parent, name, sort)
values (102, 100, 'HTML & CSS', 102);
insert into `category` (id, parent, name, sort)
values (200, 000, 'Java', 200);
insert into `category` (id, parent, name, sort)
values (201, 200, '基础应用', 201);
insert into `category` (id, parent, name, sort)
values (202, 200, '框架应用', 202);
insert into `category` (id, parent, name, sort)
values (300, 000, 'Python', 300);
insert into `category` (id, parent, name, sort)
values (301, 300, '基础应用', 301);
insert into `category` (id, parent, name, sort)
values (302, 300, '进阶方向应用', 302);
insert into `category` (id, parent, name, sort)
values (400, 000, '数据库', 400);
insert into `category` (id, parent, name, sort)
values (401, 400, 'MySQL', 401);
insert into `category` (id, parent, name, sort)
values (500, 000, '其它', 500);
insert into `category` (id, parent, name, sort)
values (501, 500, '服务器', 501);
insert into `category` (id, parent, name, sort)
values (502, 500, '开发工具', 502);
insert into `category` (id, parent, name, sort)
values (503, 500, '热门服务端语言', 503);

-- 文档表
drop table if exists `doc`;
create table `doc`
(
    `id`         bigint      not null comment 'id',
    `ebook_id`   bigint      not null default 0 comment '电子书id',
    `parent`     bigint      not null default 0 comment '父id',
    `name`       varchar(50) not null comment '名称',
    `sort`       int comment '顺序',
    `view_count` int                  default 0 comment '阅读数',
    `vote_count` int                  default 0 comment '点赞数',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='文档';

insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count)
values (1, 1, 0, '文档1', 1, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count)
values (2, 1, 1, '文档1.1', 1, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count)
values (3, 1, 0, '文档2', 2, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count)
values (4, 1, 3, '文档2.1', 1, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count)
values (5, 1, 3, '文档2.2', 2, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count)
values (6, 1, 5, '文档2.2.1', 1, 0, 0);

-- 文档内容，是一种纵向的分表
drop table if exists `content`;
create table `content`
(
    `id`      bigint     not null comment '文档id',
    `content` mediumtext not null comment '内容',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='文档内容';

-- 用户表
drop table if exists `user`;
create table `user`
(
    `id`         bigint      not null comment 'ID',
    `login_name` varchar(50) not null comment '登陆名',
    `name`       varchar(50) comment '昵称',
    `password`   char(32)    not null comment '密码',
    primary key (`id`),
    unique key `login_name_unique` (`login_name`)
) engine = innodb
  default charset = utf8mb4 comment ='用户';

insert into `user` (id, `login_name`, `name`, `password`)
values (1, 'test', '测试', 'e70e2222a9d67c4f2eae107533359aa4');
insert into `user` (id, `login_name`, `name`, `password`)
values (12, 'admin', 'admin', 'e70e2222a9d67c4f2eae107533359aa4');

-- 电子书快照表
drop table if exists `ebook_snapshot`;
create table `ebook_snapshot`
(
    `id`            bigint auto_increment not null comment 'id',
    `ebook_id`      bigint                not null default 0 comment '电子书id',
    `date`          date                  not null comment '快照日期',
    `view_count`    int                   not null default 0 comment '阅读数',
    `vote_count`    int                   not null default 0 comment '点赞数',
    `view_increase` int                   not null default 0 comment '阅读增长',
    `vote_increase` int                   not null default 0 comment '点赞增长',
    primary key (`id`),
    unique key `ebook_id_date_unique` (`ebook_id`, `date`)
) engine = innodb
  default charset = utf8mb4 comment ='电子书快照表';

# 方案一（ID不连续）：
# 删除今天的数据
# 为所有的电子书生成一条今天的记录
# 更新总阅读数、总点赞数
# 更新今日阅读数、今日点赞数
#
# 方案二（ID连续）：
# 为所有的电子书生成一条今天的记录，如果还没有
# 更新总阅读数、总点赞数
# 更新今日阅读数、今日点赞数
insert into ebook_snapshot(ebook_id, `date`, view_count, vote_count, view_increase, vote_increase)
select t1.id, curdate(), 0, 0, 0, 0
from ebook t1
where not exists(select 1
                 from ebook_snapshot t2
                 where t1.id = t2.ebook_id
                   and t2.date = curdate());

update ebook_snapshot t1, ebook t2
set t1.view_count = t2.view_count,
    t1.vote_count = t2.vote_count
where t1.`date` = curdate()
  and t1.ebook_id = t2.id;

# 获取昨天的数据
select t1.ebook_id, view_count, vote_count
from ebook_snapshot t1
where t1.`date` = date_sub(curdate(), interval 1 day);

# 用left join 更新数据，不管昨天的有没有，如果昨天有数据，那就更新，如果没有，那就值为0
update ebook_snapshot t1 left join (select ebook_id, view_count, vote_count
                                    from ebook_snapshot
                                    where `date` = date_sub(curdate(), interval 1 day)) t2
    on t1.ebook_id = t2.ebook_id
set t1.view_increase = (t1.view_count - ifnull(t2.view_count, 0)),
    t1.vote_increase = (t1.vote_count - ifnull(t2.vote_count, 0))
where t1.`date` = curdate();