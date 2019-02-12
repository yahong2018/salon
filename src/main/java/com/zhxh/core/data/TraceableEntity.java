package com.zhxh.core.data;


import com.zhxh.admin.service.AuthenticateService;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TraceableEntity<T> extends EntityObject<T> {
    public static void fillCreateInfo(TraceableEntity entity){
        entity.setCreateBy(AuthenticateService.getCurrentLogin().getRecordId());
        entity.setCreateDate(new Date());
    }

    public static void fillUpdateInfo(TraceableEntity entity){
        entity.setUpdateBy(AuthenticateService.getCurrentLogin().getRecordId());
        entity.setUpdateDate(new Date());
    }

    private Date createDate;
    private Date updateDate;
    private Long createBy;
    private Long updateBy;
    private int optLock;

    static final List<String> internal_fields = new ArrayList<>();
    static {
        internal_fields.add("createBy");
        internal_fields.add("createDate");
        internal_fields.add("updateBy");
        internal_fields.add("updateDate");
        internal_fields.add("optLock");
//        internal_fields.add("delFlag");
    }
}
