package com.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.entity.Role;
import com.demo.entity.UserRole;
import com.demo.service.IRoleService;
import com.demo.service.IUserRoleService;

import net.sf.json.JSONObject;

@Controller
public class RoleManagerController {

	@Resource
	IRoleService roleService;
	
	@Resource
	IUserRoleService userRoleService;
	
	@RequestMapping("assignRole.do")
	public String toUserRole(HttpServletRequest request){
		String userId = request.getParameter("id");
		request.setAttribute("userId",userId);
		return "assignRole";
	}
	@RequestMapping("/findAll.do")
	public void findAll(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		//获取所有的角色信息
		List<Role> list = roleService.findAll();
		if(list != null && list.size() > 0){
			json.put("data",list);
			json.put("success",true);
			json.put("msg", "获取角色信息成功！");
		}else{
			json.put("success",false);
			json.put("msg", "获取角色信息失败！");
		}
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	@RequestMapping("/findRoleByUserId.do")
	public void findRoleByUserId(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		JSONObject json = new JSONObject();
		//根据userId查询tb_user_role表中该userId所属的角色
		if(userId != null && !"".equals(userId)){
			List<UserRole> roleList = userRoleService.findByUserId(Integer.parseInt(userId));
			if(roleList != null && roleList.size() > 0){
				json.put("data",roleList);
				json.put("success", true);
			}else{
				json.put("success", false);
			}
		}else{
			json.put("success", false);
		}
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		System.out.println(json.toString());
		out.flush();
		out.close();
	}
	@RequestMapping("/save_assignRole.do")
	public void save_assignRole(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		String userId = request.getParameter("userId");
		String id = request.getParameter("ids");
		String[] ids = id.split(",");
		//j进数据库操作
		if(userId != null && !"".equals(userId)){
			//根据userId删除用户角色表中的数据
			userRoleService.deleteByUserId(Integer.parseInt(userId));
			//把新分配的角色保存到数据库表中
			int result = 0;
			if(ids != null && ids.length > 0){
				for(String roleId : ids){
					UserRole ur = new UserRole();
					ur.setUserId(Integer.parseInt(userId));
					ur.setRoleId(Integer.parseInt(roleId));
					result += userRoleService.insertUserRole(ur);
				}
				if(result >= 1){
					json.put("success",true);
					json.put("msg", "用户分配角色成功!");
				}else{
					json.put("success",false);
					json.put("msg", "用户分配角色失败!");
				}
			}else{
				json.put("success",false);
				json.put("msg", "用户分配角色失败!");
			}
		}else{
			json.put("success",false);
			json.put("msg", "用户Id为空!");
		}
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	@RequestMapping("/roleList.do")
	public String toRoleList(){
		return "roleList";
	}
	@RequestMapping("/findRoleByPage.do")
	public void findByPage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		//获取分页信息
		String pageNum= request.getParameter("page");
		String pageSize = request.getParameter("rows");
		if(pageNum == null || "".equals(pageNum)){
			pageNum = "1";
		}
		if(pageSize == null || "".equals(pageSize)){
			pageSize = "10";
		}
		int end = Integer.parseInt(pageSize);
		//计算开始记录
		int start = (Integer.parseInt(pageNum) - 1 ) * end;
		//获取总记录数据
		int total = roleService.count();
		List<Role> list = roleService.findByPage(start,end);
		if(list != null && list.size() > 0){
			json.put("rows", list);
			json.put("total", total);
			json.put("success",true);
		}else{
			json.put("success",false);
			json.put("msg","没有获取的角色信息！");
		}
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
}
