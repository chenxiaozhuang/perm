package com.demo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.dao.IUserRoleMapper;
import com.demo.entity.UserRole;
import com.demo.service.IUserRoleService;

@Service
public class UserRoleServiceImpl implements IUserRoleService {

	@Resource
	IUserRoleMapper userRoleMapper;
	
	public List<UserRole> findByUserId(Integer userId) {
		return userRoleMapper.findByUserId(userId);
	}

	public int deleteByUserId(int userId) {
		return userRoleMapper.deleteByUserId(userId);
	}

	public int insertUserRole(UserRole ur) {
		return userRoleMapper.insert(ur);
	}

}
