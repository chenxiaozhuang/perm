package com.demo.service;

import java.util.List;

import com.demo.entity.UserRole;

public interface IUserRoleService {

	 /**
     * 根据用户id查询该用户所具有的角色
     * @param userId
     * @return
     */
    List<UserRole> findByUserId(Integer userId);

	int deleteByUserId(int userId);

	int insertUserRole(UserRole ur);
}
