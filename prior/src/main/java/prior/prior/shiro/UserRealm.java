package prior.prior.shiro;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import prior.prior.Login.Entity.User;
import prior.prior.Login.Service.IUserService;

import java.util.ArrayList;
import java.util.List;

/**
 * 该方法实现的是shiro的内置权限认证，它会在用户登录时，自动调用
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private IUserService userSerivce;
    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加资源的授权字符串
       // info.addStringPermission("user:add");  单个的时候
        //到数据库查询当前登录用户的授权字符串
        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        /**
         * 目前就一个权限，后期可以添加多个权限,根据用户id找到用户需要的权限
         */
       // User dbUser = userSerivce.findById(user.getId());//不能用该方法了，因为已经把权限分在另外一个表中
        //  info.addStringPermission(dbUser.getPerms());
        // 从数据库获取到权限数据
        List<String> permissionList=null;
        permissionList=userSerivce.findAllPermByUserId(user.getUser_id());
        info.addStringPermissions(permissionList);//多个的时候
        return info;
    }
    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        //编写shiro判断逻辑，判断用户名和密码
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken)arg0;
        User user = userSerivce.findUserByName(token.getUsername());
        if(user==null){
            //用户名不存在
            return null;//shiro底层会抛出UnKnowAccountException
        }
        //2.判断密码
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }

}
