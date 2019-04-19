create table job
(
  record_id                        bigint      auto_increment      not null,
  job_name                         varchar(50)                     not null,    --  职务名称
  job_level                        tinyint                         not null,    --  职位等级 0 院长 1 店长 2 前台 3 顾问 4 美容师

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,

  primary key (record_id),
  index idx_job_01(job_name)
)comment '职务';

create  table city
(
  record_id                        bigint    auto_increment        not null,
  city_code                        varchar(12)                     not null,     -- 编码 (省/市)  500235
  city_name                        varchar(50)                     not null,     -- 名称    东城区
  parent_id                        varchar(12)                     not null,     -- 所属上级(上级省份或者上级城市)  9
  name_path                        varchar(500)                    null,         -- 路径:广东省东莞市东城区

  primary key (record_id),
  index idx_city_01(city_code),
  index idx_city_02(city_name),
  index idx_city_03(parent_id)
)comment '市';


create table salon(
  record_id                        bigint     auto_increment       not null,
  salon_name                       varchar(120)                    not null,               -- 美容院/门店名称
  parent_id                        bigint                          not null,               -- 所属美容院:如果是美容院，则 parent_id = -1
  tel                              varchar(50)                     not null,               -- 联系电话
  city_id                          bigint                          not null,               -- 所属城市（省市区）
  address                          varchar(255)                    not null,               -- 地址
  door_2_door                      bit                             null,     -- 是否上门服务:美容院专用
  bed_num                          int                             not null default 0,     -- 床位数：门店专用
  area                             double(8,2)                     not null default 0.00,  -- 面积：门店专用
  time_open                        varchar(50)                            null,                   -- 营业开始时间：门店专用
  time_close                       varchar(50)                            null,                   -- 营业结束时间：门店专用
  longitude                        decimal(10,7)                   not null,               -- 经度
  latitude                         decimal(10,7)                   not null,               -- 纬度
  description                      varchar(500)                    null,                   -- 简介
  audit                            tinyint                         not null default 0,     -- 门店的审核状态 0 审核不通过 1 审核通过

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,

  primary key (record_id),
  index idx_beauty_salon_01(salon_name),
  index idx_beauty_salon_02(tel),
  index idx_beauty_salon_03(city_id)
)comment '美容院/门店';

create table salon_invite_code(
  record_id                      bigint          auto_increment           not null,
  salon_id                       bigint                                   not null, -- 美容院id
  invite_code                    varchar(10)                              not null, -- 美容院的邀请码

  primary key (record_id),
  index idx_salon_invite_code_01(salon_id),
  index idx_salon_invite_code_02(invite_code)
)comment '美容院邀请码';


create table stuff
(
  record_id                        bigint      auto_increment      not null,
  store_id                         bigint                          not null,  -- 所属门店
  stuff_name                       varchar(50)                     not null,  -- 姓名
  tel                              varchar(50)                     not null,  -- 电话
  gender                           tinyint                         not null,  -- 性别：    0.男     1.女 2.未知
  entry_time                       datetime                        null,  -- 入职时间
  work_age                         double(3,1)                     null,  -- 工作年限
  birth_day                        datetime                        null,  -- 生日
  special                          varchar(120)                    null,      -- 特长
  dream                            varchar(120)                    null,      -- 梦想
  weixin                           varchar(50)                     null,      -- 微信
  qq                               varchar(50)                     null,      -- QQ
  address                          varchar(120)                    null,      -- 联系地址
  in_office                        tinyint                         not null default 0; -- 0 在职 1 离职

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,


  primary key (record_id),
  index idx_stuff_01(store_id),
  index idx_stuff_02(stuff_name),
  index idx_stuff_03(tel),
  index idx_stuff_04(gender)
) comment '员工';


create table stuff_job
(
  record_id                        bigint      auto_increment      not null,
  stuff_id                         bigint                          not null,    -- 员工编号
  job_id                           bigint                          not null,    -- 职位编号

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,


  primary key (record_id),
  index idx_stuff_job_01(stuff_id),
  index idx_stuff_job_02(job_id)
)comment '员工职务';


create table store_room
(
  record_id                        bigint        auto_increment    not null,
  store_id                         bigint                          not null,    -- 所属门店
  room_name                        varchar(50)                     not null,    -- 房间名门
  record_status                    tinyint                         not null,    -- 房间状态：0. 启用    1.停用

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,


  primary key (record_id),
  index idx_store_room_01(store_id),
  index idx_store_room_02(room_name),
  index idx_store_room_03(record_status)
)comment '门店房间';

create table service_series
(
  record_id                        bigint          auto_increment    not null,
  store_id                         bigint                            not null,  -- 所属门店
  series_name                      varchar(50)                       not null,   -- 系列/类别名称
  parent_id                        bigint                            not null,   -- 项目类别：如果本身是一级类别，则parent_id=0
  record_status                    tinyint                           not null,   -- 状态： 0. 启用   1. 停用

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,


  primary key (record_id),
  index idx_service_series_01(series_name),
  index idx_service_series_02(parent_id),
  index idx_service_series_03(record_status)
)comment '项目类别/系列';

