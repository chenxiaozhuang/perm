package com.demo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.dao.IFunctionMapper;
import com.demo.entity.Function;
import com.demo.service.IFunctionService;

@Service
public class FunctionServiceImpl implements IFunctionService {

	@Resource
	IFunctionMapper functionMapper;
	public List<Function> findFunctionList() {
		return functionMapper.selectAll();
	}
	public int insertFunction(Function function) {
		return functionMapper.insert(function);
	}
	public Function findByFuncName(String funcName) {
		return functionMapper.findByFuncName(funcName);
	}
	public List<Function> findbyParentId(int parentId) {
		// TODO Auto-generated method stub
		return functionMapper.findByParentId(parentId);
	}
	public int deleteByFuncId(int funcId) {
		return functionMapper.deleteByPrimaryKey(funcId);
	}
	public Function findByFuncId(int id) {
		return functionMapper.selectByPrimaryKey(id);
	}
	public List<Function> findTreeByParentId(String id) {
		// TODO Auto-generated method stub
		return functionMapper.findTreeByParentId(id);
	}
	public int updateFunction(Function function) {
		return functionMapper.updateByPrimaryKey(function);
	}

}
