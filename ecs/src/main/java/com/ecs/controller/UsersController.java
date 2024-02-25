package com.ecs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecs.entity.Address;
import com.ecs.entity.Users;
import com.ecs.exceptions.OperationFaliureException;
import com.ecs.exceptions.UserNotLoggedInException;
import com.ecs.service.UsersServices;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class UsersController {
	
 private PasswordEncoder pEncoder;	
 private UsersServices uservices;

    @Autowired
	public void setUservices(UsersServices uservices) {
	this.uservices = uservices;
}
    
 @Autowired	
 public void setpEncoder(PasswordEncoder pEncoder) {
		this.pEncoder = pEncoder;
	}

 
    /**
     * It is used to register the user for either admin or user
     * 
     * Required fields for registration:
     * {
     *   "email":" ",
     *   "password":"",
     *   "fname":"",
     *   "lname":"",//optional
     *   "location":"",
     *   "role":"" // user or admin
     * }
     * @param Users object with necessary field
     * @return Users object with user_id
     * @author Ankit choubey
     */
    @PostMapping("/signUp")
	public ResponseEntity<Users> signUp(@Valid @RequestBody	 Users user){
    	log.info("User controller for register user started...");
    	
    	user.setPassword(pEncoder.encode(user.getPassword()));
		Users res= uservices.registerUser(user);
		log.info("Controller response user got registered successfull");
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}
    
    /**
     * It is used to logIn for  a user
     * Here username is your email address
     * Username and password should pass in under Authorization header 
     * "Authorization":"Basic base64Encoded(username : password)"
     * @param Authentication object which will be taken internally for spring security so no need to pass it explicitly
     * @return String with the message like user logged successful 
     * @author Ankit choubey
     */
    @GetMapping("/logIn")
    public ResponseEntity<String> signIn(Authentication auth){
    	log.info("User Controller for login started...");
    	String res= null;
    	   String email= auth.getName();
    	   Users user= uservices.getUserByEmail(email);
    	   res= user.getFname()+" logged successfully";
    	   log.info("Controller response "+user.getFname()+" login successful");
    	   return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
    }
    
    /**
     * It is used to update the password of the current logged user
     * @param Authentication object (It will be taken internally from spring security)
     * @param New password ,passed as Request param("npassword)
     * @return String with message like password updated successfully
     * @author Ankit choubey
     */
    @PatchMapping("/update/Password")
    public ResponseEntity<String> updatePassword(Authentication auth ,@RequestParam("npassword")String nPassword){
    	log.info("Controller for updating user password started...");
    	
    	
    	String email= auth.getName();
    	if(email==null)
    		  throw new UserNotLoggedInException("User should login first");
    	
    	nPassword= pEncoder.encode(nPassword);
    	String res= uservices.updatePassword(email, nPassword);
    	log.info("Controller response,User password updated successfully");
    	return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
    	
    	
    }
    
    
    /**
     * It is used to update email for authenticated user
     * @param Authentication object
     * @param nemail (new updated email) : RequestParam("nemail")
     * @return Users object
     * @author Ankit choubey
     */
    @PatchMapping("/update/Email")
    public ResponseEntity<Users> updateEmail(Authentication auth,@RequestParam("nemail") String nemail){
    	log.info("Controller for updating email started...");
    	
    	String email= auth.getName();
    	if(email==null)
  		  throw new UserNotLoggedInException("User should login first");
    	
    	Users res= uservices.updateEmail(email,nemail);
    	log.info("Controller response ,User email updated successfully");
    	return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
    	
    }
    
    
	@DeleteMapping("/admin/delete/{uid}")
	 public ResponseEntity<String> deleteUser(@PathVariable("uid") Integer uid){
		log.info("Controller for removing user started...");
		
		if(uid==null)
			 throw new OperationFaliureException("Null value passed instead of valid user id");
		
		String res= uservices.removeById(uid);
		log.info("Controller response ,User removed successfully");
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/admin/Users")
	public ResponseEntity<List<Users>> getAllUsers(@RequestParam( value="field",required=false) String field, @RequestParam(value="dir",required=false) String direction, @RequestParam(value="pageno",required=false) Integer pageno,@RequestParam(value="records",required=false) Integer records){
		log.info("Controller for getting all users started...");
		if(field==null)
			  field= "createdAt";
		
		if(direction==null)
			  direction= "desc";
		
		if(pageno==null)
			  pageno=1;
		
		if(records==null)
			  records=10;
		
		
		List<Users> res= uservices.getAllUsers(field, direction, pageno, records);
		log.info("Controller response , All users found successfully");
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/addresses")
	public ResponseEntity<List<Address>> getAllAddress(Authentication auth){
		log.info("Controller for getting all the user addresses started... ");
		
		String email= auth.getName();
		if(email==null)
  		  throw new UserNotLoggedInException("User should login first");
		
		List<Address> res= uservices.getAllAddress(email);
		log.info("Controller response ,All addresses found successfully");
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/address")
	public ResponseEntity<String> addAddress(Authentication auth, @RequestBody Address address){
		log.info("Controller for adding address for logged user started...");
		String email= auth.getName();
		
		if(email==null)
  		  throw new UserNotLoggedInException("User should login first");
		
		String res= uservices.addAddress(email,address);
		log.info("Controller response ,Address has been added successfully");
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("admin/update/status")
	 public ResponseEntity<Users> updateStatus(Authentication auth){
		log.info("Controller to change the user status started...");
		String email= auth.getName();
		if(email==null)
  		  throw new UserNotLoggedInException("User should login first");
		
		Users res= uservices.updateStatus(email);
		log.info("Controller response ,User status has been changed successfully");
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	}
	
}