create table service
(
  record_id                        bigint      auto_increment      not null,
  store_id                         bigint                          not null,    -- 所属门店
  service_name                     varchar(50)                     not null,    -- 项目名称
  service_series_id                bigint                          not null,    -- 项目类别/系列
  card_type                        tinyint                         not null,    -- 卡类别: 0 次卡   时效卡：1.月卡   2.季卡  3.半年卡   4.年卡
  record_status                    tinyint                         not null,    -- 项目状态：0 启用   1 停用
  expired_time                     double(3,1)                     null,        -- 有效期：时效卡专用。自购买之日起，N时间内有效。

  period_per_time                  int                             not null,    -- 单次时长(分):-1表示无限时长
  price_per_time                   double(8,2)                     not null,    -- 单次价格(次卡专用)
  price_market                     double(8,2)                     not null,    -- 市场价格(时效卡专用)
  price                            double(8,2)                     not null,    -- 办卡价格/优惠价格
  return_visit                     int                             null,        -- 回访天数
  time_total                       int                             not null,    -- 包含次数/最多包含次数：-1表示无限次数

  description                      varchar(500)                    null,        -- 简介

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,


  primary key (record_id),
  index idx_service_card_01(service_series_id),
  index idx_service_card_02(card_type),
  index idx_service_card_03(service_name),
  index idx_service_card_04(record_status)
)comment '次卡/服务项目';


create table service_suite
(
  record_id                        bigint     auto_increment         not null,
  store_id                         bigint                            not null,     -- 所属门店
  suite_name                       varchar(50)                       not null,     -- 套卡名称
  price_market                     double(8,2)                       not null,     -- 市场价格:汇总service_suite_item里面各项的单价
  price                            double(8,2)                       not null,     -- 优惠价格/开卡价格

  time_create                      datetime                          not null,     -- 建档时间
  time_expired                     datetime                          null,         -- 失效日期：如果为null，则表示无期限
  record_status                    tinyint                           not null,     -- 套卡状态：0 正常   1.停用     2.失效（即已过了有效期）

  description                      varchar(500)                      null,

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,


  primary key (record_id),
  index idx_service_suite_01(suite_name),
  index idx_service_suite_02(record_status)
)comment '套卡/服务套餐';


create table service_suite_item
(
  record_id                        bigint    auto_increment       not null,
  service_suite_id                 bigint                         not null,   -- 套卡id
  service_id                       bigint                         not null,   -- 服务项目编号
  times                            int                            not null,   -- 次数

  -- price_single  double(8,2)                    not null,   -- 单次价格：直接引用service里面的价格

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,


  primary key (record_id),
  index idx_service_suite_item_01(service_id)
) comment '套卡明细';


create table vip_suite
(
  record_id                        bigint     auto_increment       not null,
  store_id                         bigint                          not null,    -- 所属门店
  suite_name                       varchar(50)                     not null,    -- 充值卡名称
  price                            double(8,2)                     not null,    -- 充值面额
  vip_suite_status                 tinyint                         not null,    -- 记录状态：0.启用   1.停用
  description                      varchar(500)                    not null,    -- 介绍

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,


  primary key (record_id),
  index idx_vip_suite_01(suite_name)
)comment '充值卡';

create table vip_suite_item
(
  record_id                        bigint     auto_increment         not null,
  vip_suite_id                     bigint                            not null,  -- 充值卡id
  record_type                      tinyint                           not null,  -- 记录类型:0.单次折扣  1.疗程折扣  2.产品折扣
  discount                         tinyint                           not null,  -- 折扣

  create_date                      datetime                          not null,
  create_by                        bigint                            not null,
  update_date                      datetime                          null,
  update_by                        bigint                            null,
  opt_lock                         int                               null,

  primary key (record_id),
  index idx_vip_suite_item_01(record_type),
  index idx_vip_suite_item_02(vip_suite_id)
)comment '充值卡折扣项目';

create table vip_suite_item_discount_range
(
    record_id                     bigint       auto_increment        not null,
    vip_suite_item_id             bigint                             not null, -- 充值卡折扣项目id
    service_id                    bigint                             not null, -- 项目的系列id

    create_date                   datetime                           not null,
    create_by                     bigint                             not null,
    update_date                   datetime                           null,
    update_by                     bigint                             null,
    opt_lock                      int                                null,

    primary key (record_id),
    index idx_vip_suite_item_discount_range_01(vip_suite_item_id),
    index idx_vip_suite_item_discount_range_02(service_id)

)comment '充值卡折扣项目适用范围表';

create table product_series
(
  record_id                        bigint      auto_increment      not null,
  store_id                         bigint                          not null,   -- 所属门店
  series_name                      varchar(50)                     not null,   -- 品牌/系列名称
  parent_id                        bigint                          not null,   -- 所属品牌:如果本身为品牌，则parent_id=0
  record_status                    tinyint                         not null,   -- 记录状态:0.启用  1.停用

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,

  primary key (record_id),
  index idx_product_series_01(series_name),
  index idx_product_series_02(parent_id)
)comment '产品品牌/系列';

create table product
(
  record_id                        bigint       auto_increment      not null,
  store_id                         bigint                           not null,   -- 门店
  product_name                     varchar(50)                      not null,   -- 产品名称
  product_class                    tinyint                          not null,   -- 产品类型: 0.客装   1.院装   2.易耗品
  product_series_id                bigint                           not null,   -- 产品品牌/系列
  price_market                     double(8,2)                      not null,   -- 市场价
  price                            double(8,2)                      not null,   -- 优惠价
  product_code                     varchar(20)                      null,       -- 产品编号

  bar_code                         varchar(100)                     null,       -- 二维码/条形码
  shelf_life                       tinyint                          not null,   -- 保质期(月)
  day_of_pre_warning               int                              not null,   -- 产品有效期预警（天）
  stock_of_pre_warning             int                              not null,   -- 库存预警数量

  record_status                    tinyint                          not null,   -- 记录状态：0.启用   1. 停用
  description                      varchar(500)                     null,

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,


  primary key (record_id),
  index idx_product_01(product_name),
  index idx_product_02(product_class),
  index idx_product_03(product_series_id),
  index idx_product_04(bar_code),
  index idx_product_05(shelf_life),
  index idx_product_06(day_of_pre_warning),
  index idx_product_07(stock_of_pre_warning)
) comment '产品';

