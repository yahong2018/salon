package com.zhxh.core.data;

public interface ParentChildVO {
    default boolean isLeaf() {
        return this.getChildren() == null || this.getChildren().length == 0;
    }
    default boolean isExpanded(){
        return !this.isLeaf();
    }

    Object[] getChildren();
    void setChildren(Object[] children);

}
