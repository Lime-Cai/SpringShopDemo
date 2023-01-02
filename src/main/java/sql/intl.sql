

-- user
create table hs_user
(
    `user_id`       int         not null auto_increment COMMENT '編號',
    `username` varchar(30) not null COMMENT '帳號',
    `password` varchar(30) not null COMMENT '密碼',
    `name`     varchar(30) DEFAULT null COMMENT '姓名',
    `mail`     varchar(30) DEFAULT null COMMENT '郵件',
    `phone`    int         DEFAULT null COMMENT '電話',
    PRIMARY KEY (`user_id`) USING BTREE
)DEFAULT CHARSET=utf8mb4;





CREATE TABLE `hs_order` (
                            `id` int NOT NULL AUTO_INCREMENT COMMENT '订单ID',
                            `product_id` int NOT NULL COMMENT '产品流水号',
                            `order_no` varchar(218) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单编号',
                            `status` smallint NOT NULL COMMENT '订单状态 （0未提交，1已提交未审核，2初审通过待复审，3复审中，4黑名单，5复审通过待终审，6终审中，7被拒，8终审通过放款中，9放款成功待还款，10已结清，11放款失败，12已逾期，13已寄手机）',
                            `user_id` int NOT NULL COMMENT '用户ID',
                            `user_true_name` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名称',
                            `user_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                            `user_idcard` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户身份证号',
                            `is_olduser` tinyint DEFAULT NULL COMMENT '是否老用户',
                            `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '下单ip',
                            `device_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设备名称',
                            `device_model` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '产品类型 ex： Iphone 6',
                            `device_money` decimal(10,2) DEFAULT '0.00' COMMENT '设备预估金额',
                            `device_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设备唯一记录',
                            `app_name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'app平台名称',
                            `lend_money` decimal(10,2) DEFAULT '0.00' COMMENT '手机预付款',
                            `lend_days` smallint DEFAULT NULL COMMENT '借款天数 履约天数',
                            `service_money` decimal(10,2) DEFAULT '0.00' COMMENT '服务费',
                            `channel_id` int DEFAULT NULL COMMENT '渠道 id',
                            `channel_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '渠道部门',
                            `examiner1_id` int DEFAULT NULL COMMENT '身份证审核员',
                            `examiner2_id` int DEFAULT NULL COMMENT '复审审核员',
                            `examiner3_id` int DEFAULT NULL COMMENT '终审审核员',
                            `loan_time` int DEFAULT '1' COMMENT '放款时间',
                            `apply_time` int DEFAULT NULL COMMENT '申请时间',
                            `expire_time` int DEFAULT NULL COMMENT '履约到期时间',
                            `refund_reason` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '审核不通过原因',
                            `add_time` int DEFAULT NULL COMMENT '订单生成时间',
                            `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
                            `enable` int DEFAULT NULL COMMENT '是否启用',
                            `device_memory` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设备型号',
                            `black_box` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
                            `system_version` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                            `leng_num` int DEFAULT '0' COMMENT '展期次数',
                            `is_leng` tinyint DEFAULT '0' COMMENT '是否展期',
                            `is_passive_leng` tinyint DEFAULT '0' COMMENT '是否被展期',
                            `leng_order_id` int DEFAULT NULL COMMENT '展期原始订单ID',
                            `leng_order_no` varchar(218) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '展期原始订单号',
                            `leng_amount` decimal(10,2) DEFAULT NULL COMMENT '展期费用',
                            `leng_period` int DEFAULT '0' COMMENT '展期期数',
                            `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间/回调时间',
                            `operator_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '申请人',
                            `imei` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机IMEI号',
                            `finish_time` int DEFAULT NULL COMMENT '结清时间',
                            `daily_rate` decimal(4,3) DEFAULT NULL COMMENT '日利息',
                            `bank_card_no` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '银行卡号',
                            `rc_order_no` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '风控订单号',
                            `pre_interest_fee` decimal(10,2) DEFAULT NULL COMMENT '前段处理费',
                            `post_interest_fee` decimal(10,2) DEFAULT NULL COMMENT '后段处理费',
                            `dummy` tinyint DEFAULT '0',
                            PRIMARY KEY (`id`) USING BTREE,
                            KEY `trip_no_index` (`order_no`) USING BTREE,
                            KEY `user_type_index` (`loan_time`) USING BTREE,
                            KEY `open_id_index` (`user_id`) USING BTREE,
                            KEY `order_examiner2Id_idx` (`examiner2_id`) USING BTREE,
                            KEY `order_examiner3Id_idx` (`examiner3_id`) USING BTREE,
                            KEY `order_addtime_idx` (`add_time`) USING BTREE,
                            KEY `leng_order_id_idx` (`leng_order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12988 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC



