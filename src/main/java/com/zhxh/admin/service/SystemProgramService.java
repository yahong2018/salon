package com.zhxh.admin.service;

import com.zhxh.admin.dao.SystemProgramDAO;
import com.zhxh.admin.entity.SystemProgram;
import com.zhxh.admin.vo.SystemProgramWithChildren;
import com.zhxh.core.env.SysEnv;
import com.zhxh.core.utils.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Component("systemProgramService")
public class SystemProgramService {
    @Resource(name = "systemProgramDAO")
    SystemProgramDAO systemProgramDAO;

    public boolean isProgramUrl(String url) {
        String pureUrl = url.replace(SysEnv.getAppRoot(), "");
        SystemProgram program = systemProgramDAO.getSystemProgramByUrl(pureUrl);
        return program != null;
    }

    public List<SystemProgram> getAll() {
        return systemProgramDAO.getAll();
    }

    public List getPageList(Map map, Map parameters) {
        return systemProgramDAO.getPageList(map, parameters);
    }

    public int getPageListCount(Map map, Map parameters) {
        return systemProgramDAO.getPageListCount(map, parameters);
    }

    public List<SystemProgramWithChildren> getAllWithChildren() {
        List<SystemProgramWithChildren> result = new ArrayList<>();
        List<SystemProgram> systemProgramsList = systemProgramDAO.getAll();
        SystemProgram[] company = systemProgramsList.stream().filter(x -> x.getRecordId().equals(x.getParentId())).toArray(SystemProgram[]::new);
        for (int a = 0; a < company.length; a++) {

            SystemProgramWithChildren companyWithChildren = new SystemProgramWithChildren();
            BeanUtils.copy(company[a], companyWithChildren);
            SystemProgram[] childrenSystemProgram = systemProgramsList.stream()
                    .filter(SystemProgram::isTopMenu)
                    .sorted(Comparator.comparing(SystemProgram::getShowOrder))
                    .toArray(SystemProgram[]::new);

            SystemProgramWithChildren[] children = new SystemProgramWithChildren[childrenSystemProgram.length];
            companyWithChildren.setChildren(children);

            for (int i = 0; i < children.length; i++) {
                SystemProgram child = childrenSystemProgram[i];
                SystemProgramWithChildren sub = new SystemProgramWithChildren();
                BeanUtils.copy(child, sub);
                this.systemProgram2Tree(sub, systemProgramsList);

                children[i] = sub;
            }

            result.add(companyWithChildren);
        }
        return result;
    }

    private void systemProgram2Tree(SystemProgramWithChildren parent, List<SystemProgram> systemProgramsList) {
        SystemProgram[] childrenSystemProgram = systemProgramsList.stream()
                .filter(
                        x -> x.getParentId().equals(parent.getRecordId()) && (!x.getRecordId().equals(parent.getRecordId()))
                ).sorted(Comparator.comparing(SystemProgram::getShowOrder))
                .toArray(SystemProgram[]::new);
        SystemProgramWithChildren[] children = new SystemProgramWithChildren[childrenSystemProgram.length];
        parent.setChildren(children);
        for (int i = 0; i < children.length; i++) {
            SystemProgram child = childrenSystemProgram[i];
            SystemProgramWithChildren sub = new SystemProgramWithChildren();
            BeanUtils.copy(child, sub);
            children[i] = sub;

            this.systemProgram2Tree(sub, systemProgramsList);
        }
    }
}
