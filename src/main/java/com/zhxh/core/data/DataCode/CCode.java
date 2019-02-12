package com.zhxh.core.data.DataCode;

import com.zhxh.core.data.EntityObject;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("data_code_type_c")
@Getter
@Setter
public class CCode extends EntityObject {
    private String codeNo;
    private String codeName;
    private int codeType;
    private String description;
    private String parentCodeId;
    private String codeNoPath;
    private String codeNamePath;
}
