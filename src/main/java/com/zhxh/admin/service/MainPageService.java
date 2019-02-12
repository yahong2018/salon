package com.zhxh.admin.service;

import com.zhxh.admin.dao.SystemProgramDAO;
import com.zhxh.admin.entity.SystemProgram;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.vo.SystemMenu;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component("mainPageService")
public class MainPageService {
    @Resource(name="authenticateService")
    private AuthenticateService authenticateService;

    @Resource(name = "systemProgramDAO")
    private SystemProgramDAO systemProgramDAO;

    public List<SystemMenu> getCurrentUserMenu() {
        SystemUser currentUser = authenticateService.getCurrentLogin();
        assert currentUser != null;
        return this.getUserMenu(currentUser.getRecordId());
    }

    public List<SystemMenu> getUserMenu(Long userId) {
        /**
         * 获取用户的系统菜单
         *    1.获取用户的所有角色所有的
         *    2.获取每个角色的菜单
         *    3.合并所有的菜单
         **/
        List<SystemProgram> programList = systemProgramDAO.getUserProgramMenu(userId);
        return programList2MenuList(programList);
    }

    private List<SystemMenu> programList2MenuList(List<SystemProgram> programList) {
        List<SystemMenu> result = new ArrayList<>();
        SystemProgram[] topPrograms = programList.stream()
                .filter(SystemProgram::isTopMenu)
                .sorted(Comparator.comparing(SystemProgram::getShowOrder))
                .toArray(SystemProgram[]::new);

        for (SystemProgram top : topPrograms) {
            SystemMenu menu = menuFromProgram(top);
            result.add(menu);

            this.buildMenu(programList, top, menu);
        }
        return result;
    }

    private SystemMenu menuFromProgram(SystemProgram top) {
        SystemMenu menu = new SystemMenu();
        menu.setProgramId(top.getRecordId());
        menu.setProgramCode(top.getProgramCode());
        menu.setProgramName(top.getProgramName());
        menu.setUrl(top.getUrl());
        menu.setGlyph(top.getGlyph());

        if(menu.isFolder()){
            menu.setExpanded(true);
        }

        return menu;
    }

    private void buildMenu(List<SystemProgram> programList, SystemProgram program, SystemMenu menu) {
        SystemProgram[] parentPrograms = programList.stream()
                .filter(
                        x -> x.getParentId().equals(program.getRecordId()) && (!x.getRecordId().equals(program.getRecordId()))
                ).sorted(Comparator.comparing(SystemProgram::getShowOrder))
                .toArray(SystemProgram[]::new);

        SystemMenu[] children = new SystemMenu[parentPrograms.length];
        menu.setChildren(children);
        for (int i = 0; i < parentPrograms.length; i++) {
            SystemProgram son = parentPrograms[i];
            SystemMenu sonMenu = menuFromProgram(son);
            children[i] = sonMenu;

            this.buildMenu(programList, son, sonMenu);
        }
    }
    
    public List<SystemMenu> getAllMenu() {
        List<SystemProgram> programList = systemProgramDAO.getAllMenu();
        return programList2MenuList(programList);
    }
}
