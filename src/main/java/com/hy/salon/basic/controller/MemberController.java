package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.service.MemberService;
import com.hy.salon.basic.vo.MemberVo;
import com.hy.salon.basic.vo.Result;
import com.zhxh.admin.dao.RoleUserDAO;
import com.zhxh.admin.dao.SystemRoleDAO;
import com.zhxh.admin.dao.SystemUserDAO;
import com.zhxh.admin.entity.RoleUser;
import com.zhxh.admin.entity.SystemRole;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.utils.StringUtilsExt;
import com.zhxh.core.web.SimpleCRUDController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/hy/basic/member")
@Api(value = "MemberController| 档案控制器")
public class MemberController extends SimpleCRUDController<Member> {
    @Resource(name = "memberService")
    private MemberService memberService;

    @Resource(name = "memberDao")
    private MemberDao memberDao;

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;
    @Resource(name = "salonDao")
    private SalonDao salonDao;
    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

    @Resource(name="tagDao")
    private TagDao TagDao;

    @Resource(name="memberSalonTagDAO")
    private MemberSalonTagDAO memberSalonTagDAO;

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




    @Override
    protected BaseDAOWithEntity<Member> getCrudDao() {
        return memberDao;
    }
    /**
     * 获得档案信息
     * initialStoreId 0院长  其他传们店id
     */
    @ResponseBody
    @RequestMapping(value = "getArchives",method = RequestMethod.GET)
    @ApiOperation(value="获得档案信息", notes="获得档案信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "initialStoreId", value = "0院长  其他传门店id", required = true, dataType = "Long")
    })
    public Result getArchives(Long initialStoreId) {
        Result result = new Result();
        try {
            List<Member> list = memberService.getArchives(initialStoreId);
            result.setData(list);
            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * 获得档案信息 pc
     */
    @ResponseBody
    @RequestMapping(value = "getArchivespc",method = RequestMethod.GET)
    public Result getArchivespc(String page, String limit, String member_name, String tel) {
        SystemUser user = authenticateService.getCurrentLogin();
        Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
        Result result = new Result();
        try {
            List<Member> list = memberService.getArchivespc(stuff.getStoreId(),page,limit,member_name,tel);
            result.setTotal(memberDao.getPageListCount(new HashMap()));
            result.setData(list);
            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 获得会员档案
     * @param recordId 会员id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getCustomerFiles",method = RequestMethod.GET)
    @ApiOperation(value="获得会员档案", notes="获得会员档案")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "会员id", required = true, dataType = "Long")
    })
    public Result getCustomerFiles(Long recordId) {
        Result result = new Result();
        try {
            Member member = memberService.getCustomerFiles(recordId);
            result.setData(member);
            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 添加/更新档案
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addMember")
    @ApiOperation(value="添加/更新档案", notes="添加/更新档案")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "initialStoreId", value = "门店id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "memberName", value = "姓名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "tel", value = "电话", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "gender", value = "性别", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "weixin", value = "微信", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "birthday", value = "生日", required = true, dataType = "Date"),
            @ApiImplicitParam(paramType="query", name = "remarkOfMenses", value = "备注", required = true, dataType = "String")
    })
    public Result addMember(Member condition,String comeFrom,HttpServletRequest request,Long tagId,String picIdList) {

        if("PC".equals(comeFrom)){
            String  vs =  request.getParameter("condition");
            condition =  JSONObject.parseObject(vs, Member.class);
        }
        Result result = new Result();
        try {
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());

            if(condition.getRecordId()==null){
                condition.setInitialStoreId(stuff.getStoreId());
                condition.setBalanceCash(new Double(0));
                condition.setBalanceTotal(new Double(0));
                condition.setIntegral(new Double(0));
                condition.setDebt(new Double(0));
                condition.setAmountCharge(new Double(0));
                condition.setAmountConsumer(new Double(0));
                memberDao.insert(condition);
                MemberTag tag=new MemberTag();
                tag.setMemberId(condition.getRecordId());
                tag.setTagId(tagId);
                memberTagDao.insert(tag);


                SystemUser userController=new SystemUser();
                userController.setUserCode(condition.getTel());
                userController.setUserName(condition.getMemberName());
                String passwordMd5 = StringUtilsExt.getMd5("123456");
                userController.setPassword(passwordMd5);
                userController.setUserStatus(1);
                systemUserDAO.insert(userController);


                SystemRole systemRole=systemRoleDAO.getRole("member");

                RoleUser roleUser=new RoleUser();
                roleUser.setRoleId(systemRole.getRecordId());
                roleUser.setUserId(userController.getRecordId());
                roleUserDao.insert(roleUser);

                //插入照片关联
                if(null!=picIdList && !"".equals(picIdList)){
                    String[] str = picIdList.split(",");
                    for(String s:str){
                        Pictures pic= picturesDao.getPicForRecordId(Long.parseLong(s));
                        if(null != pic){
                            pic.setMasterDataId(condition.getRecordId());
                            picturesDao.update(pic);
                        }
                    }
                }

            }else{
                memberDao.update(condition);
            }
            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }


    /**
     * 档案顾客添加标签
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "binTag")
    @ApiOperation(value="档案顾客添加标签", notes="档案顾客添加标签")
    public Result binTag(String binList,Long tagId) {
        Result result = new Result();
        try {
            if(null!=binList && !"".equals(binList)){
                //插入照片关联
                String[] str = binList.split(",");
                for(String s:str){
                    MemberTag tag=memberTagDao.getMemberTag(Long.parseLong(s));
                    if(null != tag){
                        tag.setTagId(tagId);
                        memberTagDao.update(tag);
                    }else{
                        tag.setTagId(tagId);
                        tag.setMemberId(Long.parseLong(s));
                        memberTagDao.insert(tag);
                    }
                }
            }

            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * 删除顾客
     */
    @ResponseBody
    @RequestMapping(value = "deleteMember",method = RequestMethod.POST)
    public Result deleteMember(@RequestBody String[] ids) {
        Result result = new Result();
        try {
            for (String id : ids) {
                memberDao.deleteById(id);
            }
            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * 顾客标签
     */
    @ResponseBody
    @RequestMapping(value = "getMemberTag",method = RequestMethod.GET)
    public Result getMemberTag(Long recordId) {

        SystemUser user = authenticateService.getCurrentLogin();
        Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
        Result result = new Result();
        try {
            List<Member> list = memberService.getMemberTag(stuff.getStoreId());
            result.setData(list);
            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }


    /**
     * 顾客标签列表
     */
    @ResponseBody
    @RequestMapping("getTag")
    public Result getTag(int page,String  limit,Long recordId) {
        Result result = new Result();

        try {
            if(null == recordId || 0== recordId){
                SystemUser user = authenticateService.getCurrentLogin();
                Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
                recordId=stuff.getStoreId();
            }

            result.setTotal(memberSalonTagDAO.getTag(recordId).size());
            if(null==limit){
                PageHelper.startPage(page, 50);
            }else{
                PageHelper.startPage(page, Integer.parseInt(limit));
            }

            List<Tag> tagList=memberSalonTagDAO.getTag(recordId);
            for(Tag t:tagList){
                List<MemberTag> memberTagList=memberTagDao.getMemberTagList(t.getRecordId());
                t.setMemberSize(memberTagList.size());
            }
            result.setData(tagList);
            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }


    /**
     * 添加顾客标签
     */
    @RequestMapping("addTag")
    @ResponseBody
    public Result addTag(Tag condition) {
        Result r= new Result();
        try {
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
        TagDao.insert(condition);

        MemberSalonTag menberTag=new MemberSalonTag();
        menberTag.setSalonId(stuff.getStoreId());
        menberTag.setTagId(condition.getRecordId());
        memberSalonTagDAO.insert(menberTag);

        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            r.setMsgcode(StatusUtil.ERROR);
            r.setSuccess(false);
        }
        return r;


    }


    /**
     * 顾客标签删除
     */
    @RequestMapping("batchDelete")
    @ResponseBody
    public Result batchDelete(@RequestBody Long[] recordIdList) {
        Result r= new Result();
        for(Long recordId:recordIdList){
            TagDao.deleteById(recordId);

            List<MemberSalonTag> tagList= memberSalonTagDAO.getMemberTag(recordId);
            if(!tagList.isEmpty()){
                for(MemberSalonTag m:tagList){
                    memberSalonTagDAO.delete(m);
                }
            }
        }
        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        return r;


    }

    /**
     * 档案
     */
    @ResponseBody
    @RequestMapping("getMember")
    public Result getMember(int page, HttpServletRequest request,int limit,int jobLevel) {
        Result result = new Result();

        try {

            String filterExpr   = request.getParameter("filterExpr");

            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
            List<MemberVo> memberList=new ArrayList<>();

                result.setTotal(memberDao.getMember(stuff.getStoreId(),filterExpr,jobLevel).size());
                if(limit==-1){
                    memberList=memberDao.getMember(stuff.getStoreId(),filterExpr,jobLevel);
                }else{
                    PageHelper.startPage(page, limit);
                    memberList=memberDao.getMember(stuff.getStoreId(),filterExpr,jobLevel);
                }



            result.setData(memberList);
            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/deleteMemberForPc")
    @ApiOperation(value="删除档案以及其绑定的项目", notes="以id删除档案已经其绑定的项目")
    public Result deleteMemberForPc(@RequestBody Long[] userIdList){
        Result r= new Result();
        for(Long recordId:userIdList) {
            try {
                memberDao.deleteById(recordId);

                //删除绑定的项目
                MemberTag m=memberTagDao.getMemberTag(recordId);
                memberTagDao.deleteById(m);

                r.setMsg("删除成功");
                r.setSuccess(true);
                r.setMsgcode(StatusUtil.OK);
            }catch (Exception e){
                e.printStackTrace();
                r.setSuccess(false);
                r.setMsgcode(StatusUtil.ERROR);
            }
        }

        return r;
    }

}
