package com.demo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.dao.IUserMapper;
import com.demo.entity.User;
import com.demo.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserMapper userMapper;
	
	public User login(String username, String password) {
		return userMapper.findByName(username,password);
	}

	public List<User> findByPage(int offset, int pageSize) {
		return userMapper.findByPage(offset,pageSize);
	}

	public int count() {
		return userMapper.selectAll().size();
	}

	public int insert(User user) {
		return userMapper.insert(user);
	}

	public int deleteById(Integer id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	public int updateStatusById(Integer id,int status) {
		User user = new User();
		user.setUserId(id);
		user.setStatus(status);
		return userMapper.updateStatusById(user);
	}

	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}

	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKey(user);
	}

}
