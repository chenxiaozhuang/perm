package com.demo.service;

import java.util.List;

import com.demo.entity.User;

public interface IUserService {

	public User login(String username,String password);
	
	public List<User> findByPage(int offset,int pageSize);
	
	public int count();

	public int insert(User user);

	public int deleteById(Integer id);
	
	public int updateStatusById(Integer id,int status);

	public User findById(Integer id);

	public int updateUser(User user);
}