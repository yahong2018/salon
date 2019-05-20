package com.hy.salon.basic.entity;

import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("approval_process")
@Getter
@Setter
public class ApprovalProcess  {

    private Long recordId;

    private Long first;

    private Long second;

    private Long third;

    private Long four;

    private Long storeId;

    private Long billTypeId;

}
