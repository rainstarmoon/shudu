DROP TABLE IF EXISTS form_header;
CREATE TABLE form_header
(
    `id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `sort_no` INT NOT NULL DEFAULT 0 COMMENT '排序序号',
    `desc` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '描述',
    `create_time` datetime NOT NULL DEFAULT now() COMMENT '创建时间',
    `last_update_time` datetime NOT NULL DEFAULT now() ON UPDATE now() COMMENT '最后更新时间'
);


DROP TABLE IF EXISTS form_body;
CREATE TABLE form_body
(
    `id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `clock_date` INT NOT NULL DEFAULT 0 COMMENT '插入日期',
    `form_header_id` BIGINT NOT NULL DEFAULT 0 COMMENT '表单头id',
    `clock` BOOLEAN NOT NULL DEFAULT false COMMENT '是否打卡',
    `create_time` datetime NOT NULL DEFAULT now() COMMENT '创建时间',
    `last_update_time` datetime NOT NULL DEFAULT now() ON UPDATE now() COMMENT '最后更新时间'
);