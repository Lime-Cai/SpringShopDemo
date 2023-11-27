-- user

create table hs_user
(
    `id`        int         not null auto_increment COMMENT '編號',
    `token`     VARCHAR(50) NULL DEFAULT NULL,
    `username`  varchar(30) not null COMMENT '帳號',
    `password`  varchar(30) not null COMMENT '密碼',
    `name`      varchar(30) NULL DEFAULT null COMMENT '姓名',
    `mail`      varchar(30) NULL DEFAULT null COMMENT '郵件',
    `phone`     int NULL DEFAULT null COMMENT '電話',
    `is_store`  INT NULL DEFAULT 0 COMMENT '0不是、1是、2關閉',
    `status`    INT NULL DEFAULT 0 COMMENT '0未驗證、1驗證',
    `is_lock`   bollean NULL DEFAULT 0 COMMENT '0正常、1被封锁',
    `add_time`  datetime NULL DEFAULT NULL COMMENT '註冊時間',
    `last_login_time` datetime NULL DEFAULT NULL COMMENT '最後登陸時間',
    PRIMARY KEY (`id`) USING BTREE,
    PRIMARY KEY (`username`) USING BTREE,
)DEFAULT CHARSET=utf8mb4 COMMENT = '使用者';

CREATE TABLE hs_user_login_log(
    `id`            INTEGER         NOT NULL AUTO_INCREMENT,
    `user_id`       INTEGER         NULL    DEFAULT NULL    COMMENT '使用者id',
    `status`        INTEGER         NULL    DEFAULT NULL    COMMENT '0登陸成功、9登陸失敗',
    `frequency`     INTEGER         NULL    DEFAULT NULL    COMMENT '失敗次數',
    `remark`        VARCHAR(1024)   NULL    DEFAULT NULL    COMMENT '備註',
    `login_time`    datetime        NULL    DEFAULT NULL    COMMENT '登陸時間',
    PRIMARY KEY (`id`) USING BTREE
)DEFAULT CHARSET=utf8mb4 COMMENT = '使用者登陸狀況';

CREATE TABLE store_admin(
    `id`            INT             not null  AUTO_INCREMENT,
    `admin_id`      INT             not null                                            COMMENT 'admin_id',
    `mail`          varchar(50)     NULL    DEFAULT NULL                                COMMENT '郵件',
    `mail_review`   INT             NULL    DEFAULT NULL                                COMMENT '郵件審查',
    `status`        INT             NULL    DEFAULT 0                                   COMMENT '0.還未認證、1.已通過郵件認證、2.審核中、3.通過認證、4.認證失敗、5.封鎖',
    `creat_time`    datetime(0)     NULL    DEFAULT NULL                                COMMENT '新增時間',
    `remark`        varchar(2048)   NULL    DEFAULT NULL                                COMMENT '备注',
    `update_time`   datetime(0)     NULL    DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间/回调时间',
    PRIMARY KEY (`id`) USING BTREE
)DEFAULT CHARSET=utf8mb4 COMMENT = '賣家認證';


CREATE TABLE store_product
(
    `id`           INT not null AUTO_INCREMENT,
    `admin_id`     INT not null COMMENT 'admin_id',
    `product_id`   VARCHAR(1024) NULL DEFAULT NULL COMMENT '商品ID',
    `product_name` VARCHAR(1024) NULL DEFAULT NULL COMMENT '商品名',
    `type_`        VARCHAR(30) NULL DEFAULT NULL COMMENT '類型',
    `amount`       INT NULL DEFAULT 0 COMMENT '價格',
    `quantity`     INT NULL DEFAULT 0 COMMENT '數量',
    `describe_`    VARCHAR(1024) NULL DEFAULT NULL COMMENT '描述',
    `creat_time`   datetime(0) NULL DEFAULT NULL COMMENT '新增時間',
    `status`       INT NULL DEFAULT 0 COMMENT '0.下架、1.上架、2.庫存=0、9.删除',
    `hide`         INT NULL DEFAULT 0 COMMENT '0.隱藏、1.顯示',
    `remark`       varchar(1024) NULL DEFAULT NULL COMMENT '备注',
    `update_time`  datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '更新时间/回调时间',
    PRIMARY KEY (`id`) USING BTREE
)DEFAULT CHARSET=utf8mb4 COMMENT = '商品';



