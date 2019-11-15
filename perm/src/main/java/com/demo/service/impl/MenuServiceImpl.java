package com.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.MenuVo;
import com.demo.dao.IFunctionMapper;
import com.demo.entity.Function;
import com.demo.service.IMenuService;

@Service
public class MenuServiceImpl implements IMenuService {

	@Resource
	private IFunctionMapper functionMapper;
	public List<MenuVo> findMenu(int userId) {
		List<MenuVo> menus = new ArrayList<MenuVo>();
		List<Function> functions = functionMapper.findMenu(userId);
		if(functions != null && functions.size() > 0){
			for(Function f : functions){
				MenuVo mv = new MenuVo();
				mv.setId(f.getFuncId().toString());
				mv.setName(f.getFuncName());
				mv.setUrl(f.getFuncUrl());
				if(f.getParentId() != null)
				{
					mv.setpId(f.getParentId().toString());
					mv.setOpen(false);
				}else{
					mv.setOpen(true);
				}
				mv.setChecked(false);
				menus.add(mv);
			}
		}
		return menus;
	}
	/**
	 * 查询所有的菜单
	 */
	public List<Function> findAllFunction() {
		return functionMapper.selectAll();
	}

}
