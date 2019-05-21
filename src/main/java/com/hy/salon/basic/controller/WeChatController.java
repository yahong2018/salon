package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.service.MemberService;
import com.hy.salon.basic.util.GaoDeUtil;
import com.hy.salon.basic.util.UuidUtils;
import com.hy.salon.basic.vo.Result;
import com.zhxh.admin.dao.RoleUserDAO;
import com.zhxh.admin.dao.SystemRoleDAO;
import com.zhxh.admin.dao.SystemUserDAO;
import com.zhxh.admin.entity.RoleUser;
import com.zhxh.admin.entity.SystemRole;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.utils.StringUtilsExt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/hy/basic/weChat")
@Api(value = "WeChatController| 员工总结控制器")
public class WeChatController {

    @Resource(name = "salonDao")
    private SalonDao salonDao;

    @Resource(name = "memberDao")
    private MemberDao memberDao;

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

    @Resource(name = "memberTagDao")
    private MemberTagDao memberTagDao;

    @Resource(name = "picturesDao")
    private PicturesDAO picturesDao;

    @Resource(name = "systemUserDAO")
    private SystemUserDAO systemUserDAO;

    @Resource(name = "roleUserDAO")
    private RoleUserDAO roleUserDao;

    @Resource(name = "systemRoleDAO")
    private SystemRoleDAO systemRoleDAO;

    @Resource(name = "memberWalletDAO")
    private MemberWalletDAO MemberWalletDao;

    @Resource(name= "verificationCodeTemporaryDao")
    private  VerificationCodeTemporaryDAO verificationCodeTemporaryDAO;





    /**
     * 获取附件门店
     */
    @ResponseBody
    @RequestMapping(value = "getSalon")
    @ApiOperation(value="获取附件门店", notes="获取附件门店")
    public Result getSalon( Double longitude, Double latitude,Integer distance){
        Result result=new Result();
        try {
        List<Salon> salon=salonDao.getAll();
        List<Salon> salonList=new ArrayList<>();
        for(Salon s:salon){
            String myAddress = longitude+","+latitude;
            String GAddress = s.getLongitude()+","+s.getLatitude();
            boolean flag = GaoDeUtil.getBooleanAddressForDistance(myAddress,GAddress,distance);
            if(flag){
                salonList.add(s);
            }
        }

            result.setData(salonList);
            result.setMsgcode("0");
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setMsgcode("200");
            result.setSuccess(false);
        }
        System.out.println(JSONObject.toJSONString(result));
        return result;
    }






    /**
     * 小程序注册会员
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addMember")
    @ApiOperation(value="小程序注册会员", notes="小程序注册会员")
    public Result addMember(Member condition,String verificationCode) {

        Result result = new Result();
        try {
                //检查该手机号是否被使用
                SystemUser user2=systemUserDAO.getUserByCode(condition.getTel());
                if(null != user2){
                    result.setMsg("该账号已被使用");
                    result.setSuccess(false);
                    result.setMsgcode(StatusUtil.ERROR);
                    return result;
                }

            List<VerificationCodeTemporary> verificationCodeTemporaryList=verificationCodeTemporaryDAO.getCode(condition.getTel());
            if(verificationCodeTemporaryList.size()!=0){
                VerificationCodeTemporary verificationCodeTemporary=verificationCodeTemporaryList.get(verificationCodeTemporaryList.size()-1);
                if(!verificationCodeTemporary.getVerificationCode().equals(verificationCode)){
                    result.setMsg("验证码错误");
                    result.setSuccess(false);
                    result.setMsgcode(StatusUtil.ERROR);
                    return result;
                }

            }



                if(null!=condition.getInvitationCode() && !"".equals(condition.getInvitationCode())){
                    Member parentMember=memberDao.getMemberForInvitationCode(condition.getInvitationCode());
                    condition.setParentId(parentMember.getRecordId());
                }else{
                    String invitationCode= UuidUtils.generateShortUuid();
                    condition.setInvitationCode(invitationCode);
                }


                memberDao.insert(condition);

                //创建钱包
                MemberWallet memberWallet=new MemberWallet();
                memberWallet.setMemberId(condition.getRecordId());
                memberWallet.setBalanceCash(new Double(0));
                memberWallet.setBalanceTotal(new Double(0));
                memberWallet.setIntegral(new Double(0));
                memberWallet.setDebt(new Double(0));
                memberWallet.setAmountCharge(new Double(0));
                memberWallet.setAmountConsumer(new Double(0));
                memberWallet.setCashCoupon(new Double(0));
                MemberWalletDao.insert(memberWallet);

                SystemUser userController=new SystemUser();
                userController.setUserCode(condition.getTel());
                userController.setUserName(condition.getMemberName());
                String passwordMd5 = StringUtilsExt.getMd5("123456");
                userController.setPassword(passwordMd5);
                userController.setUserStatus(0);
                systemUserDAO.insert(userController);

                SystemRole systemRole=systemRoleDAO.getRole("member");

                RoleUser roleUser=new RoleUser();
                roleUser.setRoleId(systemRole.getRecordId());
                roleUser.setUserId(userController.getRecordId());
                roleUserDao.insert(roleUser);



            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }

}
