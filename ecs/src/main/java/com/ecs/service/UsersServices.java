package com.ecs.service;

import java.util.List;

import com.ecs.entity.Users;

public interface UsersServices {
  public Users registerUser(Users user);
  public Users  getUserByEmail(String email);
  public String updatePassword(String email);
  public Users updateEmail(String email);
  public String removeById(Integer id);
  public List<Users> getAllUsers(String field,String direction,Integer pageno,Integer records);
}
