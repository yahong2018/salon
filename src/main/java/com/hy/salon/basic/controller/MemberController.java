package com.hy.salon.basic.controller;

import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.MemberDao;
import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.entity.Member;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.service.MemberService;
import com.hy.salon.basic.vo.Result;
import com.hy.salon.stock.entity.ProductStock;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.data.BaseDAOWithEntity;
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

    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

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
    public Result getArchivespc(String page,String limit) {
        SystemUser user = authenticateService.getCurrentLogin();
        Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
        Result result = new Result();
        try {
            List<Member> list = memberService.getArchivespc(stuff.getStoreId(),page,limit);
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
     * @param member
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addMember",method = RequestMethod.POST)
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
    public Result addMember(@RequestBody Member member) {
        Result result = new Result();
        try {
            if(member.getRecordId()==null){
                memberService.addMember(member);
            }else{
                memberDao.update(member);
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
}
