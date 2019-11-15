package com.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.entity.User;
import com.demo.service.IUserService;
import com.demo.util.EncryptUtil;

import net.sf.json.JSONObject;

@Controller
public class UserMangerController {

	@Resource
	private IUserService userService;
	
	@RequestMapping("/userList.do")
	public String userList(){
		return "userList";
	}
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
	@RequestMapping("/addUser.do")
	public String toEdit(){
		return "userEdit";
	}
	@RequestMapping("/saveUser.do")
	public void saveUser(User user,HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String msg = "";
		JSONObject json = new JSONObject();
		//进行信息的校验
		if(user != null){
			String username = user.getUserName();
			String password = user.getPassword();
			if(username == null || "".equals(username)){
				msg = "用户名不能为空！";
				json.put("msg", msg);
				json.put("success",false);
			}
			if(password == null || "".equals(password)){
				msg = "密码不能为空";
				json.put("msg", msg);
				json.put("success",false);
			}
			//对密码进行md5的加密
			user.setPassword(EncryptUtil.encryptMD5(password));
			//创建时间
			String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			user.setCreateTime(createTime);
			//进行保存操作
			int result = userService.insert(user);
			if(result == 1){
				msg = "保存成功！";
				json.put("msg", msg);
				json.put("success",true);
			}
		}else{
			json.put("msg", "获取用信息失败");
			json.put("success",false);
		}
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	@RequestMapping("/delUser.do")
	public void delUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		//获取参数，ids
		String get_ids = request.getParameter("ids");
		if(get_ids != null && !"".equals(get_ids)){
			int result = 0;
			String[] ids = get_ids.split(",");
			if(ids != null && ids.length > 0){
				for(String id : ids){
					//循环删除信息
					result += userService.deleteById(Integer.parseInt(id));
				}
				if(result > 0){
					json.put("msg","删除成功！");
					json.put("success",true);
				}else{
					json.put("msg","删除失败！");
					json.put("success",false);
				}
			}
		}else{
			json.put("msg", "获取参数失败");
			json.put("success",false);
		}
		//把json信息打印到前台页面
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	@RequestMapping("/del_Update.do")
	public void del_update(HttpServletRequest request,HttpServletResponse response) throws IOException{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			JSONObject json = new JSONObject();
			//获取参数，ids
			String get_ids = request.getParameter("ids");
			if(get_ids != null && !"".equals(get_ids)){
				int result = 0;
				String[] ids = get_ids.split(",");
				if(ids != null && ids.length > 0){
					for(String id : ids){
						//循环删除信息
						result += userService.updateStatusById(Integer.parseInt(id), 0);
					}
					if(result > 0){
						json.put("msg","删除成功！");
						json.put("success",true);
					}else{
						json.put("msg","删除失败！");
						json.put("success",false);
					}
				}
			}else{
				json.put("msg", "获取参数失败");
				json.put("success",false);
			}
			//把json信息打印到前台页面
			PrintWriter out = response.getWriter();
			out.print(json.toString());
			out.flush();
			out.close();
	}
	@RequestMapping("/updateUser.do")
	public String toDetailUser(HttpServletRequest request){
		String id = request.getParameter("id");
		if(id != null && !"".equals(id)){
			//根据id获取用户信息
			User user = userService.findById(Integer.parseInt(id));
			if(user != null){
				request.setAttribute("user", user);
			}
		}
		return "userUpdate";
	}
	@RequestMapping("/update_user.do")
	public void updateUser(User user,HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String msg = "";
		JSONObject json = new JSONObject();
		//进行信息的校验
		if(user != null){
			Integer id = user.getUserId();
			if(id == null){
				msg = "用户Id不能为空！";
				json.put("msg", msg);
				json.put("success",false);
			}
			String username = user.getUserName();
			String password = user.getPassword();
			if(username == null || "".equals(username)){
				msg = "用户名不能为空！";
				json.put("msg", msg);
				json.put("success",false);
			}
			if(password == null || "".equals(password)){
				msg = "密码不能为空";
				json.put("msg", msg);
				json.put("success",false);
			}
			//对密码进行md5的加密
			//user.setPassword(EncryptUtil.encryptMD5(password));
			//创建时间
			String updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			user.setUpdateTime(updateTime);
			//进行保存操作
			int result = userService.updateUser(user);
			if(result == 1){
				msg = "修改成功！";
				json.put("msg", msg);
				json.put("success",true);
			}
		}else{
			json.put("msg", "获取用信息失败");
			json.put("success",false);
		}
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
}
