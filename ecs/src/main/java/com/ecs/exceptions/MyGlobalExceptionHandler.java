package com.ecs.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class MyGlobalExceptionHandler {

	
	@ExceptionHandler(OperationFaliureException.class)
	public ResponseEntity<MyErrorDetails> operationFaliureExceptionHandler(OperationFaliureException exp,WebRequest req ){
		log.error("Oops operation faliure found and OperationFaliureException is invoked");
		MyErrorDetails err = new MyErrorDetails();
		err.setMessage(exp.getLocalizedMessage());
		err.setDetails(req.getDescription(false));
		err.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(err,HttpStatus.EXPECTATION_FAILED);
	}
	
	
	@ExceptionHandler(SomethingWentWrongException.class)
	public ResponseEntity<MyErrorDetails> sometwentWrongExceptionHandler(SomethingWentWrongException exp,WebRequest req ){
		log.error("Oops ,something went wrong with the method ,SomethingWentWringException is invoked");
		MyErrorDetails err = new MyErrorDetails();
		err.setMessage(exp.getLocalizedMessage());
		err.setDetails(req.getDescription(false));
		err.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	

	@ExceptionHandler(UserNotLoggedInException.class)
	public ResponseEntity<MyErrorDetails> userNotLoggedInExceptionHandler(UserNotLoggedInException exp,WebRequest req ){
		log.error("Oops ,user is not logged in , UserNotLoggedInException is invoked");
		MyErrorDetails err = new MyErrorDetails();
		err.setMessage(exp.getLocalizedMessage());
		err.setDetails(req.getDescription(false));
		err.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exp){
		log.error("Oops ,You have passed invalid or insuffiecnt argument to api , MethodArgumentNotValidException is invoked");
		MyErrorDetails err = new MyErrorDetails();
		err.setMessage(exp.getLocalizedMessage());
		err.setDetails(exp.getBindingResult().getFieldError().getDefaultMessage());
		err.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> noHandlerFoundExceptionHandler(NoHandlerFoundException exp ,WebRequest req){
		log.error("Oops ,unknown exception  , NoHandlerFoundException is invoked");
		MyErrorDetails err = new MyErrorDetails();
		err.setMessage(exp.getLocalizedMessage());
		err.setDetails(req.getDescription(false));
		err.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> myExceptionHandler(Exception exp ,WebRequest req){
		log.error("Oops ,exception found , Exception is invoked");
		MyErrorDetails err = new MyErrorDetails();
		err.setMessage(exp.getLocalizedMessage());
		err.setDetails(req.getDescription(false));
		err.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	
}
