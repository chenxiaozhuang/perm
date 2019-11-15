package com.demo.service;

import java.util.List;

import com.demo.MenuVo;
import com.demo.entity.Function;

public interface IMenuService {

	public List<MenuVo> findMenu(int userId);

	public List<Function> findAllFunction();
}
