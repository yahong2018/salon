package com.hy.salon.basic.entity;


import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@DataTableConfiguration("store_room")
@Getter
@Setter
public class StoreRoom extends TraceableEntity<Long> {


    private Long recordId;

    private Long storeId;

    private String roomName;

    private Byte recordStatus;

    private Date createDate;

    private Long createBy;

    private Date updateDate;

    private Long updateBy;

    private int optLock;

}
