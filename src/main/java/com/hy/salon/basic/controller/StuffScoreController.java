package com.hy.salon.basic.controller;

import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.StuffScoreRecordDAO;
import com.hy.salon.basic.entity.StuffScoreRecord;
import com.hy.salon.basic.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/hy/basic/stuffScore")
@Api(value = "StuffScoreController | 积分控制器")
public class StuffScoreController {
    @Resource(name = "stuffScoreRecordDao")
    private StuffScoreRecordDAO stuffScoreRecordDao;


    @ResponseBody
    @RequestMapping("addScoreRecord")
    @ApiOperation(value="院长给员工添加积分", notes="院长给员工添加积分")
    public Result queryAllProductProperty(StuffScoreRecord condition){
        Result r= new Result();
        try {
            stuffScoreRecordDao.insert(condition);
            r.setMsg("添加成功");
            r.setMsgcode("0");
            r.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
            r.setMsg("添加失败");
        }
        return r;


    }


}
