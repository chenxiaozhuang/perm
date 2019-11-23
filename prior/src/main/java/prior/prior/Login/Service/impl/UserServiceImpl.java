package prior.prior.Login.Service.impl;

import org.springframework.stereotype.Service;
import prior.prior.Login.Dao.IUserMapper;
import prior.prior.Login.Entity.User;
import prior.prior.Login.Service.IUserService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserMapper userMapper;

    public User login(String username, String password) {
        return userMapper.findByName(username,password);
    }

    public List<User> findByPage(int offset, int pageSize) {
        return userMapper.findByPage(offset,pageSize);
    }

    public int count() {
        return userMapper.selectAll().size();
    }

    public int insert(User user) {
        return userMapper.insert(user);
    }

    public int deleteById(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    public int updateStatusById(Integer id,int status) {
        User user = new User();
        user.setUser_id(id);
        user.setStatus(status);
        return userMapper.updateStatusById(user);
    }

    public User findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public int updateUser(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public List<String> findAllPermByUserId(Integer id) {
        return userMapper.findAllPermByUserId(id);
    }
}
