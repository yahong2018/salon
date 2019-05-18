package com.hy.salon.basic.controller;

import com.github.pagehelper.PageHelper;
import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.vo.Result;
import com.zhxh.admin.dao.RoleUserDAO;
import com.zhxh.admin.entity.RoleUser;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.web.ExtJsResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hy.salon.stock.entity.ProductStock;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hy/basic/memberProductKeep")
@Api(value = "MemberProductKeepController|寄存库控制层")
public class MemberProductKeepController {


    @Resource(name="memberProductKeepDao")
    private MemberProductKeepDao memberProductKeepDao;

    @Resource(name="memberProductKeepItemDao")
    private MemberProductKeepItemDao memberProductKeepItemDao;


    @Resource(name="memberProductRejectDao")
    private MemberProductRejectDao memberProductRejectDao;

    @Resource(name="memberProductRejectItemDao")
    private MemberProductRejectItemDao memberProductRejectItemDao;

    @Resource(name="memberProductGetRecordDao")
    private MemberProductGetRecordDao memberProductGetRecordDao;
    @Resource(name = "memberDao")
    private MemberDao memberDao;
    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;
    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

    @Resource(name = "memberWalletDAO")
    private MemberWalletDAO MemberWalletDao;

    @Resource(name="businessStuffDao")
    private  BusinessStuffDao businessStuffDao;

    @Resource(name="productStockDAO")
    private ProductStockDAO  ProductStockDao;

    @Resource(name = "roleUserDAO")
    private RoleUserDAO roleUserDAO;

