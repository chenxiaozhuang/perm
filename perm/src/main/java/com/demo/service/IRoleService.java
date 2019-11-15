package com.demo.service;

import java.util.List;

import com.demo.entity.Role;

public interface IRoleService {

	public List<Role> findAll();

	public List<Role> findByPage(int start, int end);

	public int count();
}