create table product_property
(
    record_id                    bigint           auto_increment       not null,
    property_name                varchar(50)                           not null, -- 属性名称
    property_type                tinyint                               not null, -- 属性类型 0 规格 1 单位 2 适用部位 3 功效

    create_date                  datetime                              not null,
    create_by                    bigint                                not null,
    update_date                  datetime                              null,
    update_by                    bigint                                null,
    opt_lock                     int                                   null,

    primary key(record_id),
    index idx_product_property_01(property_name),
    index idx_product_property_02(property_type)
)comment '产品基础属性表';

create table product_property_map
(
  record_id                      bigint             auto_increment        not null,
  product_id                     bigint                                   not null,
  product_property_id            bigint                                   not null,

  create_date                    datetime                                 not null,
  create_by                      bigint                                   not null,
  update_date                    datetime                                 null,
  update_by                      bigint                                   null,
  opt_lock                       int                                      null,

  primary key(record_id),
  index idx_product_property_map_01(product_id),
  index idx_product_property_map_02(product_property_id)
)comment '产品属性关系表';

create table tag
(
  record_id                        bigint        auto_increment    not null,
  record_type                      tinyint                         not null, -- 0.功效标签   1.会员标签
  tag_name                         varchar(50)                     not null, -- 标签

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,

  primary key (record_id),
  index idx_tag_01(record_type),
  index idx_tag_02(tag_name)
)comment '标签';

create table pictures
(
  record_id                        bigint    auto_increment        not null ,
  master_data_id                   bigint                          null,     -- 主记录编号
  record_type                      tinyint                         not null,     -- 记录类型: 0.美容院（门店）  1. 员工    2. 项目/卡   3.套卡   4.充值卡  5.产品    6.会员/顾客    7. 护理日志    8.档案  9签名
  pic_type                         tinyint                         not null,     -- 照片类型: 0.普通照片（1张)    1.营业执照（1张）   2.身份证（反面1张）  3.身份证（反面1张）  4.开户许可(1张)
  pic_url                          varchar(255)                    not null,     -- 照片: 如果以http|https开头，则是外部绝对地址，否则为内部相对地址。

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,

  primary key (record_id),
  index idx_pictures_01(master_data_id),
  index idx_pictures_02(record_type),
  index idx_pictures_03(pic_type)
) comment '系统照片';


create table member
(
  record_id                        bigint      auto_increment      not null ,
  initial_store_id                 bigint                          not null,   -- 开卡美容院/门店(档案来源)
  member_name                      varchar(50)                     not null,   -- 姓名
  tel                              varchar(50)                     not null,   -- 电话
  gender                           tinyint                         not null,   -- 性别
  weixin                           varchar(50)                     not null,   -- 微信
  birthday                         datetime                        null,       -- 生日
  zodiac                           tinyint                         null,       -- 星座
  member_grade                     bigint                          not null default 3,   -- 分类 0 A类  1 B类 2 C类 3 D类
  -- member_tag                                                                -- 会员标签

  blood_type                       tinyint                         null,       -- 血型
  height                           int                             null,       -- 身高
  weight                           int                             null,       -- 体重

  last_day_of_menses               datetime                        null,       -- 上次月经时间
  cycle_of_menses                  tinyint                         null,       -- 月经周期
  period_of_menses                 tinyint                         null,       -- 经期长度
  remark_of_menses                 varchar(500)                    null,       -- 月经备注

  profession                       varchar(50)                     null,       -- 职业
  city_id                          bigint                          null,       -- 城市（省市区）
  address                          varchar(255)                    null,       -- 地址
  email                            varchar(255)                    null,       -- 邮箱

  member_code                      char(10)                        null,       -- 会员编码
  introducer                       varchar(50)                     null,       -- 介绍人
  primary_beautician               bigint                          null,       -- 负责美容师
  entry_time                       datetime                        null,       -- 入店时间

  balance_cash                     double(10,2)                    not null,
                      -- 现金余额: 退款的余额(账户余额)
                      -- todo: 这个钱包里的钱，会员如何使用？

  balance_total                    double(10,2)                    not null,   -- 账户总余额（账户余额+充值金额）
  integral                         double(10,2)                    not null,   -- 账户积分
  debt                             double(10,2)                    not null,   -- 账户欠款
  amount_consumer                  double(10,2)                    not null,   -- 总消费
  amount_charge                    double(10,2)                    not null,   -- 总充值

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,

  primary key (record_id),
  index idx_member_01(initial_store_id),
  index idx_member_02(member_name),
  index idx_member_03(birthday),
  index idx_member_04(tel),
  index idx_member_05(primary_beautician),
  index idx_member_06(introducer)
) comment '会员';

create table member_tag
(
  record_id                        bigint     auto_increment       not null,
  member_id                        bigint                          not null,
  tag_id                           bigint                          not null,

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,

  primary key (record_id),
  index idx_member_tag_01(member_id),
  index idx_member_tag_02(tag_id)
)comment '会员标签';

create table member_salon_tag
(
  record_id                        bigint     auto_increment       not null,
  salon_id                         bigint                          not null,
  tag_id                           bigint                          not null,

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,

  primary key (record_id),
  index idx_member_salon_tag_01(salon_id),
  index idx_member_salon_tag_02(tag_id)
)comment '会员标签-按美容院分';

create table shift
(
  record_id                        bigint      auto_increment      not null,
  store_id                         bigint                          not null,   -- 门店
  shift_type                       tinyint                         not null,   -- 班次：0.全  1.早   2. 中   3.晚
  time_start                       varchar(12)                              not null,   -- 开始时间
  time_end                         varchar(12)                              not null,   -- 结束时间

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                               null,

  primary key (record_id),
  index idx_schedule_times_02(store_id)
)comment '班次';

