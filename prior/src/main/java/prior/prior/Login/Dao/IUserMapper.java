package prior.prior.Login.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import prior.prior.Login.Entity.User;
import java.util.List;

@Mapper
public interface IUserMapper {
    int deleteByPrimaryKey(Integer userId);
    int insert(User record);
    User selectByPrimaryKey(Integer userId);

    @Select("SELECT * FROM tb_users")
    List<User> selectAll();

    int updateByPrimaryKey(User record);

    @Select("SELECT * FROM tb_users WHERE user_name = #{username} and password = #{password}")
    User findByName(@Param("username") String username, @Param("password")String password);

    @Select("select * from tb_users limit #{offset},#{pageSize}")
    List<User> findByPage(@Param("offset") int offset, @Param("pageSize")int pageSize);

    public int updateStatusById(User user);

    @Select("SELECT * FROM tb_users WHERE user_name = #{username}")
    public User findUserByName(String username);

    /**
     * 根据用户id,找到用户的所有权限编码
     * @param id
     * @return
     */
    @Select("select f.func_code from tb_roles r,tb_functions f,tb_role_function rf where r.role_id =\n" +
            "rf.role_id and f.func_id = rf.func_id and r.role_id in(select r.role_id from tb_users u,tb_roles r,tb_user_role ur where u.user_id\n" +
            "= ur.user_id and r.role_id = ur.role_id and u.user_id = #{id});")
    public List<String> findAllPermByUserId(Integer id);
}
