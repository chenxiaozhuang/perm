package prior.prior.perms.Service.impl;

import org.springframework.stereotype.Service;
import prior.prior.perms.Dao.IUserRoleMapper;
import prior.prior.perms.Entity.UserRole;
import prior.prior.perms.Service.IUserRoleService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserRoleServiceImpl implements IUserRoleService {

	@Resource
	IUserRoleMapper userRoleMapper;
	
	public List<UserRole> findByUserId(Integer userId) {
		return userRoleMapper.findByUserId(userId);
	}

	public int deleteByUserId(int userId) {
		return userRoleMapper.deleteByUserId(userId);
	}

	public int insertUserRole(UserRole ur) {
		return userRoleMapper.insert(ur);
	}

}
