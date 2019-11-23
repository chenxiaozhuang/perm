package prior.prior.perms.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import prior.prior.Menu.Entity.Function;
import prior.prior.perms.Entity.RoleFunction;

import java.util.List;
@Mapper
public interface IRoleFunctionMapper {

    int deleteByPrimaryKey(@Param("roleId") Integer roleId, @Param("funcId") Integer funcId);

    int insert(RoleFunction record);

    List<RoleFunction> selectAll();

    /**
     * 根据角色id找到权限的id列表，此处有问题，返回值有问题
     * @param roleId
     * @return
     */
    @Select("select * from tb_role_function where role_id = #{roleId}")
	List<Function> findFunctionByRoleId(@Param("roleId") Integer roleId);

	int deleteByRoleId(@Param("roleId") int roleId);

	List<RoleFunction> findRoleByFuncId(int funcId);
}