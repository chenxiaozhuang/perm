package prior.prior.perms.Service.impl;


import org.springframework.stereotype.Service;
import prior.prior.Menu.Entity.Function;
import prior.prior.perms.Dao.IRoleFunctionMapper;
import prior.prior.perms.Entity.RoleFunction;
import prior.prior.perms.Service.IRoleFunctionService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleFunctionServiceImpl implements IRoleFunctionService {
	@Resource
	IRoleFunctionMapper roleFunctionMapper;
	public List<Function> findFunctionByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return roleFunctionMapper.findFunctionByRoleId(roleId);
	}
	public int deleteByRoleId(int roleId) {
		return roleFunctionMapper.deleteByRoleId(roleId);
	}
	public int save(RoleFunction rf) {
		// TODO Auto-generated method stub
		return roleFunctionMapper.insert(rf);
	}
	public List<RoleFunction> findRoleByFuncId(int funcId) {
		// TODO Auto-generated method stub
		return roleFunctionMapper.findRoleByFuncId(funcId);
	}

}
