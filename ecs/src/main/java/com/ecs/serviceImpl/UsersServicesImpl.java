package com.ecs.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecs.entity.Address;
import com.ecs.entity.Users;
import com.ecs.service.UsersServices;

@Service
public class UsersServicesImpl implements UsersServices {

	@Override
	public Users registerUser(Users user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updatePassword(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users updateEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> getAllUsers(String field, String direction, Integer pageno, Integer records) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> getAllAddress(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addAddress(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

}
