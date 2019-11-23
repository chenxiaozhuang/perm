package prior.prior.Login.Service;

import prior.prior.Login.Entity.User;

import java.util.List;

public interface IUserService {

    public User login(String username, String password);

    public List<User> findByPage(int offset, int pageSize);

    public int count();

    public int insert(User user);

    public int deleteById(Integer id);

    public int updateStatusById(Integer id,int status);

    public User findById(Integer id);

    public int updateUser(User user);

    public User findUserByName(String username);
    /**
     * 根据用户id,找到用户所有的权限编码
     */
    public List<String> findAllPermByUserId(Integer id);
}
