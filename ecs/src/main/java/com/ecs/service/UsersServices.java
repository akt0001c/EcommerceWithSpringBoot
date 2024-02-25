package com.ecs.service;

import java.util.List;

import com.ecs.entity.Address;
import com.ecs.entity.Users;

public interface UsersServices {
  public Users registerUser(Users user);
  public Users  getUserByEmail(String email);
  public String updatePassword(String email,String nPassword);
  public Users updateEmail(String email,String nemail);
  public String removeById(Integer id);
  public List<Users> getAllUsers(String field,String direction,Integer pageno,Integer records);
  public List<Address> getAllAddress(String email);
  public String addAddress(String email,Address address);
  public Users updateStatus(String email);
  
}
