package prior.prior.RoleManager.Service.impl;

import org.springframework.stereotype.Service;
import prior.prior.RoleManager.Dao.IRoleMapper;
import prior.prior.RoleManager.Entity.Role;
import prior.prior.RoleManager.Service.IRoleService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

	@Resource
	IRoleMapper roleMapper;
	
	public List<Role> findAll() {
		return roleMapper.selectAll();
	}

	public List<Role> findByPage(int start, int end) {
		// TODO Auto-generated method stub
		return roleMapper.findByPage(start,end);
	}
	/**
	 * 获取总记录数据
	 */
	public int count() {
		return roleMapper.selectAll().size();
	}

}
