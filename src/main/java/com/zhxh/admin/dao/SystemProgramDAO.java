package com.zhxh.admin.dao;

import com.zhxh.admin.entity.ProgramPrivilege;
import com.zhxh.admin.entity.SystemProgram;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component("systemProgramDAO")
public class SystemProgramDAO extends BaseDAOWithEntity<SystemProgram> {
    public List<ProgramPrivilege> getProgramPrivileges(String programId) {
        Map parameters = new HashMap();
        parameters.put("programId", programId);

        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_PROGRAM_PRIVILEGES, parameters);
    }

    public List<SystemProgram> getUserProgramMenu(Long userId) {
        Map parameters = new HashMap();
        parameters.put("userId", userId);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_USER_PROGRAM_MENU, parameters);
    }

    public SystemProgram getParent(String programId) {
        Map parameters = new HashMap();
        parameters.put("programId", programId);
        return this.getSqlHelper().getSqlSession().selectOne(SQL_GET_PARENT_PROGRAM, parameters);
    }
    
    public SystemProgram getSystemProgramByUrl(String url) {
		String where = "url = #{url}";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("url",url);
		
		return this.getOne(where, parameters);
	}

    public List<SystemProgram> getAllMenu() {
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_ALL_MENU);
    }
    
    protected final static String SQL_GET_PROGRAM_PRIVILEGES = "com.zhxh.admin.dao.GET_PROGRAM_PRIVILEGES";
    protected final static String SQL_GET_USER_PROGRAM_MENU = "com.zhxh.admin.dao.GET_USER_PROGRAM_MENU";
    protected final static String SQL_GET_PARENT_PROGRAM = "com.zhxh.admin.dao.GET_PARENT_PROGRAM";
    protected final static String SQL_GET_ALL_MENU = "com.zhxh.admin.dao.GET_ALL_MENU";

}
