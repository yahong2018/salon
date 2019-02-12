CREATE TABLE system_user
(
  record_id                 BIGINT AUTO_INCREMENT      NOT NULL,
  user_code                 VARCHAR(20)                NOT NULL,
  user_name                 VARCHAR(50)                NOT NULL,
  pwd                       VARCHAR(50)                NOT NULL,
  user_status               TINYINT                    NOT NULL,
  email                     VARCHAR(255)               NOT NULL,
  online                    BIT                        NOT NULL DEFAULT 0, -- 0.离线  1.在线
  last_login_time           DATETIME                   NULL ,              -- 最后登录时间

  PRIMARY KEY (record_id),
  index IDX_SYSTEM_USER_0(user_code),
  index IDX_SYSTEM_USER_1(user_name),
  index IDX_SYSTEM_USER_2(user_status),
  index IDX_SYSTEM_USER_3(online)
);


CREATE TABLE system_role
(
  record_id                  BIGINT AUTO_INCREMENT    NOT NULL,
  role_code                  VARCHAR(20)              NOT NULL,
  role_name                  VARCHAR(50)              NOT NULL,

  PRIMARY KEY (record_id),
  index IDX_SYSTEM_ROLE_0(role_code),
  index IDX_SYSTEM_ROLE_1(role_name)
);


CREATE TABLE role_user
(
  record_id                 BIGINT AUTO_INCREMENT     NOT NULL,
  role_id                   BIGINT                    NOT NULL,
  user_id                   BIGINT                    NOT NULL,

  PRIMARY KEY (record_id),
  index IDX_ROLE_USER_0(role_id),
  index IDX_ROLE_USER_1(user_id)
);


CREATE TABLE role_privilege
(
  record_id                 BIGINT AUTO_INCREMENT      NOT NULL,
  program_privilege_id      BIGINT                     NOT NULL,
  role_id                   BIGINT                     NOT NULL,
  program_id                VARCHAR(50)                NOT NULL,
  privilege_code            VARCHAR(50)                NOT NULL,

  PRIMARY KEY (record_id),
  index IDX_ROLE_PRIVILEGE_0(role_id),
  index IDX_ROLE_PRIVILEGE_1(program_id)
);


CREATE TABLE system_program
(
  record_id                  VARCHAR(50)                NOT NULL,
  program_code               VARCHAR(50)                NOT NULL,
  program_name               VARCHAR(120)               NOT NULL,
  url                        VARCHAR(255)               NOT NULL,
  glyph                      VARCHAR(100)               NULL,
  show_order                 INT                        NOT NULL,
  parameters                 VARCHAR(255)               NOT NULL,
  parent_id                  VARCHAR(50)                NOT NULL,

  PRIMARY KEY (record_id),
  index IDX_SYSTEM_PROGRAM_0(program_code),
  index IDX_SYSTEM_PROGRAM_1(parent_id)
);

CREATE TABLE program_privilege
(
  record_id                  BIGINT AUTO_INCREMENT      NOT NULL,
  program_id                 VARCHAR(50)                   NOT NULL,
  privilege_code             VARCHAR(50)                NOT NULL,
  privilege_name             VARCHAR(120)               NOT NULL,

  PRIMARY KEY (record_id),
  index IDX_PROGRAM_PRIVILEGE_0(program_id),
  index IDX_PROGRAM_PRIVILEGE_1(privilege_code)
);





