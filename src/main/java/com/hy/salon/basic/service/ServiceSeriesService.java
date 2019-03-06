package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.ServiceSeriesDAO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("serviceSeriesService")
public class ServiceSeriesService {

    @Resource( name= "serviceSeriesDao")
    private ServiceSeriesDAO serviceSeriesDao;








}
