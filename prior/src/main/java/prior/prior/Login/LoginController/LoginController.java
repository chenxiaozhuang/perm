package prior.prior.Login.LoginController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;
import prior.prior.Login.Entity.User;
import prior.prior.Login.Service.IUserService;
import prior.prior.util.EncryptUtil;

@Controller
public class LoginController {

    @Resource
    private IUserService userService;

//    @ResponseBody
//    @RequestMapping("/login.do")
//    public Map<String,Object> login(String username, String password, HttpServletRequest request, HttpServletResponse response){
//        Map<String,Object> map = new HashMap<String,Object>();
//        //根据用户名密码查询数据库判断用户名密码是否正确
//        if(username != null && !"".equals(username)&&password != null && !"".equals(password)){
//            //对密码进行加密，然后再去数据库中查询
//            String password_secrety = EncryptUtil.encryptMD5(password);
//            User user = userService.login(username, password_secrety);
//            if(user != null){
//                //把用户信息放到session中
//                HttpSession session = request.getSession();
//                session.setAttribute("user",user);
//                System.out.println("当前的用户信息为"+user);
//                map.put("success",true);
//                map.put("msg", "登录成功！");
//            }else{
//                map.put("success",false);
//                map.put("msg", "用户名或密码错误！");
//            }
//        }else{
//            map.put("success",false);
//            map.put("msg", "用户名或密码为空！");
//        }
//
//     return map;
//    }

    /**
     * 登录逻辑处理
     */
    @RequestMapping(value = "/login.do")
    public String login(String name, String password, Model model){
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        String password_secrety = EncryptUtil.encryptMD5(password);
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password_secrety);
        //3.执行登录方法
        try {
            subject.login(token);
            //登录成功
            return "index";
        } catch (UnknownAccountException e) {
            //登录失败:用户名不存在
            model.addAttribute("msg", "用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e) {
            //登录失败:密码错误
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }


    /**
     * 进入首页
     * @return
     */
    @RequestMapping("/toIndex.do")
    public String toIndex(){
        return "index";
    }

    /**
     * 注销
     */
    @RequestMapping("/logout.do")
    public String logout(HttpServletRequest request){
//        HttpSession session = request.getSession();
//        if(session != null){
//            session.invalidate(); //销毁session
//        }
        /**
         * 销毁登录信息
         */
        Subject subject=SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    /**
     * 无权限
     * @return
     */
    @RequestMapping("/noAuth.do")
    public String noAuth(){
        return "noAuth";
    }

    /**
     * 获取用户列表
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/findByPage.do")
    public void findByPage(HttpServletRequest request,HttpServletResponse response) throws IOException{
        //分页查询 获取当前页和每页条数
        String pageNum = request.getParameter("page"); //当前页
        String pageSize = request.getParameter("rows"); //每页多少条数据

        if(pageNum == null || "".equals(pageNum)){
            pageNum = "1";
        }
        if(pageSize == null || "".equals(pageSize)){
            pageSize = "10";
        }
        //计算开始的记录数据
        int offset = (Integer.parseInt(pageNum) - 1) * Integer.parseInt(pageSize);
        //从数据库中获取用户数据
        List<User> list = userService.findByPage(offset, Integer.parseInt(pageSize));
        //获取用户表的总记录条数
        int total = userService.count();
        //把用户信息封装到json中，返回到页面
        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        json.put("msg", "获取用列表成功");
        PrintWriter out = response.getWriter();
        out.print(json.toString());
        out.flush();
        out.close();
    }

    /**
     * 新增用户
     * @return
     */
    @RequestMapping("/addUser.do")
    public String toEdit(){
        return "userEdit";
    }
}
