package com.zhxh.core.web;

import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.data.EntityObject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public abstract class SimpleCRUDController<T extends EntityObject> {
    @RequestMapping("getAll.handler")
    @ResponseBody
    public final List<T> getAll(HttpServletRequest request, HttpServletResponse response) {
        ListRequestProcessHandler listRequestProcessHandler = new ListRequestProcessHandler();
        ListRequest listRequest = listRequestProcessHandler.getListRequest(request);
        return this.internalGetAll(listRequest);
    }

    @RequestMapping("getAllByPage.handler")
    @ResponseBody
    public ExtJsResult getAllByPage(HttpServletRequest request, HttpServletResponse response) {
        ListRequestProcessHandler listRequestProcessHandler = new ListRequestProcessHandler();
        return listRequestProcessHandler.getListFromHttpRequest(request, new ListRequestBaseHandler() {
            @Override
            public List getByRequest(ListRequest listRequest) {
                return internalGetByRequest(listRequest);
            }

            @Override
            public int getRequestListCount(ListRequest listRequest) {
                return internalGetRequestListCount(listRequest);
            }
        });
    }

    @RequestMapping("create.handler")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public T create(T item) throws Exception {
        return this.internalCreate(item);
    }

    @RequestMapping("update.handler")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public T update(T item) throws Exception {
        return this.internalUpdate(item);
    }

    @RequestMapping("delete.handler")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public int delete(@RequestBody Object[] ids) throws Exception {
        return this.internalDelete(ids);
    }

    protected List<T> internalGetAll(ListRequest listRequest) {
        Map<String,Object> listMap = listRequest.toMap();

        return this.getCrudDao().getList(listMap);
    }

    protected int internalDelete(Object[] ids) throws Exception {
        int result =0;
        for (Object id : ids) {
            this.getCrudDao().deleteById(id);
            result += 1;
        }
        return result;
    }


    protected T internalUpdate(T item) throws Exception {
        this.getCrudDao().update(item);
        return item;
    }

    protected T internalCreate(T item) throws Exception {
        this.getCrudDao().insert(item);
        return item;
    }

    protected List internalGetByRequest(ListRequest listRequest) {
        return this.getCrudDao().getPageList(listRequest.toMap(), null);
    }

    protected int internalGetRequestListCount(ListRequest listRequest) {
        return this.getCrudDao().getPageListCount(listRequest.toMap(), null);
    }

    protected abstract BaseDAOWithEntity<T> getCrudDao();
}
