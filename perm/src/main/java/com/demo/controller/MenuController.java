package com.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.MenuVo;
import com.demo.entity.Function;
import com.demo.entity.RoleFunction;
import com.demo.entity.User;
import com.demo.service.IMenuService;
import com.demo.service.IRoleFunctionService;

import net.sf.json.JSONObject;

@Controller
public class MenuController {

	@Resource
	private IMenuService menuService;
	@Resource
	private IRoleFunctionService roleFunctionService;
	
	@ResponseBody
	@RequestMapping("/menu.do")
	public List<MenuVo> findMenu(HttpServletRequest request){
		List<MenuVo> menus = new ArrayList<MenuVo>();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user != null){
			menus = menuService.findMenu(user.getUserId());
			System.out.println(menus);
		}
		return menus;
	}
	@RequestMapping("/addPerm.do")
	public String addPerm(HttpServletRequest request){
		String roleId = request.getParameter("id");
		request.setAttribute("roleId",roleId);
		return "assignFunc";
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
		out.print(json.toString());
		out.flush();
		out.close();
	}
	@RequestMapping("/findFunctionByRoleId.do")
	public void findFunctionByRoleId(HttpServletRequest request,HttpServletResponse response) throws IOException{
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
	@RequestMapping("/save_roleFunction.do")
	public void save_roleFunction(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		String roleId = request.getParameter("roleId");
		String id = request.getParameter("ids");
		String[] ids = id.split(",");
		if(roleId != null && !"".equals(roleId)){
			if(ids != null && ids.length > 0){
				int result = 0;
				//删除数据库表中的数据
				roleFunctionService.deleteByRoleId(Integer.parseInt(roleId));
				//进行数据库插入操作
				for(String funcId : ids){
					RoleFunction rf = new RoleFunction();
					rf.setFuncId(Integer.parseInt(funcId));
					rf.setRoleId(Integer.parseInt(roleId));
					result += roleFunctionService.save(rf);
				}
				if(result >= 1){
					json.put("msg", "授权成功！");
					json.put("success",true);
				}else{
					json.put("msg", "授权失败！");
					json.put("success",false);
				}
			}else{
				json.put("msg", "没有获取权限id！");
				json.put("success",false);
			}
		}else{
			json.put("msg", "没有获取角色id！");
			json.put("success",false);
		}
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
}
