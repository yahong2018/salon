package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.AttendanceSheetDAO;
import com.hy.salon.basic.entity.AttendanceSheet;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("attendanceSheetService")
public class AttendanceSheetService {

    @Resource(name = "attendanceSheetDao")
    private AttendanceSheetDAO attendanceSheetDao;

    public int insert(AttendanceSheet condition){
        return attendanceSheetDao.insert(condition);
    }


}