create table holiday
(
  record_id                        bigint       auto_increment     not null,
  store_id                         bigint                          not null,   -- 门店
  day                              date                            not null,   -- 日期

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,


  primary key (record_id),
  index idx_holiday_01(store_id)
) comment '休息日';


create table schedule
(
  record_id                        bigint      auto_increment      not null,
  stuff_id                         bigint                          not null,  -- 员工 id
  shift_id                         bigint                          not null,  -- 班次 id
  day                              date                            not null,  -- 日期

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,


  primary key (record_id),
  index idx_schedule_01(stuff_id),
  index idx_schedule_02(day)
)comment '排班信息表';


create table time_sheet
(
  record_id                         bigint     auto_increment      not null,
  stuff_id                          bigint                         not null,  -- 员工信息
  day                               date                           not null,  -- 日期
  time_start                        datetime                       null,      -- 上班时间
  time_end                          datetime                       null,      -- 下班时间

  time_sheet_type                   tinyint                        not null,  -- 出勤类型：0.正常  1.迟到   2.早退    3. 休假  4. 又迟到又早退  5. 签到缺勤  6. 签退缺勤 ** 没有记录  旷工/缺勤

  create_date                       datetime                        not null,
  create_by                         bigint                          not null,
  update_date                       datetime                        null,
  update_by                         bigint                          null,
  opt_lock                          int                             null,


  primary key (record_id),
  index idx_clocking_in_01(stuff_id),
  index idx_clocking_in_02(day),
  index idx_clocking_in_03(time_sheet_type)
)comment '考勤表';

create table attendance_sheet
(
  record_id                        bigint    auto_increment        not null,
  stuff_id                         bigint                          not null,   -- 员工
  attendance_time                  datetime                        not null,   -- 签到时间
  address                          varchar(255)                    not null,   -- 签到地点

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,


  primary key (record_id),
  index idx_attendance_sheet_01(stuff_id),
  index idx_attendance_sheet_02(address),
  index idx_attendance_sheet_03(attendance_time)
)comment '签到表';

create table retroactive
(
  record_id                        bigint         auto_increment      not null,
  audit_person                     bigint                             not null, -- 申请人 ： 员工id
  date                             datetime                           not null, -- 补卡时间
  reson                            varchar(100)                       not null, -- 补卡原因
  audit_statu                      tinyint                            not null, -- 审核状态  0 通过  1 不通过
  user_id                          bigint                             null, -- 审核人
  audit_opinion                    varchar(100)                       null, -- 审核意见
  salon_id                         bigint                             not null, -- 所属门店id
  retroactive_type                 int                                 not null, -- 补卡类型：1 上班    2 下班


  create_date                      datetime                           not null,
  create_by                        bigint                             not null,
  update_date                      datetime                           null,
  update_by                        bigint                             null,
  opt_lock                         int                                null,

  primary key (record_id),
  index idx_retroactive_01(date),
  index idx_retroactive_02(audit_statu)
)comment '补签记录表';

create table reservation
(
  record_id                        bigint      auto_increment      not null,
  member_id                        bigint                          not null,   -- 会员
  member_sourc                     tinyint                         not null,   -- 会员来源 0 散客 1 会员
  stuff_id                         bigint                          not null,   -- 预约美容师
  room_id                          bigint                          not null,   -- 预约的房间
  date_                            datetime                        not null,   -- 预约日期
  duration                         float                           not null,   -- 时长
  time_start                       datetime                        not null,   -- 开始时间
  time_end                         datetime                        not null,   -- 结束时间
  record_status                    tinyint                         not null,   -- 记录状态：0.未开始  1.已确认    2.服务中    3.已完成
  remark                           varchar(500)                    null,       -- 备注

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,

  primary key (record_id),
  index idx_appointment_01(member_id),
  index idx_appointment_02(stuff_id),
  index idx_appointment_03(room_id)
)comment '预约';


create table reservation_item
(
  record_id                        bigint       auto_increment     not null,
  reservation_id                   bigint                          not null,   -- 预约编号
  service_id                       bigint                          not null,   -- 预约服务

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,

  primary key (record_id),
  index idx_reservation_item_01(reservation_id),
  index idx_reservation_item_02(service_id)
)comment '预约项目';

create table nurse
(
  record_id                        bigint       auto_increment     not null,
  reservation_id                   bigint                          null,       -- 预约编号: 是否可以没有预约而直接开始服务？
  member_id                        bigint                          not null,   -- 会员
  stuff_id                         bigint                          not null,   -- 美容师
  room_id                          bigint                          not null,   -- 护理房间
  time_start                       datetime                        not null,   -- 开始时间
  time_end                         datetime                        null,       -- 结束时间

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,

  primary key (record_id),
  index idx_nurse_01(member_id),
  index idx_nurse_02(stuff_id),
  index idx_nurse_03(room_id),
  index idx_nurse_04(time_start)
)comment '护理';


create table nurse_item
(
  record_id                        bigint       auto_increment     not null,
  nurse_id                         bigint                          not null,   -- 护理编号
  service_id                       bigint                          not null,   -- 预约服务

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,

  primary key (record_id),
  index idx_nurse_item_01(nurse_id),
  index idx_nurse_item_02(service_id)
)comment '护理项目';


