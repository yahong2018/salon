package com.zhxh.admin.service;

import static com.zhxh.core.exception.ErrorCode.ERROR_UNKNOWN_EXCEPTION;
import static com.zhxh.core.exception.ExceptionHelper.throwException;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zhxh.admin.dao.ProgramPrivilegeDAO;
import com.zhxh.admin.dao.RolePrivilegeDAO;
import com.zhxh.admin.entity.ProgramPrivilege;

@Component("programPrivilegeService")
public class ProgramPrivilegeService {
	@Resource(name="programPrivilegeDAO")
	ProgramPrivilegeDAO programPrivilegeDAO;
	
	 @Resource(name = "rolePrivilegeDAO")
    private RolePrivilegeDAO rolePrivilegeDAO;

	@Transactional(rollbackFor = Exception.class)
	public int update(ProgramPrivilege item) {
		return programPrivilegeDAO.update(item);
	}

	@Transactional(rollbackFor = Exception.class)
	public int delete(Object[] ids){
		for (Object id : ids) {
				ProgramPrivilege item = programPrivilegeDAO.getById(id);
				rolePrivilegeDAO.removeRolePrivilegeByProgramIdAndPrivilegeId(item.getProgramId(),item.getPrivilegeCode());
		}
		for (Object id : ids) {
			int result = programPrivilegeDAO.deleteById(id);
			if (result != 1) {
				throwException(ERROR_UNKNOWN_EXCEPTION,  "数据删除失败！");
			}
		}
		
		return ids.length;
	}

	public List getPageListByProgramId(Map map, Map parameters) {
		return programPrivilegeDAO.getPageListByProgramId(map, parameters);
	}

	public int getPageListCountByProgramId(Map map, Map parameters) {
		return programPrivilegeDAO.getPageListCountByProgramId(map, parameters);
	}
}
