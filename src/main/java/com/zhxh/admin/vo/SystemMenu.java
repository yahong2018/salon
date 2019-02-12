package com.zhxh.admin.vo;


import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

@Getter
@Setter
public class SystemMenu {
    private String programId;
    private String programCode;
    private String programName;
    private String url;
    private String parameters;
    private Object[] children;
    private String glyph;
    private boolean expanded;

    public boolean isFolder() {
        return StringUtils.isBlank(this.url);
    }
    public boolean isLeaf(){
        return !this.isFolder();
    }
}
