package prior.prior.Menu.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import prior.prior.Menu.Entity.Function;

import java.util.List;
@Mapper
public interface IFunctionMapper {
    int deleteByPrimaryKey(Integer funcId);
    int insert(Function record);
    Function selectByPrimaryKey(Integer funcId);

    @Select("select * from tb_functions")
    List<Function> selectAll();

    int updateByPrimaryKey(Function record);

    /**
     * 根据用户的id查找权限菜单
     * @param userId
     * @return
     */
    @Select("select\n" +
            "\t\t\tdistinct\n" +
            "\t\t\tf.func_id,\n" +
            "\t\t\tf.func_name,\n" +
            "\t\t\tf.func_url,\n" +
            "\t\t\tf.func_code,\n" +
            "\t\t\tf.parent_id,\n" +
            "\t\t\tf.func_type,\n" +
            "\t\t\tf.status,\n" +
            "\t\t\tf.sort_num,\n" +
            "\t\t\tf.create_time,\n" +
            "\t\t\tf.update_time\n" +
            "\t\tfrom tb_roles r,tb_functions f,tb_role_function rf where r.role_id =\n" +
            "\t\trf.role_id and f.func_id = rf.func_id and r.role_id in(\n" +
            "\t\tselect r.role_id from tb_users u,tb_roles r,tb_user_role ur where u.user_id\n" +
            "\t\t= ur.user_id and r.role_id = ur.role_id and f.func_type=1 and u.user_id = #{userId});")
    List<Function> findMenu(@Param("userId")int userId);

    Function findByFuncName(String funcName);

    @Select("select * from tb_functions where parent_id = #{parentId}")
    List<Function> findByParentId(int parentId);

    @Select("select * from tb_functions where func_type = 1 and parent_id = #{parentId}")
    List<Function> findTreeByParentId(@Param("parentId") int parentId);
}
