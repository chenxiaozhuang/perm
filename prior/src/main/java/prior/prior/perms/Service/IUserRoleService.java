package prior.prior.perms.Service;
import prior.prior.perms.Entity.UserRole;

import java.util.List;

public interface IUserRoleService {

    List<UserRole> findByUserId(Integer userId);

	int deleteByUserId(int userId);

	int insertUserRole(UserRole ur);
}
