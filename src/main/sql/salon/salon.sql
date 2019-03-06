create table job
(
  record_id                        bigint      auto_increment      not null,
  job_name                         varchar(50)                     not null,    --  职务名称
  job_level                        tinyint                         not null,    --  职位等级

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
  parent_id                        bigint                          not null,     -- 所属上级(上级省份或者上级城市)  9
  name_path                        varchar(500)                    not null,     -- 路径:广东省东莞市东城区

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,


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
  door_2_door                      bit                             not null default 0,     -- 是否上门服务:美容院专用
  bed_num                          int                             not null default 0,     -- 床位数：门店专用
  area                             double(8,2)                     not null default 0.00,  -- 面积：门店专用
  time_open                        time                            null,                   -- 营业开始时间：门店专用
  time_close                       time                            null,                   -- 营业结束时间：门店专用
  description                      varchar(500)                    null,                   -- 简介

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


create table stuff
(
  record_id                        bigint      auto_increment      not null,
  store_id                         bigint                          not null,  -- 所属门店
  stuff_name                       varchar(50)                     not null,  -- 姓名
  tel                              varchar(50)                     not null,  -- 电话
  gender                           tinyint                         not null,  -- 性别
  entry_time                       datetime                        not null,  -- 入职时间
  work_age                         double(3,1)                     not null,  -- 工作年限
  special                          varchar(120)                    null,      -- 特长
  dream                            varchar(120)                    null,      -- 梦想
  weixin                           varchar(50)                     null,      -- 微信
  qq                               varchar(50)                     null,      -- QQ
  address                          varchar(120)                    null,      -- 联系地址
  system_role_id                   bigint                          not null,  -- 所属系统角色

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
  service_suite_id                 bigint                         not null, //套卡id
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
  suite_name                       varchar(50)                     not null,    -- 充值卡名称
  price                            double(8,2)                     not null,    -- 充值面额
  vip_suite_status                 tinyint                         not null,    -- 记录状态：0.启用   1.停用
  discount_time                    tinyint                         not null,    -- 单次折扣
  discount_period                  tinyint                         not null,    -- 疗程折扣
  discount_production              tinyint                         not null,    -- 产品折扣
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
  record_type                      tinyint                           not null,  -- 记录类型:0.单次折扣  1.疗程折扣  2.产品折扣
  item_id                          bigint                            not null,  -- 折扣项目：服务/产品

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,

  primary key (record_id),
  index idx_vip_suite_item_01(record_type),
  index idx_vip_suite_item_02(item_id)
)comment '充值卡折扣项目';

create table product_series
(
  record_id                        bigint      auto_increment      not null,
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
  salon_id                         bigint                           not null,   -- 美容院
  product_name                     varchar(50)                      not null,   -- 产品名称
  product_class                    tinyint                          not null,   -- 产品类型: 0.客装   1.院装   2.易耗品
  product_series_id                bigint                           not null,   -- 产品品牌/系列
  price_market                     double(8,2)                      not null,   -- 市场价
  price                            double(8,2)                      not null,   -- 优惠价

  product_code                     varchar(20)                      null,       -- 产品编号
  specification                    int                              null,       -- 规格:就是数量，比如3kg/瓶
  specification_unit               tinyint                          null,       -- 规格单位：0. g(克)   1.Kg(千克)  2.ml(毫升)  3.L(升)
  product_unit_id                  bigint                           not null,   -- 单位：瓶/袋/包等
--  part_of_applicable_id            bigint                           not null,   -- 适用部位
--  efficiency_tag_id                bigint                           not null,   -- 功效

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

create table product_unit
(
  record_id                        bigint        auto_increment    not null,
  unit_name                        varchar(50)                     not null,

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,


  primary key (record_id),
  index idx_product_unit_01(unit_name)
)comment '产品单位';

create table body_part
(
  record_id                        bigint         auto_increment   not null ,
  part_name                        varchar(50)                     not null,  -- 部位名称

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,

  primary key (record_id),
  index idx_body_part_01(part_name)
)comment '部位';

create table product_body_part
(
  record_id                        bigint       auto_increment     not null,
  product_id                       bigint                          not null,

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,


  primary key (record_id),
  index idx_product_body_part_01(product_id)
) comment '产品作用部位';


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

create table product_tag
(
  record_id                        bigint         auto_increment   not null ,
  tag_id                           bigint                          not null,

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,


  primary key (record_id),
  index idx_product_tag_01(tag_id)
)comment '产品功效';


create table pictures
(
  record_id                        bigint    auto_increment        not null ,
  master_data_id                   bigint                          not null,     -- 主记录编号
  record_type                      tinyint                         not null,     -- 记录类型: 0.美容院（门店）  1. 员工    2. 项目/卡   3.套卡   4.充值卡  5.产品    6.会员/顾客    7. 护理日志
  pic_type                         tinyint                         not null,     -- 照片类型: 0.普通照片   1.营业执照（1张)    2.身份证（要有2张，正反面各1张）  3.开户许可(1张)
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
  member_grade_id                  bigint                          not null,   -- 分类
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

  balance                          double(10,2)                    not null,   -- 账户总余额
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


create  table member_grade
(
  record_id                        bigint      auto_increment      not null,
  grade_name                       varchar(10)                     not null,
  grade_level                      tinyint                         not null,
  salon_id                         bigint                          not null,  --  所属美容院

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,


  primary key (record_id),
  index idx_member_grade_01(grade_name)
)comment '会员级别';

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


create table shift
(
  record_id                        bigint      auto_increment      not null,
  store_id                         bigint                          not null,   -- 门店
  shift_type                       tinyint                         not null,   -- 班次：0.全  1.早   2. 中   3.晚
  time_start                       datetime                        not null,   -- 开始时间
  time_end                         datetime                        not null,   -- 结束时间

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,

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
  stuff_id                         bigint                          not null,  -- 员工
  shift_id                         bigint                          not null,  -- 班次： -1 表示休息
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

  time_sheet_type                   tinyint                        not null,  -- 出勤类型：0.正常  1.迟到   2.早退    3. 缺勤（旷工、休假）

  create_date                      datetime                        not null,
  create_by                        bigint                          not null,
  update_date                      datetime                        null,
  update_by                        bigint                          null,
  opt_lock                         int                             null,


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


create table reservation
(
  record_id                        bigint      auto_increment      not null,
  member_id                        bigint                          not null,   -- 会员
  stuff_id                         bigint                          not null,   -- 预约美容师
  room_id                          bigint                          not null,   -- 预约的房间
  time_start                       datetime                        not null,   -- 开始时间
  time_end                         datetime                        not null,   -- 结束时间
  record_status                    tinyint                         not null,   -- 记录状态：0.未开始  1.已确认    2.服务中    3.已完成

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
  log_content                      varchar(1000)                   not null,   -- 内容：里面包含有文字、表情(emoj)、位置等，是否需要blob?
  parent_id                        bigint                          not null,   -- 前一主题的编号，如果是顶级主题，则parent_id = -1

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
)comment '护理日志';

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
  opt_lock                         int                             null,


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

  movement_no                      char(12)                        not null,  -- 异动单号
  product_id                       bigint                          not null,  -- 产品
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
  out_warehouse_id                 bigint                                not null,  -- 调出仓库/门店
  remark_application               varchar(500)                          null,      -- 申请原因
  remark_audit                     varchar(500)                          null,      -- 审批原因
  record_status                    tinyint                               not null,  -- 记录状态： 4.已完成   3.已批准   2.已驳回   1.已撤销  0.已提交(注意，一旦提交，就不可以修改，只能撤销以后重新提交)

  create_date                      datetime                              not null,  -- 创建/申请时间
  create_by                        bigint                                not null,  -- 创建人/申请人：调拨必须是调入门店的店长
  update_date                      datetime                              not null,  -- 修改/审批时间
  update_by                        bigint                                not null,  -- 审批人/修改人：调拨必须是调出门店的店长
  opt_lock                         int                              not null,

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


#
#
# create table purchase_card_info
# (
#   record_id                         bigint        auto_increment      not null,
#   member_id                         bigint                            not null,  -- 开卡的会员
#   vip_suite_id                      bigint                            not null,  -- 充值卡的种类
#   purchase_card_info_detail_id      bigint                            not null,  -- 明细表的id
#   records_of_consumption_id         bigint                            not null,  -- 消费记录明细
#
#   primary key (record_id),
#   index idx_purchase_card_info_01(member_id)
# )comment '会员开卡记录';
#
# create table purchase_card_info_detail
# (
#   record_id                bigint        auto_increment       not null,
#   service_id               bigint                             not null,  -- 次卡/服务项目
#
#   primary key(record_id),
#   index idx_purchase_card_info_detail_01(service_id)
# )comment '会员卡开卡记录明细表';
#
# create table records_of_consumption
# (
#   record_id                   bigint           auto_increment          not null,
#   consumption_type            bigint                                   not null,  -- 消费类型 0 开卡 1 充值 2 做服务 3 购买本店商品
#   purchase_card_info_id       bigint                                   not null,  -- 消费类型0： 绑定开卡记录
#   vip_suite_id                bigint                                   not null,  -- 消费类型1： 充值的时候，绑定充值卡
#   vip_suite_num               int                                      not null,  -- 消费类型1： 购买充值卡的数量
#   payment_amount              double(10,2)                             not null,  -- 消费类型0/1/2/3: 应付款金额
#   terms_of_payment            tinyint                                  not null,  -- 消费类型0/1/2/3: 付款方式 0 现金 1 支付宝 2 微信 3 信用卡 4 划卡
#   service_id                  bigint                                   not null,  -- 消费类型2：使用的次卡
#   service_num                 tinyint                                  not null,  -- 消费类型2：使用次卡服务的次数，默认1
#   is_vip_suite                tinyint                                  not null,  -- 消费类型2/3：是否使用会员卡 0 否 1 是
#   discount_price_before       double(10,2)                             not null,  -- 消费类型2/3：折前价
#   present_cash_coupon         double(10,2)                             not null,  -- 消费类型2/3：赠送的现金券
#   present_integral            double(10,2)                             not null,  -- 消费类型2/3：赠送的积分
#   signature                   blob                                     not null,  -- 消费类型2/3：顾客签字
#   opt_time                    bigint                                   not null,  -- 操作时间
#   salon_id                    bigint                                   not null,  -- 操作的门店
#   stuff_id                    bigint                                   not null,  -- 操作的员工
#   opt_type                    tinyint                                  not null,  -- 操作类型 0 店内消费
#
#   primary key(record_id),
#   index idx_records_of_consumption_01(consumption_type),
#   index idx_records_of_consumption_02(opt_time),
#   index idx_records_of_consumption_03(salon_id),
#   index idx_records_of_consumption_04(stuff_id),
#   index idx_records_of_consumption_05(opt_type)
# )comment '消费记录表';


/*
   五类结算：
       1.会员与店员(门店/美容院)结算
       2.店员与门店结算
       3.门店与美容院(商户)结算
       4.美容院(商户)与美容院(商户)结算
       5.美容院(商户)与HY结算

    会员与HY不直接结算.
 */