create table nurse_log
(
  record_id                        bigint        auto_increment    not null,
  stuff_id                         bigint                          not null,   -- 员工
  member_id                        bigint                          not null,   -- 会员
  log_content                      varchar(1000)                   not null,   -- 内容：里面包含有文字、表情(emoj)
  log_type                         tinyint                         not null,   -- 日志类型 0 回访日志 1 护理日志

  -- parent_id                        bigint                          not null,   -- 前一主题的编号，如果是顶级主题，则parent_id = -1
  -- pic                      照片存储在照片表里面
  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,

  primary key (record_id),
  index idx_nurse_log_01(stuff_id),
  index idx_nurse_log_02(member_id),
  index idx_nurse_log_03(create_date)
)comment '回访日志/护理日志';

create table nurse_log_model
(
  record_id                       bigint              auto_increment        not null,
  model_content                   varchar(1000)                             not null, -- 模版内容

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,

  primary key (record_id),
  index nurse_log_model_01(model_content)
)comment '日志模版内容';

#
#  不需要仓库，因为每个门店只有1个仓库
#
# create table warehouse
# (
#   record_id        bigint        auto_increment    not null,
#   store_id         bigint                          not null,    -- 所属门店
#   warehouse_name   varchar(50)                     not null,    -- 门店仓库的名称
#   record_status    tinyint                         not null,    -- 房间状态：0. 启用    1.停用
#
#   primary key (record_id),
#   index idx_store_warehouse_01(store_id),
#   index idx_store_warehouse_02(warehouse_name),
#   index idx_store_warehouse_03(record_status)
# )comment '仓库';
#

create table product_stock
(
  record_id                        bigint        auto_increment     not null,
  product_id                       bigint                           not null,  -- 产品
  warehouse_id                     bigint                           not null,  -- 所在仓库/门店
  stock_qty                        int                              not null,  -- 在库总数
  cost                             double(10,2)                     not null,  -- 占用成本

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             not null default 0,


  primary key (record_id),
  index idx_product_stock_01(product_id),
  index idx_product_stock_02(stock_qty),
  index idx_product_stock_03(cost),
  index idx_product_stock_04(warehouse_id)
)comment '产品库存表';



create table product_stock_movement
(
  record_id                        bigint         auto_increment   not null,
  record_create_type               tinyint                         not null,  -- 记录产生方式：0.手工  1.扫码
  movement_type                    tinyint                         not null,  -- 异动类型：0~63 为入库    64~127为出库
                    --       0.  采购入库  1.调拨入库  2.调整入库/盘盈
                    --       64. 内部员工  65.顾客   66. 赠送  67. 报废  68.院用  69.退回供应商  70.下发到店    71.调拨出库  72.调整出库/盘亏

  movement_no                      char(20)                        not null,  -- 异动单号
  product_id                       bigint                          not null,  -- 产品
  movement_pur_qty                 int                             not null,  -- 异动前数量
  movement_qty                     int                             not null,  -- 异动数量

  date_of_manufacture              datetime                        not null,  -- 生产日期
  purchase_cost                    double(10,2)                    null,      -- 进货单价：入库的时候需要填

  warehouse_id                     bigint                          not null,  -- 发生仓库/门店
  reference_record_no              varchar(50)                     null,      -- 引用单号:采购单/送货单/销售单/调拨申请单/盘点单号
                                                                              --  调拨：必须要填写调拨申请单，而且要检查调拨申请单是否已经被批准

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,

  remark                           varchar(1000)                   null,      -- 备注

  primary key (record_id),
  index idx_product_stock_movement_01(movement_type),
  index idx_product_stock_movement_02(movement_no),
  index idx_product_stock_movement_03(date_of_manufacture),
  index idx_product_stock_movement_04(create_date),
  index idx_product_stock_movement_05(product_id),
  index idx_product_stock_movement_06(create_by),
  index idx_product_stock_movement_06(update_by),
  index idx_product_stock_movement_06(update_date),
  index idx_product_stock_movement_06(warehouse_id)
)comment '产品库存异动';


create table stock_transfer_application
(
  record_id                        bigint              auto_increment    not null,
  application_no                   char(12)                              not null,
  product_stock_movement_id        bigint                                not null,  -- 出库单号
  in_warehouse_product_id          bigint                                not null,  -- 调入仓库产品号
  out_warehouse_id                 bigint                                not null,  -- 调出仓库/门店
  in_warehouse_id                  bigint                                not null,  -- 调入仓库/门店
  remark_application               varchar(500)                          null,      -- 申请原因
  remark_audit                     varchar(500)                          null,      -- 审批原因
  record_status                    tinyint                               not null,  -- 记录状态： 4.已完成   3.已批准   2.已驳回   1.已撤销  0.已提交(注意，一旦提交，就不可以修改，只能撤销以后重新提交)
  creator                          bigint                                null,  -- 创建人/申请人：调拨必须是调入门店的店长
  approver                         bigint                                null,  -- 审批人/修改人：调拨必须是调出门店的店长

  create_date                      datetime                              not null,
  create_by                        bigint                                not null,
  update_date                      datetime                              null,
  update_by                        bigint                                null,
  opt_lock                         int                                   null,

  primary key (record_id),
  index idx_stock_transfer_application_01(application_no),
  index idx_stock_transfer_application_02(create_date),
  index idx_stock_transfer_application_03(create_by),
  index idx_stock_transfer_application_04(update_by),
  index idx_stock_transfer_application_05(update_date),
  index idx_stock_transfer_application_06(record_status),
  index idx_stock_transfer_application_07(out_warehouse_id)
)comment '调拨申请单';


create table stock_transfer_application_item
(
  record_id                      bigint        auto_increment             not null,
  stock_transfer_application_id  bigint                                   not null,
  product_id                     bigint                                   not null,   -- 调出产品
  qty                            double(10,2)                             not null,   -- 调出数量

  primary key (record_id),
  index idx_stock_transfer_application_item_01(stock_transfer_application_id),
  index idx_stock_transfer_application_item_02(product_id)
)comment '调拨明细';

