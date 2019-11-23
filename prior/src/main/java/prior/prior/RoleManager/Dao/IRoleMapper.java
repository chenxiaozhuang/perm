package prior.prior.RoleManager.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import prior.prior.RoleManager.Entity.Role;

@Mapper
public interface IRoleMapper {
    int deleteByPrimaryKey(Integer roleId);
    int insert(Role record);
    Role selectByPrimaryKey(Integer roleId);
    @Select("select * from tb_roles")
    List<Role> selectAll();
    int updateByPrimaryKey(Role record);
    @Select("select * from tb_roles limit #{start},#{end}")
	List<Role> findByPage(@Param("start")int start, @Param("end")int end);
}