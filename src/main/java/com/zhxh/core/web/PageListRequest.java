package com.zhxh.core.web;

import com.github.pagehelper.PageHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class PageListRequest extends ListRequest {
 //   private int start;
//    private int limit;
    private String orderBy;

    public PageListRequest(){
        super();

      //  this.start=-1;
       // this.limit=-1;
    }

    @Override
    public HashMap<String, Object> toMap() {
        HashMap<String, Object> result = super.toMap();

       /// if (limit > 0) {
          //  result.put("start", start);
            //result.put("limit", limit);
       // }

        return result;
    }

    @Override
    public void fromServletRequest(HttpServletRequest request) {
        super.fromServletRequest(request);
//        String strStart = request.getParameter("start");
//        if (strStart != null && !strStart.isEmpty()) {
//            this.setStart(Integer.parseInt(strStart));
//        }

//        String strLimit = request.getParameter("limit");
//        if (strLimit != null && !strLimit.isEmpty()) {
//            this.setLimit(Integer.parseInt(strLimit));
//        }
//
        String pageNum = request.getParameter("page");
        if (pageNum == null || pageNum.isEmpty()) {
            pageNum = "1";
        }
        String pageSize = request.getParameter("pageSize");

        if (pageSize == null || pageSize.isEmpty()) {
            pageSize = "10";
        }
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        String orderBy = request.getParameter("orderBy");
        if(orderBy != null && !orderBy.equals("")) {
        	this.setOrderBy(orderBy);
        }
    }

//    public int getStart() {
//        return start;
//    }
//
//    public void setStart(int start) {
//        this.start = start;
//    }

//    public int getLimit() {
//        return limit;
//    }
//
//    public void setLimit(int limit) {
//        this.limit = limit;
//    }

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
    
    
}
