DROP TABLE IF EXISTS chessboard_library;
CREATE TABLE chessboard_library
(
    `id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `eigenvalue` INT NOT NULL DEFAULT 0 COMMENT '特征值',
    `create_time` datetime NOT NULL DEFAULT now() COMMENT '创建时间',
    `last_update_time` datetime NOT NULL DEFAULT now() ON UPDATE now() COMMENT '最后更新时间'
);

DROP TABLE IF EXISTS form_header;
CREATE TABLE game_chessboard_library
(
    `id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `eigenvalue` INT NOT NULL DEFAULT 0 COMMENT '特征值',
    `level` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '难度水平',
    `create_time` datetime NOT NULL DEFAULT now() COMMENT '创建时间',
    `last_update_time` datetime NOT NULL DEFAULT now() ON UPDATE now() COMMENT '最后更新时间'
);
