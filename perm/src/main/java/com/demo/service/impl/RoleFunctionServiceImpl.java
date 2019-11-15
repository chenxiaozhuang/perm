package com.demo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.dao.IRoleFunctionMapper;
import com.demo.entity.Function;
import com.demo.entity.RoleFunction;
import com.demo.service.IRoleFunctionService;
@Service
public class RoleFunctionServiceImpl implements IRoleFunctionService {
	@Resource
	IRoleFunctionMapper roleFunctionMapper;
	public List<Function> findFunctionByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return roleFunctionMapper.findFunctionByRoleId(roleId);
	}
	public int deleteByRoleId(int roleId) {
		return roleFunctionMapper.deleteByRoleId(roleId);
	}
	public int save(RoleFunction rf) {
		// TODO Auto-generated method stub
		return roleFunctionMapper.insert(rf);
	}
	public List<RoleFunction> findRoleByFuncId(int funcId) {
		// TODO Auto-generated method stub
		return roleFunctionMapper.findRoleByFuncId(funcId);
	}

}