create table stuff_score
(
  record_id                     bigint       auto_increment               not null,
  stuff_id                      bigint                                    not null, -- 哪个员工
  existing                      bigint                                    not null, -- 现有的积分

  primary key (record_id),
  index idx_stuff_score_01(stuff_id)

)comment '员工的积分表';

create table stuff_score_record
(
  record_id                     bigint        auto_increment              not null,
  stuff_id                      bigint                                    not null, -- 哪个员工
  matter                        varchar(500)                              not null, -- 在哪里，做了些什么事
  score                         bigint                                    not null, -- 得到的积分总数
  issued_by                     bigint                                    not null, -- 谁给的 ,这个id来源于员工表， 一般是店长或者院长给的

  create_date                   datetime                                  not null,--  在什么时候
  create_by                     bigint                                    not null,--
  update_date                   datetime                                  null,--
  update_by                     bigint                                    null,--
  opt_lock                      int                                       null,

  primary key (record_id),
  index idx_stuff_score_record_01(stuff_id),
  index idx_stuff_score_record_02(get_by_id)
)comment '员工的积分产生记录表';

create table work_summary
(
  record_id                     bigint              atuto_increment          not null,
  stuff_id                      bigint                                       not null, -- 员工 id
  summary                       varchar(1000)                                not null, -- 本月总结
  plan                          varchar(1000)                                not null, -- 下月计划
  cur_month                     date                                         not null, -- 报告的当前年月
  summary_type                  tinyint                                      not null, -- 总结类型 0 月总结  1 日总结

  create_date                   datetime                                     not null,--  在什么时候
  create_by                     bigint                                       not null,--
  update_date                   datetime                                     null,--
  update_by                     bigint                                       null,--
  opt_lock                      int                                          null,

  primary key (record_id),
  index idx_work_summary_01(stuff_id),
  index idx_work_summary_02(cur_month)
)comment '工作总结表';

create table role_action
(
  record_id                    bigint                auto_increment           not null,
  stuff_id                     bigint                                         not null,
  system_user_id               bigint                                         not null,

  primary key (record_id),
  index idx_role_action_01(stuff_id),
  index idx_role_action_02(system_user_id)
)comment '角色权限映射表';

create table notice
(
  record_id                   bigint                 auto_increment            not null,
  salon_id                    bigint                                           not null, -- 美容院/门店
  title                       varchar(50)                                      not null, -- 标题
  content                     varchar(1000)                                    not null, -- 公告内容

  primary key (record_id),
  index idx_notice_01(salon_id),
  index idx_notice_02(title)
)comment '公告表';

create table visual_range
(
  record_id                   bigint                 auto_increment            not null,
  stuff_id                    bigint                                           not null, -- 可视员工
  status                      int                                              not null, -- 可视状态 0 可见 1 不可见

  primary key (record_id),
  index idx_visual_range_01(stuff_id),
  index idx_visual_range_02(status)
)comment '可视范围表';

create table visual_range_mapping
(
  record_id                   bigint                  auto_increment            not null,
  notice_id                   bigint                                            not null, -- 公告id
  visual_range_id             bigint                                            not null, -- 可视范围id

  primary key (record_id),
  index idx_visual_range_mapping_01(notice_id),
  index idx_visual_range_mapping_02(visual_range_id)
)comment '可视范围映射表';

create table operate_log
(
  record_id                    bigint                 auto_increment            not null,
  opt_user_id                  bigint                                           not null, -- 操作用户
  opt_role_id                  bigint                                           not null, -- 操作用户角色
  opt_action                   varchar(10)                                      not null, -- 操作动作
  opt_date                     datetime                                         not null, -- 操作时间
  opt_info                     varchar(100)                                     not null, -- 操作的详细描述
  opt_terminal                 tinyint                                          null,     -- 操作端 0 web 1 android 2 ios 3 手机浏览器 4 小程序 5 其他
  opt_url                      varchar(500)                                     null,     -- 请求url
  opt_statu                    int                                              null,     -- 请求状态 200 404 500
  opt_result                   varchar(10)                                      not null, -- 操作结果 成功 失败

  primary key (record_id),
  index idx_operate_log_01(opt_user_id)
)comment '系统操作日志';

create table verification_code_temporary
(
  record_id                    bigint                 auto_increment            not null,
  phone_no                     varchar(11)                                      not null, -- 手机号码
  verification_code            varchar(10)                                      not null, -- 验证码
  valid_time                   tinyint                                          not null, -- 有效时间
  effectiveness                tinyint                                          not null, -- 有效性 0 有效 1 无效

  create_date                  datetime                                         not null, -- 创建时间
  create_by                    bigint                                           not null,
  update_date                  datetime                                         null,
  update_by                    bigint                                           null,
  opt_lock                     int                                              null,

  primary key (record_id),
  index idx_verification_code_temporary_01(phone_no)
)comment '短信验证码临时表';

create table card_balance
(
  record_id                    bigint              auto_increment               not null,
  member_id                    bigint                                           not null,   -- 会员Id
  card_id                      bigint                                           not null,   -- 卡Id
  card_type                    tinyint                                          not null,
                -- 卡类型:0. 充值卡  1.次卡    3.套卡   4.套卡的项目
                -- 如果是充值卡，写程序的时候要注意卡金额的合并

  balance                      double(8,2)                                      not null,  -- 卡户余额/剩余 次数

  card_status                  tinyint                                          not null,  -- 卡的状态: 0.正常  1.失效
  date_expired                 datetime                                         null,      -- 失效时间：null表示永久有效

  parent_id                    bigint                                           not null,  -- 所属套卡:如果是充值卡、次卡、套卡，则为0，只有次卡的项目才有此字段的值
  remark                       varchar(500)                                    null,       -- 备注
  create_date                  datetime                                         not null, -- 创建时间/购买时间
  create_by                    bigint                                           not null,
  update_date                  datetime                                         null,     -- 最后一次充值时间
  update_by                    bigint                                           null,     -- 最后一次充值操作人
  opt_lock                     int                                              null,

  primary key (record_id),
  index idx_card_balance_01(member_id),
  index idx_card_balance_02(card_type)
) comment  '卡户余额';

