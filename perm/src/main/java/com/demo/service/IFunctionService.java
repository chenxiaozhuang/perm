package com.demo.service;

import java.util.List;

import com.demo.entity.Function;

public interface IFunctionService {

	public List<Function> findFunctionList();

	public int insertFunction(Function function);

	public Function findByFuncName(String funcName);

	public List<Function> findbyParentId(int parentId);

	public int deleteByFuncId(int funcId);

	public Function findByFuncId(int id);

	public List<Function> findTreeByParentId(String id);

	public int updateFunction(Function function);
}
