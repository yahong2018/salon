package com.zhxh.admin.vo;

import com.zhxh.admin.entity.SystemProgram;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemProgramWithChildren extends SystemProgram {
    private SystemProgram[] children;

    public boolean isLeaf() {
        return children == null || children.length == 0;
    }
    public boolean isExpanded(){
        return !this.isLeaf();
    }
}
