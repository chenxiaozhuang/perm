package prior.prior.perms.Dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import prior.prior.perms.Entity.UserRole;
import java.util.List;
@Mapper
public interface IUserRoleMapper {
    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
    int insert(UserRole record);
    List<UserRole> selectAll();
    /**
     * 根据用户id查询该用户所具有的角色
     * @param userId
     * @return
     */
    List<UserRole> findByUserId(@Param("userId") Integer userId);
	int deleteByUserId(@Param("userId") int userId);
}