create table card_purchase
(
  record_id                    bigint              auto_increment              not null,
  member_id                    bigint                                          not null,   -- 会员Id
  card_id                      bigint                                          not null,   -- 卡Id
  card_type                    tinyint                                         not null,
               -- 卡类型:0. 充值卡  1.套卡  2.次卡
               -- 如果是充值卡，写程序的时候要注意卡金额的合并
  amount_market                double(8,2)                                     not null,   -- 原价
  amount                       double(8,2)                                     not null,   -- 充值金额/总金额 = 原价 - 赠送金额(在子表中)
  amount_debit                 double(8,2)                                     not null,   -- 欠款
  amount_payed                 double(8,2)                                     not null,   -- 实际支付/现金支付 = 交易价格-欠款
  method_payed                 tinyint                                         not null,
       -- 支付方式: 0.微信  1.支付宝  2.银行卡  10.现金
       --        todo: 如果使用会员的现金账户(member.balance_cash)支付，如何操作？

  remark                       varchar(500)                                    null,       -- 备注
  member_signature             bigint                                          not null,   -- 客户签名(系统照片ID)

  recharge_type                tinyint                                         not null,  -- 充值类型 0 旧卡充值  1 新购卡

  create_date                  datetime                                         not null,  -- 创建时间
  create_by                    bigint                                           not null,
  update_date                  datetime                                         null,
  update_by                    bigint                                           null,
  opt_lock                     int                                              null,

  primary key (record_id),
  index idx_card_purchase_01 (member_id),
  index idx_card_purchase_02(card_id),
  index idx_card_purchase_03(card_type)
) comment '购卡记录';


create table member_gift
(
  record_id                    bigint               auto_increment               not null,
  member_id                    bigint                                            not null,  -- 会员Id
  ref_trans_id                 bigint                                            not null,  -- 参考交易Id，比如 充值/买卡Id
  trans_type                   tinyint                                           not null,  -- 交易类型: 0.购卡/充值交易
  gift_type                    tinyint                                           not null,
                -- 赠品类型：0.项目 1.产品 2.优惠券 3.金额
                --   产品:注意扣除库存数量
                --   金额:注意修改应支付的金额数量
  git_id                       bigint                                            not null,  -- 赠品Id: 金额:-1，优惠券:-2 项目和产品:填写其对应的Id
  qty                          double(8,2)                                       not null,  -- 数量/金额

  gift_sub_type                tinyint                                           not null,  -- 优惠券类型： 0.项目券   1.代金券
  gift_expired_date            datetime                                          null,      -- 有效期

  create_date                  datetime                                         not null,   -- 创建时间
  create_by                    bigint                                           not null,
  update_date                  datetime                                         null,
  update_by                    bigint                                           null,
  opt_lock                     int                                              null,

  primary key (record_id),
  index idx_member_gift_01(member_id),
  index idx_member_gift_02(ref_trans_id),
  index idx_member_gift_03(gift_type),
  index idx_member_gift_04(git_id)
)comment '赠送明细表';



create table payment
(
  record_id                    bigint              auto_increment              not null,
  member_id                    bigint                                          not null,  -- 会员Id
  payment_type                 tinyint                                         not null,  -- 0.一般支付   1.偿还欠款

  remark                       varchar(500)                                    null,       -- 备注
  member_signature             bigint                                          not null,   -- 客户签名(系统照片ID)

  create_date                  datetime                                         not null,   -- 创建时间
  create_by                    bigint                                           not null,
  update_date                  datetime                                         null,
  update_by                    bigint                                           null,
  opt_lock                     int                                              null,

  primary key (record_id),
  index idx_payment_01(member_id)
)comment  '划卡支付';

create table payment_item
(
  record_id                    bigint              auto_increment              not null,
  payment_id                   bigint                                          not null,  -- 支付Id
  card_balance_id              bigint                                          not null,  -- 卡户Id:充值卡/次卡/套卡项目/优惠券的编号  -1.欠款
  merchandise_id               bigint                                          not null,  -- 商品Id:服务/产品的编号   -1.欠款
  merchandise_type             tinyint                                         not null,  -- 商品类型: 0.服务  1.产品  -1.欠款

  qty                          double(8,2)                                     not null,  -- 数量： 还欠款的话，数量为 1
  price                        double(10,2)                                    not null,  -- 单价： 还欠款的话，单价=偿还金额
  amount                       double(10,2)                                    not null,
                      -- 金额：一般支付 注意扣除card_balance或者服务明细的金额或者次数
                      --      偿还欠款：注意修改账户欠款余额

  create_date                  datetime                                         not null,   -- 创建时间
  create_by                    bigint                                           not null,
  update_date                  datetime                                         null,
  update_by                    bigint                                           null,
  opt_lock                     int                                              null,

  primary key (record_id),
  index idx_payment_item_01(payment_id),
  index idx_payment_item_02(card_balance_id),
  index idx_payment_item_03(merchandise_id),
  index idx_payment_item_04(merchandise_type)
)comment  '划卡支付明细';

