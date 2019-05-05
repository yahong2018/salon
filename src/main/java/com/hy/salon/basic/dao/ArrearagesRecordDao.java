package com.hy.salon.basic.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hy.salon.basic.entity.ArrearagesRecord;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.util.DateString;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.ExtJsResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("arrearagesRecordDao")
public class ArrearagesRecordDao extends BaseDAOWithEntity<ArrearagesRecord> {
    protected final static String SQL_GET_ARREARSLIST = "com.hy.salon.basic.dao.GET_ARREARSLIST";
    public ExtJsResult getSystemArrearsList(Long memberId, Long storeId, HttpServletRequest request,String toDays) {
        ExtJsResult extJsResult = new ExtJsResult();
        Map parameters = new HashMap();
        parameters.put("storeId",storeId);
        if(memberId!=null){
            parameters.put("memberId",memberId);
        }
        if(StringUtils.isNotEmpty(toDays)){
            String days[] =  toDays.split("~");
            String timeStart = days[0];
            String  timeEnd = days[1];
            parameters.put("timeStart", timeStart);
            parameters.put("timeEnd", timeEnd);
        }
        PageHelper.startPage(Integer.parseInt(request.getParameter("page")),2);
        List<Map> listMap = this.getSqlHelper().getSqlSession().selectList(SQL_GET_ARREARSLIST, parameters);
        PageInfo<Map> pageInfo = new PageInfo<>(listMap);
        extJsResult.setSuccess(true);
        extJsResult.setMsg("获取成功");
        extJsResult.setTotal(Integer.parseInt(pageInfo.getTotal()+""));
        JSONArray jsonArray = new JSONArray();

        if(listMap.size()>0){
            for(Map map :pageInfo.getList()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("recordId",map.get("recordId"));
                jsonObject.put("memberName",map.get("memberName"));
                Date date = (Date) map.get("arrearagesDate");
                jsonObject.put("arrearagesDate",DateString.DateToString2(date));
                int arrearagesType = (int) map.get("arrearagesType"); //0  充值  1 划卡 2 消
                String arrearagesTypeS = "";
                if(arrearagesType==0){
                    arrearagesTypeS = "充值";
                }else if(arrearagesType==1){
                    arrearagesTypeS = "划卡";
                }else if(arrearagesType==2){
                    arrearagesTypeS = "消费";
                }
                jsonObject.put("arrearagesType",arrearagesTypeS);
                jsonObject.put("amountOfRealPay",map.get("amountOfRealPay"));
                jsonObject.put("amountDept",map.get("amountDept"));
                jsonObject.put("amountPayable",map.get("amountPayable"));
                jsonObject.put("reimbursement",map.get("reimbursement"));
                int isPaidOff  = (int) map.get("isPaidOff");
                String isPaidOffS = "";
                if(isPaidOff==0){
                    isPaidOffS = "是";
                }else{
                    isPaidOffS = "否";
                }
                jsonObject.put("isPaidOff",isPaidOffS);
                jsonArray.add(jsonObject);
            }
        }
        extJsResult.setData(jsonArray);
        return extJsResult;

    }
    public Map<String,Object> getArreagesAmount(Long storeId) {
        Map parameters = new HashMap();
        parameters.put("storeId", storeId);
        return this.getSqlHelper().getSqlSession().selectOne(SQL_GET_ARREARAGES_RECORD, parameters);
    }



    protected final static String SQL_GET_ARREARAGES_RECORD = "com.hy.salon.basic.dao.GET_ARREARAGES_RECORD";


}
