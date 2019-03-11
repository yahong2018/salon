package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.SalonDao;
import com.hy.salon.basic.dao.ScheduleDao;
import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.vo.NurseLogVo;
import com.hy.salon.basic.vo.ShiftVo;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("scheduleService")
public class ScheduleService {
    @Resource(name = "scheduleDao")
    private ScheduleDao scheduleDao;

    public List<ShiftVo> getScheduleByTime(Long recordId, String timeStart, String timeEnd) {
        return scheduleDao.getScheduleByTime(recordId,timeStart,timeEnd);
    }
}
