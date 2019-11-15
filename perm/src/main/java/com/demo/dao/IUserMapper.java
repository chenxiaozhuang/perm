package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.entity.User;

public interface IUserMapper {

	int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    User selectByPrimaryKey(Integer userId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

	User findByName(@Param("username") String username, @Param("password")String password);

	List<User> findByPage(@Param("offset") int offset, @Param("pageSize")int pageSize);
	
	public int updateStatusById(User user);
}