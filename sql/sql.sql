
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`user_name` varchar(256) NOT NULL COMMENT '用户名称',
`user_password` varchar(256) NOT NULL COMMENT '用户密码',
`user_desc` varchar(256)  NOT NULL COMMENT '用户简介',
`user_type` tinyint(1)  NOT NULL COMMENT '用户类型：0 社区用户，1 回收员，2 管理员',
`user_area` int(11)  NOT NULL COMMENT '用户地址',
`full_address` varchar(256) DEFAULT NOT NULL COMMENT '详细地址',
`phone` int(11)  NOT NULL COMMENT '电话',
`status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户状态0：正常，1：禁用',
`gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '新增时间',
`gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
`is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0正常 1逻辑删除',
PRIMARY KEY (`id`),
UNIQUE KEY `idx_tenant_name` (`user_name`(128))
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 comment="用户表";

DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(256) NOT NULL COMMENT '地址名',
`level` tinyint(1) DEFAULT NULL COMMENT '地址等级',
`parent_id` int(11) DEFAULT NULL COMMENT '上级地址ID',
`gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '新增时间',
`gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '修改时间',
`is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0正常 1逻辑删除',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='地址表';

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (

`id` int(11) NOT NULL AUTO_INCREMENT,
`role_name` varchar(256) NOT NULL COMMENT '角色名称',
`role_type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '角色类型：1：功能权限',
`role_value` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'TEANTOWNER(1), ADMIN(2), MEMBER(3)',
`role_desc` varchar(256) NOT NULL DEFAULT '' COMMENT '角色描述',
`gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP   COMMENT '新增时间',
`gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP   COMMENT '修改时间',
`is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0正常 1逻辑删除',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='角色表';

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`user_id`  int(11) NOT NULL COMMENT '用户id',
`order_desc` varchar(256) NOT NULL COMMENT '订单信息',
`money` double  NOT NULL COMMENT '订单金额',
`receiverinfo`  varchar(256) NOT NULL DEFAULT '1' COMMENT '接收信息',
`pay_state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '支付状态',
`gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '新增时间',
`gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '修改时间',
`is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0正常 1逻辑删除',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='订单表';

DROP TABLE IF EXISTS `order_grade`;
CREATE TABLE `order_grade` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`order_id`  int(11) NOT NULL COMMENT '订单id',
`order_grade` tinyint(1) NOT NULL COMMENT '订单评分  1-5',
`user_grade` tinyint(1) NOT NULL COMMENT '对回收员的评分  1-5',
`user_review` varchar(256) COMMENT '用户评价 1000111001110000，  详见userReview类 ',
`review_text` varchar(256) COMMENT '用户手打的评价',
`gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '新增时间',
`gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '修改时间',
`is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0正常 1逻辑删除',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='订单评价表';

DROP TABLE IF EXISTS ` sort`;
CREATE TABLE `sort` (

`id` int(11) NOT NULL AUTO_INCREMENT,
`name`  varchar (256) NOT NULL COMMENT '分类名',
`sort_desc` varchar(256) NOT NULL COMMENT '详细信息',
`price`   double COMMENT '单价',
`level`  tinyint(1) NOT NULL  COMMENT '分类级别， ',
`type`  tinyint(1) NOT NULL  COMMENT '资源类型  0 可再生 1不可再生资源 ',
`parent_id`  int(11)  NOT NULL  COMMENT '父id',
`gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '新增时间',
`gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '修改时间',
`is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0正常 1逻辑删除',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='资源分类表';

DROP TABLE IF EXISTS ` article`;
CREATE TABLE `article` (
`id` int(11) NOT NULL AUTO_INCREMENT,
‘name’ varchar (256) NOT NULL COMMENT '资源名',
`sort_desc` varchar(256) NOT NULL COMMENT '详细信息',
`sort_id`  int(11) NOT NULL  COMMENT '类别id ',
`user_id`  int(11) NOT NULL  COMMENT '用户id ',
`gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '新增时间',
`gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '修改时间',
`is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0正常 1逻辑删除',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='资源表';