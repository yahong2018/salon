package com.zhxh.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import com.zhxh.admin.entity.RolePrivilege;
import com.zhxh.admin.service.SystemRoleService;
import com.zhxh.admin.vo.ProgramPrivilegeVO;
import com.zhxh.admin.vo.SystemMenuWithPrivilege;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhxh.admin.entity.SystemRole;

@Controller
@RequestMapping("/admin/systemRoles")
public class SystemRoleController {
	@Resource(name="systemRoleService")
	private SystemRoleService systemRoleService;
	
	@RequestMapping("getAll.handler")
	@ResponseBody
	public List<SystemRole> getAll(){
		return systemRoleService.getAll();
	}

	@RequestMapping("getRolePrivileges.handler")
	@ResponseBody
	public List<RolePrivilege> getRolePrivileges(Long roleId){
		return  systemRoleService.getRolePrivileges(roleId);
	}

	@RequestMapping("getAllMenuWithPrivilege.handler")
	@ResponseBody
	public List<SystemMenuWithPrivilege> getAllMenuWithPrivilege(){
		return systemRoleService.getSystemMenuWithPrivilege();
	}

	@RequestMapping("getRoleMenuWithPrivilege.handler")
	@ResponseBody
	public List<SystemMenuWithPrivilege> getRoleMenuWithPrivilege(Long roleId){
		return systemRoleService.getRoleMenuWithPrivilege(roleId);
	}

	@RequestMapping("insert.handler")
	@ResponseBody
	public SystemRole insert(SystemRole role){
		systemRoleService.insert(role);

		return role;
	}

	@RequestMapping("update.handler")
	@ResponseBody
	public SystemRole update(SystemRole role)  {
		systemRoleService.update(role);
		return role;
	}

	@RequestMapping("delete.handler")
	@ResponseBody
	public int delete(@RequestBody Long[] roleIdList) {
		return systemRoleService.delete(roleIdList);
	}

	@RequestMapping("updateRolePrivileges.handler")
	@ResponseBody
	public int updateRolePrivileges(Long roleId,@RequestBody ProgramPrivilegeVO[] privilegeList) {
		systemRoleService.updatePrivileges(roleId,privilegeList);
		return 0;
	}
}
