

-- user
create table hs_user
(
    `user_id`   int             not null    auto_increment  COMMENT '編號',
    `username`  varchar(30)     not null                    COMMENT '帳號',
    `password`  varchar(30)     not null                    COMMENT '密碼',
    `name`      varchar(30)     NULL    DEFAULT null        COMMENT '姓名',
    `mail`      varchar(30)     NULL    DEFAULT null        COMMENT '郵件',
    `phone`     int             NULL    DEFAULT null        COMMENT '電話',
    `token`     VARCHAR(50)     NULL    DEFAULT NULL ,
    `add_time`  datetime        NULL    DEFAULT NULL        COMMENT '註冊時間',
    `last_time` datetime        NULL    DEFAULT NULL        COMMENT '最後登陸時間',
    `is_admin`  INT             NULL    DEFAULT 0           COMMENT '0不是、1是、2關閉',
    `status`    INT             NULL    DEFAULT 0           COMMENT '0未驗證、1驗證、9登陸失敗次數高於X次封號'
    PRIMARY KEY (`user_id`) USING BTREE
)DEFAULT CHARSET=utf8mb4 COMMENT = '使用者';

CREATE TABLE user_login_log(
    `id`            INTEGER         NOT NULL AUTO_INCREMENT,
    `user_id`       INTEGER         NULL    DEFAULT NULL    COMMENT '使用者id',
    `status`        INTEGER         NULL    DEFAULT NULL    COMMENT '0登陸成功、9登陸失敗',
    `frequency`     INTEGER         NULL    DEFAULT NULL    COMMENT '失敗次數',
    `remark`        VARCHAR(1024)   NULL    DEFAULT NULL    COMMENT '備註',
    `login_time`    datetime        NULL    DEFAULT NULL    COMMEMT '登陸時間',
    PRIMARY KEY (`id`) USING BTREE
)DEFAULT CHARSET=utf8mb4 COMMENT = '使用者登陸狀況';


CREATE TABLE admin_product
(
    `id`            INT             not null  AUTO_INCREMENT,
    `admin_id`      INT             not null                                            COMMENT 'admin_id',
    `product_name`  VARCHAR (20)    NULL    DEFAULT NULL                                COMMENT '商品名',
    `token`         INT             NULL    DEFAULT NULL                                COMMENT '商品ID',
    `type`          VARCHAR (30)    NULL    DEFAULT NULL                                COMMENT '類型',
    `amount`        INT             NULL    DEFAULT 0                                   COMMENT '價格',
    `quantity`      INT             NULL    DEFAULT 0                                   COMMENT '數量',
    `describe`      VARCHAR(1024)   NULL    DEFAULT NULL                                COMMENT '描述',
    `add_time`      datetime(0)     NULL    DEFAULT NULL                                COMMENT '新增時間',
    `status`        INT             NULL    DEFAULT 0                                   COMMENT '0.下架、1.上架、2.庫存=0',
    `hide`          INT             NULL    DEFAULT 1                                   COMMENT '0.隱藏、1.顯示',
    `remark`        varchar(1024)   NULL    DEFAULT NULL                                COMMENT '备注',
    `update_time`   datetime(0)     NULL    DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间/回调时间',
    PRIMARY KEY (`id`) USING BTREE
)DEFAULT CHARSET=utf8mb4 COMMENT = '商品';



