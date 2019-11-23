package prior.prior.perms.Service;

import prior.prior.Menu.Entity.Function;
import prior.prior.perms.Entity.RoleFunction;

import java.util.List;

public interface IRoleFunctionService {

	public List<Function> findFunctionByRoleId(Integer roleId);

	public int deleteByRoleId(int parseInt);

	public int save(RoleFunction rf);

	public List<RoleFunction> findRoleByFuncId(int parseInt);
}