    /**
     * 会员退款寄存库产品列表
     */
    @ResponseBody
    @RequestMapping("refundMemberProductKeepList")
    @ApiOperation(value="会员退款寄存库产品列表", notes="会员退款寄存库产品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "memberProductKeepItem", value = "寄存明细表id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "toDays", value = "时间", required = true, dataType = "String")
    })
    public Result refundMemberProductKeepList(Long memberProductKeepItem,Long memberId,String toDays) {
        Result result = new Result();
    /*    Map parameterP = new HashMap();
        parameterP.put("memberProductKeepItem", memberProductKeepItem);
        String rwhereP="member_product_keep_item=#{memberProductKeepItem} ";*/
        // Result result =    memberProductKeepDao.receiveMemberProductKeepList(storeId,memberId,toDays);
         result = memberProductRejectDao.refundMemberProductKeepList(memberProductKeepItem);


        return  result;
    }
    /**
     * 会员领取寄存库产品列表
     */
    @ResponseBody
    @RequestMapping("receiveMemberProductKeepList")
    @ApiOperation(value="会员领取寄存库产品列表", notes="会员领取寄存库产品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "memberProductKeepItem", value = "寄存明细表id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "toDays", value = "时间", required = true, dataType = "String")
    })
    public Result receiveMemberProductKeepList(Long memberProductKeepItem,Long memberId,String toDays) {
        Result result = new Result();
        Map parameterP = new HashMap();
        parameterP.put("memberProductKeepItem", memberProductKeepItem);
        String rwhereP="member_product_keep_item=#{memberProductKeepItem} ";
       // Result result =    memberProductKeepDao.receiveMemberProductKeepList(storeId,memberId,toDays);
        List<MemberProductGetRecord> list = memberProductGetRecordDao.getByWhere(rwhereP,parameterP);
        result.setTotal(list.size());
        result.setData(list);
        result.setSuccess(true);

        return  result;
    }

    /**
     * 会员领取寄存库产品列表(所有)
     */
    @ResponseBody
    @RequestMapping("queryReceiveMemberProductKeepList")
    @ApiOperation(value="会员领取寄存库产品列表(所有)", notes="会员领取寄存库产品列表(所有)")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "memberProductKeepItem", value = "寄存明细表id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "toDays", value = "时间", required = true, dataType = "String")
    })
    public ExtJsResult queryReceiveMemberProductKeepList(HttpServletRequest request,Long recordId,String toDays) {

        String role="0";
        if(recordId==null){
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff2 = stuffDao.getStuffForUser(user.getRecordId());
            String where="user_id=#{userId}";
            Map parameters = new HashMap();
            parameters.put("userId", user.getRecordId());
            RoleUser roleUser =roleUserDAO.getOne(where,parameters);
            //判断用户角色
            if(roleUser.getRoleId()==1){
                role="1";
            }else if(roleUser.getRoleId()==10){
                role="2";
            }else if(roleUser.getRoleId()==11){
                role="3";
            }

            recordId = stuff2.getStoreId();
        }

        ExtJsResult extJsResult=memberProductGetRecordDao.getProductList(recordId,request,toDays,role);

        return  extJsResult;
    }



    /**
     * 退款记录（PC）
     */
    @ResponseBody
    @RequestMapping("queryProductReject")
    @ApiOperation(value="退款记录（PC）", notes="退款记录（PC）")
    public ExtJsResult queryProductReject(HttpServletRequest request,Long recordId,String toDays) {

        ExtJsResult extJsResult=new ExtJsResult();
        String role="0";
        if(recordId==null){
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff2 = stuffDao.getStuffForUser(user.getRecordId());
            String where="user_id=#{userId}";
            Map parameters = new HashMap();
            parameters.put("userId", user.getRecordId());
            RoleUser roleUser =roleUserDAO.getOne(where,parameters);
            //判断用户角色
            if(roleUser.getRoleId()==1){
                role="1";
            }else if(roleUser.getRoleId()==10){
                role="2";
            }else if(roleUser.getRoleId()==11){
                role="3";
            }

            recordId = stuff2.getStoreId();
        }

        List<Map<String,Object>> productRejectList=memberProductRejectDao.queryProductReject(recordId,toDays,role);
        PageHelper.startPage(Integer.parseInt(request.getParameter("page")),10);
        List<Map<String,Object>> productRejectList2=memberProductRejectDao.queryProductReject(recordId,toDays,role);
        extJsResult.setTotal(productRejectList.size());
        extJsResult.setData(productRejectList2);
        return  extJsResult;
    }


    /**
     * 获取一个门店的会员寄存库产品列表
     */
    @ResponseBody
    @RequestMapping("getStoreMemberProductKeepList")
    @ApiOperation(value="获取会员寄存库产品列表", notes="获取会员寄存库产品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "storeId", value = "店铺id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "memberId", value = "会员id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "toDays", value = "时间", required = true, dataType = "String")
    })
    public Result getStoreMemberProductKeepList(Long storeId, Long memberId, String toDays, HttpServletRequest request) {

        String role="0";
        if(storeId==null){
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff2 = stuffDao.getStuffForUser(user.getRecordId());

            String where="user_id=#{userId}";
            Map parameters = new HashMap();
            parameters.put("userId", user.getRecordId());
            RoleUser roleUser =roleUserDAO.getOne(where,parameters);
            //判断用户角色
            if(roleUser.getRoleId()==1){
                role="1";
            }else if(roleUser.getRoleId()==10){
                role="2";
            }else if(roleUser.getRoleId()==11){
                role="3";
            }

            storeId = stuff2.getStoreId();
        }
        Result result =    memberProductKeepDao.getStoreMemberProductKeepList(storeId,memberId,toDays,request,role);

        return  result;
    }


    /**
     * 获取会员寄存库产品列表
     */
    @ResponseBody
    @RequestMapping("getMemberProductKeepList")
    @ApiOperation(value="获取会员寄存库产品列表", notes="获取会员寄存库产品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "memberId", value = "会员id", required = true, dataType = "Long")
    })
    public Result getMemberProductKeepList(Long memberId) throws ParseException {
        Result result =    memberProductKeepDao.getMemberProductKeepList(memberId);
        return  result;
    }

    /**
     * 领取产品
     */
    @ResponseBody
    @RequestMapping("receiveMemberProductKeep")
    @ApiOperation(value="获取会员寄存库产品列表", notes="获取会员寄存库产品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "memberProductKeepItem", value = "产品寄存明细id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "qty", value = "当前领取数量", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "remark", value = "备注", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "memberSignature", value = "客户签名(系统照片ID)", required = true, dataType = "Long"),
    })
    public Result receiveMemberProductKeep(MemberProductGetRecord memberProductGetRecord,String stuffIdListJson) {
        Result result = new Result();
       long  memberProductKeepItemId = memberProductGetRecord.getMemberProductKeepItem();
        MemberProductKeepItem memberProductKeepItem = memberProductKeepItemDao.getById(memberProductKeepItemId);//获取寄存产品明细

       double QtyPurchased =  memberProductKeepItem.getQtyPurchased();
       double QtyReceived = memberProductKeepItem.getQtyReceived();
        double temp = QtyPurchased - QtyReceived;

        double qty  =  memberProductGetRecord.getQty();
        if(temp>=qty){//说明可以领取
            ProductStock proStock= ProductStockDao.getOneProdectStockForId(memberProductKeepItem.getProductId());
            if(proStock==null || proStock.getStockQty()<1){
                result.setSuccess(false);
                result.setMsgcode("200");
                result.setMsg("仓库库存不足");
            }

            memberProductKeepItem.setQtyReceived(memberProductKeepItem.getQtyReceived()+qty);
        }else{//不能领取
            result.setSuccess(false);
            result.setMsgcode("200");
            result.setMsg("不能领取");
        }


        memberProductGetRecordDao.insert(memberProductGetRecord);

        memberProductKeepItemDao.update(memberProductKeepItem);



        net.sf.json.JSONArray stuffListArr=net.sf.json.JSONArray.fromObject(stuffIdListJson);
        List<Long> stufflist = net.sf.json.JSONArray.toList(stuffListArr, Long.class);// 转换成实体类

        for(Long stuffId:stufflist){
            BusinessStuff businessStuff  = new BusinessStuff();
            businessStuff.setStuffId(stuffId);
            businessStuff.setTransType((byte)0);
            businessStuff.setRefTransId(memberProductGetRecord.getRecordId());
            businessStuffDao.insert(businessStuff);//关联员工
        }

        result.setSuccess(true);
        result.setMsgcode("0");
        result.setMsg("领取成功");

        return  result;

    }