create table member_product_keep
(
  record_id                    bigint              auto_increment              not null,
  member_id                    bigint                                          not null,

  remark                       varchar(500)                                    null,       -- 备注
  member_signature             bigint                                          not null,   -- 客户签名(系统照片ID)

  create_date                  datetime                                         not null,   -- 创建时间
  create_by                    bigint                                           not null,
  update_date                  datetime                                         null,
  update_by                    bigint                                           null,
  opt_lock                     int                                              null,

  primary key (record_id),
  index idx_member_product_keep(member_id)
)comment  '产品寄存';

create table member_product_keep_item
(
  record_id                    bigint              auto_increment              not null,
  member_product_keep_id       bigint                                          not null,  -- 寄存Id
  product_id                   bigint                                          not null,  -- 产品Id
  price                        double(8,2)                                     not null,  -- 单价
  qty_purchased                double(8,2)                                     not null,  -- 购买数量
  amount                       double(10,2)                                    not null,  -- 金额

  qty_received                 double(8,2)                                     not null,  -- 已领取数量

  create_date                  datetime                                         not null,   -- 创建时间
  create_by                    bigint                                           not null,
  update_date                  datetime                                         null,
  update_by                    bigint                                           null,
  opt_lock                     int                                              null,

  primary key (record_id),
  index idx_member_product_keep_item_01 (member_product_keep_id),
  index idx_member_product_keep_item_02 (product_id)
)comment  '产品寄存明细';


create table member_product_reject
(
  record_id                    bigint              auto_increment              not null,
  member_id                    bigint                                          not null,

  remark                       varchar(500)                                    null,       -- 备注
  member_signature             bigint                                          not null,   -- 客户签名(系统照片ID)

  create_date                  datetime                                         not null,   -- 创建时间
  create_by                    bigint                                           not null,
  update_date                  datetime                                         null,
  update_by                    bigint                                           null,
  opt_lock                     int                                              null,

  primary key (record_id),
  index idx_member_product_reject(member_id)
)comment '产品退款';


create table member_product_reject_item
(
  record_id                    bigint              auto_increment              not null,
  member_product_reject_id     bigint                                          not null,  -- 产品退款Id
  product_keep_item_id         bigint                                          not null,  -- 原购买记录Id

  reject_type                  tinyint                                         not null,  -- 0.未领取退款  1.已领取退款
  qty_reject                   double(8,2)                                     not null,  -- 退还数量
  amount_reject                double(10,2)                                    not null,  -- 退还金额
  type_amount_return           tinyint                                         not null,
        -- 资金退还方式: 0.现金  1.余额
        --     如果是以余额的方式，则要修改member的balance_cash字段

  create_date                  datetime                                         not null,   -- 创建时间
  create_by                    bigint                                           not null,
  update_date                  datetime                                         null,
  update_by                    bigint                                           null,
  opt_lock                     int                                              null,

  primary key (record_id),
  index idx_member_product_reject_item_01 (member_product_reject_id),
  index idx_member_product_reject_item_02 (product_keep_item_id)
)comment  '产品退款明细';


create table business_stuff
(
  record_id                    bigint              auto_increment              not null,
  ref_trans_id                 bigint                                          not null,  -- 交易Id
  trans_type                   tinyint                                         not null,  -- 交易类型: 0.购卡/充值交易  1.划卡明细   2.寄库存   3.库存退还  4.还欠款
  stuff_id                     bigint                                          not null,  -- 关联员工

  create_date                  datetime                                         not null,  -- 创建时间
  create_by                    bigint                                           not null,
  update_date                  datetime                                         null,
  update_by                    bigint                                           null,
  opt_lock                     int                                              null,

  primary key (record_id),
  index idx_business_stuff_01 (stuff_id),
  index idx_business_stuff_02 (ref_trans_id),
  index idx_business_stuff_03 (trans_type)
) comment  '关联员工';


create table arrearages_record
(
  record_id                  bigint                auto_increment              not null,
  ref_trans_id               bigint                                            not null,  -- 交易Id
  member_id                    bigint                                          not null,  --会员id
  arrearages_date            datetime                                          not null,  -- 欠款日期
  arrearages_type            tinyint                                           not null,  -- 欠款产生类型  0  充值  1 划卡 2 消费
  amount_of_real_pay         double(8,2)                                       not null,  -- 实付金额
  amount_payable             double(8,2)                                       not null,  -- 应付总额
  is_paid_off                tinyint                                           not null,  -- 是否还清 0 是 1 否

  create_date                datetime                                          not null,   -- 创建时间
  create_by                  bigint                                            not null,
  update_date                datetime                                          null,
  update_by                  bigint                                            null,
  opt_lock                   int                                               null,

  primary key (record_id),
  index idx_arrearages_record_01 (ref_trans_id),
  index idx_arrearages_record_02 (arrearages_date),
)comment '欠款记录表';

create table repayment_record
(
  record_id                  bigint                auto_increment              not null,
  arrearages_record          bigint                                            not null,  -- 还的是哪一笔欠款
  reimbursement_date         datetime                                          not null,  -- 还款日期
  reimbursement_amount       double(8,2)                                       not null,  -- 本次还款金额
  still_need_to_pay          double(8,2)                                       not null,  -- 仍需还款
  is_paid_off                tinyint                                           not null,  -- 是否还清 0 是 1 否

  create_date                datetime                                          not null,   -- 创建时间
  create_by                  bigint                                            not null,
  update_date                datetime                                          null,
  update_by                  bigint                                            null,
  opt_lock                   int                                               null,

  primary key (record_id),
  index idx_repayment_record_01 (arrearages_record),
  index idx_repayment_record_02 (reimbursement_date),
) comment '还款记录表';

/*
   五类结算：
       1.会员与店员(门店/美容院)结算
       2.店员与门店结算
       3.门店与美容院(商户)结算
       4.美容院(商户)与美容院(商户)结算
       5.美容院(商户)与HY结算

    会员与HY不直接结算.
 */