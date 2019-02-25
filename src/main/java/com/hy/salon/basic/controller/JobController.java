package com.hy.salon.basic.controller;

import com.hy.salon.basic.dao.JobDAO;
import com.hy.salon.basic.entity.Job;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/hy/basic/job")
public class JobController extends SimpleCRUDController<Job> {

    @Resource(name="jobDAO")
    private JobDAO jobDAO;

    @Override
    protected BaseDAOWithEntity<Job> getCrudDao() {
        return jobDAO;
    }
}