/*    member_id                    bigint                                          not null,

    remark                       varchar(500)                                    null,       -- 备注
    member_signature             bigint                                          not null,   -- 客户签名(系统照片ID)*/

/*    member_product_reject_id     bigint                                          not null,  -- 产品退款Id
    product_keep_item_id         bigint                                          not null,  -- c

    reject_type                  tinyint                                         not null,  -- 0.未领取退款  1.已领取退款
    qty_reject                   double(8,2)                                     not null,  -- 退还数量
    amount_reject                double(10,2)                                    not null,  -- 退还金额
    type_amount_return           tinyint                                         not null,
            -- 资金退还方式: 0.现金  1.余额
        --     如果是以余额的方式，则要修改member的balance_cash字段*/

    /**
     * 退款产品
     */
    @ResponseBody
    @RequestMapping("refundMemberProductKeep")
    @ApiOperation(value="退款产品", notes="退款产品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "memberId", value = "会员id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "remark", value = "备注", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "memberSignature", value = "客户签名", required = true, dataType = "Long"),

            @ApiImplicitParam(paramType="query", name = "rejectType", value = "0.未领取退款  1.已领取退款", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "qtyReject", value = "退还数量", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "productKeepItemId", value = "原购买记录Id(产品寄存明细表id)", required = true, dataType = "long"),
            @ApiImplicitParam(paramType="query", name = "amountReject", value = "退还金额", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "typeAmountReturn", value = "资金退还方式: 0.现金  1.余额\n" +
                    "        --     如果是以余额的方式，则要修改member的balance_cash字段*/", required = true, dataType = "byte"),

    })
    public Result refundMemberProductKeep(MemberProductReject memberProductReject, MemberProductRejectItem memberProductRejectItem,String stuffIdListJson){
        Result result = new Result();


        MemberProductKeepItem memberProductKeepItem = memberProductKeepItemDao.getById(memberProductRejectItem.getProductKeepItemId());
        byte type = memberProductKeepItem.getProductGetType();


//        if(type==1){//赠送
//            result.setSuccess(false);
//            result.setMsgcode("200");
//            result.setMsg("赠送商品不能退款");
//            return  result;
//        }
        memberProductRejectDao.insert(memberProductReject);
        memberProductRejectItem.setMemberProductRejectId(memberProductReject.getRecordId());
        long memberId =  memberProductReject.getMemberId();
        memberProductRejectItemDao.insert(memberProductRejectItem);

        //获取顾客钱包
        MemberWallet memberWallet=MemberWalletDao.getMemberWalletForMemberId(memberId);

        if(memberProductRejectItem.getTypeAmountReturn()==1){
//            member.setBalanceCash(member.getBalanceCash()+memberProductRejectItem.getAmountReject());//如果是以余额的方式，则要修改member的balance_cash字段

            memberWallet.setBalanceCash(memberWallet.getBalanceCash()+memberProductRejectItem.getAmountReject());//如果是以余额的方式，则要修改member的balance_cash字段
            MemberWalletDao.update(memberWallet);
        }


        net.sf.json.JSONArray stuffListArr=net.sf.json.JSONArray.fromObject(stuffIdListJson);
        List<Long> stufflist = net.sf.json.JSONArray.toList(stuffListArr, Long.class);// 转换成实体类

        for(Long stuffId:stufflist){
            BusinessStuff businessStuff  = new BusinessStuff();
            businessStuff.setStuffId(stuffId);
            businessStuff.setTransType((byte)0);
            businessStuff.setRefTransId(memberProductReject.getRecordId());
            businessStuffDao.insert(businessStuff);//关联员工
        }
        memberProductKeepDao.deleteById(memberProductKeepItem.getMemberProductKeepId());
        memberProductKeepItemDao.deleteById(memberProductKeepItem.getRecordId());

        result.setSuccess(true);
        result.setMsgcode("0");
        result.setMsg("领取成功");

        return  result;
    }

}
