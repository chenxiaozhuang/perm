package prior.prior.Menu.MenuController;

import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import prior.prior.Login.Entity.User;
import prior.prior.Menu.Entity.Function;
import prior.prior.Menu.Service.IMenuService;
import prior.prior.Menu.vo.MenuVo;
import prior.prior.perms.Service.IRoleFunctionService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController {
    @Resource
    private IMenuService menuService;
    /**
     * 角色权限关联
     */
    @Resource
    private IRoleFunctionService roleFunctionService;
    /**
     * 生成权限菜单
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/menu.do")
    public List<MenuVo> findMenu(HttpServletRequest request){
        List<MenuVo> menus = new ArrayList<MenuVo>();
//        HttpSession session = request.getSession();
//        User user = (User)session.getAttribute("user");
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(user != null){
            menus = menuService.findMenu(user.getUser_id());
            System.out.println(menus);
        }
        return menus;
    }

    /**
     * 跳转到授权页面
     * @param request
     * @return
     */
    @RequestMapping("/addPerm.do")
    public String addPerm(HttpServletRequest request){
        String roleId = request.getParameter("id");
        request.setAttribute("role_id",roleId);
        return "assignFunc";
    }

    /**
     * 通过
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/findFunctionByRoleId.do")
    public void findFunctionByRoleId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String roleId = request.getParameter("id");
        JSONObject json = new JSONObject();
        if(roleId != null && !"".equals(roleId)){
            //根据角色id或该角色的现有的所有权限
            List<Function> list = roleFunctionService.findFunctionByRoleId(Integer.parseInt(roleId));
            if(list != null && list.size() > 0){
                json.put("data", list);
            }
        }
        PrintWriter out = response.getWriter();
        out.print(json.toString());
        out.flush();
        out.close();
    }

    @RequestMapping("/findAllFunction.do")
    public void findAllFunction(HttpServletRequest request,HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        JSONObject json = new JSONObject();
        List<Function> list = menuService.findAllFunction();
        if(list != null && list.size() > 0){
            json.put("data", list);
        }else{
            json.put("msg", "获取菜单列表失败！");
        }
        PrintWriter out = response.getWriter();
        System.out.println("当前的用户权限蔡为"+json.toString());
        out.print(json.toString());
        out.flush();
        out.close();
    }

}
