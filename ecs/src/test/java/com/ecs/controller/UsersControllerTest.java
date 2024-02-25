package com.ecs.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ecs.entity.Users;
import com.ecs.exceptions.OperationFaliureException;
import com.ecs.service.UsersServices;

@ExtendWith(MockitoExtension.class)
public class UsersControllerTest {

	@Mock
	UsersServices uservices;
	
	@Mock
	PasswordEncoder pencoder;
	
	@InjectMocks
	UsersController ucontroller;
	
	@Test
	@DisplayName("User object created")
	public void testSignUp_WhenUserDetailsProvided_returnsUserObject() {
		
		Users user= new Users();
		user.setUserId(1);
		user.setPassword("Ankit12345");
		
		Mockito.when(uservices.registerUser(user)).thenReturn(user);
		
		ResponseEntity<Users> res= ucontroller.signUp(user);
		assertEquals(HttpStatus.CREATED,res.getStatusCode(),"Response user should have status created");
		
		assertEquals(user.getUserId(),res.getBody().getUserId(),"User id and response user id didn't match");
		
		verify(uservices,times(1)).registerUser(user);
	}
	
	@DisplayName("User login testing ")
	@Test
	public void testSignIn_whenAuthorizationPassed_returnsLoggedMessage() {
		Authentication auth= new UsernamePasswordAuthenticationToken(null,null);
		Users user= new Users();
		user.setUserId(1);
		user.setFname("Ankit");
		
		Mockito.when(uservices.getUserByEmail(anyString())).thenReturn(user);
  
		ResponseEntity<String> res= ucontroller.signIn(auth);
		
		assertEquals(HttpStatus.ACCEPTED,res.getStatusCode(),"Response  should have status Accepted");
		assertEquals("Ankit logged successfully",res.getBody(),"Response string didn't match");
		
		
	}
	
	@DisplayName("Pasoword upation method works")
	@Test
	public void testUpdatePassword_WhenNewPasswordPassed_returnsMessageWithPasswordUpadation() {
	  
		Authentication auth= new UsernamePasswordAuthenticationToken("email","password");
		
		Mockito.when(uservices.updatePassword(anyString(), anyString())).thenReturn("Password has been updated");
		Mockito.when(pencoder.encode(anyString())).thenReturn("nPassword");
		
		ResponseEntity<String> res= ucontroller.updatePassword(auth, "Password");
		assertEquals(HttpStatus.ACCEPTED,res.getStatusCode());
		assertEquals("Password has been updated",res.getBody());
		verify(uservices,times(1)).updatePassword("email", "nPassword");
	}
	
	@DisplayName("Testing email update method")
	@Test
	public void testUpdateEmail_WhenNewEmailPassed_ReturnUpdatedUser() {
	  Authentication auth = new UsernamePasswordAuthenticationToken("email","password");
	  Users user = new Users();
	  user.setEmail("nemail@gmail.com");
	  
	  Mockito.when(uservices.updateEmail(anyString(), anyString())).thenReturn(user);
	  ResponseEntity<Users> res= ucontroller.updateEmail(auth, user.getEmail());
	  assertEquals(HttpStatus.ACCEPTED,res.getStatusCode(),"Response status code should be accepted");
	  assertEquals(user.getEmail(),res.getBody().getEmail(),"Response email should be equal to nemail@gmail.com");
	  verify(uservices,times(1)).updateEmail("email", user.getEmail());
		
	}
	
	@DisplayName("Testing user deletion method,it should return valid message after user deletion")
	@Test
	public void testDeleteUser_WhenValidUserIdPassed_ReturnsDeleteMessage() {
		
		Mockito.when(uservices.removeById(anyInt())).thenReturn("User deleted successfully");
		
		ResponseEntity<String> res= ucontroller.deleteUser(1);
		assertEquals(HttpStatus.ACCEPTED,res.getStatusCode(),"Response status code should be accepted");
		assertEquals("User deleted successfully",res.getBody(),"Response should have valid string message");
		
		verify(uservices,times(1)).removeById(1);
		
	}
	
	@DisplayName("Null userid causes correct exception")
	@Test
	public void testDeleteUser_WhenNullUserIdPassed_throwsOperationFaliureException() {
		String actual_message= "Null value passed instead of valid user id";
		
		OperationFaliureException exp= Assertions.assertThrows(OperationFaliureException.class, ()->{
			ucontroller.deleteUser(null);
		}, "null userid should have caused OperationFaliueException");
		
		
		assertEquals(actual_message,exp.getMessage(),"exception error message is not correct");
	}
	

}
