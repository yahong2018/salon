package com.zhxh.admin.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemMenuWithPrivilege extends SystemMenu {
    private boolean checked;
    private String dataType;

    @Override
    public boolean isFolder() {
        boolean result = super.isFolder();
        if(!result){
            result = (this.getChildren()!=null && this.getChildren().length>0);
        }
        return result;
    }

}
