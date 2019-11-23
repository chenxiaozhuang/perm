package prior.prior.RoleManager.Service;

import prior.prior.RoleManager.Entity.Role;

import java.util.List;


public interface IRoleService {

	public List<Role> findAll();

	public List<Role> findByPage(int start, int end);

	public int count();
}
