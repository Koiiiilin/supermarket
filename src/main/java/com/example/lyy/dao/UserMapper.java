package com.example.lyy.dao;


import com.example.lyy.pojo.Perms;
import com.example.lyy.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    public List<User> getUserList();
    public User findUserByNameAndPWD(@Param("userCode") String userCode,
                                     @Param("password") String password);
    public int addUser(User user);
    public User findUserById(Integer id);
    public User findUserByName(@Param("userCode") String userCode);
    public List<Integer> getUserRole();
//    根据用户名获取角色信息
    public User getUserRoleByName(String name);
    public int updateUser(User user);
    public int deleteUser(int id);
    public List<User> findUserByAdd(String name);
    //根据用户名查询所有角色
    User findRolesByUserName(String username);
    //根据角色id查询权限集合
    List<Perms> findPermsByRoleId(String id);

}
