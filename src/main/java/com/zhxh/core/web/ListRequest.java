package com.zhxh.core.web;

import com.zhxh.core.utils.Logger;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;

public class ListRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String where;
    private String orderBy;
    private String sortDir;

    public ListRequest(){
        this.where="";
        this.orderBy="";
        this.sortDir="";
    }

    public ListRequest(String where){
        this.where = where;
        this.orderBy="";
        this.sortDir="";
    }

    public ListRequest(String where,String orderBy){
        this.where = where;
        this.orderBy=orderBy;
        this.sortDir="";
    }

    public ListRequest(String where,String orderBy,String sortDir){
        this.where = where;
        this.orderBy=orderBy;
        this.sortDir=sortDir;
    }

    public void fromServletRequest(HttpServletRequest request) {
        try {
            if (request.getParameter("filterExpr") != null) {
                String rawSearch = request.getParameter("filterExpr")
                        .replaceAll("\r|\n", "")
                        .replaceAll(" ","+")
                        ;
                byte[] buffer = Base64.getDecoder().decode(rawSearch);
                String strUtf8 = new String(buffer, "UTF-8");

                //   String strIso = new String(buffer, "ISO-8859-1");
                // String strUtf8 = java.net.URLDecoder.decode(strIso, "UTF-8");

                this.setWhere(strUtf8);
            }
        } catch (Exception e) {
            Logger.error(e);
        }
    }

    public HashMap<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("where",this.getWhere());
        result.put("orderBy",this.getOrderBy());
        result.put("sortDir",this.getSortDir());

        return result;
    }


    public ListRequest and(String expr) {
        if (this.getWhere() == null || this.getWhere().isEmpty()) {
            this.setWhere(expr);
        } else {
            this.setWhere(this.getWhere() + " and " + expr);
        }
        return this;
    }

    public ListRequest or(String expr) {
        if (this.getWhere() == null || this.getWhere().isEmpty()) {
            this.setWhere(expr);
        } else {
            this.setWhere(this.getWhere() + " or " + expr);
        }
        return this;
    }

    public ListRequest add(String expr) {
        if (this.getWhere() == null || this.getWhere().isEmpty()) {
            this.setWhere(expr);
        } else {
            this.setWhere(this.getWhere() + expr);
        }
        return this;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSortDir() {
        return sortDir;
    }

    public void setSortDir(String sortDir) {
        this.sortDir = sortDir;
    }
}
