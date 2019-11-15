package com.demo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.dao.IRoleMapper;
import com.demo.entity.Role;
import com.demo.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Resource
	IRoleMapper roleMapper;
	
	public List<Role> findAll() {
		return roleMapper.selectAll();
	}

	public List<Role> findByPage(int start, int end) {
		// TODO Auto-generated method stub
		return roleMapper.findByPage(start,end);
	}

	/**
	 * 获取总记录数据
	 */
	public int count() {
		return roleMapper.selectAll().size();
	}

}
