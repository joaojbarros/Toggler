package com.joaojbarros.exception;

import java.util.Date;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.joaojbarros.model.ResponseError;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({	NotUpdatableFieldsException.class, 
						ObjectNotExistsOnDeletionException.class,
						ObjectAlreadyExistsOnCreationException.class,
						ObjectNotExistsOnUpdateException.class})
  public final ResponseEntity<ResponseError> handleBadRequestException(DuplicateKeyException ex, WebRequest request) {
	  ResponseError errorDetails = new ResponseError();
	  errorDetails.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
	  errorDetails.setException(ex.getClass().getName());
	  errorDetails.setMessage(ex.getMessage());
	  errorDetails.setTimestamp(new Date().getTime()); 
	  errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }
	
	@ExceptionHandler(DuplicateKeyException.class)
		public final ResponseEntity<ResponseError> handleBadRequesrVanilaException(DuplicateKeyException ex, WebRequest request) {
		ResponseError errorDetails = new ResponseError();
		errorDetails.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorDetails.setException(ex.getClass().getName());
		errorDetails.setMessage("The object with same identifiers already exists.");
		errorDetails.setTimestamp(new Date().getTime()); 
		errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
}
	
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ResponseError> handleGeneralException(DuplicateKeyException ex, WebRequest request) {
	  ResponseError errorDetails = new ResponseError();
	  errorDetails.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
	  errorDetails.setException(ex.getClass().getName());
	  errorDetails.setMessage("An unexpected error occurs.");
	  errorDetails.setTimestamp(new Date().getTime()); 
	  errorDetails.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  @ExceptionHandler(DependenceObjectNotExistsOnCreationException.class)
  public final ResponseEntity<ResponseError> handleUnprocessableEntityException(DependenceObjectNotExistsOnCreationException ex, WebRequest request) {
	  ResponseError errorDetails = new ResponseError();
	  errorDetails.setError(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
	  errorDetails.setException(ex.getClass().getName());
	  errorDetails.setMessage(ex.getMessage());
	  errorDetails.setTimestamp(new Date().getTime()); 
	  errorDetails.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
    return new ResponseEntity<>(errorDetails, HttpStatus.UNPROCESSABLE_ENTITY);
  }
}