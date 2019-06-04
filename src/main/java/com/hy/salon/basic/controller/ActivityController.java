package com.hy.salon.basic.controller;


        import com.hy.salon.basic.service.ActivityDetailInfoService;
        import com.zhxh.core.web.ExtJsResult;
        import io.swagger.annotations.Api;
        import io.swagger.annotations.ApiOperation;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;

        import javax.annotation.Resource;
        import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/activity")
@Api(value = "ActivityController| 活动控制器")
public class ActivityController {


    @Resource(name = "activityDetailInfoService")
    private ActivityDetailInfoService activityDetailInfoService;


    @RequestMapping("/rewardList")
    @ApiOperation(value="赏金排行榜", notes="赏金排行榜")
    public ExtJsResult getRewardListByActivityId(long activityId ,String openId,HttpServletRequest request){
        ExtJsResult extJsResult =   activityDetailInfoService.getRewardListByActivityId(activityId, openId);
        return  extJsResult;
    }


    @RequestMapping("/participantList")
    @ApiOperation(value="参与人列表", notes="参与人列表")
    public ExtJsResult getParticipantList(long activityId ,HttpServletRequest request){
        ExtJsResult extJsResult =   activityDetailInfoService.getParticipantList(activityId);
        return  extJsResult;
    }





}
