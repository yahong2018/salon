package com.hy.salon.basic.controller;

import com.hy.salon.basic.dao.TimeSheetDao;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.entity.TimeSheet;
import com.hy.salon.basic.service.StuffService;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/hy/basic/timeSheet")
public class TimeSheetController extends SimpleCRUDController<TimeSheet> {
    @Resource(name = "timeSheetDao")
    private TimeSheetDao timeSheetDao;
    @Override
    protected BaseDAOWithEntity<TimeSheet> getCrudDao() {
        return timeSheetDao;
    }
}
