package com.zhxh.core.web;

import com.github.pagehelper.PageHelper;
import com.zhxh.core.utils.Logger;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ListRequestProcessHandler {
    public ExtJsResult getListFromHttpRequest(HttpServletRequest request, ListRequestBaseHandler listHandler) {
        ListRequest listRequest = getListRequest(request);

        ExtJsResult result = new ExtJsResult();
        result.setTotal(0);
        try {
            //PageHelper.startPage(2, 5);
            List list = listHandler.getByRequest(listRequest);
            int listCount = listHandler.getRequestListCount(listRequest);
            if (listCount == -1) {
                result.setTotal(list.size());
            } else {
                result.setTotal(listCount);
            }
            result.setRootProperty(list);
        } catch (Exception e) {
            Logger.error(e);
        }
        return result;
    }

    public ListRequest getListRequest(HttpServletRequest request) {
        ListRequest listRequest;
        if(StringUtils.isNotEmpty(request.getParameter("page"))){
            listRequest = new PageListRequest();
        }else{
            listRequest = new ListRequest();
        }
        listRequest.fromServletRequest(request);
        return listRequest;
    }

    /*
    public <T> ActionResult processAction(T item, ActionHandler actionHandler) {
        ActionResult result = new ActionResult();
        try {
            int resultCode = actionHandler.handleAction(item);
            result.setResultCode(resultCode);
            result.setResultId(ExceptionHelper.getErrorId(resultCode));
            result.setErrorMessage(ExceptionHelper.getErrorMessage(resultCode));
            result.setEntityObject(item);
        } catch (Exception e) {
            Logger.error(e);
        }

        return result;
    }
    */
}
