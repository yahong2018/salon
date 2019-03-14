package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.ReservationDao;
import com.hy.salon.basic.dao.SalonDao;
import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.dao.WorkSummaryDao;
import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.entity.WorkSummary;
import com.hy.salon.basic.vo.SalonVo;
import com.hy.salon.basic.vo.StuffVo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Transactional(rollbackFor = Exception.class)
@Component("workSummaryService")
public class WorkSummaryService {
    @Resource(name="stuffDao")
    private StuffDao stuffDao;
    @Resource(name="workSummaryDao")
    private WorkSummaryDao workSummaryDao;
    @Resource(name = "reservationDao")
    private ReservationDao reservationDao;
    @Resource(name = "salonDao")
    private SalonDao salonDao;

    public List<StuffVo> getWorkSummaryBySalonId(Long recordId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<StuffVo> list=new ArrayList<>();
        //查询门店下所有的员工
        Map parameters = new HashMap();
        parameters.put("storeId", recordId);
        Map listMap = new HashMap();
        String where="store_id=#{storeId}";
        listMap.put("where", where);
        List<Stuff> stufflist = stuffDao.getList(listMap, parameters);
        for (Stuff stuff : stufflist) {
            StuffVo vo=new StuffVo();
            vo.setRecordId(stuff.getRecordId());
            vo.setStuffName(stuff.getStuffName());
            List<StuffVo> stuffName = reservationDao.getStuffName(stuff.getRecordId());
            for (StuffVo stuffVo : stuffName) {
                vo.setRole(stuffVo.getRole());
            }
            Map parameter = new HashMap();
            parameter.put("stuffId", stuff.getRecordId());
            parameter.put("curMonth",sdf.format(new Date()));
            Map map = new HashMap();
            String wheres="stuff_id=#{stuffId} and cur_month=#{curMonth}";
            map.put("where", wheres);
            List<WorkSummary> workList = workSummaryDao.getList(map, parameter);
            vo.setWorkSummary(workList.size());
            list.add(vo);
        }
        return list;
    }

    public List<SalonVo> getWorkSummary() {
        List<SalonVo> list=new ArrayList<>();
        List<Salon> salonList = salonDao.getSalon();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Salon salon : salonList) {
            SalonVo vo=new SalonVo();
            vo.setRecordId(salon.getRecordId());
            vo.setSalonName(salon.getSalonName());
            //查询门店下所有员工
            Map parameters = new HashMap();
            parameters.put("storeId", salon.getRecordId());
            Map listMap = new HashMap();
            String where="store_id=#{storeId}";
            listMap.put("where", where);
            List<Stuff> stufflist = stuffDao.getList(listMap, parameters);
            vo.setStuffNum(salonList.size());
            int num=0;
            //查询员工当月已写总结数
            for (Stuff stuff : stufflist) {
                Map parameter = new HashMap();
                parameter.put("stuffId", stuff.getRecordId());
                parameter.put("curMonth",sdf.format(new Date()));
                Map map = new HashMap();
                String wheres="stuff_id=#{stuffId} and cur_month=#{curMonth}";
                map.put("where", wheres);
                List<WorkSummary> workList = workSummaryDao.getList(map, parameter);
                num+=workList.size();
            }
            vo.setWorkSummary(num);
            list.add(vo);
        }
        return list;
    }
}
