package com.hy.salon.basic.dao;

import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.entity.Member;
import com.hy.salon.basic.entity.MemberSalonTag;
import com.hy.salon.basic.entity.ProductSeries;
import com.hy.salon.basic.vo.MemberVo;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component("memberDao")
public class MemberDao extends BaseDAOWithEntity<Member> {


//    public List<Member> getMember(Long salonId,String filterExpr){
//        String startTime="";
//        String endTime="";
//        String defaultStartDate="";
////        String ="";
//        String where="";
//        if(filterExpr!=null){
//        JSONObject json=JSONObject.parseObject(filterExpr);
//            String birthday=json.getString("birthday");
//            if(!"".equals(birthday) && !"1".equals(birthday)){
//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                //获取当前月第一天：
//                Calendar c = Calendar.getInstance();
//                c.add(Calendar.MONTH, 0);
//                c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
//                String first = format.format(c.getTime());
//                startTime=first;
//
//                //获取当前月最后一天
//                Calendar ca = Calendar.getInstance();
//                ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
//                String last = format.format(ca.getTime());
//                endTime=last;
//                if("".equals(where)){
//                    where=" birthday > #{startTime} and birthday < #{endTime}";
//                }else{
//                    where=where+" and birthday > #{startTime} and birthday < #{endTime}";
//                }
//
//            }
//            String earlyWarning=json.getString("earlyWarning");
//            if(!"".equals(earlyWarning)){
//                if("0".equals(earlyWarning)){
//
//                    Date dNow = new Date();   //当前时间
//                    Date dBefore = new Date();
//                    Calendar calendar = Calendar.getInstance(); //得到日历
//                    calendar.setTime(dNow);//把当前时间赋给日历
//                    calendar.add(Calendar.MONTH, -1);  //设置为前3月
//                    dBefore = calendar.getTime();   //得到前3月的时间
//                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
//                    defaultStartDate = sdf.format(dBefore);    //格式化前3月的时间
//
//
//                }else if("1".equals(earlyWarning)){
//                    Date dNow = new Date();   //当前时间
//                    Date dBefore = new Date();
//                    Calendar calendar = Calendar.getInstance(); //得到日历
//                    calendar.setTime(dNow);//把当前时间赋给日历
//                    calendar.add(Calendar.MONTH, -3);  //设置为前3月
//                    dBefore = calendar.getTime();   //得到前3月的时间
//                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
//                    defaultStartDate = sdf.format(dBefore);    //格式化前3月的时间
//
//                }
//                if("".equals(where)){
//                    where=" entry_time < #{defaultStartDate} ";
//                }else{
//                    where=where+" and entry_time < #{defaultStartDate} ";
//                }
//
//
//            }
//            String tag=json.getString("tag");
//            String memberGradeId=json.getString("memberGradeId");
//
//            if("".equals(where)){
//                where=" initial_store_id=#{salonId} ";
//            }else{
//                where=where+" and initial_store_id=#{salonId} ";
//            }
//
//            Map parameters = new HashMap();
//            parameters.put("salonId", salonId);
//            return this.getByWhere(where,parameters);
//
//        }else{
//            String where2="initial_store_id=#{salonId}";
//            Map parameters = new HashMap();
//            parameters.put("salonId", salonId);
//
//            return this.getByWhere(where2,parameters);
//        }
//
//
//
//    }



    public List<MemberVo> getMember(Long salonId, String filterExpr,int jobLevel){
        String startTime="";
        String endTime="";
        String defaultStartDate="";
        String memberGradeId="";
        String tag="";
        if(filterExpr!=null){
            JSONObject json=JSONObject.parseObject(filterExpr);
            String birthday=json.getString("birthday");
            if(!"".equals(birthday) && !"1".equals(birthday)){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                //获取当前月第一天：
                Calendar c = Calendar.getInstance();
                c.add(Calendar.MONTH, 0);
                c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
                String first = format.format(c.getTime());
                startTime=first;

                //获取当前月最后一天
                Calendar ca = Calendar.getInstance();
                ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
                String last = format.format(ca.getTime());
                endTime=last;

            }
            String earlyWarning=json.getString("earlyWarning");
            if(!"".equals(earlyWarning)){
                if("0".equals(earlyWarning)){

                    Date dNow = new Date();   //当前时间
                    Date dBefore = new Date();
                    Calendar calendar = Calendar.getInstance(); //得到日历
                    calendar.setTime(dNow);//把当前时间赋给日历
                    calendar.add(Calendar.MONTH, -1);  //设置为前3月
                    dBefore = calendar.getTime();   //得到前3月的时间
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
                    defaultStartDate = sdf.format(dBefore);    //格式化前3月的时间


                }else if("1".equals(earlyWarning)){
                    Date dNow = new Date();   //当前时间
                    Date dBefore = new Date();
                    Calendar calendar = Calendar.getInstance(); //得到日历
                    calendar.setTime(dNow);//把当前时间赋给日历
                    calendar.add(Calendar.MONTH, -3);  //设置为前3月
                    dBefore = calendar.getTime();   //得到前3月的时间
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
                    defaultStartDate = sdf.format(dBefore);    //格式化前3月的时间

                }

            }

            memberGradeId=json.getString("memberGradeId");


            tag=json.getString("tag");




        }

        Map parameters = new HashMap();
        parameters.put("salonId", salonId);
        if(startTime.equals("")){
            parameters.put("startTime", null);
        }else{
            parameters.put("startTime", startTime);
        }
        parameters.put("endTime", endTime);
        parameters.put("defaultStartDate", defaultStartDate);
        if(defaultStartDate.equals("")){
            parameters.put("defaultStartDate", null);
        }else{
            parameters.put("defaultStartDate", defaultStartDate);
        }

        if(memberGradeId.equals("")){
            parameters.put("memberGradeId", null);
        }else{
            parameters.put("memberGradeId", memberGradeId);
        }

        if(tag.equals("")){
            parameters.put("tag", null);
        }else{
            parameters.put("tag", tag);
        }
        if(jobLevel==0){
            return this.getSqlHelper().getSqlSession().selectList(SQL_QUERY_MEMBER_FOR_SALON2, parameters);
        }else{
            return this.getSqlHelper().getSqlSession().selectList(SQL_QUERY_MEMBER_FOR_SALON, parameters);
        }


    }

    public Member getMemberForId(Long recordId){
        String where = "record_id=#{recordId}";
        Map parameters = new HashMap();
        parameters.put("recordId", recordId);
        return this.getOne(where, parameters);
    }


    public List<Member> getMemberForTime(String startTime,String endTime,Long storeId){

        String where ="";

        if(null!=storeId){
            where = where+"initial_store_id = #{storeId} ";
        }
        if(null!=startTime){
            if(!"".equals(where)){
                where =where+" and ";
            }
            where = where+" create_date > #{startTime} and create_date < #{endTime}";
        }



        Map parameters = new HashMap();
        parameters.put("storeId", storeId);
        parameters.put("startTime", startTime);
        parameters.put("endTime", endTime);
        return this.getByWhere(where, parameters);
    }





    protected final static String SQL_QUERY_MEMBER_FOR_SALON2 = "com.hy.salon.basic.dao.QUERY_MEMBER_FOR_SALON2";

    protected final static String SQL_QUERY_MEMBER_FOR_SALON = "com.hy.salon.basic.dao.QUERY_MEMBER_FOR_SALON";



}
