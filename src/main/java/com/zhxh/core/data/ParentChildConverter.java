package com.zhxh.core.data;

import com.zhxh.core.utils.BeanUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ParentChildConverter {
    public static <T, C extends ParentChildVO> List<C> getAllWithChildren(List<T> sourceList, Class<C> childClass, String id, String pId) throws Exception {
        if (sourceList == null || sourceList.size() == 0) {
            return new ArrayList<>();
        }

        Object[] topList = sourceList.stream().filter(x ->
                BeanUtils.getValue(x, id).equals(BeanUtils.getValue(x, pId))
        ).toArray();

        List<C> result = new ArrayList<>();

        for (int i = 0; i < topList.length; i++) {
            T topItem = (T) topList[i];

            C itemWithChildren = childClass.newInstance();
            BeanUtils.copy(topItem, itemWithChildren);

            Object[] childrenSource = getChildren(topItem,sourceList, id, pId);
            C[] children = (C[]) Array.newInstance(childClass, childrenSource.length);
            itemWithChildren.setChildren(children);

            for (int j = 0; j < children.length; j++) {
                T child = (T) childrenSource[j];
                C sub = childClass.newInstance();
                BeanUtils.copy(child, sub);
                plan2Tree(sub, sourceList, id, pId);

                children[j] = sub;
            }
            result.add(itemWithChildren);
        }
        return result;
    }



    private static <T, C extends ParentChildVO> void plan2Tree(C parent, List<T> sourceList, String id, String pId) throws Exception {
        Object[] childrenSource = getChildren(parent,sourceList,id,pId);
        Class<C> childClass = (Class<C>) parent.getClass();

        C[] children = (C[]) Array.newInstance(childClass, childrenSource.length);
        parent.setChildren(children);

        for (int i = 0; i < children.length; i++) {
            T child = (T) childrenSource[i];
            C sub = childClass.newInstance();
            BeanUtils.copy(child, sub);
            children[i] = sub;

            plan2Tree(sub, sourceList, id, pId);
        }
    }

    private static Object[] getChildren( Object parent,List sourceList, String id, String pId) {
        Object parentId = BeanUtils.getValue(parent, id);
        return sourceList.stream().filter(
                x -> {
                    Object beanID = BeanUtils.getValue(x, id);
                    Object beanPID = BeanUtils.getValue(x, pId);
                    return beanPID.equals(parentId) && !parentId.equals(beanID);
                }
        ).toArray();
    }
}
