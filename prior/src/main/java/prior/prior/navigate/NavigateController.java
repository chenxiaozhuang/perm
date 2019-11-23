package prior.prior.navigate;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 路由
 * author  chenxiaozhuang
 */
@Controller
public class NavigateController {

    @RequestMapping("/login")
    public String test()
    {
        return "login";
    }

    @RequestMapping("/userManager.do")
    @RequiresPermissions("user:delete")
    public String userlist()
    {
        return "userList";
    }

    @RequestMapping("/roleManager.do")
    public String rolelist()
    {
        return "roleList";
    }

    @RequestMapping("/functionManager.do")
    public String functionlist()
    {
        return "functionList";
    }

    @RequestMapping("/department.do")
    public String departmentList()
    {
        return "departmentList";
    }

}
