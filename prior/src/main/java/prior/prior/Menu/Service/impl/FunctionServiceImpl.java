package prior.prior.Menu.Service.impl;

import org.springframework.stereotype.Service;
import prior.prior.Menu.Dao.IFunctionMapper;
import prior.prior.Menu.Entity.Function;
import prior.prior.Menu.Service.IFunctionService;

import javax.annotation.Resource;
import java.util.List;

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

	public List<Function> findTreeByParentId(int id) {
		// TODO Auto-generated method stub
		return functionMapper.findTreeByParentId(id);
	}

	public int updateFunction(Function function) {
		return functionMapper.updateByPrimaryKey(function);
	}

}
